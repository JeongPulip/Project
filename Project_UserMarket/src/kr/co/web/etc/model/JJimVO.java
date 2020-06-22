package kr.co.web.etc.model;

public class JJimVO {
	private String jjim_id;
	private String user_id;
	private String post_id;
	
	public JJimVO() {}
	public JJimVO(String jjim_id, String user_id, String post_id) {
		super();
		this.jjim_id = jjim_id;
		this.user_id = user_id;
		this.post_id = post_id;
	}
	public String getJjim_id() {
		return jjim_id;
	}
	public void setJjim_id(String jjim_id) {
		this.jjim_id = jjim_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	

}
