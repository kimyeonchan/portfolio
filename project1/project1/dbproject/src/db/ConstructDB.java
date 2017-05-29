package db;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConstructDB {
	public ConstructDB() throws SQLException{
		//JOptionPane select = new JOptionPane();
		int option = JOptionPane.showConfirmDialog(null, "DB를 업데이트 하시겠습니까?","DB 관리", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION){
			new UpdateDB();
			JOptionPane.showMessageDialog(null, "DB가 업데이트 되었습니다.");
		}
		
	}
}
