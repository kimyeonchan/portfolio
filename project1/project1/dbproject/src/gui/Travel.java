package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Travel extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//컴포넌트 생성
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Container container = null;
	JLabel data;
	JScrollPane info;
	JButton btn1 = new JButton("뒤로가기");
	Image_Panel img;
	MyPage mypage;
	
	JTextArea field = null;
	JButton btn2 = new JButton("등록");
	JScrollPane commentSC = null;
	JScrollPane dataSC = null;
	JScrollPane commentlistSC = null;

	JPanel Text = null;
	private CommentPanel[] commentPanel = new CommentPanel[300];
	private static int cnt;
	
	TravelData tData = null;

	private int ID, MODE, MODE2;
	private String TAG, TAG2;
	private String USER;
	private JScrollPane pane;
	public Travel(JScrollPane pane, MyPage mypage, int ID, String User, int Mode, String Tag, int Mode2, String Tag2, Connection conn) throws MalformedURLException
	{
		this.pane = pane;
		this.mypage = mypage;
		this.ID = ID;
		this.USER = User;
		this.MODE = Mode;
		this.TAG = Tag;
		this.MODE2 = Mode2;
		this.TAG2 = Tag2;
		cnt = 0;
		try {
			this.conn = conn;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select T_ID, Image, Name, Local, Ask, Time, DayOff, During, Season, Spot, Post, ViewTime, Appoint, Heritage, Description from TourInfo where T_ID = "+this.ID+";");
			if(rs.next())
				tData = new TravelData(rs.getInt("T_ID"), new URL(rs.getString("Image")), rs.getString("Name"), rs.getString("Local"), rs.getString("Ask"), rs.getString("Time"), rs.getString("DayOff"), rs.getString("During"), rs.getString("Season"), rs.getString("Spot"), rs.getString("Post"), rs.getString("ViewTime"), rs.getString("Appoint"), rs.getString("Heritage"), rs.getString("Description"));
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);
		//컴포넌트를 넣을 컨테이너 구하기
		
		JTextPane tmpText = new JTextPane();
		tmpText.setContentType("text/html");
		tmpText.setText(dataSet(tData));
		tmpText.setEditable(false);
		tmpText.setBackground(this.pane.getBackground());
		dataSC = new JScrollPane(tmpText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JTextPane tmp = new JTextPane();
		tmp.setContentType("text/html");
		tData.setDescription(newStr(tData.getDescription()));
		String fontfamily = btn1.getFont().getFamily();
		int fontsize = btn1.getFont().getSize();
		tmp.setText("<html><body style=\"font-family:"+ fontfamily +"; font-size: "+fontsize+"\">"+tData.getDescription()+"</body></html>");
		tmp.setEditable(false);
		tmp.setBackground(this.pane.getBackground());
		info = new JScrollPane(tmp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		img = new Image_Panel(tData.getImage(), 300, 300);
		field = new JTextArea();
		field.setText("");
		field.setLineWrap(true);
		commentSC = new JScrollPane(field, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		try {
			rs = stmt.executeQuery("select * from TourComment where T_ID = '"+ID+"'");
			while(rs.next()){
				commentPanel[cnt++] = new CommentPanel(rs.getString("Comment"),rs.getString("ID"));
				
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		Text = adaptPanel();
		commentlistSC = new JScrollPane(Text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//크기지정
		dataSC.setLocation(320,20);
		info.setLocation(20,340);
		img.setLocation(0,0);
		btn1.setLocation(490,305);
		btn2.setLocation(780,120);
		commentSC.setLocation(610, 30);
		commentlistSC.setLocation(610, 150);
		//사이즈 지정
		dataSC.setSize(280,280);
		info.setSize(580,185);
		img.setSize(300,300);
		btn1.setSize(100,20);
		btn2.setSize(100,20);
		commentSC.setSize(270, 80);
		commentlistSC.setSize(270, 375);
		
		//프레임 보이기 설정
		btn1.addActionListener(this);
		btn2.addActionListener(this);

		add(dataSC);
		add(info);
		add(img);
		add(btn1);
		add(btn2);
		add(commentSC);
		add(commentlistSC);

		info.getVerticalScrollBar().setValue(info.getVerticalScrollBar().getMaximum());
		commentSC.getVerticalScrollBar().setValue(commentSC.getVerticalScrollBar().getMaximum());
		commentlistSC.getVerticalScrollBar().setValue(commentlistSC.getVerticalScrollBar().getMaximum());
       JOptionPane.showMessageDialog(null,"준비중입니다.");
       commentlistSC.getVerticalScrollBar().setValue(commentlistSC.getVerticalScrollBar().getMinimum());
       commentSC.getVerticalScrollBar().setValue(commentSC.getVerticalScrollBar().getMinimum());
       info.getVerticalScrollBar().setValue(info.getVerticalScrollBar().getMinimum());

		setSize(900, 580);
		setLocation(0, 0);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btn1){
			this.pane.removeAll();
			this.pane.add(new TourPanel(this.USER, this.mypage, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn));
			this.pane.revalidate();
			this.pane.repaint();
		}
		if(e.getSource() == btn2){
			String Com = field.getText();
			field.setText("");
			if(!Com.trim().equals(""))
				commentPanel[cnt++] = new CommentPanel(Com, USER);
			
			Text = adaptPanel();
			try {
				stmt.executeUpdate("Insert into TourComment values("+cnt+",'"+USER+"','"+ID+"','"+Com+"')");
			} catch (SQLException e1) {
				// TODO 자동 생성된 catch 블록
				e1.printStackTrace();
			}
				
			commentlistSC = new JScrollPane(Text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			commentlistSC.setLocation(610, 150);
			commentlistSC.setSize(270, 400);
			add(commentlistSC);

			setVisible(true);
		}
    }

	public String dataSet(TravelData Data){
		String fontfamily = btn1.getFont().getFamily();
		int fontsize = btn1.getFont().getSize();
		String dataSet = "";

		if(!Data.getName().equals("-"))
			dataSet += ("<html><body style=\"font-family:"+ fontfamily +"; font-size: "+fontsize+"\"><table border=\"0\"><tr><th colspan = \"2\">"+Data.getName()+"</th></tr>");
		if(!Data.getLocal().equals("-"))
			dataSet += ("<tr><td>위치"+setHTM(Data.getLocal()));
		if(!Data.getAsk().equals("-"))
			dataSet += ("<tr><td>문의"+setHTM(Data.getAsk()));
		if(!Data.getTime().equals("-"))
			dataSet += ("<tr><td>이용시간"+setHTM(Data.getTime()));
		if(!Data.getDayOff().equals("-"))
			dataSet += ("<tr><td>쉬는날"+setHTM(Data.getDayOff()));
		if(!Data.getDuring().equals("-"))
			dataSet += ("<tr><td>개장기간"+setHTM(Data.getDuring()));
		if(!Data.getSeason().equals("-"))
			dataSet += ("<tr><td>이용시기"+setHTM(Data.getSeason()));
		if(!Data.getSpot().equals("-"))
			dataSet += ("<tr><td>지점현황"+setHTM(Data.getSpot()));
		if(!Data.getPost().equals("-"))
			dataSet += ("<tr><td>공지사항"+setHTM(Data.getPost()));
		if(!Data.getViewTime().equals("-"))
			dataSet += ("<tr><td>관람소요시간"+setHTM(Data.getViewTime()));
		if(!Data.getAppoint().equals("-"))
			dataSet += ("<tr><td>지정현황"+setHTM(Data.getAppoint()));
		if(!Data.getHeritage().equals("-"))
			dataSet += ("<tr><td>세계문화 유사"+setHTM(Data.getHeritage())+"</table></body></html>");
		return dataSet;
	}
	private String setHTM(String str){
		return "</td><td style = \"width:200px\">"+str+"</td></tr>";
	}
	private String newStr(String str) {
		return str.replace("|", "<br>");
	}

	
	private JPanel adaptPanel(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(270, cnt*60));
		panel.setLayout(null);
		
		for(int i=0;i<cnt;i++){
			commentPanel[i].setSize(300, 60);
			commentPanel[i].setLocation(10, 60*(cnt-i-1));
			//commentPanel[i].getButton().addActionListener(this);
			panel.add(commentPanel[i]);
		}
		panel.setVisible(true);
		return panel;
	}
	public void setUSER(String user) {
		// TODO 자동 생성된 메소드 스텁
		this.USER = user;
	}
}
