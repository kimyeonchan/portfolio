package db;

import java.sql.*;

import javax.swing.JOptionPane;

public class CreateTable {
	Connection conn = null;
	Statement stmt = null;

	public CreateTable() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://pluto.smu.ac.kr:3306/g_2_6", "dbgroup-2-6", "dbgroup-2-6");

			stmt = conn.createStatement();	

			int select = JOptionPane.showConfirmDialog(null, "처음인가요?", "", JOptionPane.YES_NO_CANCEL_OPTION);
			
			
			if(select == JOptionPane.YES_OPTION){
				stmt.executeUpdate("create table TourInfo(T_ID int, Name varchar(100), Ddo varchar(10), Tag varchar(10)"
									+", Image varchar(100), LikeCnt int"+", Local varchar(50), Ask varchar(200), Time varchar(400)"
									+", DayOff varchar(200), During varchar(50), Season varchar(50), Spot varchar(200)"
									+", Post varchar(200), ViewTime varchar(50), Appoint varchar(50), Heritage varchar(50)"
									+", Description text, Primary Key(T_ID), check(T_ID>=0))");
				stmt.executeUpdate("create table CourseInfo(C_ID int, Text varchar(50), Tag varchar(10), Image varchar(100)"
									+", LikeCnt int, primary Key(C_ID), check(C_ID>=0))");
				stmt.executeUpdate("create table Identity(ID varchar(20), Password varchar(20), Name varchar(20), Phone varchar(20)"
									+", Address varchar(100), Primary Key(ID))");
				stmt.executeUpdate("create table CourseRouteInfo(C_ID int, R_ID int, Name varchar(100), Description text, Tag varchar(10)"
									+", Image varchar(100), Distance varchar(10), Time varchar(20), Text Text, Primary Key (R_ID, C_ID)"
									+", Foreign Key (C_ID) References CourseInfo(C_ID), check(C_ID>=0 and R_ID>=0))");
				stmt.executeUpdate("create table secRouteDetInfo(C_ID int, R_ID int, Image varchar(100), Primary Key (R_ID, C_ID, Image)"
									+", Foreign Key (R_ID, C_ID) References CourseRouteInfo(R_ID, C_ID), check(C_ID>=0 and R_ID>=0))");
				stmt.executeUpdate("create table FavoriteTour(ID varchar(20), T_ID int, Primary Key (ID, T_ID)"
									+", Foreign Key (T_ID) References TourInfo(T_ID), Foreign Key (ID) References Identity(ID), check(T_ID>=0))");
				stmt.executeUpdate("create table FavoriteCourse(ID varchar(20),C_ID int, Primary Key (ID, C_ID)"
									+", Foreign Key (C_ID) References CourseInfo(C_ID), Foreign Key (ID) References Identity(ID), check(C_ID>=0))");
				stmt.executeUpdate("create table TourComment(Ind int, ID varchar(20), T_ID int, Comment Text"
									+", Primary Key (Ind, ID, T_ID), Foreign Key (T_ID) References TourInfo(T_ID)"
									+", Foreign Key (ID) References Identity(ID), check(T_ID>=0))");
		
			}
			else if(select == JOptionPane.NO_OPTION){
				stmt.executeUpdate("delete from TourComment");
				stmt.executeUpdate("delete from FavoriteCourse");
				stmt.executeUpdate("delete from FavoriteTour");
				stmt.executeUpdate("delete from secRouteDetInfo");
				stmt.executeUpdate("delete from CourseRouteInfo");
				stmt.executeUpdate("delete from CourseInfo");
				stmt.executeUpdate("delete from TourInfo");
			}
			else{

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt.close();
		conn.close();
		
	}
}
