package Poketmon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class rest extends JFrame implements ActionListener{
	   String nickname;
	   JLabel nick, jlimg, jl, jl2;
	   Poketmon_MainGUI main;
	   JPanel jpanel,jp1,jp2;
	   JButton jb1, jb2;
	   int energy;
	   int exp;
	   int level;
   public rest(String nickname, Poketmon_MainGUI main) {
	   setTitle("�޽��ϱ�");
	      this.main = main;
	      this.nickname = nickname;
	      setLayout(null);
	      ImageIcon image2 = new ImageIcon("img/�޽��ϴ���ī��.gif");  
	      
	        jlimg = new JLabel(image2);
	        add(jlimg);
	        jlimg.setBounds(85, 0, 400,400);
	        
	        JPanel jp1 = new JPanel();
	        JButton jb1 = new JButton("��");
	        jb1.addActionListener(this);
	        JButton jb2 = new JButton("���ư���");
	        jb2.addActionListener(this);
	        JLabel jlabel = new JLabel("�޽� �Ͻðڽ��ϱ�?");
	        
	        
	        add(jlabel);
	        add(jb1);
	        add(jb2);
	        
	        jlabel.setBounds(220, 380, 160, 50);
	        jlabel.setFont(new Font("���� ���",Font.BOLD,16));
	        jb1.setBounds(150,450,100,50);
	        jb1.setFont(new Font("���� ���",Font.BOLD,16));
	        jb2.setBounds(320,450,100,50);
	        jb2.setFont(new Font("���� ���",Font.BOLD,16));
	        
	        JLabel jl = new JLabel("(�޽��� �ϸ� ������ 30 �����˴ϴ�.)");
	      add(jl);
	      jl.setBounds(160, 405, 300 , 50);
	      
	      
	        
	        
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
		   if(energy >= 100) {
		         JOptionPane.showMessageDialog(this, "�������� ���� ���־� �޽��� �Ҽ� �����ϴ�.");
		      }else {
		         if(dao.updateEnergy(nickname)) {
		            JOptionPane.showMessageDialog(this, "�������� 30 ���� �Ǿ����ϴ�.");
		            main.refresh(nickname);
		            dispose();
		         }else {
		            JOptionPane.showMessageDialog(this, "������ ���� ����");
		         }
		         
		      }
	   
	        
	      
	   }else if(ButtonFlag.equals("���ư���")) {
	      this.dispose();
	   }
	   	
}

}