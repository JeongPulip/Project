package kr.co.web.etc.model;

import java.util.ArrayList;

public class MyListVO {
	private ArrayList<AllOfPostVO> my_post; // 내 게시물
	private String p_count; // 게시물 수
	
	private ArrayList<MyCommentVO> my_comment; // 내 댓글
	private String c_count; // 댓글 수
	
	private ArrayList<AllOfPostVO> my_jjim; // 내 찜목록
	private String j_count; // 찜 수
	
	private ArrayList<MyNoticeVO> my_notice; // 내 알림
	private String n_count; // 알림 수
	
	public MyListVO() {}
	public MyListVO(ArrayList<AllOfPostVO> my_post, String p_count, ArrayList<MyCommentVO> my_comment, String c_count,
			ArrayList<AllOfPostVO> my_jjim, String j_count, ArrayList<MyNoticeVO> my_notice, String n_count) {
		super();
		this.my_post = my_post;
		this.p_count = p_count;
		this.my_comment = my_comment;
		this.c_count = c_count;
		this.my_jjim = my_jjim;
		this.j_count = j_count;
		this.my_notice = my_notice;
		this.n_count = n_count;
	}
	public ArrayList<AllOfPostVO> getMy_post() {
		return my_post;
	}
	public void setMy_post(ArrayList<AllOfPostVO> my_post) {
		this.my_post = my_post;
	}
	public String getP_count() {
		return p_count;
	}
	public void setP_count(String p_count) {
		this.p_count = p_count;
	}
	public ArrayList<MyCommentVO> getMy_comment() {
		return my_comment;
	}
	public void setMy_comment(ArrayList<MyCommentVO> my_comment) {
		this.my_comment = my_comment;
	}
	public String getC_count() {
		return c_count;
	}
	public void setC_count(String c_count) {
		this.c_count = c_count;
	}
	public ArrayList<AllOfPostVO> getMy_jjim() {
		return my_jjim;
	}
	public void setMy_jjim(ArrayList<AllOfPostVO> my_jjim) {
		this.my_jjim = my_jjim;
	}
	public String getJ_count() {
		return j_count;
	}
	public void setJ_count(String j_count) {
		this.j_count = j_count;
	}
	public ArrayList<MyNoticeVO> getMy_notice() {
		return my_notice;
	}
	public void setMy_notice(ArrayList<MyNoticeVO> my_notice) {
		this.my_notice = my_notice;
	}
	public String getN_count() {
		return n_count;
	}
	public void setN_count(String n_count) {
		this.n_count = n_count;
	}
	
	
	
	
	
	
	

}
