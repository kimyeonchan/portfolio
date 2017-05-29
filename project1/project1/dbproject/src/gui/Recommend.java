package gui;

import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Recommend extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	//컴포넌트 생성

	JPanel Contents = new JPanel();
	
	JScrollPane Scroll = null;
	JButton back = new JButton("뒤로가기");
	JButton[] image = new JButton[10];
	JTextPane[] info = new JTextPane[10];
	JLabel[] arrow = new JLabel[10];
	JLabel[] way = new JLabel[10];
	ImageIcon img;
	int cnt=0; // image의 갯수를 알기 위한 변수

	RecommendData[] cData = new RecommendData[10];
	
	private RecommendDet recommendDet;

	JTextArea field = null;
	JScrollPane dataSC = null;

	
	private int MODE, MODE2;
	private String TAG, TAG2;
	private String USER;
	private JScrollPane pane;
	private MyPage mypage;
	private int ID;
	
	public Recommend(JScrollPane pane,MyPage mypage, int ID, String User, int Mode, String Tag, int Mode2, String Tag2, Connection conn) throws MalformedURLException
	{
		this.pane = pane;
		this.mypage = mypage;
		this.ID =ID;
		this.USER = User;
		this.MODE = Mode;
		this.TAG = Tag;
		this.MODE2 = Mode2;
		this.TAG2 = Tag2;
		
		try {
			this.conn = conn;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select CourseInfo.C_ID, R_ID, CourseRouteInfo.Image, CourseRouteInfo.Text, Name, Description, Distance, Time from CourseInfo, CourseRouteInfo where CourseInfo.C_ID = CourseRouteInfo.C_ID and CourseInfo.C_ID = "+ID+";");
			while(rs.next())
				cData[cnt++] = new RecommendData(rs.getInt("C_ID"), rs.getInt("R_ID"), new URL(rs.getString("CourseRouteInfo.Image")), rs.getString("CourseRouteInfo.Text"), rs.getString("Name"), rs.getString("Description"), rs.getString("Distance"), rs.getString("Time"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		Contents.setLayout(null);
		Contents.setLocation(0, 0);
		Contents.setPreferredSize(new Dimension(900, 220*cnt+30));
        for(int i=0; i<cnt;i++){
        	img = new ImageIcon(cData[i].getImage());
        	JButton imageTmp = new JButton("", img);
			imageTmp.setSize(300,150);
			imageTmp.setLocation(30,40+220*i);
			image[i]=imageTmp;
			image[i].addActionListener(this);
			
			JTextPane infoTmp = new JTextPane();
			String fontfamily = back.getFont().getFamily();
			int fontsize = (int) (back.getFont().getSize()*1.2);
			infoTmp.setContentType("text/html");
			infoTmp.setText("<html><body><p style=\"font-family:"+ fontfamily +"; font-size: "+fontsize+";\"><b style = \" color: orange; font-size: "+(int)(fontsize*1.2)+";\">"+cData[i].getName()+"</b><br>"+cData[i].getDescription()+"</p></body></html>");
			infoTmp.setSize(400, 180);
			infoTmp.setLocation(340,60+220*i);
			infoTmp.setBackground(this.pane.getBackground());
			infoTmp.setEditable(false);
			info[i]=infoTmp;
			
			if(!cData[i].getDistance().equals("-")){
				ImageIcon img = new ImageIcon("src\\arrow.png");
				JLabel arrowTmp = new JLabel(img);
				arrowTmp.setSize(30, 125);
				arrowTmp.setLocation(350,160+220*i);
				arrow[i] = arrowTmp;
				
				JLabel wayTmp = new JLabel();
				wayTmp.setText("<html>"+cData[i].getDistance()+"<br>"+cData[i].getTime()+"</html>");
				wayTmp.setSize(100, 125);
				wayTmp.setLocation(390,150+220*i);
				way[i] = wayTmp;
				
				Contents.add(arrow[i]);
				Contents.add(way[i]);
			}
			//컴포넌트를 컨테이너에 추가
			Contents.add(image[i]);
			Contents.add(info[i]);
		}
        Scroll = new JScrollPane(Contents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        field = new JTextArea();
		field.setText("");
		field.setLineWrap(true);

        back.setLocation(700,30);
        Scroll.setLocation(0,0);
		back.addActionListener(this);
        back.setSize(100,20);
        Scroll.setSize(900,580);

        Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMaximum());
       JOptionPane.showMessageDialog(null,"준비중입니다.");
       Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMinimum());
       // JOptionPane.showMessageDialog(null,Scroll.getVerticalScrollBar().getValue() );
       
        
        Contents.add(back);
        add(Scroll);


		setSize(900, 580);
		setLocation(0, 0);
        setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == back){
			this.pane.removeAll();
			this.pane.add(new CoursePanel(this.USER,this.mypage,this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn));
			this.pane.revalidate();
			this.pane.repaint();
		}
		if(!this.USER.equals("")){
			for(int i=0;i<cnt;i++){
				if(e.getSource() == image[i]){
					this.pane.removeAll();
					recommendDet = new RecommendDet(this.pane,mypage, cData[i].getC_ID(), cData[i].getR_ID(), this.USER, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn);
	    			this.pane.add(recommendDet);
	    			this.pane.revalidate();
	    			this.pane.repaint();
				}
			}
		}
    }

	public void setUSER(String user) {
		if(recommendDet!=null)
			recommendDet.setUSER(user);
		this.USER = user;
	}
	
}