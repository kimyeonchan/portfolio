package db;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConstructDB {
	public ConstructDB() throws SQLException{
		//JOptionPane select = new JOptionPane();
		int option = JOptionPane.showConfirmDialog(null, "DB�� ������Ʈ �Ͻðڽ��ϱ�?","DB ����", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION){
			new UpdateDB();
			JOptionPane.showMessageDialog(null, "DB�� ������Ʈ �Ǿ����ϴ�.");
		}
		
	}
}
