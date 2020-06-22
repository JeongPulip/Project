package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;
import webprojectDBConn.DBConn;

public class PostAll2DAO {

	//전역
		private Connection con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		private static PostAll2DAO postAll2DAO = new PostAll2DAO();
		
		public PostAll2DAO() {
		
			con = DBConn.getConnection();

		}//end constr
		
		public static PostAll2DAO getInstance() {
			
			if(postAll2DAO == null) {
				postAll2DAO = new PostAll2DAO();
			}
			return postAll2DAO;
		}
		
		public void pstmtClose() throws SQLException {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		public void getAllInfoClose() {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.commit();
					con.close();
				} 
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		////////////////////////////////////////////
		
		
		public CityVO getCityRegion(String cityId){
			
			String sql = "SELECT region, city, region_id FROM city WHERE city_id = ?";
			CityVO vo = null;
			
			try {
				con = DBConn.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cityId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					String city = rs.getString("city");
					String region = rs.getString("region");
					String regionId = rs.getString("region_id");
					
					if (region.length() == 4) {//지역(시/도) 이름이 4글자면 첫글자랑 세번째 글자만으로 표현
						region = region.substring(0, 1) + region.substring(2, 3);
					} else {// 나머지는 앞에 2글자만으로 표현
						region = region.substring(0, 2);
					}
					
					vo = new CityVO(cityId, city, regionId, region);
				}
				
					
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("getCityRegion EXCEPTION");
			} finally {
				getAllInfoClose();
			}// finally-end
			
			return vo;
		}// getCity-end
		
		
		public CategoryVO getCtgName(String ctg_id) {
			
			CategoryVO ctgVO = null;
			String sql = "SELECT ctg_name FROM category WHERE ctg_id = ?";
			
			try {
				con = DBConn.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ctg_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String ctg_name = rs.getString("ctg_name");
					ctgVO = new CategoryVO(ctg_id, ctg_name);
					
				}// if-end
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				getAllInfoClose();
			}// finally-end
			
			return ctgVO;
		}// getCtgName-end
		
		
		public String idToNick(String userId) {
			
			String sql = "SELECT nick FROM members WHERE id = ?";
			String nick = null;
			try {
				con = DBConn.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					nick = rs.getString("nick");
				}// if-end
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("idToNick EXCEPTION");
			} finally {
				getAllInfoClose();
			}// finally-end
			
			return nick;
		}// getCtgName-end
		
		public List<Integer> searchPostId(String cityId, String ctgId, String sType, String search) {
			
			String sql;
			if(cityId.equals("ALL")) {
				sql = "SELECT DISTINCT s.post_id "
							+ "FROM s_post s, post p "
							+ "WHERE s.ctg_id like ? "
							+ "AND s.s_type like ? "
							+ "AND p.title like ? "
							+ "AND p.board_id = 'a1' "
							+ "ORDER BY post_id DESC";
			}else {
				sql = "SELECT DISTINCT s.post_id "
						+ "FROM s_post s, post p "
						+ "WHERE s.city_id IN(" + cityId + ") "
						+ "AND s.ctg_id like ? "
						+ "AND s.s_type like ? "
						+ "AND p.title like ? "
						+ "AND p.board_id = 'a1' "
						+ "ORDER BY post_id DESC";
			}
			
			
			List<Integer> postIdList = new ArrayList<Integer>();
			
			try {
				con = DBConn.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ctgId);
				pstmt.setString(2, sType);
				pstmt.setString(3, "%"+search+"%");

				rs = pstmt.executeQuery();

				while(rs.next()) {
					
					int postId = rs.getInt("post_id");
//					System.out.println("postId : " + postId);
					postIdList.add(postId);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("searchPostId EXCEPTION");
			} finally {
				getAllInfoClose();
			}// finally-end
			
			return postIdList;
			
		}
		
		
//		public PostAllVO PostAll(int postId) {
//			
//			PostAllVO article = null;
//			String sql = "SELECT p.post_id, p.title, s.ctg_id, s.city_id, s.s_type, s.progress, s.price, i.img_id, i.img_path " + 
//							"FROM post p, s_post s, image i " + 
//							"WHERE p.post_id = s.post_id " + 
//							"AND s.post_id = i.post_id " + 
//							"AND i.img_id = (" + 
//							"        SELECT min(img_id) " + 
//							"        FROM image " + 
//							"        WHERE post_id = ?" + 
//							")";
//			
//			
//			
//			try {
//				pstmt = con.prepareStatement(sql);
//				pstmt.setInt(1, postId);
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//
//					article = new PostAllVO(
//							
//							rs.getInt("post_id"),
//							rs.getString("title"),
//							rs.getString("ctg_id"),
//							rs.getString("city_id"),
//							rs.getString("s_type"),
//							rs.getString("progress"),
//							rs.getInt("price"),
//							rs.getInt("img_id"),
//							rs.getString("img_path")
//							);
//
//				} 
//			} catch (Exception e) {
//				System.out.println("LISTPOSTALL Exception");
//				e.printStackTrace();
//			}
//			return article;
//
//		}

}
