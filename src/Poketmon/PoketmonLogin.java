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
   JLabel idl = new JLabel("���̵�");
   JLabel pwl = new JLabel("�н�����");
   
   // JTextField
   JTextField id = new JTextField();
   JPasswordField pw = new JPasswordField();
   
   // JButton
   JButton loginBtn = new JButton("�α���");
   JButton joinBtn = new JButton("ȸ������");
   JButton exitBtn = new JButton("����");
   
   
   public PoketmonLogin() {
      setTitle("��ī�� Ű��� �α���");
      // �г��� ũ�� �۾�
      centerPanel.setPreferredSize(new Dimension(260, 80));
      westPanel.setPreferredSize(new Dimension(210, 75));
      eastPanel.setPreferredSize(new Dimension(90, 75));
      southPanel.setPreferredSize(new Dimension(290, 40));
      
      // label ũ��
      idl.setPreferredSize(new Dimension(50, 30));
      pwl.setPreferredSize(new Dimension(50, 30));
      
      // textfield ũ��
      id.setPreferredSize(new Dimension(140, 30));
      pw.setPreferredSize(new Dimension(140, 30));
      
      // ��ư�� ũ��
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
      
      if(jb.getText().equals("�α���")) {
         dao = new PoketmonDAO();
         dto = new PoketmonDTO();
         String uid = id.getText();
         String upw = pw.getText();
         String check = dao.logincheck(uid, upw);
         String gmCheck[] = check.split("/");
         
         if(gmCheck[0].equals("fail")){
            JOptionPane.showMessageDialog(null, "�α��� ����");
         }else if(gmCheck[0] != null && gmCheck[1].equals("1")){
            JOptionPane.showMessageDialog(null, "������ �α��� ����");
            dispose();
            new Poketmon_MainGUI(gmCheck[0], gmCheck[1]);
         }else if(gmCheck[0] != null && gmCheck[1].equals("0")){
        	 JOptionPane.showMessageDialog(null, "�α��� ����");
        	 dispose();
        	 new Poketmon_MainGUI(gmCheck[0], gmCheck[1]);
         }
         
         if(uid.equals("") || upw.equals("")) {
            JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �Է��ϼ���.", " ",JOptionPane.ERROR_MESSAGE);
            return;
         }
      }else if(jb.getText().equals("ȸ������")) {
         join = new PoketmonJoin();
      }else if(jb.getText().equals("����")) {
    	  System.exit(1);
      }
   }


}