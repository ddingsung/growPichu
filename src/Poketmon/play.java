package Poketmon;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class play extends JFrame implements ActionListener {
	String nickname;
	JLabel nick, jlimg, jl, jl2;
	Poketmon_MainGUI main;
	JPanel jpanel, jp1, jp2;
	JButton jb1, jb2;
	int energy;
	int exp = 0;;
	int level;

	public play(String nickname, Poketmon_MainGUI main) {
		setTitle("����ֱ�");
		this.main = main;
		this.nickname = nickname;
		setLayout(null);
		ImageIcon image2 = new ImageIcon("img/�����ī��.gif");

		jlimg = new JLabel(image2);
		add(jlimg);
		jlimg.setBounds(85, 0, 400, 400);

		JPanel jp1 = new JPanel();
		JButton jb1 = new JButton("��");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("���ư���");
		jb2.addActionListener(this);
		JLabel jlabel = new JLabel("����ֽðڽ��ϱ�?");

		add(jlabel);
		add(jb1);
		add(jb2);

		jlabel.setBounds(220, 380, 160, 50);
		jlabel.setFont(new Font("���� ���", Font.BOLD, 16));
		jb1.setBounds(150, 450, 100, 50);
		jb1.setFont(new Font("���� ���", Font.BOLD, 16));
		jb2.setBounds(320, 450, 100, 50);
		jb2.setFont(new Font("���� ���", Font.BOLD, 16));

		JLabel jl = new JLabel("(����ָ� �������� 30��ŭ ���̰� 20���� 30���̿� ���� ����ġ�� ����ϴ�)");
		add(jl);
		jl.setBounds(100, 405, 450, 50);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 550);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);


//      ( Math.random () * (�ִ밪 - �ּҰ�) ) + �ּҰ�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PoketmonDAO dao = new PoketmonDAO();
		String ButtonFlag = e.getActionCommand();
		PoketmonDTO dto = dao.getMemberDTO(nickname);
		energy = dto.getEnergy();

		if (ButtonFlag.equals("��")) {
			exp = (int) (Math.random() * (30 - 20)) + 20;
			energy = dto.getEnergy();
			if (energy <= 30) {
				JOptionPane.showMessageDialog(this, "���ļ� ����� ���̾����ϴ�.");
			} else {
				if (dao.playGain(nickname, exp)) {
					main.refresh(nickname);
					dispose();

				} else {
					JOptionPane.showMessageDialog(this, "����ֱ� ����.");

				}
			
			}

		} else if (ButtonFlag.equals("���ư���")) {
			this.dispose();
		}

	}

}