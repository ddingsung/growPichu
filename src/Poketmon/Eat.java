
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
      setTitle("��Ա�");
      setLayout(null);
      ImageIcon image2 = new ImageIcon("img/��Դ���ī��.gif");  
      
        jlimg = new JLabel(image2);
        add(jlimg);
        jlimg.setBounds(85, 0, 400,400);
        
        JPanel jp1 = new JPanel();
        JButton jb1 = new JButton("��");
        jb1.addActionListener(this);
        JButton jb2 = new JButton("���ư���");
        jb2.addActionListener(this);
        JLabel jlabel = new JLabel("���̸� �ֽðڽ��ϱ�?");
        
        
        add(jlabel);
        add(jb1);
        add(jb2);
        
        jlabel.setBounds(220, 380, 160, 50);
        jlabel.setFont(new Font("���� ���",Font.BOLD,16));
        jb1.setBounds(150,450,100,50);
        jb1.setFont(new Font("���� ���",Font.BOLD,16));
        jb2.setBounds(320,450,100,50);
        jb2.setFont(new Font("���� ���",Font.BOLD,16));
        
        JLabel jl = new JLabel("(���̸� ������ ������ 20, ����ġ 20�� ����ϴ�)");
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
      
   if(ButtonFlag.equals("��")) {
      if(energy >= 90) { //�������� 90 �̻��϶��� ������ ���� X
            JOptionPane.showMessageDialog(this, "�̹� �������� ���� �� �����Դϴ�");
            this.dispose();
         }else {//�������� 90 �̸��϶�
            if(dao.updateEnergy(nickname)) {
               JOptionPane.showMessageDialog(this, "�������� 20 �����Ǿ����ϴ�");
              
               if(dao.updateExp(nickname)) { // �������� ���� �Ǹ鼭 ����ġ�� �ö�!(������ ���� + ����ġ)
                   main.refresh(nickname);
                   dispose();
               }
            }else {
               JOptionPane.showMessageDialog(this, "������ ���� ����!");
            }
            
         }
      
   }else if(ButtonFlag.equals("���ư���")) {
      this.dispose();
   }
   
}
      }
      