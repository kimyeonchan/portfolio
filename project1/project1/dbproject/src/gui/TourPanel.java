package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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



public class TourPanel extends JPanel implements ActionListener{

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
	MyPage mypage;
	Vector<String> userRow;
	JButton[] like_btn = new JButton[300];
	JButton[] button = new JButton[300];
	JLabel[] name = new JLabel[300];
	JLabel[] address = new JLabel[300];
	JLabel[] like = new JLabel[300];
	TourData[] data = new TourData[300];
	@SuppressWarnings("rawtypes")
	JComboBox scombo = null;
	JTextField search_field = null;
	JButton ok = null;
	ImageIcon icon;
	ImageIcon like_img;
	boolean check= true ; // 좋아요 중복 체크 변수
	
	private Travel travel;
	
	private int tourCnt = 0;
	private String search = null;
	int search_index=2; //초기 값 설정 콤보 박스
	String [] search_menu = {"이름 " , "지역 "};
	
	
	public TourPanel(String USER,  MyPage mypage, int MODE1, String TAG1, int MODE2, String TAG2, Connection conn){
		this.USER = USER;
		this.MODE1 = MODE1;
		this.TAG1 = TAG1;
		this.MODE2 = MODE2;
		this.TAG2 = TAG2;
		this.mypage = mypage;
		setLayout(null);

		try {
			this.conn = conn;
			tourQuery(this.MODE1, this.TAG1);
			tour_view();
		} catch (MalformedURLException e) {
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
	public void tourQuery(int MODE, String search) throws MalformedURLException{
		System.out.println("tourQuery");
		if(MODE == 0){
			try {
				tourCnt=0;
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from TourInfo where Name like '%"+search+"%'");

				while(rs.next()){
					data[tourCnt] = new TourData(rs.getInt("T_ID"), rs.getString("Name"), rs.getString("Local"), new URL(rs.getString("Image")), rs.getInt("LikeCnt"));
					tourCnt++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(MODE == 1){
			try {
				tourCnt=0;
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select T_ID, Name,Local,LikeCnt,Image from TourInfo where Ddo like '%"+search+"%';");

				while(rs.next()){
					data[tourCnt] = new TourData(rs.getInt("T_ID"), rs.getString("Name"), rs.getString("Local"), new URL(rs.getString("Image")), rs.getInt("LikeCnt"));
					tourCnt++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void tour_view(){
		System.out.println("tour_view");
		int width=0;
		int height=50;
		//첫번쨰 여행지 탭에 대한것들 
		jpn.setLayout(null);
		jpn.setPreferredSize(new Dimension(700,100+370*(tourCnt/3+1)));

		//실제로 검색하는 textfield
		search_field = new JTextField(10);
		ok = new JButton("검색");
		
		search_field.setLocation(460,10);
		search_field.setSize(300,30);
		ok.setLocation(760,10);
		ok.setSize(60,30);
		//콥보박스 메뉴 선택 창 
		scombo = new JComboBox<Object>(search_menu);
		scombo.setLocation(360,10);
		scombo.setSize(100,30);
		search_index = 0;
		scombo.addActionListener(this);
		for(int i=0;i<tourCnt;i++){
			like_btn[i] = new JButton();
			icon = new ImageIcon(data[i].getImage());
			
			JButton Button = new JButton("",icon);
			button[i]=Button;
			button[i].setSize(250,250);
			JLabel label1 = new JLabel("이름:"+data[i].getName());
			name[i] = label1;
			name[i].setSize(250,250);

			JLabel label2 = new JLabel("<html>위치:"+data[i].getAddress()+"</html>");
			address[i] = label2;
			address[i].setSize(250,300);
			
			JLabel label3 = new JLabel("<html>좋아요 :"+data[i].getLikeCnt()+" 개</html>");
			like[i] = label3;
			like[i].setSize(250,500);
			

			
			like_img = new ImageIcon("src\\like_btn.png");
			Image image = like_img.getImage(); 
			Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
			like_img = new ImageIcon(newimg);
			JButton btn = new JButton(like_img);
			
			like_btn[i] = btn;
			like_btn[i].setSize(20,20);
			like_btn[i].setBorderPainted(false);
			like_btn[i].setOpaque(false);

			if(width < 3){
				button[i].setLocation(20+280*width, height);
				name[i].setLocation(25+280*width, height+150);
				address[i].setLocation(25+280*width, height+160);
				like[i].setLocation(50+280*width, height+100);
				like_btn[i].setLocation(25+280*width, height+345);
				
			}
			if(width == 2){
				height+=370;
				width = -1;
			}
			jpn.add(button[i]);
			jpn.add(name[i]);
			jpn.add(address[i]);
			jpn.add(like[i]);
			jpn.add(like_btn[i]);
		
			width++;
		}

		for(int i =0;i<tourCnt;i++){
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
			if(search != null && search != "")
			{
				this.MODE1 = search_index;
				this.TAG1 = search;
			}
			if(e.getSource() == scombo){
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox)e.getSource();
				search_index = cb.getSelectedIndex();
			}
			if(e.getSource() == ok){
				tourCnt=0;
				data[tourCnt]=null;
				search = search_field.getText();
				try{
					tourQuery(search_index, search);
					if(tourCnt==0){
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다");
						return;
					}
				}
				catch(Exception e1){}
				jpn.removeAll();
				tour_view();
				jpn.repaint();
			}
			
			for(int i=0;i<tourCnt;i++){
				if(e.getSource() == button[i]){
					sc.removeAll();
					try {
						travel=new Travel(sc, this.mypage, data[i].getID(), this.USER, this.MODE1, this.TAG1, this.MODE2, this.TAG2, this.conn);
						sc.add(travel);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sc.revalidate();
					sc.repaint();
				}
			}
			for(int i=0;i<tourCnt;i++){
				if(e.getSource() == like_btn[i]){
					int cnt=1;
					try {
						rs = stmt.executeQuery("select count(*) from FavoriteTour where ID = '"+USER+"' and T_ID = "+this.data[i].getID());
						while(rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
						if(cnt==0){
							this.data[i].setLikeCnt(this.data[i].getLikeCnt()+1);
							stmt.executeUpdate("update TourInfo set LikeCnt="+this.data[i].getLikeCnt()+" where T_ID = "
												+this.data[i].getID()+" and not exists (select * from FavoriteTour "
												+"where ID ='"+USER+"' and T_ID = "+this.data[i].getID()+")");
							stmt.executeUpdate("Insert into FavoriteTour values('"+this.USER+"',"+this.data[i].getID()+")");
						}
						else if(cnt==1){
							this.data[i].setLikeCnt(this.data[i].getLikeCnt()-1);
							stmt.executeUpdate("update TourInfo set LikeCnt="+this.data[i].getLikeCnt()+" where T_ID = "
												+this.data[i].getID()+" and (select count(*) from FavoriteTour "
												+"where ID = '"+USER+"' and T_ID = "+this.data[i].getID()+") = 1");
							stmt.executeUpdate("delete from FavoriteTour where ID = '"+USER+"' and T_ID = "+this.data[i].getID());
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
		if(travel!=null)
			travel.setUSER(user);
		this.USER = user;
	}
}
