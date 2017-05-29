package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseCrawl {
	static int C_ID=0;
	public CourseCrawl(){
		try{
			for(int i=1;i<=11;i++){//11까지 
				URL targetUrl = new URL("http://korean.visitkorea.or.kr/kor/bz15/tp/planner/list.jsp?func_name=list&orderType=V&pageNum="
								+i+"&localGroup=&month=00&theme=");
				WebCrawl(targetUrl);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public void WebCrawl(URL url){
		Connection conn = null;
		Statement stmt = null;
		
		boolean outerSTF = false;
		boolean outerETF = false;
		boolean innerSTF = false;
		boolean innerETF = false;
		CourseInfo Info = new CourseInfo();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://pluto.smu.ac.kr:3306/g_2_6", "dbgroup-2-6", "dbgroup-2-6");
			
			String tmp = "";
			String line;
			Matcher mainMC, linkMC, imageMC, tagMC, tagtmpMC1, tagtmpMC2, textMC;
			
			BufferedReader Br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

			while ((line = Br.readLine()) != null) {
				if(line.contains("<ul class=\"plan_list\">")){outerSTF = true;}//여행지 정보는 이 태그 안쪽에 있음
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
					

					Pattern linkPattern = Pattern.compile("<a href=\"(.*?)\"");//link
					linkMC = linkPattern.matcher(tmp);;
					Pattern imageSrcPattern = Pattern.compile("<img src=\"(.*?)\"");//이미지 링크 태그
					imageMC = imageSrcPattern.matcher(tmp);
					Pattern tagPattern = Pattern.compile("<figcaption>(.*?)</figcaption>");//지역tag
					tagMC = tagPattern.matcher(tmp);
					Pattern tagtmpPattern1 = Pattern.compile("<span>(.*?)</span>");//지역tagtmp
					Pattern tagtmpPattern2 = Pattern.compile("<figcaption>(.*?)<span>");//지역tagtmp
					tagtmpMC2 = tagtmpPattern2.matcher(tmp);
					Pattern textPattern = Pattern.compile("<p>(.*?)</p>");//지역tag
					textMC = textPattern.matcher(tmp);

					while(linkMC.find() && imageMC.find() && tagMC.find() && textMC.find()){
						String tagtmp;
						Info.setID(C_ID++);
						Info.setLink(new URL("http://korean.visitkorea.or.kr"+linkMC.group(1)));
						Info.setImage(new URL(imageMC.group(1)));
						
						tagtmp = tagMC.group(1);
						tagtmpMC1 = tagtmpPattern1.matcher(tagtmp);
						tagtmpMC1.find();
						if(!tagtmpMC1.group(1).equals(""))
							Info.setTag(tagtmpMC1.group(1));
						else{
							tagtmpMC2.find();
							Info.setTag(tagtmpMC2.group(1));					
						}
						Info.setText(textMC.group(1));
						stmt = conn.createStatement();	                                        
						stmt.executeUpdate("Insert into CourseInfo values("+Info.getID()+",'"+Info.getText()+"','"+Info.getTag()+"','"+Info.getImage()+"',"+Info.getLikeCnt()+")");
						new CourseRouteCrawl(Info.getLink(), Info.getID());
					}
					/////////////////////////////////////////////////////
					//여기서 SQL에 넣으면 될 듯
					
					
					tmp = "";
					innerSTF = innerETF = false;
				}
				if(outerETF){
					break;
				}
			}
			stmt.close();
			conn.close();
		}
		catch (Exception e) {e.printStackTrace();}
		System.out.println("CourseCrawl");
	}
}

