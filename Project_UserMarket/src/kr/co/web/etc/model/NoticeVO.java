package kr.co.web.etc.model;

public class NoticeVO {
	private String notice_id;
	private String post_id;
	private String cmt_id;
	private String user_id;
	private String n_content;
	private String n_time;
	
	public NoticeVO() {}

	public NoticeVO(String notice_id, String post_id, String cmt_id, String user_id, String n_content, String n_time) {
		super();
		this.notice_id = notice_id;
		this.post_id = post_id;
		this.cmt_id = cmt_id;
		this.user_id = user_id;
		this.n_content = n_content;
		this.n_time = n_time;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getCmt_id() {
		return cmt_id;
	}

	public void setCmt_id(String cmt_id) {
		this.cmt_id = cmt_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public String getN_time() {
		return n_time;
	}

	public void setN_time(String n_time) {
		this.n_time = n_time;
	}

	

	
	
	
}
