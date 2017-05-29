package gui;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SideNav extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SideNav(JFrame frame, Contents contents, Connection conn){
		setLayout(null);
		
		try {
			add(new MainPage(this, frame, contents, conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(300, 600);
        setVisible(true);
	}
}
