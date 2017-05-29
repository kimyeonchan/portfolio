package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Head extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Head(){
		
		JLabel head = new JLabel("<html><b style = \"font-size: 40pt; color:white\">대한민국 구석구석</b></html>");
		head.setSize(500, 100);
		head.setLocation(350, 100);
		head.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		head.setAlignmentY(JLabel.CENTER_ALIGNMENT);

		add(head);
        setVisible(true);
	}
}