
package Poketmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Eat extends JFrame implements ActionListener{
   String nickname;
   JLabel nick, jlimg, jl, jl2;
   Poketmon_MainGUI main;
   JPanel jpanel,jp1,jp2;
   JButton jb1, jb2;
   int energy;
   int exp;
   int level;

   public Eat(String nickname, Poketmon_MainGUI main) {
      this.main = main;
      this.nickname = nickname;
      setTitle("밥먹기");
      setLayout(null);
      ImageIcon image2 = new ImageIcon("img/밥먹는피카츄.gif");  
      
        jlimg = new JLabel(image2);
        add(jlimg);
        jlimg.setBounds(85, 0, 400,400);
        
        JPanel jp1 = new JPanel();
        JButton jb1 = new JButton("예");
        jb1.addActionListener(this);
        JButton jb2 = new JButton("돌아가기");
        jb2.addActionListener(this);
        JLabel jlabel = new JLabel("먹이를 주시겠습니까?");
        
        
        add(jlabel);
        add(jb1);
        add(jb2);
        
        jlabel.setBounds(220, 380, 160, 50);
        jlabel.setFont(new Font("맑은 고딕",Font.BOLD,16));
        jb1.setBounds(150,450,100,50);
        jb1.setFont(new Font("맑은 고딕",Font.BOLD,16));
        jb2.setBounds(320,450,100,50);
        jb2.setFont(new Font("맑은 고딕",Font.BOLD,16));
        
        JLabel jl = new JLabel("(먹이를 먹으면 에너지 20, 경험치 20을 얻습니다)");
      add(jl);
      jl.setBounds(160, 405, 300 , 50);
      
      
        
        
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setSize(600,550);
      setResizable(false);      
      setVisible(true);
		setLocationRelativeTo(null);

      
     
         }

@Override
public void actionPerformed(ActionEvent e) {
  PoketmonDAO dao = new PoketmonDAO();
    String ButtonFlag = e.getActionCommand();
      PoketmonDTO dto = dao.getMemberDTO(nickname);
      energy = dto.getEnergy();
      
   if(ButtonFlag.equals("예")) {
      if(energy >= 90) { //에너지가 90 이상일때는 에너지 충전 X
            JOptionPane.showMessageDialog(this, "이미 에너지가 가득 찬 상태입니다");
            this.dispose();
         }else {//에너지가 90 미만일때
            if(dao.updateEnergy(nickname)) {
               JOptionPane.showMessageDialog(this, "에너지가 20 충전되었습니다");
              
               if(dao.updateExp(nickname)) { // 에너지가 충전 되면서 경험치가 올라감!(에너지 충전 + 경험치)
                   main.refresh(nickname);
                   dispose();
               }
            }else {
               JOptionPane.showMessageDialog(this, "에너지 충전 실패!");
            }
            
         }
      
   }else if(ButtonFlag.equals("돌아가기")) {
      this.dispose();
   }
   
}
      }
      