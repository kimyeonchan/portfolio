package gui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Contents extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();

	private boolean Add = false;

	private int MODE, MODE2, TAPIND;
	private String TAG, TAG2;
	private String USER;
	private MyPage Mypage;
	private TourPanel tour;
	private CoursePanel course;
	private Connection conn;
	public Contents(String title, String USER , int TapInd, int Mode, String Tag, int Mode2, String Tag2, Connection conn)
	{
		this.USER = USER;
		this.TAPIND = TapInd;
		this.MODE = Mode;
		this.TAG = Tag;
		this.MODE2 = Mode2;
		this.TAG2 = Tag2;
		this.conn = conn;
		
		if(Add){
			tabbedPane.remove(0);
			tabbedPane.remove(0);
		}	
	
		setLayout(new BorderLayout());
		tour = new TourPanel(this.USER, Mypage, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn);
		course = new CoursePanel(this.USER,Mypage, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn);
		tabbedPane.add(tour, "단일 여행지", 0);
		tabbedPane.add(course, "여행지 코스", 1);
		Add = true;

		tabbedPane.setSelectedIndex(this.TAPIND);
		tabbedPane.setVisible(true);
		add(tabbedPane,0);
		setSize(900, 600);
		setVisible(true);
	}

	public void setPanel(MyPage panel){
		this.Mypage = panel;
		tabbedPane.removeAll();
		tour = new TourPanel(this.USER, Mypage, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn);
		course = new CoursePanel(this.USER,Mypage, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn);
		tabbedPane.add(tour, "단일 여행지", 0);
		tabbedPane.add(course, "여행지 코스", 1);
		tabbedPane.setSelectedIndex(this.TAPIND);
		tabbedPane.setVisible(true);
		add(tabbedPane,0);
		setSize(900, 600);
		setVisible(true);
	}
	public void setUSER(String user){
		tour.setUSER(user);
		course.setUSER(user);
		this.USER = user;
	}
}







