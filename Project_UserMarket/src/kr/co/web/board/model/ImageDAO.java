package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webprojectDBConn.DBConn;

public class ImageDAO implements IImageDAO{

	//전역
		private Connection con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		private static ImageDAO imageDAO = new ImageDAO();
		
		public ImageDAO() {
		
			con = DBConn.getConnection();

		}//end constr
		
		public static ImageDAO getInstance() {
			
			if(imageDAO == null) {
				imageDAO = new ImageDAO();
			}
			return imageDAO;
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
	
		// 이미지 관련
		@Override
		public boolean registImg(String imgPath) {
			
//			String sql = "INSERT INTO image(post_id, img_path) VALUES(SEQ_post_post_id.CURRVAL, ?)";
			String sql = "INSERT INTO image(post_id, img_path) VALUES(( " + 
								"SELECT last_number-1 " + 
								"FROM USER_SEQUENCES " + 
								"WHERE SEQUENCE_NAME = 'SEQ_POST_POST_ID'), ?" + 
							")";
					
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, imgPath);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("INSERT image EXCEPTION");
				return false;
			}
			
			return true;
		}

		@Override
		public List<String> searchImgPath(int postId) {
			
			List<String> imgPathList = new ArrayList<>();
			String sql = "SELECT img_path FROM image WHERE post_id = ? ORDER BY img_id";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, postId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					String imgPath = rs.getString("img_path");
					imgPathList.add(imgPath);
				} 
			} catch (Exception e) {
				System.out.println("SEARCH IMG EXCEPTION");
				e.printStackTrace();
			}
			
			return imgPathList;
		}

		@Override
		public boolean deleteImg(int postId) {

			String sql = "DELETE FROM image WHERE post_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, postId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("deleteImg EXCEPTION");
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		public boolean updateImg(int postId, String imgPath) {

			String sql = "INSERT INTO image(post_id, img_path) VALUES(?, ?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, postId);
				pstmt.setString(2, imgPath);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("updateImg EXCEPTION");
				return false;
			}
			
			return true;
		}

		@Override
		public List<ImageVO> selectImage(int postId) {

			List<ImageVO> listImageVO = new ArrayList<ImageVO>();
			String sql = "SELECT * FROM image WHERE post_id = ? ORDER BY img_id";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, postId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {

					ImageVO imageVO = new ImageVO(
							rs.getInt("img_id"),
							rs.getInt("post_id"),
							rs.getString("img_path")	
							);
					listImageVO.add(imageVO);

				} 
			} catch (Exception e) {
				System.out.println("selectSPost Exception");
				e.printStackTrace();
			}
			return listImageVO;
			
			
		}

}
