package Poketmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class release extends JFrame {
	String nickname;


	public release(String nickname, Poketmon_MainGUI main) {
		this.nickname = nickname;
		PoketmonDAO dao = new PoketmonDAO();
		PoketmonDTO dto = dao.getMemberDTO(nickname);

		dao.die(nickname);
		JOptionPane.showMessageDialog(this, "���ϸ��� ������׽��ϴ�." + "ó�� ���� �ٽ� �����մϴ�.");
		main.refresh(nickname);

	}
}