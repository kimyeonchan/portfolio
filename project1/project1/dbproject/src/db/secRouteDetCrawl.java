package db;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class secRouteDetCrawl {

	Connection conn = null;
	Statement stmt = null;
	secRouteDetInfo secInfo = new secRouteDetInfo();
	public secRouteDetCrawl(int R_ID, int C_ID, String tmp) throws SQLException, MalformedURLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection("jdbc:mysql://pluto.smu.ac.kr:3306/g_2_6", "dbgroup-2-6", "dbgroup-2-6");
		stmt = conn.createStatement();
		
		Pattern imagePattern = Pattern.compile("<img src=\"(.*?)\"");
		Matcher imageMC = imagePattern.matcher(tmp);
		secInfo.setR_ID(R_ID);
		secInfo.setC_ID(C_ID);
		
		while(imageMC.find()){
			secInfo.setImage(new URL(imageMC.group(1)));	                                        
			stmt.executeUpdate("Insert into secRouteDetInfo values("+secInfo.getC_ID()+","+secInfo.getR_ID()+",'"+secInfo.getImage().toString()+"')");
			
		}
		stmt.close();
		conn.close();
	}
}
