package Poketmon;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PoketmonStart extends JFrame implements ActionListener {
	JButton button;
	JScrollPane scrollPane;
	ImageIcon icon, icon1;

	public PoketmonStart() {
		icon = new ImageIcon("img/게임시작1.png");
		icon1 = new ImageIcon("img/게임시작버튼.png");

// 배경 Panel 생성후 컨텐츠페인으로 지정
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절

			}
		};
		background.setLayout(null);

		button = new JButton(new ImageIcon("img/게임시작버튼.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
			}
		};

		button.setBounds(100, 360, 190, 55);
		background.add(button);
		button.addActionListener(this);
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 260, 800, 482);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			new PoketmonLogin();
			dispose();
		}
	}
}