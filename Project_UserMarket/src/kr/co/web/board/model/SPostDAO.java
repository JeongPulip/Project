package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webprojectDBConn.DBConn;

public class SPostDAO implements ISPostDAO{
	
	//전역
		private Connection con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		private static SPostDAO sPostDAO = new SPostDAO();
		
		public SPostDAO() {
		
				con = DBConn.getConnection();

		}//end constr
		
		public static SPostDAO getInstance() {
			
			if(sPostDAO == null) {
				sPostDAO = new SPostDAO();
			}
			return sPostDAO;
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
		////////////////////////////////////////////
	@Override
	public boolean registSPost(String ctgId, String cityId, String sType, String progress, Integer price) {

//		String sql = "INSERT INTO s_post VALUES(SEQ_post_post_id.CURRVAL,?,?,?,?,?)";
		String sql = "INSERT INTO s_post VALUES((" + 
							"SELECT last_number-1 " + 
							"FROM USER_SEQUENCES " + 
							"WHERE SEQUENCE_NAME = 'SEQ_POST_POST_ID'),?,?,?,?,?" + 
						")";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, ctgId);
			pstmt.setString(2, cityId);
			pstmt.setString(3, sType);
			pstmt.setString(4, progress);
			pstmt.setInt(5, price);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("registSPost EXCEPTION");
			e.printStackTrace();
			return false;

		}
		
		return true;
	}

	@Override
	public boolean deleteSPost(int postId) {

		String sql = "DELETE FROM s_post WHERE post_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteSPost Exception");
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	@Override
	public boolean updateSPost(int postId, String ctgId, String cityId, String sType, String progress, Integer price) {

		String sql = "INSERT INTO s_post VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.setString(2, ctgId);
			pstmt.setString(3, cityId);
			pstmt.setString(4, sType);
			pstmt.setString(5, progress);
			pstmt.setInt(6, price);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("updateSPost EXCEPTION");
			return false;
		}
		
		return true;
		
	}

	@Override
	public SPostVO selectSPost(int postId) {
		
		SPostVO sPostVO = null;
		String sql = "SELECT * FROM s_post WHERE post_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				sPostVO = new SPostVO(
						
						rs.getInt("post_id"),
						rs.getString("ctg_id"),
						rs.getString("city_id"),
						rs.getString("s_type"),
						rs.getString("progress"),
						rs.getInt("price")

						);

			} 
		} catch (Exception e) {
			System.out.println("selectSPost Exception");
			e.printStackTrace();
		}
		return sPostVO;
		
	}
	
	
}
