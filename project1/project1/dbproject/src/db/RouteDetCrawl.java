package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteDetCrawl {
	CourseRouteInfo Info;
	public RouteDetCrawl(CourseRouteInfo Info) {
		// TODO Auto-generated constructor stub
		this.Info = Info;
		Connection conn = null;
		Statement stmt = null;
		
		boolean TF = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://pluto.smu.ac.kr:3306/g_2_6", "dbgroup-2-6", "dbgroup-2-6");
			
			BufferedReader Br = new BufferedReader(new InputStreamReader(this.Info.getLink().openStream(),"utf-8"));
			String line, temp, tmp = "";
			Matcher textMC;
			StringTokenizer token = null;
			while ((line = Br.readLine()) != null) {
				if(TF)
					tmp+=line;
				if(line.contains("<li class=\"likebutton_view\">"))
					TF = true;
				if(line.contains("<div class=\"useinfo\">"))
					TF = false;
			}
				

			Pattern textPattern = Pattern.compile("이미지 적용 e(.*?)<div");
			textMC = textPattern.matcher(tmp);

			temp = "";
			if(textMC.find()){
				token = new StringTokenizer(textMC.group(1), "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --");
				
				while(token.hasMoreTokens()){
					temp+=token.nextToken();
				}
				temp = temp.substring(2).trim();
				temp = temp.replace("'", "‘");
				this.Info.setText(temp);
				stmt = conn.createStatement();	                                        
				stmt.executeUpdate("Insert into CourseRouteInfo values("+this.Info.getC_ID()+","+this.Info.getR_ID()+",'"+this.Info.getName()
							+"','"+this.Info.getDescription()+"','"+this.Info.getTag() +"','"+this.Info.getImage()+"','"+this.Info.getDistance()
							+"','"+this.Info.getTime()+"','"+this.Info.getText()+"');");
				
			}
			stmt.close();
			conn.close();
			new secRouteDetCrawl(Info.getR_ID(), Info.getC_ID(), tmp);
			////////////////////////////////////////////////////////////////////////
			//여기서부터 MYSQL에 넣으면 될듯

			
		} catch (IOException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
