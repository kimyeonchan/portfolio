package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CoursePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int MODE1, MODE2;
	private String TAG1, TAG2, USER;
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	JScrollPane sc = null;
	JPanel jpn = new JPanel();
	JButton[] like_btn = new JButton[300];
	JButton[] button = new JButton[300];
	JLabel[] name = new JLabel[300];
	JLabel[] text = new JLabel[300];
	JLabel[] like = new JLabel[300];
	CourseData[] data = new CourseData[300];
	@SuppressWarnings("rawtypes")
	JComboBox scombo = null;
	JTextField search_field = null;
	JButton ok = null;
	ImageIcon icon;
	ImageIcon like_img;
	boolean check= true;
	Vector<String> userRow;
	MyPage mypage;

	private Recommend recommend;
	
	int courseCnt=0;
	String search = "";
	int search_index=3; //초기 값 설정 콤보 박스
	String [] search_menu = {"이름 " , "태그 "};
	
	
	public CoursePanel(String USER,MyPage mypage, int MODE1, String TAG1, int MODE2, String TAG2, Connection conn) {
		this.USER = USER;
		this.mypage = mypage;
		this.MODE1 = MODE1;
		this.TAG1 = TAG1;
		this.MODE2 = MODE2;
		this.TAG2 = TAG2;
		setLayout(null);
		

		try {
			this.conn = conn;
			stmt = conn.createStatement();
			courseQuery(this.MODE2, this.TAG2);
			course_view();
		} catch (MalformedURLException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		sc = new JScrollPane(jpn);
		sc.setSize(900, 600);
		sc.setVisible(true);
		
		add(sc);
		setSize(900, 600);
		setVisible(true);
	}
	public void courseQuery(int MODE, String search) throws MalformedURLException{
		System.out.println("courseQuery");
		if(MODE == 0){
			try {
				courseCnt=0;
				rs = stmt.executeQuery("select * from CourseInfo where Text like '%"+search+"%'");

				while(rs.next()){
					data[courseCnt] = new CourseData(rs.getInt("C_ID"), rs.getString("Text"), new URL(rs.getString("Image")), rs.getInt("LikeCnt"));
					courseCnt++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(MODE == 1){
			try {
				courseCnt=0;
				rs = stmt.executeQuery("select * from CourseInfo where Tag like '%"+search+"%';");

				while(rs.next()){
					data[courseCnt] = new CourseData(rs.getInt("C_ID"), rs.getString("Text"), new URL(rs.getString("Image")), rs.getInt("LikeCnt"));
					courseCnt++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private void course_view() {
		System.out.println("course_view");
		// TODO Auto-generated method stub
		int width=0;
		int height=50;
		//두번째 추천 탭에 대한 것들
		jpn.setLayout(null);
		jpn.setPreferredSize(new Dimension(700,100+370*(courseCnt/3+1)));
		

		search_field = new JTextField(10);
		ok = new JButton("검색");
		
		search_field.setLocation(460,10);
		search_field.setSize(300,30);
		ok.setLocation(760,10);
		ok.setSize(60,30);
		//콥보박스 메뉴 선택 창 
		scombo = new JComboBox<String>(search_menu);
		scombo.setLocation(360,10);
		scombo.setSize(100,30);
		search_index = 0;
		scombo.addActionListener(this);
		for(int i=0;i<courseCnt;i++){
			like_btn[i] = new JButton();
			icon = new ImageIcon(data[i].getImage());
			
			like_img = new ImageIcon("src\\like_btn.png");
			Image image = like_img.getImage(); 
			Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
			like_img = new ImageIcon(newimg);
			
			JButton Button = new JButton("",icon);
			button[i]=Button;
			button[i].setSize(250,250);
			JLabel label1 = new JLabel(data[i].getText());
			name[i] = label1;
			name[i].setSize(250,250);
			
			JLabel label3 = new JLabel("<html>좋아요 :"+data[i].getLikeCnt()+" 개</html>");
			like[i] = label3;
			like[i].setSize(250,300);
			

			JButton btn = new JButton(like_img);
			
			like_btn[i] = btn;
			like_btn[i].setSize(20,20);
			like_btn[i].setBorderPainted(false);
			like_btn[i].setOpaque(false);

			if(width < 3){
				button[i].setLocation(20+280*width, height);
				name[i].setLocation(25+280*width, height+150);
				like[i].setLocation(50+280*width, height+160);
				like_btn[i].setLocation(25+280*width, height+300);
				
			}
			if(width == 2){
				height+=350;
				width = -1;
			}
			jpn.add(button[i]);
			jpn.add(name[i]);
			jpn.add(like[i]);
			jpn.add(like_btn[i]);
		
			width++;
		}
		for(int i = 0;i < courseCnt;i++){
			button[i].addActionListener(this);
			like_btn[i].addActionListener(this);
		}
		ok.addActionListener(this);
		
		jpn.add(scombo);
		jpn.add(search_field);
		jpn.add(ok);
		jpn.setLocation(0,0);
		jpn.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
    {
		if(!this.USER.equals(""))
		{
			if(!search.equals(""))
			{
				this.MODE2 = search_index;
				this.TAG2 = search;
			}
			if(e.getSource() == scombo){
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox)e.getSource();
				search_index = cb.getSelectedIndex();
			}
			if(e.getSource() == ok){
				courseCnt=0;
				data[courseCnt]=null;
				search = search_field.getText();
				try{
					courseQuery(search_index, search);
					if(courseCnt==0){
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다");
						return;
					}
				}
				catch(Exception e1){}
				jpn.removeAll();
				course_view();
				jpn.repaint();
			}
			for(int i=0;i<courseCnt;i++){
				if(e.getSource() == button[i]){
					sc.removeAll();
					try {
						recommend = new Recommend(sc, mypage, data[i].getID(), this.USER, this.MODE1, this.TAG1, this.MODE2, this.TAG2, this.conn);
						sc.add(recommend);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc.revalidate();
					sc.repaint();
				}
			}
			for(int i=0;i<courseCnt;i++){
				if(e.getSource() == like_btn[i]){
					int cnt=1;
					try {
						rs = stmt.executeQuery("select count(*) from FavoriteCourse where ID = '"+USER+"' and C_ID = "+this.data[i].getID());
						while(rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
						if(cnt==0){
							this.data[i].setLikeCnt(this.data[i].getLikeCnt()+1);
							stmt.executeUpdate("update CourseInfo set LikeCnt="+this.data[i].getLikeCnt()+" where C_ID = "+this.data[i].getID()
												+" and not exists (select * from FavoriteCourse where ID ='"+USER+"' and C_ID = "+this.data[i].getID()+")");
							stmt.executeUpdate("Insert into FavoriteCourse values('"+this.USER+"',"+this.data[i].getID()+")");
						}
						else if(cnt==1){
							this.data[i].setLikeCnt(this.data[i].getLikeCnt()-1);
							stmt.executeUpdate("update CourseInfo set LikeCnt="+this.data[i].getLikeCnt()+" where C_ID = "+this.data[i].getID()
												+" and (select count(*) from FavoriteCourse where ID = '"+USER+"' and C_ID = "+this.data[i].getID()+") = 1");
							stmt.executeUpdate("delete from FavoriteCourse where ID = '"+USER+"' and C_ID = "+this.data[i].getID());
						}
						

						rs = stmt.executeQuery("select * from TourInfo where exists(select * from FavoriteTour "
											+"where FavoriteTour.ID ='"+USER+"' and FavoriteTour.T_ID = TourInfo.T_ID)");
						mypage.getModel().setRowCount(0);
						while(rs.next()){

							userRow = new Vector<String>();
							userRow.add("여행지");
							userRow.add(rs.getString("Ddo"));
							userRow.add(rs.getString("Name"));
							mypage.getModel().addRow(userRow);
						}
						rs = stmt.executeQuery("select * from FavoriteCourse, CourseInfo where FavoriteCourse.ID ='"
												+USER+"' and FavoriteCourse.C_ID = CourseInfo.C_ID");
						while(rs.next()){
							userRow = new Vector<String>();
							userRow.add("코스");
							userRow.add(rs.getString("Tag"));
							userRow.add(rs.getString("Text"));
							mypage.getModel().addRow(userRow);
						}
						mypage.revalidate();
						mypage.repaint();
						
						like[i].setText("<html>좋아요 :"+data[i].getLikeCnt()+" 개</html>");
						JPanel drawing = new JPanel();
						drawing.add(like[i]);
						jpn.add(like[i]);
						
						repaint();
					} catch (SQLException e1) {
						// TODO 자동 생성된 catch 블록
						e1.printStackTrace();
					}
				}
			}
		}
	}
	public void setUSER(String user){
		if(recommend!=null)
			recommend.setUSER(user);
		this.USER = user;
	}
}
