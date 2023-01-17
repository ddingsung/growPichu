package Poketmon;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class managerProc extends JFrame implements ActionListener{
	manager manager;
	Poketmon_MainGUI main;
	JPanel p;
	JTextField tfId, tfNickname, tfEmail, tfLevel, tfExp, tfGold, tfEnergy;
	JRadioButton rbGm, rbNotGm;
	JButton btnInsert, btnCancle, btnUpdate, btnDelete;
	
	GridBagLayout gb;
	GridBagConstraints gbc;
	
	public managerProc(String id, manager manager, Poketmon_MainGUI main) {
		this.manager = manager;
		this.main = main;
		createUI();
		PoketmonDAO dao = new PoketmonDAO();
		PoketmonDTO dto = dao.getMemberDTO(id);
		viewData(dto);
	}
	private void viewData(PoketmonDTO dto) {
		String id = dto.getId();
		String nickname = dto.getNickname();
		String email = dto.getEmail();
		String level = Integer.toString(dto.getLevel());
		String exp =Integer.toString(dto.getExp());
		String gold = Integer.toString(dto.getGold());
		String energy = Integer.toString(dto.getEnergy());
		String gm = Integer.toString(dto.getGm());
		
		tfId.setText(id);
		tfId.setEditable(false);
		tfNickname.setText(nickname);
		if(gm.equals("1")) {
			rbGm.setSelected(true);
		}else if(gm.equals("0")) {
			rbNotGm.setSelected(true);
		}
		tfEmail.setText(email);
		tfLevel.setText(level);
		tfExp.setText(exp);
		tfGold.setText(gold);
		tfEnergy.setText(energy);
		
		
	}
	public void createUI(){
		this.setTitle("회원 정보");
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		JLabel bId = new JLabel("아이디 : ");
		tfId = new JTextField(20);
		gbAdd(bId, 0, 0, 1, 1);
		gbAdd(tfId, 1, 0, 3, 1);
		JLabel bNickname = new JLabel("닉네임 : ");
		tfNickname = new JTextField(20);
		tfNickname.setEditable(false);
		gbAdd(bNickname, 0, 1, 1, 1);
		gbAdd(tfNickname, 1, 1, 3, 1);
		JLabel bEmail = new JLabel("이메일 : ");
		tfEmail = new JTextField(20);
		gbAdd(bEmail, 0, 2, 1, 1);
		gbAdd(tfEmail, 1, 2, 3, 1);
		JLabel bLevel = new JLabel("레벨 : ");
		tfLevel = new JTextField(20);
		gbAdd(bLevel, 0, 3, 1, 1);
		gbAdd(tfLevel, 1, 3, 3, 1);
		JLabel bExp = new JLabel("경험치 : ");
		tfExp = new JTextField(20);
		gbAdd(bExp, 0, 4, 1, 1);
		gbAdd(tfExp, 1, 4, 3, 1);
		JLabel bGold = new JLabel("골드 : ");
		tfGold = new JTextField(20);
		gbAdd(bGold, 0, 5, 1, 1);
		gbAdd(tfGold, 1, 5, 3, 1);
		JLabel bEnergy = new JLabel("에너지 : ");
		tfEnergy = new JTextField(20);
		gbAdd(bEnergy, 0, 6, 1, 1);
		gbAdd(tfEnergy, 1, 6, 3, 1);
		JLabel bGmSet = new JLabel("관리자 설정 : ");
		JPanel pGm = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rbGm = new JRadioButton("관리자");
		rbNotGm = new JRadioButton("사용자", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rbGm);
		group.add(rbNotGm);
		pGm.add(rbGm);
		pGm.add(rbNotGm);
		gbAdd(bGmSet, 0, 7, 1, 1);
		gbAdd(pGm, 1, 7, 3, 1);
		JPanel pButton = new JPanel();
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("탈퇴");
		btnCancle = new JButton("취소");
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancle);
		gbAdd(pButton, 0, 8, 4, 1);
		
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnCancle.addActionListener(this);
		
		setSize(370,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void gbAdd(JComponent c, int x, int y ,int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
	}
	public void UpdateMember(){
		PoketmonDTO dto = getViewData();
		PoketmonDAO dao = new PoketmonDAO();
		boolean flag = dao.updateMember(dto);
		if(flag == true) {
			JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
		}
	}
	private PoketmonDTO getViewData() {
		PoketmonDTO dto = new PoketmonDTO();
		String id = tfId.getText();
		String nickname = tfNickname.getText();
		String email = tfEmail.getText();
		int exp = Integer.parseInt(tfExp.getText());
		String gm = "";
		if(rbGm.isSelected()) {
			gm = "1";
		}else if(rbNotGm.isSelected()){
			gm = "0";
		}
		int level = Integer.parseInt(tfLevel.getText());
		int gold = Integer.parseInt(tfGold.getText());
		int energy = Integer.parseInt(tfEnergy.getText());
		
		dto.setId(id);
		dto.setNickname(nickname);
		dto.setEmail(email);
		dto.setExp(exp);
		dto.setGm(Integer.parseInt(gm));
		dto.setLevel(level);
		dto.setGold(gold);
		dto.setEnergy(energy);
		
		return dto;
	}
	private void deleteMember() {
		String id = tfId.getText();
		PoketmonDAO dao = new PoketmonDAO();
		boolean flag = dao.deleteMember(id);
		if(flag == true) {
			JOptionPane.showMessageDialog(this, "삭제되었습니다");
		}else {
			JOptionPane.showMessageDialog(this, "삭제에 실패했습니다.");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnUpdate) {
			UpdateMember();
		}else if(e.getSource() == btnCancle) {
			this.dispose();
		}else if(e.getSource() == btnDelete) {
			int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겟습니까?", "삭제", JOptionPane.OK_OPTION);
			if(x == JOptionPane.OK_OPTION) {
				deleteMember();
			}else {
			JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다");
			}
		}
		manager.jTableRefresh();
		
	}
	}


