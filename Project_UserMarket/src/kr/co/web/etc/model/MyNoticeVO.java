package kr.co.web.etc.model;

public class MyNoticeVO {
	private String post_id;
	private String cmt_id;
	private String content;
	private String c_num;
	private String count;
	private String ntime;
	
	public MyNoticeVO() {}

	public MyNoticeVO(String post_id, String cmt_id, String content, String c_num, String count, String ntime) {
		super();
		this.post_id = post_id;
		this.cmt_id = cmt_id;
		this.content = content;
		this.c_num = c_num;
		this.count = count;
		this.ntime = ntime;
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

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getNtime() {
		return ntime;
	}

	public void setNtime(String ntime) {
		this.ntime = ntime;
	}


	
	
	
	
	
	
	
	
	
	

}
