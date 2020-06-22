package kr.co.web.board.model;

import java.sql.Date;

/*
CREATE TABLE post
(
   post_id number NOT NULL,	
   board_id char(2),
   id varchar2(20),
   title varchar2(100),
   content varchar2(4000),
   w_date date,
   hit number,
   PRIMARY KEY (post_id)
);


//데이터 샘플

INSERT INTO members VALUES('abc1234','abc1234','admin','관리자',TO_DATE('20200101','YYYYMMDD'),'M','01012345678','abc1234@xxxxx.xxx','11010','prf_path',SYSDATE);

INSERT INTO post(board_id, user_id, title, content, w_date, hit) VALUES('a1','abc1234','제목2','내용2',SYSDATE,111);

INSERT INTO post(board_id, user_id, title, content, w_date, hit) VALUES('a1','abc1234','제목','내용',SYSDATE,222);

*/
public class PostVO {

	private Integer postId;
	private String boardId;	//게시판 분류(삽니다글, 팝니다글, 공지사항)
	private String userId;
	private String title;
	private String content;
	private Date wDate;
	private Integer hit;
	
	
	public PostVO() {
		// TODO Auto-generated constructor stub
	}


	public PostVO(Integer postId, String boardId, String userId, String title, String content, Date wDate, Integer hit) {
		super();
		this.postId = postId;
		this.boardId = boardId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.wDate = wDate;
		this.hit = hit;
		
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public String getBoardId() {
		return boardId;
	}


	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getwDate() {
		return wDate;
	}


	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}


	public Integer getHit() {
		return hit;
	}


	public void setHit(Integer hit) {
		this.hit = hit;
	}



	
	
	
}

