package db;

import java.sql.SQLException;

public class UpdateDB {
	public UpdateDB(){
			try {
				new CreateTable();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new TourCrawl();
			new CourseCrawl();
	}
}
