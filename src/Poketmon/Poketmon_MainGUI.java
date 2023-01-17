package Poketmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Poketmon_MainGUI extends JFrame implements ActionListener {
	String nick;

	ImageIcon image1 = new ImageIcon("img/�ų���ī��.png");
	ImageIcon image2 = new ImageIcon("img/��ī��.png");
	ImageIcon image3 = new ImageIcon("img/�������ī��.gif");
	Image img = image1.getImage();
	int status = 0;

	Poketmon_MainGUI main;
	JPanel poketmon = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel southPanel = new JPanel();

	// JLabel
	JLabel jlimg;
	JLabel idl = new JLabel("ĳ���� �̸�");
	JLabel pwl = new JLabel("��й�ȣ");

	// ����â
	JLabel jllevel, jlexp, jlgold, jlenergy, jnick;
	JTextField jtlevel, jtexp, jtgold, jtenergy;

	// JTextField
	JTextArea info = new JTextArea("",10,20);
	

	// JButton
	JButton feedingBtn = new JButton("���ֱ�");
	JButton playBtn = new JButton("����ֱ�");
	JButton restBtn = new JButton("�޽��ϱ�");
	JButton fightBtn = new JButton("�����ϱ�");
	JButton exitBtn = new JButton("�����ϱ�");
	JButton releaseBtn = new JButton("�߻����� ����ϱ�");
	JButton rankingBtn = new JButton("���� ��ŷ");
	JButton saveBtn = new JButton("�����ϱ�");
	JButton gmBtn = new JButton("�����ϱ�");

	Poketmon_MainGUI(String nick, String gm) {
		setTitle("��");
		setLayout(null);
		this.nick = nick;
//		add(centerPanel);
//		centerPanel.setLayout(null);
		add(gmBtn);
		gmBtn.addActionListener(this);
		gmBtn.setBounds(610, 145, 140, 35);
		gmBtn.setVisible(false);
		if(gm.equals("1")) {
			gmBtn.setVisible(true);
		}


		// �г��� ����
		add(jnick = new JLabel(nick));
		jnick.setHorizontalAlignment(JLabel.CENTER);
		jnick.setFont(new Font("���� ���", Font.BOLD, 20));
		jnick.setBorder(new TitledBorder(new LineBorder(Color.black,5),"�г���"));;
		jnick.setBounds(300, 50, 150, 45);

		class imgPanel extends JPanel {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}

			imgPanel() {
				

				new Thread(new Runnable() {
					PoketmonDAO dao = new PoketmonDAO();
					

					@Override
					public void run() {
						while (true) {
							PoketmonDTO dto = dao.getMemberDTO(nick);
							int status = dto.getEnergy();
							if (status >= 70) {
								img = image1.getImage();
								repaint();
							} else if (status > 30) {
								img = image2.getImage();
								repaint();
							} else if (status <= 30) {
								img = image3.getImage();
								repaint();
							}
							
							try {
								Thread.sleep(500);
							} catch (Exception e) {

							}
						}
					}
				}).start();
			}
		}
		JPanel panel = new imgPanel();
		add(panel);
		panel.setBounds(230, 120, 300, 300);
//		panel.setBounds(0, 0,500, 500);
		JScrollPane scroll = new JScrollPane(info);
		add(scroll);
		scroll.setBounds(580, 180, 170, 250);
		info.setEditable(false);
		info.setLineWrap(true);
		info.append("���ϸ� Ű��⿡ ���Ű��� ȯ���մϴ�\n");
		// ��ư�� ũ��, ��ġ�۾�
		add(feedingBtn);
		feedingBtn.setBounds(10, 450, 140, 100);
		feedingBtn.addActionListener(this);
		add(playBtn);
		playBtn.setBounds(160, 450, 140, 100);
		playBtn.addActionListener(this);
		add(restBtn);
		restBtn.setBounds(310, 450, 140, 100);
		restBtn.addActionListener(this);
		add(fightBtn);
		fightBtn.setBounds(460, 450, 140, 100);
		fightBtn.addActionListener(this);
		add(exitBtn);
		exitBtn.setBounds(610, 450, 140, 100);
		exitBtn.addActionListener(this);
		add(releaseBtn);
		releaseBtn.setBounds(610, 10, 140, 35);
		releaseBtn.addActionListener(this);
		add(rankingBtn);
		rankingBtn.setBounds(610, 55, 140, 35);
		rankingBtn.addActionListener(this);
		add(saveBtn);
		saveBtn.setBounds(610, 100, 140, 35);
		saveBtn.addActionListener(this);

		// ����â
		add(jllevel = new JLabel("���� :"));
		jllevel.setFont(new Font("���� ���", Font.BOLD, 13));
		//jllevel.setBorder(BorderFactory.createBevelBorder(0)); // �������丮�� �׵θ��� ����
		jllevel.setBounds(10, 10, 55, 30);
		add(jtlevel = new JTextField(""));
		jtlevel.setBounds(80, 10, 50, 35);
		jtlevel.setEditable(false);

		add(jlexp = new JLabel("����ġ :"));
		jlexp.setFont(new Font("���� ���", Font.BOLD, 13));
		jlexp.setBounds(10, 45, 55, 30);
		add(jtexp = new JTextField(""));
		jtexp.setBounds(80, 45, 50, 30);
		jtexp.setEditable(false);

		add(jlgold = new JLabel("��� :"));
		jlgold.setFont(new Font("���� ���", Font.BOLD, 13));
		jlgold.setBounds(10, 80, 55, 30);
		add(jtgold = new JTextField(""));
		jtgold.setBounds(80, 80, 50, 30);
		jtgold.setEditable(false);

		add(jlenergy = new JLabel("������ :"));
		jlenergy.setFont(new Font("���� ���", Font.BOLD, 13));
		jlenergy.setBounds(10, 115, 55, 30);
		add(jtenergy = new JTextField(""));
		jtenergy.setBounds(80, 115, 50, 30);
		jtenergy.setEditable(false);
		refresh(nick);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setSize(775, 600);
		setLocationRelativeTo(null);
	}

	public void refresh(String nickname) {
		PoketmonDAO dao = new PoketmonDAO();
		PoketmonDTO dto = dao.getMemberDTO(nickname);
		jtlevel.setText(Integer.toString(dto.getLevel()));
		jtexp.setText(Integer.toString(dto.getExp()));
		jtgold.setText(Integer.toString(dto.getGold()));
		jtenergy.setText(Integer.toString(dto.getEnergy()));
		if(dto.getEnergy() >= 70) {
			info.append("[system] " + nick + "�� ���� �ſ� �����ϴ� ���� �Դϴ�.\n");
			info.append("=====================\n");

		}else if(dto.getEnergy() > 30) {
			info.append("[system] " + nick + "�� ���� �����׷� ���� �Դϴ�.\n");
			info.append("=====================\n");

		}else if(dto.getEnergy() <= 30) {
			info.append("[system] " + nick + "�� ���� �ſ� ����� ���� �Դϴ�.\n");
			info.append("=====================\n");

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ButtonFlag = e.getActionCommand();
		if (ButtonFlag.equals("�޽��ϱ�")) {
			new rest(nick, this);
		} else if (ButtonFlag.equals("���ֱ�")) {
			new Eat(nick, this);

		} else if (ButtonFlag.equals("����ֱ�")) {
			new play(nick, this);

		} else if (ButtonFlag.equals("�����ϱ�")) {
			new fight(nick, this);
		} else if (ButtonFlag.equals("�����ϱ�")) {
			PoketmonLogin login = new PoketmonLogin();
			dispose();
		} else if (ButtonFlag.equals("�����ϱ�")) {
			new Save(nick);
		} else if (ButtonFlag.equals("�߻����� ����ϱ�")) {
			new release(nick, this);
		} else if (ButtonFlag.equals("���� ��ŷ")) {
			new ranking(nick, this);
		} else if (ButtonFlag.equals("�����ϱ�")) {
			new manager(this);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//   public static void main(String[] args) {
//      new Poketmon_MainGUI();
//
//   }

}