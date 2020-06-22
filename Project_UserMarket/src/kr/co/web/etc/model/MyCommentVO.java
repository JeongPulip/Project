package kr.co.web.etc.model;

public class MyCommentVO {
	private String post_id; // 원문 게시글
	private String cmt_id; // 댓글 번호
	private String content; // 댓글 내용
	private String w_date; // 작성일
	private String p_title; // 원문(게시글) 제목
	private String c_num; // 원문 댓글수
	
	public MyCommentVO() {}

	public MyCommentVO(String post_id, String cmt_id, String content, String w_date, String p_title, String c_num) {
		super();
		this.post_id = post_id;
		this.cmt_id = cmt_id;
		this.content = content;
		this.w_date = w_date;
		this.p_title = p_title;
		this.c_num = c_num;
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

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	

	

	
	

}
