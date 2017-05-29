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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class RecommendDet extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	RecommendDetData[] rData = new RecommendDetData[20];
	
	JPanel Contents = new JPanel();
	JScrollPane Scroll = null;
	JButton back = new JButton("뒤로가기");
	Image_Panel[] img = new Image_Panel[100];
	JTextPane info = null;
	int Cnt = 0;
	int MODE, MODE2;
	String TAG, TAG2;
	String USER;
	int ID;
	MyPage mypage;
	JScrollPane pane;
	public RecommendDet(JScrollPane pane,MyPage mypage, int C_ID, int R_ID, String User, int Mode, String Tag, int Mode2, String Tag2, Connection conn){
		this.pane = pane;
		this.USER = User;
		this.MODE = Mode;
		this.TAG = Tag;
		this.MODE2 = Mode2;
		this.TAG2 = Tag2;
		this.ID = C_ID;
		try {
			this.conn = conn;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select CourseRouteInfo.C_ID, CourseRouteInfo.R_ID, secRouteDetInfo.Image, Text, Description from CourseRouteInfo, secRouteDetInfo where CourseRouteInfo.C_ID = secRouteDetInfo.C_ID and CourseRouteInfo.R_ID = secRouteDetInfo.R_ID and secRouteDetInfo.C_ID = "+C_ID+" and secRouteDetInfo.R_ID = "+R_ID+";");
			while(rs.next()){
				rData[Cnt] = new RecommendDetData(rs.getInt("C_ID"), rs.getInt("R_ID"), new URL(rs.getString("Image")), rs.getString("Text"), rs.getString("Description"));
				Cnt ++;
			}
		} catch (SQLException| MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLayout(null);
		Contents.setLayout(null);
		Contents.setLocation(0, 0);
		Contents.setPreferredSize(new Dimension(900, 370*(Cnt+1)+40));
		int num=0;
		int width=40;
		int height=40;
		for(int i=0;i<Cnt;i++){ 
			Image_Panel imgTmp = new Image_Panel(rData[i].getImage(), 400, 300);
			imgTmp.setSize(400,400);
			if(num==0){
				imgTmp.setLocation(width,height);
				width +=420;
				num++;
			}
			else if(num==1){
				imgTmp.setLocation(width,height);
				width = 40;
				height +=370;
				num= 0;
			}
			img[i]=imgTmp;
			Contents.add(img[i]);
		}

		JTextPane infoTmp = new JTextPane();
		String fontfamily = back.getFont().getFamily();
		int fontsize = (int) (back.getFont().getSize());
		infoTmp.setContentType("text/html");
		infoTmp.setText("<html><body><p style=\"font-family:"+ fontfamily +"; font-size: "+fontsize+";\"><br>"+rData[0].getText()+"</p></body></html>");
		infoTmp.setSize(700, 400);
		if(Cnt%2==0)
			infoTmp.setLocation(40,40+370*(Cnt/2));
		else
			infoTmp.setLocation(40,40+370*(Cnt/2+1));
		infoTmp.setBackground(this.pane.getBackground());
		infoTmp.setEditable(false);
		info=infoTmp;
		Contents.add(info);
		
		back.setLocation(770,10);
        back.setSize(100,20);
		back.addActionListener(this);

        Contents.add(back);
        
        Scroll = new JScrollPane(Contents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroll.setSize(900,580);
        Scroll.setLocation(0,0);
        Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMaximum());
       JOptionPane.showMessageDialog(null,"준비중입니다.");
       Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMinimum());
        add(Scroll);
        
		setSize(900, 580);
		setLocation(0, 0);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == back){

			this.pane.removeAll();
			try {
				this.pane.add(new Recommend(this.pane,this.mypage,this.ID, this.USER, this.MODE, this.TAG, this.MODE2, this.TAG2, this.conn));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.pane.revalidate();
			this.pane.repaint();
		}
    }

	public void setUSER(String user) {
		this.USER = user;
	}
}
