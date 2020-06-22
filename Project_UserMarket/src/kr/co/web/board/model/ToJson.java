package kr.co.web.board.model;

import java.util.List;

public class ToJson {
	
	List<PostAll2VO> listPa2VO;
	Paging paging;
	String search;

	public ToJson() {
		// TODO Auto-generated constructor stub
	}

	public ToJson(List<PostAll2VO> listPa2VO, Paging paging, String search) {
		super();
		this.listPa2VO = listPa2VO;
		this.paging = paging;
		this.search = search;
	}

	public List<PostAll2VO> getListPa2VO() {
		return listPa2VO;
	}

	public void setListPa2VO(List<PostAll2VO> listPa2VO) {
		this.listPa2VO = listPa2VO;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	
	
	
}
