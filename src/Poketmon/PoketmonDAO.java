package Poketmon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class PoketmonDAO {

	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/poketmon";
			String id = "root";
			String pw = "1234";

			con = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

	public boolean insertMember(PoketmonDTO dto) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;
		int o = 0;
		String a = "a";
		con = getConn();
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getId());
			ps.setString(4, dto.getPassword());
			ps.setString(5, dto.getEmail());
			ps.setInt(6, 0);
			ps.setInt(7, 1);
			ps.setInt(8, 0);
			ps.setInt(9, 100);
			ps.setInt(10, 0);

			int r = ps.executeUpdate();
			if (r > 0) {
				System.out.println("가입성공");
				ok = true;
			} else {
				System.out.println("가입실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오류");
			// TODO: handle exception
		}
		return ok;
	}

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

//	public String getMember() {
//		PoketmonDTO dto = new PoketmonDTO();
//		String nickname = dto.getNickname();
//		System.out.println(nickname);
//		return nickname;
//	}
	public boolean updateMember(PoketmonDTO dto) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update member set id=?,nickname=?, email=?, level =?, exp =?,gold=?,energy=?,gm=? where id=?";
		con = getConn();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getEmail());
			ps.setInt(4, dto.getLevel());
			ps.setInt(5, dto.getExp());
			ps.setInt(6, dto.getGold());
			ps.setInt(7, dto.getEnergy());
			ps.setInt(8, dto.getGm());
			ps.setString(9, dto.getId());
			
			int r = ps.executeUpdate();
			if(r>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	
	}
	public boolean deleteMember(String id) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from member where id =?";
		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			int r = ps.executeUpdate();
			if(r > 0)
				flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public PoketmonDTO getMemberDTO(String nick) {
		PoketmonDTO dto = new PoketmonDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = getConn();
		String sql = "select id, nickname, email, energy, level, exp, gold, gm from member where nickname = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nick);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setEnergy(rs.getInt("energy"));
				dto.setLevel(rs.getInt("level"));
				dto.setExp(rs.getInt("exp"));
				dto.setGold(rs.getInt("gold"));
				dto.setGm(rs.getInt("gm"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;

	}

	public boolean updateEnergy(String nickname) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;

		con = getConn();
		String sql = "update member set energy = energy+30 where nickname =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			int r = ps.executeUpdate();
			if (r > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public boolean minusEnergy(String nickname) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;

		con = getConn();
		String sql = "update member set energy = energy-30 where nickname =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			int r = ps.executeUpdate();
			if (r > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean playGain(String nickname, int exp) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		PoketmonDTO dto = new PoketmonDTO();

		con = getConn();
		String sql = "update member set exp = exp + ?,energy = energy-30 where nickname = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, exp);
			ps.setString(2, nickname);

			int r = ps.executeUpdate();
			if (r > 0) {
				JOptionPane.showMessageDialog(null, "에너지가 20 감소했습니다.");
				JOptionPane.showMessageDialog(null, "경험치가" + exp + "만큼 올랐습니다.");
				levelUpCheck(nickname);
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public boolean updateExp(String nickname) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		PoketmonDTO dto = new PoketmonDTO();

		con = getConn();
		String sql = "update member set exp = exp+20 where nickname =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);

			int r = ps.executeUpdate();
			if (r > 0) {
				JOptionPane.showMessageDialog(null, "경험치가 20만큼 올랐습니다.");
				flag = levelUpCheck(nickname);
				if(flag = false) {
					flag = true;
				}else if(flag = true)
					flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public boolean levelUpCheck(String nickname) {
		boolean flag = false;
		PoketmonDTO dto = new PoketmonDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = getConn();
		String sql = "select exp from member where nickname = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setExp(rs.getInt("exp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dto.getExp() >= 60) {
			sql = "update member set level = level+1, exp = 0 where nickname = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, nickname);
				int r = ps.executeUpdate();
				if (r > 0) {
					JOptionPane.showMessageDialog(null, "레벨업 !");
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		return flag;
	}

	public boolean updateGold(String nickname) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		PoketmonDTO dto = new PoketmonDTO();

		con = getConn();
		String sql = "update member set gold = gold+20 where nickname =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			int r = ps.executeUpdate();
			if (r > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public Vector viewRank() {
		Vector data = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = getConn();
		String sql = "select nickname,level,gold,exp  from member order by level desc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String nick = rs.getString("nickname");
				int level = rs.getInt("level");
				int gold = rs.getInt("gold");
				int exp = rs.getInt("exp");

				Vector row = new Vector();
				row.add(nick);
				row.add(level);
				row.add(gold);
				row.add(exp);
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public Vector viewMember() {
		Vector data = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = getConn();
		String sql = "select id, nickname, email,level, gold, exp, energy, gm from member order by id asc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String nickname = rs.getString("nickname");
				int level = rs.getInt("level");
				int gold = rs.getInt("gold");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				int gm = rs.getInt("gm");
				
				Vector row = new Vector();
				row.add(id);
				row.add(nickname);
				row.add(level);
				row.add(gold);
				row.add(exp);
				row.add(energy);
				row.add(gm);
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public boolean save(String nickname) {
		boolean flag = false;
		PoketmonDTO dto = getMemberDTO(nickname);
		Connection con = null;
		PreparedStatement ps = null;
		con = getConn();
		String sql = "update member set exp=?,level=?,gold=?,energy=? where nickname=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getExp());
			ps.setInt(2, dto.getLevel());
			ps.setInt(3, dto.getGold());
			ps.setInt(4, dto.getEnergy());
			ps.setString(5, nickname);
			int r = ps.executeUpdate();
			if (r > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	public boolean die(String nickname) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		PoketmonDTO dto = new PoketmonDTO();

		con = getConn();
		String sql = "update member set exp=0,level=1,gold=0,energy=100 where nickname=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			int r = ps.executeUpdate();
			if (r > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

}