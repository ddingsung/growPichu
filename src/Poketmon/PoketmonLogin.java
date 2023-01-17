package Poketmon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PoketmonLogin extends JFrame implements ActionListener{
   PoketmonDAO dao;
   PoketmonDTO dto;
   PoketmonJoin join;

   JPanel basePanel = new JPanel(new BorderLayout());
   JPanel centerPanel = new JPanel(new BorderLayout());
   JPanel westPanel = new JPanel();
   JPanel eastPanel = new JPanel();
   JPanel southPanel = new JPanel();
   
   // JLabel
   JLabel idl = new JLabel("아이디");
   JLabel pwl = new JLabel("패스워드");
   
   // JTextField
   JTextField id = new JTextField();
   JPasswordField pw = new JPasswordField();
   
   // JButton
   JButton loginBtn = new JButton("로그인");
   JButton joinBtn = new JButton("회원가입");
   JButton exitBtn = new JButton("종료");
   
   
   public PoketmonLogin() {
      setTitle("피카츄 키우기 로그인");
      // 패널의 크기 작업
      centerPanel.setPreferredSize(new Dimension(260, 80));
      westPanel.setPreferredSize(new Dimension(210, 75));
      eastPanel.setPreferredSize(new Dimension(90, 75));
      southPanel.setPreferredSize(new Dimension(290, 40));
      
      // label 크기
      idl.setPreferredSize(new Dimension(50, 30));
      pwl.setPreferredSize(new Dimension(50, 30));
      
      // textfield 크기
      id.setPreferredSize(new Dimension(140, 30));
      pw.setPreferredSize(new Dimension(140, 30));
      
      // 버튼의 크기
      loginBtn.setPreferredSize(new Dimension(75, 63));
      joinBtn.setPreferredSize(new Dimension(135, 25));
      exitBtn.setPreferredSize(new Dimension(135, 25));
      
      setContentPane(basePanel);
      basePanel.add(centerPanel, BorderLayout.CENTER);
      basePanel.add(southPanel, BorderLayout.SOUTH);
      centerPanel.add(westPanel, BorderLayout.WEST);
      centerPanel.add(eastPanel, BorderLayout.EAST);
      
      westPanel.setLayout(new FlowLayout());
      eastPanel.setLayout(new FlowLayout());
      southPanel.setLayout(new FlowLayout());
      
      westPanel.add(idl);
      westPanel.add(id);
      westPanel.add(pwl);
      westPanel.add(pw);
      
      eastPanel.add(loginBtn);
      
      southPanel.add(exitBtn);
      southPanel.add(joinBtn);
      
      
      loginBtn.addActionListener(this);
      exitBtn.addActionListener(this);
      joinBtn.addActionListener(this);
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      setSize(310, 150);
      setResizable(false);
      setLocationRelativeTo(null);
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      JButton jb = (JButton)e.getSource();
      
      if(jb.getText().equals("로그인")) {
         dao = new PoketmonDAO();
         dto = new PoketmonDTO();
         String uid = id.getText();
         String upw = pw.getText();
         String check = dao.logincheck(uid, upw);
         String gmCheck[] = check.split("/");
         
         if(gmCheck[0].equals("fail")){
            JOptionPane.showMessageDialog(null, "로그인 실패");
         }else if(gmCheck[0] != null && gmCheck[1].equals("1")){
            JOptionPane.showMessageDialog(null, "관리자 로그인 성공");
            dispose();
            new Poketmon_MainGUI(gmCheck[0], gmCheck[1]);
         }else if(gmCheck[0] != null && gmCheck[1].equals("0")){
        	 JOptionPane.showMessageDialog(null, "로그인 성공");
        	 dispose();
        	 new Poketmon_MainGUI(gmCheck[0], gmCheck[1]);
         }
         
         if(uid.equals("") || upw.equals("")) {
            JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", " ",JOptionPane.ERROR_MESSAGE);
            return;
         }
      }else if(jb.getText().equals("회원가입")) {
         join = new PoketmonJoin();
      }else if(jb.getText().equals("종료")) {
    	  System.exit(1);
      }
   }


}