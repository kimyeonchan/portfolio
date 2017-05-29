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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.ConstructDB;

public class MainPage extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame jframe;
	JTextField id;
	JPasswordField pw;
	JButton signupbt = new JButton("ȸ������");
	JButton loginbt = new JButton("�α���");
	JButton search_pw = new JButton("��й�ȣ ã��");
	JButton help = new JButton("����");
	JButton db = new JButton("DB ������Ʈ");
	JLabel subject = new JLabel("������ ã�� ���α׷� ");
	JLabel make = new JLabel("5�� : �迬�� ����� ����� �̿�");
	JLabel description = new JLabel(" ");

	JPanel panel;
	JFrame frame;
	Contents contents;
	public MainPage(JPanel panel, JFrame frame, Contents contents, Connection conn) throws SQLException{
		
		setLayout(null);
		this.panel = panel;
		this.frame = frame;
		this.contents = contents;
		this.conn = conn;
		subject.setLocation(10,20);
		subject.setSize(280,40);
		
		JLabel Lid = new JLabel("���̵� :");
		Lid.setLocation(10,70);
		Lid.setSize(60,40);
		
		id = new JTextField();
		id.setLocation(70,75);
		id.setSize(120,30);
		
		JLabel Lpw = new JLabel("��й�ȣ :");
		Lpw.setLocation(10,110);
		Lpw.setSize(60,40);

		pw = new JPasswordField();
		pw.setLocation(70,115);
		pw.setSize(120,30);
		
		
		loginbt.setLocation(195,75);
		loginbt.setSize(80,70);
		loginbt.addActionListener(this);
	                
		
		signupbt.setLocation(25,155);
		signupbt.setSize(120,30);
		signupbt.addActionListener(this);
		
		search_pw.setLocation(155,155);
		search_pw.setSize(120,30);
		search_pw.addActionListener(this);
		
		db.setLocation(25,195);
		db.setSize(120,30);
		db.addActionListener(this);
		
		help.setLocation(155,195);
		help.setSize(120,30);
		help.addActionListener(this);
		
		make.setLocation(50, 500);
		make.setSize(200,40);
			
		add(subject);
		add(Lid);
		add(Lpw);
		add(id);
		add(pw);
		add(signupbt);
		add(loginbt);
		add(search_pw);
		add(db);
		add(help);
		add(make);
		setSize(300, 600);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == search_pw){

			this.panel.removeAll();
			this.panel.add(new SearchPw(this.panel, this.frame, this.contents, this.conn));
			this.panel.revalidate();
			this.panel.repaint();
		}
		if(e.getSource() == signupbt){

			this.panel.removeAll();
			this.panel.add(new Signup(this.panel, this.frame, this.contents, this.conn));
			this.panel.revalidate();
			this.panel.repaint();
		}
		if(e.getSource() == loginbt){
			try {
				stmt = conn.createStatement();
				boolean check=false;
				String sid = id.getText();
				@SuppressWarnings("deprecation")
				String spw = pw.getText();//.getPassword().toString();
				if(sid.equals("")||spw.equals("")){
					JOptionPane.showMessageDialog(null, "��������� �Է����ּ���");
				}
				else{
					rs = stmt.executeQuery("select * from Identity");		
					while(rs.next()){
						if(sid.equals(rs.getString("ID"))&&spw.equals(rs.getString("Password"))){
							check=true;
						}
					}	
					if(!check){
						JOptionPane.showMessageDialog(null, "���̵�� �н����带 Ȯ�����ּ���");
					}
					else if(check){
						JOptionPane.showMessageDialog(null,"�α����� �Ϸ�Ǿ����ϴ�");
						this.contents.setVisible(false);
						
						Contents Ncontents = new Contents("�츮���� ���ѹα� ��������",sid ,0 , 1, "����", 0, "��", this.conn);

						Ncontents.setSize(900, 560);
						Ncontents.setLocation(0, 100);
						this.panel.removeAll();
						MyPage A = new MyPage(this.panel, sid, frame, Ncontents, this.conn);
						this.panel.add(A);
						this.panel.revalidate();
						this.panel.repaint();
						Ncontents.setPanel(A);
						frame.add(Ncontents);
					}
				}
				//stmt.close(); 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == db){
			try {
				new ConstructDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == help){
			this.panel.removeAll();
			this.panel.add(new HelpPage(this.panel, this.frame, this.contents, this.conn));
			this.panel.revalidate();
			this.panel.repaint();
		}
	}
}
