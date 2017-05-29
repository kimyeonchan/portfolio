package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseRouteCrawl {

	public CourseRouteCrawl(URL link, int C_ID) {
		// TODO Auto-generated constructor stub

		
		boolean TF = false;
		CourseRouteInfo Info =null;
		StringTokenizer distTmp = null;
		try {
			
			BufferedReader Br = new BufferedReader(new InputStreamReader(link.openStream(),"utf-8"));
			String line, tmp = "";
			Matcher idMC, idTmpMC, distMC, linkMC, nameMC, descriptionMC, tagMC, imageMC;
			while ((line = Br.readLine()) != null) {
				if(TF)
					tmp+=line;
				if(line.contains("<div class=\"leftc\">"))
					TF = true;
				if(line.contains("<div class=\"rightc\">"))
					TF = false;
			}

			Pattern idPattern = Pattern.compile("<p class=\"num\">(.*?)코스");
			idMC = idPattern.matcher(tmp);
			Pattern idTmpPattern = Pattern.compile("<p class=\"num_last\">(.*?)코스");
			idTmpMC = idTmpPattern.matcher(tmp);
			Pattern distPattern = Pattern.compile("<p class=\"km\">(.*?)</p>");
			distMC = distPattern.matcher(tmp);
			Pattern linkPattern = Pattern.compile("<a href=\"(.*?)\"");
			linkMC = linkPattern.matcher(tmp);
			Pattern namePattern = Pattern.compile("<p class=\"tit\">(.*?)</p>");
			nameMC = namePattern.matcher(tmp);
			Pattern descriptionPattern = Pattern.compile("<p class=\"txt\">(.*?)</p>");
			descriptionMC = descriptionPattern.matcher(tmp);
			Pattern tagPattern = Pattern.compile("<p class=\"region\">(.*?)</p>");
			tagMC = tagPattern.matcher(tmp);
			Pattern imagePattern = Pattern.compile("<img src=\"(.*?)\"");
			imageMC = imagePattern.matcher(tmp);
			
			while(true){
				Info = new CourseRouteInfo();
				Info.setC_ID(C_ID);
				if(idMC.find())
					Info.setR_ID(Integer.parseInt(idMC.group(1)));
				//Info.setR_ID(Integer.parseInt(idMC.group(1)));
				else{
					if(idTmpMC.find())
						Info.setR_ID(Integer.parseInt(idTmpMC.group(1)));
				}
				if(Info.getR_ID()==-1)
					break;
				if(distMC.find()){
					distTmp = new StringTokenizer(distMC.group(1), "<br />");
					if(distTmp.hasMoreTokens())
						Info.setDistance(distTmp.nextToken());
					if(distTmp.hasMoreTokens())
						Info.setTime(distTmp.nextToken());
				}
				if(linkMC.find())
					Info.setLink(new URL("http://korean.visitkorea.or.kr"+linkMC.group(1)));
				if(nameMC.find())
					Info.setName(nameMC.group(1));
				if(descriptionMC.find())
				{
					distTmp = new StringTokenizer(descriptionMC.group(1), "'");
					String tp = distTmp.nextToken();
					Info.setDescription(tp);
				}
				if(tagMC.find())
					Info.setTag(tagMC.group(1));
				if(imageMC.find())
					Info.setImage(new URL(imageMC.group(1)));
				
				
				new RouteDetCrawl(Info);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
