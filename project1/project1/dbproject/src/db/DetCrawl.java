package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetCrawl {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	boolean TF = false;
	boolean DTF = false;
	TourInfo Info = new TourInfo();
	public DetCrawl(TourInfo Info){
		this.Info = Info;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://pluto.smu.ac.kr:3306/g_2_6", "dbgroup-2-6", "dbgroup-2-6");
			
			BufferedReader Br = new BufferedReader(new InputStreamReader(this.Info.getLink().openStream(),"utf-8"));
			String line;
			String tmp ="", temp, category, det = null;
			while ((line = Br.readLine()) != null) {
				tmp+=line;
			}
			Pattern pattern = Pattern.compile("<figcaption>(.*?)</figcaption>");
			Matcher MC = pattern.matcher(tmp);
			MC.find();
			temp = MC.group(1);

			Pattern categoryPattern = Pattern.compile("<b>(.*?)</b>");
			Matcher categoryMC = categoryPattern.matcher(temp);
			Pattern detPattern = Pattern.compile("<span>(.*?)</span>");
			Matcher detMC = detPattern.matcher(temp);
			while(categoryMC.find() && detMC.find()){
				category = categoryMC.group(1);
				if(category.contains("위치"))
				{	
					det = detMC.group(1);
					this.Info.setLocal(det);
				}
				else if(category.contains("문의"))
				{	
					det = detMC.group(1);
					this.Info.setAsk(det);
				}
				else if(category.contains("이용시간"))
				{	
					det = detMC.group(1);
					this.Info.setTime(det);
				}
				else if(category.contains("이용시기"))
				{	
					det = detMC.group(1);
					this.Info.setSeason(det);
				}
				else if(category.contains("쉬는날"))
				{	
					det = detMC.group(1);
					this.Info.setDayOff(det);
				}
				else if(category.contains("개장기간"))
				{	
					det = detMC.group(1);
					this.Info.setDuring(det);
				}
				else if(category.contains("지정현황"))
				{	
					det = detMC.group(1);
					this.Info.setSpot(det);
				}
				else if(category.contains("지점현황"))
				{	
					det = detMC.group(1);
					this.Info.setAppoint(det);
				}
				else if(category.contains("공지사항"))
				{	
					det = detMC.group(1);
					this.Info.setPost(det);
				}
				else if(category.contains("관람소요시간"))
				{	
					det = detMC.group(1);
					this.Info.setViewTime(det);
				}
				else if(category.contains("세계문화유산"))
				{	
					det = detMC.group(1);
					this.Info.setHeritage(det);
				}
				else{
					System.out.println(det);
				}

				//System.out.println(det);
			}
			//description
			Pattern descriptionPattern = Pattern.compile("<ul class=\"ptList\"(.*?)</ul>");
			Matcher descriptionMC = descriptionPattern.matcher(tmp);
			if(descriptionMC.find()){
				String Description = "";
				String tp = descriptionMC.group(1);
				//System.out.println(tp);
				Pattern tmpPattern1 = Pattern.compile("<p style=\"(.*?)/p></");
				Matcher tmpMC1 = tmpPattern1.matcher(tp);
				Pattern tmpPattern2 = Pattern.compile("</em>(.*?)</li>");
				Matcher tmpMC2 = tmpPattern2.matcher(tp);
				Pattern tmpPattern3 = Pattern.compile(">(.*?)<");
				if(tmpMC1.find()){
					do{
						Matcher tmpMC3 = tmpPattern3.matcher(tmpMC1.group(1));
						//StringTokenizer token = new StringTokenizer(tmpMC1.group(1), "");
						//token.nextToken();
						while(tmpMC3.find()){
							String desc = tmpMC3.group(1);
							desc = desc.replace("'", "‘");
							Description+=((desc)+"|");
						}
						Description+=("<br/>");
					}while(tmpMC1.find());
				}
				else{
					if(tmpMC2.find()){
						do{
							Matcher tmpMC3 = tmpPattern3.matcher(tmpMC2.group(1));
							//token.nextToken();
							while(tmpMC3.find()){
								String desc = tmpMC3.group(1);
								desc = desc.replace("'", "‘");
								Description+=((desc)+"|");
							}
							Description+=("<br/>");
						}while(tmpMC2.find());
					}
				}
				
				this.Info.setDescription(Description.substring(0));
				//System.out.println(Info.getDescription());
			}
			////////////////////////////////////////////////////////////////////////
			//여기서부터 MYSQL에 넣으면 될듯
			stmt = conn.createStatement();	
			stmt.executeUpdate("Insert into TourInfo values("+this.Info.getID()+",'"+this.Info.getName()
								+"','"+this.Info.getDdo()+"','"+this.Info.getTag()+"','"+this.Info.getImage()
								+"',"+this.Info.getLikeCnt()+",'"+this.Info.getLocal()+"','"+this.Info.getAsk()
								+"','"+this.Info.getTime()+"','"+this.Info.getDayOff()+"','"+this.Info.getDuring()
								+"','"+this.Info.getSeason()+"','"+this.Info.getSpot()+"','"+this.Info.getPost()
								+"','"+this.Info.getViewTime()+"','"+this.Info.getAppoint()+"','"
								+this.Info.getHeritage()+"','"+this.Info.getDescription()+"')");

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
