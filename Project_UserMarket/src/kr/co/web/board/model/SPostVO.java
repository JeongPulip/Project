package kr.co.web.board.model;

/*
CREATE TABLE s_post
(
   post_id number NOT NULL,
   ctg_id char(2),
   city_id char(5),
   s_type char(1),
   progress char(1),
   price number,
   PRIMARY KEY (post_id)
);
*/

public class SPostVO {
	
	private Integer postId;
	private String ctgId;	
	private String cityId;
	private String sType;
	private String progress;
	private Integer price;
	
	public SPostVO() {
		// TODO Auto-generated constructor stub
	}
	
	public SPostVO(Integer postId, String ctgId, String cityId, String sType, String progress, Integer price) {
		super();
		this.postId = postId;
		this.ctgId = ctgId;
		this.cityId = cityId;
		this.sType = sType;
		this.progress = progress;
		this.price = price;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
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
	
	
	
	
}
