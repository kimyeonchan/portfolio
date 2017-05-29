package gui;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CommentPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea pane = null;
	//private JButton del = null;
	public CommentPanel(String text, String user){
		String str = text+"\n-"+user;
		setLayout(null);
		pane = new JTextArea();
		pane.setText(str);
		pane.setEditable(false);
		pane.setLineWrap(true);
		pane.setLocation(5, 0);
		pane.setSize(240,55);
		add(pane);
		//del = new JButton();
		//del.setSize(20, 20);
		//del.setLocation(232,5);
		//add(del);
		pane.setBackground(new Color(220,220,220));
		setSize(270, 60);
		setVisible(true);
	}
	/*public AbstractButton getButton() {
		// TODO Auto-generated method stub
		return del;
	}*/
}
