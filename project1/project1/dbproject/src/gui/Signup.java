package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Signup extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField id,name,phone,adress;
	JPasswordField pw1,pw2;
	JButton ok,cancel;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JLabel subject = new JLabel("회원가입");
	JLabel Lid = new JLabel("아이디 :");
	JLabel Lpw = new JLabel("비밀번호 :");
	JLabel Lpw2 = new JLabel("비밀번호 확인 :");
	JLabel Lname = new JLabel("이름 :");
	JLabel Ladress = new JLabel("주소 :");
	JLabel Lphone = new JLabel("전화번호 :");

	private JPanel panel;
	private JFrame frame;
	private Contents contents;
	
	public Signup(JPanel panel, JFrame frame, Contents contents, Connection conn){

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

		Lpw.setLocation(10,110);
		Lpw.setSize(100,40);
		pw1 = new JPasswordField();
		pw1.setLocation(120,115);
		pw1.setSize(150,30);
		
		Lpw2.setLocation(10,150);
		Lpw2.setSize(100,40);
		pw2 = new JPasswordField();
		pw2.setLocation(120,155);
		pw2.setSize(150,30);
		
		Lname.setLocation(10,190);
		Lname.setSize(100,40);
		name = new JTextField();
		name.setLocation(120,195);
		name.setSize(150,30);
		
		Ladress.setLocation(10,230);
		Ladress.setSize(100,40);
		adress = new JTextField();
		adress.setLocation(120,235);
		adress.setSize(150,30);
		
		Lphone.setLocation(10,270);
		Lphone.setSize(100,40);
		phone = new JTextField();
		phone.setLocation(120,275);
		phone.setSize(150,30);

		ok = new JButton("확인");
		ok.setLocation(120,315);
		ok.setSize(70,30);
		
		cancel = new JButton("취소");
		cancel.setLocation(200,315);
		cancel.setSize(70,30);
		
		add(subject);
		add(Lid);
		add(Lpw);
		add(Lpw2);
		add(Lname);
		add(Lphone);
		add(Ladress);
		add(id);
		add(pw1);
		add(pw2);
		add(name);
		add(phone);
		add(adress);
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
				
				boolean check = true;
				// TODO 자동 생성된 메소드 스텁
				String sid = id.getText();
				@SuppressWarnings("deprecation")
				String spw = pw1.getText();//.getPassword().toString();
				@SuppressWarnings("deprecation")
				String spw1 = pw2.getText();//.getPassword().toString();
				
				String sname = name.getText();
				String sphone = phone.getText();
				String sadress = adress.getText();
				if(sid.equals("")||spw.equals("")||sname.equals("")||sphone.equals("")||sadress.equals("")){
					JOptionPane.showMessageDialog(null, "모든정보를 입력해주세요");
				}
				else if(!spw.equals(spw1)){
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인에 입력하신 비밀번호가 다릅니다");
				}
				else{
					try {				
						rs = stmt.executeQuery("select * from Identity");
						while(rs.next()){
							if(sid.equals(rs.getString("ID"))){
								check=false;
							}
						}
						if(check){
							stmt.executeUpdate("Insert into Identity values('"+sid+"','"+spw+"','"+sname+"','"+sphone+"','"+sadress+"')");
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
	
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
						else{
							JOptionPane.showMessageDialog(null, "Id가 중복되었습니다 확인후 다시 눌러주세요");
						}
					} catch (SQLException  e1) {
						// TODO 자동 생성된 catch 블록
						e1.printStackTrace();
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
