package Poketmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Poketmon_MainGUI extends JFrame implements ActionListener {
	String nick;

	ImageIcon image1 = new ImageIcon("img/신난피카츄.png");
	ImageIcon image2 = new ImageIcon("img/피카츄.png");
	ImageIcon image3 = new ImageIcon("img/배고픈피카츄.gif");
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
	JLabel idl = new JLabel("캐릭터 이름");
	JLabel pwl = new JLabel("비밀번호");

	// 정보창
	JLabel jllevel, jlexp, jlgold, jlenergy, jnick;
	JTextField jtlevel, jtexp, jtgold, jtenergy;

	// JTextField
	JTextArea info = new JTextArea("",10,20);
	

	// JButton
	JButton feedingBtn = new JButton("밥주기");
	JButton playBtn = new JButton("놀아주기");
	JButton restBtn = new JButton("휴식하기");
	JButton fightBtn = new JButton("전투하기");
	JButton exitBtn = new JButton("종료하기");
	JButton releaseBtn = new JButton("야생으로 방생하기");
	JButton rankingBtn = new JButton("레벨 랭킹");
	JButton saveBtn = new JButton("저장하기");
	JButton gmBtn = new JButton("관리하기");

	Poketmon_MainGUI(String nick, String gm) {
		setTitle("집");
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


		// 닉네임 띄우기
		add(jnick = new JLabel(nick));
		jnick.setHorizontalAlignment(JLabel.CENTER);
		jnick.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		jnick.setBorder(new TitledBorder(new LineBorder(Color.black,5),"닉네임"));;
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
		info.append("포켓몬 키우기에 오신것을 환영합니다\n");
		// 버튼의 크기, 위치작업
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

		// 정보창
		add(jllevel = new JLabel("레벨 :"));
		jllevel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		//jllevel.setBorder(BorderFactory.createBevelBorder(0)); // 보더팩토리는 테두리가 나옴
		jllevel.setBounds(10, 10, 55, 30);
		add(jtlevel = new JTextField(""));
		jtlevel.setBounds(80, 10, 50, 35);
		jtlevel.setEditable(false);

		add(jlexp = new JLabel("경험치 :"));
		jlexp.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		jlexp.setBounds(10, 45, 55, 30);
		add(jtexp = new JTextField(""));
		jtexp.setBounds(80, 45, 50, 30);
		jtexp.setEditable(false);

		add(jlgold = new JLabel("골드 :"));
		jlgold.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		jlgold.setBounds(10, 80, 55, 30);
		add(jtgold = new JTextField(""));
		jtgold.setBounds(80, 80, 50, 30);
		jtgold.setEditable(false);

		add(jlenergy = new JLabel("에너지 :"));
		jlenergy.setFont(new Font("맑은 고딕", Font.BOLD, 13));
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
			info.append("[system] " + nick + "는 지금 매우 만족하는 상태 입니다.\n");
			info.append("=====================\n");

		}else if(dto.getEnergy() > 30) {
			info.append("[system] " + nick + "는 지금 그저그런 상태 입니다.\n");
			info.append("=====================\n");

		}else if(dto.getEnergy() <= 30) {
			info.append("[system] " + nick + "는 지금 매우 배고픈 상태 입니다.\n");
			info.append("=====================\n");

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ButtonFlag = e.getActionCommand();
		if (ButtonFlag.equals("휴식하기")) {
			new rest(nick, this);
		} else if (ButtonFlag.equals("밥주기")) {
			new Eat(nick, this);

		} else if (ButtonFlag.equals("놀아주기")) {
			new play(nick, this);

		} else if (ButtonFlag.equals("전투하기")) {
			new fight(nick, this);
		} else if (ButtonFlag.equals("종료하기")) {
			PoketmonLogin login = new PoketmonLogin();
			dispose();
		} else if (ButtonFlag.equals("저장하기")) {
			new Save(nick);
		} else if (ButtonFlag.equals("야생으로 방생하기")) {
			new release(nick, this);
		} else if (ButtonFlag.equals("레벨 랭킹")) {
			new ranking(nick, this);
		} else if (ButtonFlag.equals("관리하기")) {
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