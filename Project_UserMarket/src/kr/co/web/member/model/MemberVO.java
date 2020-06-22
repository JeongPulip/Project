package kr.co.web.member.model;

public class MemberVO {
	private String id;
	private String pwd;
	private String nick;
	
	private String name;
	private String b_date;
	private String gender;
	
	private String email;
	private String tel;
	
	private String city_id;
	private String prf_path;
	
	private String j_date;
	
	public MemberVO() {}
	public MemberVO
	(String id, String pwd, String nick, String name,
	String b_date, String gender, String email, String tel,
	String city_id, String prf_path, String j_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.name = name;
		this.b_date = b_date;
		this.gender = gender;
		this.email = email;
		this.tel = tel;
		this.city_id = city_id;
		this.prf_path = prf_path;
		this.j_date = j_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getPrf_path() {
		return prf_path;
	}
	public void setPrf_path(String prf_path) {
		this.prf_path = prf_path;
	}
	public String getJ_date() {
		return j_date;
	}
	public void setJ_date(String j_date) {
		this.j_date = j_date;
	}
	
	
	
	
	
}	