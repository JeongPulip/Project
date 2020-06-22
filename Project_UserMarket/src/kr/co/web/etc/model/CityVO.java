package kr.co.web.etc.model;

public class CityVO {
	
	private String city_id;
	private String city;
	private String region_id;
	private String region;
	
	public CityVO() {}
	public CityVO(String city_id, String city, String region_id, String region) {
		super();
		this.city_id = city_id;
		this.city = city;
		this.region_id = region_id;
		this.region = region;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	
}
