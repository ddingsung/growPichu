package Poketmon;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Save extends JFrame{
//�����Ҷ� �ʿ��Ѱ�  nickname<-exp,level,gold,energy ����!

	String nickname;
	int energy;
	int exp;
	int level;
	int gold;
	
	public Save(String nickname) {
		this.nickname = nickname;
		PoketmonDAO dao = new PoketmonDAO();
		PoketmonDTO dto = dao.getMemberDTO(nickname);
		System.out.println("�����ϱ�");
		if(dao.save(nickname)) {
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�");
		}else {
			JOptionPane.showMessageDialog(this, "������ �����߽��ϴ�");
		}
	}

}
