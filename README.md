# growPichu
> 국비교육중 팀프로젝트 과제로 만든 간단한 게임입니다.

## 1. 제작기간&참여인원
- 제작기간 : 2022.07.25 ~ 2022.08.09
- 참여인원 : 총 4명

## 2. 사용기술
- JAVA11, MySQL

## 3. 주요 기능
- Thread를 활용해 변수값에 따른 이미지 변경하기
<details>
<summary><b>코드 보기</b></summary>
<div markdown="1">

> 메인 GUI의 피카츄 모습이 에너지 값에 따라서 달라지게 PaintComponent 와 Thread를 이용해서<br>0.5초마다 status 의 값을 받아와서조건에 맞는 이미지를 Repaint();

~~~java
protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		imgPanel() {
	  new Thread(new Runnable() {
				PoketmonDAO dao = new PoketmonDAO();
				@Override
				public void run() {
					while (true) {
						PoketmonDTO dto = dao.getMemberDTO(nick);
						int status = dto.getEnergy();
						if (status >= 70) {
							img = image1.getImage();
							repaint();
							} else if (status > 30) {
								img = image2.getImage();
								repaint();
							} else if (status <= 30) {
								img = image3.getImage();
								repaint();
							}
							try {
								Thread.sleep(500);
							} catch (Exception e) {
						}
					}
				}
			}).start();
}
~~~
</div>
</details>

- 로그인기능 구현 및 일반회원 관리자 구분

<details>
<summary><b>코드 보기</b></summary>
<div markdown="1">

> 로그인시 반환되는값을 String으로 해놔서 추후수정하려했지만 하나의 객체로 계속 이어받다보니 <br>
다른 클래스들을 다 수정해야하는 번거로움이 생김 그래서 임시방편으로 Split으로 구별할수있게끔 코드를 작성함

-PoketmonDAO
~~~java
String logincheck(String _id, String _pw) {
		String nickname = "fail";
		String id = _id;
		String pw = _pw;
		Connection con = null;
		PreparedStatement ps = null;
		con = getConn();
		String sql = "select password, nickname, gm from member where id =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				if (pw.equals(rs.getString("password"))) {
					nickname = rs.getString("nickname");
					PoketmonDTO dto = new PoketmonDTO();
					dto.setNickname(nickname);
					if(rs.getInt("gm") == 1 && nickname != null) {
						nickname = nickname + "/1";
					}else if(rs.getInt("gm") == 0 && nickname != null) {
						nickname = nickname + "/0";
					}
				} else if(!(pw.equals(rs.getString("password")))) {
					nickname = "fail/0";
					System.out.println("비밀번호가 틀립니다.");
				}
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nickname;
	}
~~~

-PoketmonLogin.java
~~~java
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
~~~
</div>
</details>

- CRUD를 사용한 랭킹, 저장, 초기화 등 
