package kr.co.web.board.model;

import java.sql.Date;

public class PostAllVO {

	/*
		<article onclick="location.href='content.board?postId=${article.postId}'">
			<div class="info">
				<span class="list_Num">${article.postId}</span> 
				<span class="list_State">${progress}</span>
			</div>
			<img src="../img/a.jpg<%-- ${이미지경로} --%>" alt="이미지" />
			<div class="ListText">
				<div class="list_Title">${article.title}</div>
				<div class="list_Location">>${cityId}</div>
				<div class="list_Price">>${price}</div>
			</div>
		</article> 
	 */
	
	//post
	private Integer postId;
//	private String boardId;	//게시판 분류(삽니다글, 팝니다글, 공지사항)
//	private String userId;
	private String title;
//	private String content;
	private Date wDate;
	private Integer hit;
	
	//s_post
	private String ctgId;	
	private String cityId;
	private String sType;
	private String progress;
	private Integer price;
	
	//image
	private Integer imgId;
	private String imgPath;	//대표 이미지 => searchList에서 1개 이미지만 출력
	
	
	
	public PostAllVO() {
		// TODO Auto-generated constructor stub
	}



	public PostAllVO(Integer postId, String title, Date wDate, Integer hit, String ctgId, String cityId, String sType,
			String progress, Integer price, Integer imgId, String imgPath) {
		super();
		this.postId = postId;
		this.title = title;
		this.wDate = wDate;
		this.hit = hit;
		this.ctgId = ctgId;
		this.cityId = cityId;
		this.sType = sType;
		this.progress = progress;
		this.price = price;
		this.imgId = imgId;
		this.imgPath = imgPath;
	}



	public Integer getPostId() {
		return postId;
	}



	public void setPostId(Integer postId) {
		this.postId = postId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
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



	public String getCtgId() {
		return ctgId;
	}



	public void setCtgId(String ctgId) {
		this.ctgId = ctgId;
	}



	public String getCityId() {
		return cityId;
	}



	public void setCityId(String cityId) {
		this.cityId = cityId;
	}



	public String getsType() {
		return sType;
	}



	public void setsType(String sType) {
		this.sType = sType;
	}



	public String getProgress() {
		return progress;
	}



	public void setProgress(String progress) {
		this.progress = progress;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public Integer getImgId() {
		return imgId;
	}



	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}



	public String getImgPath() {
		return imgPath;
	}



	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}



	
	
	
	
	
}
