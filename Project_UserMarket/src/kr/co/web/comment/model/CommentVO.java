package kr.co.web.comment.model;

public class CommentVO {
	private String cmt_id;
	private String post_id;
	private String parent_id;
	private String user_id;
	private String nick;
	private String secret;
	private String content;
	private String w_date;
	
	public CommentVO() {}

	public CommentVO(String cmt_id, String post_id, String parent_id, String user_id, String nick, String secret,
			String content, String w_date) {
		super();
		this.cmt_id = cmt_id;
		this.post_id = post_id;
		this.parent_id = parent_id;
		this.user_id = user_id;
		this.nick = nick;
		this.secret = secret;
		this.content = content;
		this.w_date = w_date;
	}

	public String getCmt_id() {
		return cmt_id;
	}

	public void setCmt_id(String cmt_id) {
		this.cmt_id = cmt_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getW_date() {
		return w_date;
	}

	public void setW_date(String w_date) {
		this.w_date = w_date;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
	
	
	
	
	
}
