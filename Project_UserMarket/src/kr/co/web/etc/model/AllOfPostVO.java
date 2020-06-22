package kr.co.web.etc.model;

public class AllOfPostVO {
	private String post_id; // 게시글 번호
//	private String board_id; // 게시판 번호
//	private String user_id; // 작성자 ID
	private String title; // 제목
//	private String content; // 내용i
	private String w_date; // 작성일
	private String hit; // 조회수
	
//	private String ctg_id; // 카테고리 번호
	private String city; // 판매지역
	private String s_type; // 거래방식
	private String price; // 판매가격
	private String p; // 진행 상태
	
	private String img_path; // 대표 이미지
	
	private String c_num; // 댓글 수
	private String j_num; // 찜수
	private String jjim_id; // 찜목록에서 쓸 찜번호
	
	public AllOfPostVO() {}

	public AllOfPostVO(String post_id, String title, String w_date, String hit, String city, String s_type,
			String price, String p, String img_path, String c_num, String j_num, String jjim_id) {
		super();
		this.post_id = post_id;
		this.title = title;
		this.w_date = w_date;
		this.hit = hit;
		this.city = city;
		this.s_type = s_type;
		this.price = price;
		this.p = p;
		this.img_path = img_path;
		this.c_num = c_num;
		this.j_num = j_num;
		this.jjim_id = jjim_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getW_date() {
		return w_date;
	}

	public void setW_date(String w_date) {
		this.w_date = w_date;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getS_type() {
		return s_type;
	}

	public void setS_type(String s_type) {
		this.s_type = s_type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public String getJ_num() {
		return j_num;
	}

	public void setJ_num(String j_num) {
		this.j_num = j_num;
	}

	public String getJjim_id() {
		return jjim_id;
	}

	public void setJjim_id(String jjim_id) {
		this.jjim_id = jjim_id;
	}

	

	

	

}
