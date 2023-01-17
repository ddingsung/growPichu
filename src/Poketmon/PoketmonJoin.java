package Poketmon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PoketmonJoin extends JFrame implements ActionListener{
     PoketmonJoin poketmonjoin;
      JPanel p;
      JTextField tfName,tfNickName, tfId, tfEmail; 
      JPasswordField pfpwd; 
      JButton btnInsert, btnCancel;
      
      GridBagLayout gb;
      GridBagConstraints gbc;
      
   //   public OmokProc() {
//         createUI();
   //   }
      
      public PoketmonJoin() {
            createUI();
       
            this.poketmonjoin = poketmonjoin;   
            
         }
         
         private void viewData(PoketmonDTO vOm) {
            String name = vOm.getName();
            String nickname = vOm.getNickname();
            String id = vOm.getId();
            String pwd = vOm.getPassword();
            String email = vOm.getEmail();
            
            tfName.setText(name);
            tfNickName.setText(nickname);
            tfId.setText(id);
            pfpwd.setText("");
            tfEmail.setText(email);
         }
         
         
         public void createUI() {
            this.setTitle("회원가입");
            gb = new GridBagLayout();
            setLayout(gb);
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            
            //?   ?
            JLabel bName = new JLabel("사용자이름");
            tfName = new JTextField(20);
            gbAdd(bName,0,0,1,1);
            gbAdd(tfName,1,0,3,1);
            
            //?  ?  ?  
            JLabel bNickName = new JLabel("닉네임");
            tfNickName = new JTextField(20);
            gbAdd(bNickName,0,1,1,1);
            gbAdd(tfNickName,1,1,3,1);
            
            //id
            JLabel bId = new JLabel("아이디");
            tfId = new JTextField(20);
            gbAdd(bId,0,2,1,1);
            gbAdd(tfId,1,2,3,1);
            
            //비 ?번호
            JLabel bpwd = new JLabel("비밀번호");
            pfpwd = new JPasswordField(20);
            
            gbAdd(bpwd, 0, 3, 1, 1);
            gbAdd(pfpwd, 1, 3, 3, 1);
            
            //Email
            JLabel bEmail = new JLabel("이메일");
            tfEmail = new JTextField(20);
            gbAdd(bEmail,0,4,1,1);
            gbAdd(tfEmail,1,4,3,1);
            
            // ??  버튼
            JPanel pButton = new JPanel();
            btnInsert = new JButton("가입");
            btnCancel = new JButton("취소");
            
          pButton.add(btnInsert);
          pButton.add(btnCancel);
           gbAdd(pButton, 0, 5, 5, 1);
            
            btnInsert.addActionListener(this);
            btnCancel.addActionListener(this);
            
            setSize(280,300);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
         
         }
         
         private void gbAdd(JComponent c, int x, int y, int w, int h ) {
            gbc.gridx = x;
            gbc.gridy =y;
            gbc.gridwidth =w;
            gbc.gridheight=h;
            gb.setConstraints(c, gbc);
            gbc.insets = new Insets(3,3,3,3);
            add(c,gbc);
         }

         
         
         @Override
         public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnInsert) {
               insertMember();
            }else if (e.getSource() == btnCancel) {
               this.dispose();
            }
            
         }
         private PoketmonDTO getviewData() {
            PoketmonDTO dto = new PoketmonDTO();
            String name = tfName.getText();
            String nickname = tfNickName.getText();
            String id = tfId.getText();
            String pwd = pfpwd.getText();
            String email = tfEmail.getText();
            
            dto.setName(name);
            dto.setNickname(nickname);
            dto.setId(id);
            dto.setPassword(pwd);
            dto.setEmail(email);
            
            return dto;
         }
         private void insertMember() {
            PoketmonDTO dto = getviewData();
            PoketmonDAO dao = new PoketmonDAO();
            boolean ok = dao.insertMember(dto);
            if(ok) {
               JOptionPane.showMessageDialog(this, "회원가입이 성공적으로 처리되었습니다!");
               dispose();
            }else {
               JOptionPane.showMessageDialog(this, "회원가입에 실패했습니다.");
            }
         }

      }