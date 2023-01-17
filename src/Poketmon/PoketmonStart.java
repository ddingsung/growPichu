package Poketmon;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PoketmonStart extends JFrame implements ActionListener {
	JButton button;
	JScrollPane scrollPane;
	ImageIcon icon, icon1;

	public PoketmonStart() {
		icon = new ImageIcon("img/���ӽ���1.png");
		icon1 = new ImageIcon("img/���ӽ��۹�ư.png");

// ��� Panel ������ �������������� ����
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����

			}
		};
		background.setLayout(null);

		button = new JButton(new ImageIcon("img/���ӽ��۹�ư.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
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