package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPage extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private static final long serialVersionUID = 1L;
	private JLabel subject = new JLabel("도움말");
	private JLabel Text = new JLabel("");
	private JButton back = new JButton("뒤로가기");

	private JPanel panel;
	private JFrame frame;
	private Contents contents;

	private int TotalTour;
	private int TotalCourse;
	
	public HelpPage(JPanel panel, JFrame frame, Contents contents, Connection conn){
		this.panel = panel;
		this.frame = frame;
		this.contents = contents;
		
		setLayout(null);

		try {
			this.conn = conn;
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select count(*) from TourInfo");
			if(rs.next())
				TotalTour = rs.getInt("count(*)");
			
			rs = stmt.executeQuery("select count(*) from CourseInfo");
			if(rs.next())
				TotalCourse = rs.getInt("count(*)"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subject.setLocation(10,20);
		subject.setSize(280,40);

		Text.setLocation(10,60);
		Text.setSize(280,400);
		Text.setText("<html>0. 회원가입 후 로그인을 해야지<br>모든 기능이 작동합니다."
					+"<br>1. 여행지와 코스 탭 둘 중 하나를 선택하세요."
					+"<br>2. 이미지를 클릭하면 상세정보를 볼 수 있습니다."
					+"<br>3. 좋아요를 누르면 마이페이지에 추가되며<br>한번더 누르면 좋아요가 취소됩니다."
					+"<br>4. 이름과 지역 태그로 검색이 가능합니다.<br>"
					+"<br><br><br><br>총 여행지 수:"+TotalTour+"총 코스 수:"+TotalCourse+" <br><br><br><br><br><br><br><br></html>");
	
		back.setSize(100,30);
		back.setLocation(175,450);
		back.addActionListener(this);

		add(subject);
		add(Text);
		add(back);
		setSize(300, 600);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == back){
			this.panel.removeAll();
			try {
				this.panel.add(new MainPage(this.panel, this.frame, this.contents, this.conn));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.panel.revalidate();
			this.panel.repaint();
		}
    }
}
