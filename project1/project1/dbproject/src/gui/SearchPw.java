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
	
	JLabel subject = new JLabel("��й�ȣ ã��");
	JLabel Lid = new JLabel("���̵� :");
	JLabel Lname = new JLabel("�̸� :");
	JLabel Lphone = new JLabel("��ȭ��ȣ :");

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
		
		ok = new JButton("Ȯ��");
		ok.setLocation(120,195);
		ok.setSize(70,30);
		
		cancel = new JButton("���");
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
					JOptionPane.showMessageDialog(null, "��������� �Է����ּ���");
				}
				else{
					try {				
						rs = stmt.executeQuery("select * from Identity where ID ='"+sid+"' and Name ='"+sname+"' and Phone = '"+sphone +"'");
						while(rs.next()){
							check = true;
								JOptionPane.showMessageDialog(null, "��й�ȣ : "+rs.getString("Password"));

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
						// TODO �ڵ� ������ catch ���
							e1.printStackTrace();
						}
					if(!check){
						JOptionPane.showMessageDialog(null, "������ Ȯ�����ּ���");
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
