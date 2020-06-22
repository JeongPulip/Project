package kr.co.web.board.model;

/*
CREATE TABLE image(
   img_id number NOT NULL,
   post_id number,
   img_path varchar2(50),
   PRIMARY KEY (img_id)
);*/

public class ImageVO {
	
	private Integer imgId;
	private Integer postId;
	private String imgPath;
	
	public ImageVO() {
		// TODO Auto-generated constructor stub
	}

	public ImageVO(Integer imgId, Integer postId, String imgPath) {
		super();
		this.imgId = imgId;
		this.postId = postId;
		this.imgPath = imgPath;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	
}


