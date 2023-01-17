package Poketmon;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Save extends JFrame{
//저장할때 필요한것  nickname<-exp,level,gold,energy 저장!

	String nickname;
	int energy;
	int exp;
	int level;
	int gold;
	
	public Save(String nickname) {
		this.nickname = nickname;
		PoketmonDAO dao = new PoketmonDAO();
		PoketmonDTO dto = dao.getMemberDTO(nickname);
		System.out.println("저장하기");
		if(dao.save(nickname)) {
			JOptionPane.showMessageDialog(this, "저장이 완료되었습니다");
		}else {
			JOptionPane.showMessageDialog(this, "저장을 실패했습니다");
		}
	}

}
