package kr.co.web.etc.model;

public class CategoryVO {
		private String ctg_id;
		private String ctg_name;
		
		public CategoryVO() {}
		public CategoryVO(String ctg_id, String ctg_name) {
			super();
			this.ctg_id = ctg_id;
			this.ctg_name = ctg_name;
		}
		public String getCtg_id() {
			return ctg_id;
		}
		public void setCtg_id(String ctg_id) {
			this.ctg_id = ctg_id;
		}
		public String getCtg_name() {
			return ctg_name;
		}
		public void setCtg_name(String ctg_name) {
			this.ctg_name = ctg_name;
		}
}
