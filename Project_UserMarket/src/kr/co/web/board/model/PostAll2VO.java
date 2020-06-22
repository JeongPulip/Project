package kr.co.web.board.model;

import java.util.List;

import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;

public class PostAll2VO {
	
	
	private PostVO postVO;
	private SPostVO sPostVO;
	
	private CityVO cityVO;				//게시글에 표시되는 한 CITY 만의 정보
	private CategoryVO categoryVO;		//게시글에 표시되는 한 CATEGORY 만의 정보
	private List<ImageVO> listImageVO;	//게시글이 가지는 다수의 IMAGE 정보 

	public PostAll2VO() {
		// TODO Auto-generated constructor stub
	}

	public PostAll2VO(PostVO postVO, SPostVO sPostVO, CityVO cityVO, CategoryVO categoryVO, List<ImageVO> listImageVO) {
		super();
		this.postVO = postVO;
		this.sPostVO = sPostVO;
		this.cityVO = cityVO;
		this.categoryVO = categoryVO;
		this.listImageVO = listImageVO;
	}

	public PostVO getPostVO() {
		return postVO;
	}

	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}

	public SPostVO getsPostVO() {
		return sPostVO;
	}

	public void setsPostVO(SPostVO sPostVO) {
		this.sPostVO = sPostVO;
	}

	public CityVO getCityVO() {
		return cityVO;
	}

	public void setCityVO(CityVO cityVO) {
		this.cityVO = cityVO;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	public List<ImageVO> getListImageVO() {
		return listImageVO;
	}

	public void setListImageVO(List<ImageVO> listImageVO) {
		this.listImageVO = listImageVO;
	}



	
	
	
	
	
	
}
