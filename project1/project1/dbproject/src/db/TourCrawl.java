package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TourCrawl {

	
	static int T_ID=0;
	public TourCrawl(){
		try{
			for(int i=1;i<=39;i++){//39까지 중간에 몇군데 비는 곳 처리하기
				if(9<=i && i<=30)
					continue;
				URL targetUrl = new URL("http://korean.visitkorea.or.kr/kor/bz15/where/where_tour.jsp?areaCode="
						+i+"&gotoPage=1&listType=rdesc&cid=&out_service=");
				WebCrawl(targetUrl);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("TourCrawl");
		
	}
	public void WebCrawl(URL url){
		
		boolean outerSTF = false;
		boolean outerETF = false;
		boolean innerSTF = false;
		boolean innerETF = false;
		TourInfo Info = new TourInfo();

		try {

			
			String tmp = "";
			String line;
			Matcher mainMC, nameMC, linkMC, ddoMC, tagMC, imageMC;
			
			BufferedReader Br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

			while ((line = Br.readLine()) != null) {
				if(line.contains("selected") && line.contains("option") ){
					Pattern ddoPattern = Pattern.compile(">(.*?)</option>");
					ddoMC = ddoPattern.matcher(line);
					ddoMC.find();
					Info.setDdo(ddoMC.group(1));
				}
				if(line.contains("<div class=\"whereWrap\">")){outerSTF = true;}//여행지 정보는 이 태그 안쪽에 있음
				if(line.contains("<div class=\"paginate\">")){outerETF = true;}//여행지 정보는 여기 까지
				if(line.contains("<a") && outerSTF){innerSTF = true;}
				if(line.contains("</a>") && outerSTF){innerETF = true;}
				if(innerSTF && !innerETF){
					tmp += line;
				}//해당 페이지에서 여행지 정보만 추출
				if(innerETF){
					Pattern mainPattern = Pattern.compile("<li><(.*?)</ul>");
					mainMC = mainPattern.matcher(tmp);
					if(mainMC.find())
						tmp = mainMC.group(1);
					tmp = "<" + tmp;
					
					Pattern namePattern = Pattern.compile("<b>(.*?)</b>");//이미지 링크 태그
					nameMC = namePattern.matcher(tmp);
					Pattern linkPattern = Pattern.compile("<a href=\"(.*?)\"");//link
					linkMC = linkPattern.matcher(tmp);
					Pattern tagPattern = Pattern.compile("<p class=\"region\">(.*?)</p>");//지역tag
					tagMC = tagPattern.matcher(tmp);
					Pattern imageSrcPattern = Pattern.compile("<img src=\"http://tong.visitkorea.or.kr/cms/(.*?)\"");//이미지 링크 태그
					imageMC = imageSrcPattern.matcher(tmp);
					
					while(nameMC.find() && linkMC.find() && tagMC.find() && imageMC.find()){
						Info.setID(T_ID++);
						Info.setName(nameMC.group(1));
						Info.setLink(new URL("http://korean.visitkorea.or.kr/kor/bz15/where/"+linkMC.group(1)));
						Info.setTag(tagMC.group(1));
						Info.setImage(new URL("http://tong.visitkorea.or.kr/cms/"+imageMC.group(1)));
						
						new DetCrawl(Info);
					}
					tmp = "";
					innerSTF = innerETF = false;
				}
				if(outerETF){
					break;
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
