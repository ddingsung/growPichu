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
	      setTitle("전투하기");
	      setLayout(null);
	      ImageIcon image2 = new ImageIcon("img/전투하는피카츄.gif");  
	      
	        jlimg = new JLabel(image2);
	        add(jlimg);
	        jlimg.setBounds(85, 0, 400,400);
	        
	        JPanel jp1 = new JPanel();
	        JButton jb1 = new JButton("예");
	        jb1.addActionListener(this);
	        JButton jb2 = new JButton("돌아가기");
	        jb2.addActionListener(this);
	        JLabel jlabel = new JLabel("전투를 하시겠습니까?");
	        
	        
	        add(jlabel);
	        add(jb1);
	        add(jb2);
	        
	        jlabel.setBounds(220, 380, 160, 50);
	        jlabel.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        jb1.setBounds(150,450,100,50);
	        jb1.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        jb2.setBounds(320,450,100,50);
	        jb2.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        
	        JLabel jl = new JLabel("(전투를 하게되면 에너지가 30감소하고 30의 경험치를 얻습니다.)");
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
		      
		   if(ButtonFlag.equals("예")) {
				if (dto.getEnergy() <= 20) {
					JOptionPane.showConfirmDialog(null, "전투를 하게되어 에너지가 0이하가 되면 포켓몬이 죽게됩니다, 정말로 전투를 시작 하시겠습니까?","경고",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null);
					JOptionPane.showMessageDialog(this, "위험 에너지가 낮습니다.");
					if (dao.minusEnergy(nickname)) {
						JOptionPane.showMessageDialog(this, "에너지가 -30 되었습니다.");
						main.refresh(nickname);
						if (dto.getEnergy() <= 20) {
							dao.die(nickname);
							JOptionPane.showMessageDialog(this, "포켓몬이 죽었습니다." + "처음 부터 다시 시작합니다.");
							main.refresh(nickname);
							dispose();
						}else if (dao.updateExp(nickname)) {
							main.refresh(nickname);
							dispose();
						}
					}
				} else {
					if (dao.minusEnergy(nickname)) {
						JOptionPane.showMessageDialog(this, "에너지가 -30 되었습니다.");
						main.refresh(nickname);
						if (dao.updateExp(nickname)) {
							main.refresh(nickname);
							dispose();
							
						}
					}

				}
				
			         
			      
		   
		        
		      
		   }else if(ButtonFlag.equals("돌아가기")) {
		      this.dispose();
		   }
	}
}