package Poketmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class fight extends JFrame implements ActionListener{
	   String nickname;
	   JLabel nick, jlimg, jl, jl2;
	   Poketmon_MainGUI main;
	   JPanel jpanel,jp1,jp2;
	   JButton jb1, jb2;
	   int energy;
	   int exp;
	   int level;
	public fight(String nickname, Poketmon_MainGUI main) {
		
	    this.main = main;
	      this.nickname = nickname;
	      setTitle("�����ϱ�");
	      setLayout(null);
	      ImageIcon image2 = new ImageIcon("img/�����ϴ���ī��.gif");  
	      
	        jlimg = new JLabel(image2);
	        add(jlimg);
	        jlimg.setBounds(85, 0, 400,400);
	        
	        JPanel jp1 = new JPanel();
	        JButton jb1 = new JButton("��");
	        jb1.addActionListener(this);
	        JButton jb2 = new JButton("���ư���");
	        jb2.addActionListener(this);
	        JLabel jlabel = new JLabel("������ �Ͻðڽ��ϱ�?");
	        
	        
	        add(jlabel);
	        add(jb1);
	        add(jb2);
	        
	        jlabel.setBounds(220, 380, 160, 50);
	        jlabel.setFont(new Font("���� ���",Font.BOLD,16));
	        jb1.setBounds(150,450,100,50);
	        jb1.setFont(new Font("���� ���",Font.BOLD,16));
	        jb2.setBounds(320,450,100,50);
	        jb2.setFont(new Font("���� ���",Font.BOLD,16));
	        
	        JLabel jl = new JLabel("(������ �ϰԵǸ� �������� 30�����ϰ� 30�� ����ġ�� ����ϴ�.)");
	      add(jl);
	      jl.setBounds(110, 405, 450, 50);
	      
	      
	        
	        
	      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	      setSize(600,550);
	      setResizable(false);      
	      setVisible(true);
			setLocationRelativeTo(null);

		this.nickname = nickname;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		  PoketmonDAO dao = new PoketmonDAO();
		    String ButtonFlag = e.getActionCommand();
		      PoketmonDTO dto = dao.getMemberDTO(nickname);
		      energy = dto.getEnergy();
		      
		   if(ButtonFlag.equals("��")) {
				if (dto.getEnergy() <= 20) {
					JOptionPane.showConfirmDialog(null, "������ �ϰԵǾ� �������� 0���ϰ� �Ǹ� ���ϸ��� �װԵ˴ϴ�, ������ ������ ���� �Ͻðڽ��ϱ�?","���",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null);
					JOptionPane.showMessageDialog(this, "���� �������� �����ϴ�.");
					if (dao.minusEnergy(nickname)) {
						JOptionPane.showMessageDialog(this, "�������� -30 �Ǿ����ϴ�.");
						main.refresh(nickname);
						if (dto.getEnergy() <= 20) {
							dao.die(nickname);
							JOptionPane.showMessageDialog(this, "���ϸ��� �׾����ϴ�." + "ó�� ���� �ٽ� �����մϴ�.");
							main.refresh(nickname);
							dispose();
						}else if (dao.updateExp(nickname)) {
							main.refresh(nickname);
							dispose();
						}
					}
				} else {
					if (dao.minusEnergy(nickname)) {
						JOptionPane.showMessageDialog(this, "�������� -30 �Ǿ����ϴ�.");
						main.refresh(nickname);
						if (dao.updateExp(nickname)) {
							main.refresh(nickname);
							dispose();
							
						}
					}

				}
				
			         
			      
		   
		        
		      
		   }else if(ButtonFlag.equals("���ư���")) {
		      this.dispose();
		   }
	}
}