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
	   setTitle("휴식하기");
	      this.main = main;
	      this.nickname = nickname;
	      setLayout(null);
	      ImageIcon image2 = new ImageIcon("img/휴식하는피카츄.gif");  
	      
	        jlimg = new JLabel(image2);
	        add(jlimg);
	        jlimg.setBounds(85, 0, 400,400);
	        
	        JPanel jp1 = new JPanel();
	        JButton jb1 = new JButton("예");
	        jb1.addActionListener(this);
	        JButton jb2 = new JButton("돌아가기");
	        jb2.addActionListener(this);
	        JLabel jlabel = new JLabel("휴식 하시겠습니까?");
	        
	        
	        add(jlabel);
	        add(jb1);
	        add(jb2);
	        
	        jlabel.setBounds(220, 380, 160, 50);
	        jlabel.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        jb1.setBounds(150,450,100,50);
	        jb1.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        jb2.setBounds(320,450,100,50);
	        jb2.setFont(new Font("맑은 고딕",Font.BOLD,16));
	        
	        JLabel jl = new JLabel("(휴식을 하면 에너지 30 충전됩니다.)");
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
	      
	   if(ButtonFlag.equals("예")) {
		   if(energy >= 100) {
		         JOptionPane.showMessageDialog(this, "에너지가 가득 차있어 휴식을 할수 없습니다.");
		      }else {
		         if(dao.updateEnergy(nickname)) {
		            JOptionPane.showMessageDialog(this, "에너지가 30 충전 되었습니다.");
		            main.refresh(nickname);
		            dispose();
		         }else {
		            JOptionPane.showMessageDialog(this, "에너지 충전 실패");
		         }
		         
		      }
	   
	        
	      
	   }else if(ButtonFlag.equals("돌아가기")) {
	      this.dispose();
	   }
	   	
}

}