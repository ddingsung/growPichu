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
		JOptionPane.showMessageDialog(this, "포켓몬을 방생시켰습니다." + "처음 부터 다시 시작합니다.");
		main.refresh(nickname);

	}
}