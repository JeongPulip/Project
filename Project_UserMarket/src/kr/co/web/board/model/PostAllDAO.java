package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webprojectDBConn.DBConn;

public class PostAllDAO {

			//전역
			private Connection con;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			private static PostAllDAO postAllDAO = new PostAllDAO();
			
			public PostAllDAO() {
			
				con = DBConn.getConnection();

			}//end constr
			
			public static PostAllDAO getInstance() {
				
				if(postAllDAO == null) {
					postAllDAO = new PostAllDAO();
				}
				return postAllDAO;
			}
			
			public void pstmtClose() throws SQLException {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			
			public void getAllInfoClose() throws SQLException {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.commit();
					con.close();
				}
			}
			
		
			public PostAllVO PostAll(int postId) {
				
				PostAllVO article = null;
				String sql = "SELECT p.post_id, p.title, s.ctg_id, s.city_id, s.s_type, s.progress, s.price, i.img_id, i.img_path " + 
								"FROM post p, s_post s, image i " + 
								"WHERE p.post_id = s.post_id " + 
								"AND s.post_id = i.post_id " + 
								"AND i.img_id = (" + 
								"        SELECT min(img_id) " + 
								"        FROM image " + 
								"        WHERE post_id = ?" + 
								")";
				
				
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, postId);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {

					/*	article = new PostAllVO(
								
								rs.getInt("post_id"),
								rs.getString("title"),
								rs.getString("ctg_id"),
								rs.getString("city_id"),
								rs.getString("s_type"),
								rs.getString("progress"),
								rs.getInt("price"),
								rs.getInt("img_id"),
								rs.getString("img_path")
								);
*/
					} 
				} catch (Exception e) {
					System.out.println("LISTPOSTALL Exception");
					e.printStackTrace();
				}
				return article;

			}
	
}
