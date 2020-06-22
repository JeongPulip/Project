package kr.co.web.etc.model;

import java.util.ArrayList;

public class CityListVO {
	private String region_id;
	private String region;
	private ArrayList<CityVO> clist;
	
	public CityListVO() {}
	public CityListVO(String region_id, String region, ArrayList<CityVO> clist) {
		super();
		this.region_id = region_id;
		this.region = region;
		this.clist = clist;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public ArrayList<CityVO> getClist() {
		return clist;
	}
	public void setClist(ArrayList<CityVO> clist) {
		this.clist = clist;
	}

}
