package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPw extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	JTextField id, name, phone;
	JButton ok,cancel;
	boolean check = false;
	
	JLabel subject = new JLabel("비밀번호 찾기");
	JLabel Lid = new JLabel("아이디 :");
	JLabel Lname = new JLabel("이름 :");
	JLabel Lphone = new JLabel("전화번호 :");

	private JFrame frame;
	private JPanel panel;
	private Contents contents;
	public SearchPw(JPanel panel, JFrame frame, Contents contents, Connection conn){

		this.panel = panel;
		this.frame = frame;
		this.contents = contents;
		setLayout(null);
		
		subject.setLocation(10,20);
		subject.setSize(280,40);
		
		Lid.setLocation(10,70);
		Lid.setSize(100,40);
		id = new JTextField();
		id.setLocation(120,75);
		id.setSize(150,30);
		
		Lname.setLocation(10,110);
		Lname.setSize(100,40);
		name = new JTextField();
		name.setLocation(120,115);
		name.setSize(150,30);
		
		Lphone.setLocation(10,150);
		Lphone.setSize(100,40);
		phone = new JTextField();
		phone.setLocation(120,155);
		phone.setSize(150,30);
		
		ok = new JButton("확인");
		ok.setLocation(120,195);
		ok.setSize(70,30);
		
		cancel = new JButton("취소");
		cancel.setLocation(200,195);
		cancel.setSize(70,30);
		
		add(subject);
		add(Lid);
		add(Lname);
		add(Lphone);
		add(id);
		add(name);
		add(phone);
		add(ok);
		add(cancel);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);

		setSize(300, 600);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == ok){
			try {
				this.conn = conn;
				stmt = conn.createStatement();
				String sid = id.getText();
				String sname = name.getText();
				String sphone = phone.getText();
				if(sid.equals("")||sname.equals("")||sphone.equals("")){
					JOptionPane.showMessageDialog(null, "모든정보를 입력해주세요");
				}
				else{
					try {				
						rs = stmt.executeQuery("select * from Identity where ID ='"+sid+"' and Name ='"+sname+"' and Phone = '"+sphone +"'");
						while(rs.next()){
							check = true;
								JOptionPane.showMessageDialog(null, "비밀번호 : "+rs.getString("Password"));

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
						} catch (SQLException e1) {
						// TODO 자동 생성된 catch 블록
							e1.printStackTrace();
						}
					if(!check){
						JOptionPane.showMessageDialog(null, "정보를 확인해주세요");
					}
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if(e.getSource() == cancel){
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
