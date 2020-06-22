package kr.co.web.etc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webprojectDBConn.DBConn;

public class JJimDAO {
	private JJimDAO(){}

	
	public static JJimDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final JJimDAO INSTANCE = new JJimDAO();
	}
	
	
	
	
	public boolean checkJJim(String user_id, String post_id) { // 이미 찜목록에 있나 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from jjim"
					+ " where user_id = ? and post_id = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, Integer.parseInt(post_id));
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			if(rs.getInt(1) != 0) { // 검색결과가 있으면
				return true; // (이미 찜한 거다)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return false; // 검색결과가 없다 (찜 안했다)
	}// checkID-end
		
		
		
	
	
	public boolean doJJim(String user_id, String post_id) { // 찜하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into jjim(user_id, post_id) values(?, ?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, Integer.parseInt(post_id));
			pstmt.executeUpdate(); // 찜한 정보를 DB에 입력하고
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true; //찜하기 완료
	}
	
	
	
	public boolean undoJJim(String user_id, String post_id) { // 찜취소
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from jjim where user_id = ? and post_id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, Integer.parseInt(post_id));
			pstmt.executeUpdate(); // 찜한 정보를 DB에 입력하고
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true; // 찜해제 완료
	}
	
	
	
	public ArrayList<JJimVO> getMyJJim(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from jjim where user_id = ?";
		ArrayList<JJimVO> arrjjim = new ArrayList<JJimVO>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				arrjjim.add(new JJimVO(
					
						"" + rs.getInt("jjim_id"),
						rs.getString("user_id"),
						"" + rs.getInt("post_id")
					
					));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrjjim; // 회원이 찜한 목록 반환
	}
	
	
	
	
	public int getJJimNum(String post_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from jjim where post_id = ?";
		int jjim_num = 0;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			jjim_num = rs.getInt(1);
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return jjim_num; // 해당 게시물의 찜 횟수 반환
	}
	
	
	
	
	
	

}
