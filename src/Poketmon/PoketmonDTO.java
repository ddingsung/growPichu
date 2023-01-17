package Poketmon;

public class PoketmonDTO {
	private String id;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private int exp;
	private int gold;
	private int level;
	private int energy;
	private int gm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getGm() {
		return gm;
	}

	public void setGm(int gm) {
		this.gm = gm;
	}

	@Override
	public String toString() {
		return "PoketmonDTO [id=" + id + ", password=" + password + ", name=" + name + ", nickname=" + nickname
				+ ", email=" + email + ", exp=" + exp + ", gold=" + gold + ", level=" + level + ", energy=" + energy
				+ ", gm=" + gm + "]";
	}

}
