package gui;

import java.awt.Color;
import java.awt.Container;
import java.net.MalformedURLException;
import java.sql.SQLException;


import javax.swing.JFrame;

import java.sql.Connection;

public class Title extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Title(String title, String USER , int TapInd, int Mode, String Tag, int Mode2, String Tag2, Connection conn) throws MalformedURLException, SQLException {
		// TODO Auto-generated constructor stub

		Container container = getContentPane();
		container.setLayout(null);
		
		Contents contents = new Contents(title, USER, TapInd ,Mode , Tag, Mode2, Tag2, conn);
		Head head = new Head();
		
		SideNav nav = new SideNav(this, contents, conn);
		nav.setSize(300,600);
		nav.setLocation(900, 100);
			
		
		contents.setSize(900, 560);
		contents.setLocation(0, 100);
		
		head.setSize(1200, 100);
		head.setLocation(0, 0);
		head.setBackground(Color.DARK_GRAY);
		

		container.add(nav);
		container.add(contents);
		container.add(head);
		setSize(1200, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
