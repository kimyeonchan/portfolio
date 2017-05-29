package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyPage extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private static final long serialVersionUID = 1L;
	private JLabel subject = new JLabel("마이페이지");
	private JLabel Ltable = new JLabel("좋아하는 여행지");
	private JLabel Lid = null;
	private JButton logout = new JButton("로그아웃");
	
	private JTable table = null;
	private JPanel listPanel = null;
	private JScrollPane listScroll = null;
	private Vector<String> userColumn = new Vector<String>();
	private Vector<String> userRow;
	private static DefaultTableModel model;
	
	private String USER;
	private JPanel panel;
	private JFrame frame;
	private Contents contents;
	public MyPage(JPanel panel, String User, JFrame frame, Contents contents, Connection conn){
		this.USER = User;
		this.panel = panel;
		this.frame = frame;
		this.contents = contents;

		this.conn = conn;
		setLayout(null);

		subject.setLocation(10,20);
		subject.setSize(280,40);
		
		Lid = new JLabel("아이디: "+ User);
		Lid.setSize(200, 40);
		Lid.setLocation(10,60);

		Ltable.setSize(200, 40);
		Ltable.setLocation(10,100);
		userColumn.addElement("종류");
		userColumn.addElement("지역");
		userColumn.addElement("이름");
		model = new DefaultTableModel(userColumn, 0);
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(230);
		
		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listScroll = new JScrollPane(table);
		listPanel.add(listScroll, BorderLayout.CENTER);
		
		listPanel.setSize(265, 300);
		listPanel.setLocation(10,140);
		
		addTable(model);
		
		table.setEnabled(false);
	
		
		logout.setSize(100,30);
		logout.setLocation(175,450);
		logout.addActionListener(this);

		add(subject);
		add(Lid);
		add(Ltable);
		add(listPanel);
		add(logout);
		setSize(300, 600);
        setVisible(true);
	}
	public void addTable(DefaultTableModel model){
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from FavoriteTour, TourInfo where ID ='"+USER+"' and FavoriteTour.T_ID = TourInfo.T_ID");
			
			while(rs.next()){
				userRow = new Vector<String>();
				userRow.add("여행지");
				userRow.add(rs.getString("Ddo"));
				userRow.add(rs.getString("Name"));
				model.addRow(userRow);
				
			}
			rs = stmt.executeQuery("select * from FavoriteCourse, CourseInfo where ID ='"+USER+"' and FavoriteCourse.C_ID = CourseInfo.C_ID");
			
			while(rs.next()){
				userRow = new Vector<String>();
				userRow.add("코스");
				userRow.add(rs.getString("Tag"));
				userRow.add(rs.getString("Text"));
				model.addRow(userRow);
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == logout){
			this.contents.setUSER("");
			
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
	
	public DefaultTableModel getModel(){
		return  model;
	}
}
