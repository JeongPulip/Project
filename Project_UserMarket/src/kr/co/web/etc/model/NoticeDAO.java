package kr.co.web.etc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webprojectDBConn.DBConn;

public class NoticeDAO {
	private NoticeDAO(){}

	
	public static NoticeDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final NoticeDAO INSTANCE = new NoticeDAO();
	}

	
	
	
	
	
	
	
	
	
	
	
	public boolean insertNoticeP
	(String post_id, String user_id, String n_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into notice(post_id, user_id, n_content, n_time)"
				+ " values(?, ?, ?, sysdate)";
		
		String content = n_content.replace("&nbsp;", " ");
		if(content.length() > 15) {
			content = content.substring(0, 15) + "..";
		}
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setString(2, user_id);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true;
	}
	
	
	public boolean insertNoticeC
	(String post_id, String cmt_id, String user_id, String n_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into notice(post_id, cmt_id, user_id, n_content, n_time)"
				+ " values(?, ?, ?, ?, sysdate)";
		
		String content = n_content.replace("&nbsp;", " ");
		if(content.length() > 15) {
			content = content.substring(0, 15) + "..";
		}
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setInt(2, Integer.parseInt(cmt_id));
			pstmt.setString(3, user_id);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true;
	}
	
	
	
	
	
	public boolean deletetNoticeP(String post_id, String user_id) { // 게시글-댓글 알림 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from notice where post_id = ? and user_id = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true;
	}
	
	
	
	public boolean deletetNoticeC(String cmt_id, String user_id) { // 댓글-댓글 알림 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from notice where cmt_id = ? and user_id = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(cmt_id));
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true;
	}
	
	
	
	
	public boolean deletetNoticeAll(String user_id) { // 전체 알림 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from notice where user_id = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return true;
	}
	
	
	
	
	
	
	/*
	public ArrayList<NoticeVO> getMyNotice(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from notice where user_id = ?";
		ArrayList<NoticeVO> arrn = new ArrayList<NoticeVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. hh:mm");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				arrn.add(new NoticeVO(
						
						"" + rs.getInt("notice_id"),
						"" + rs.getInt("post_id"),
						"" + rs.getInt("cmt_id"),
						user_id,
						"" + rs.getString("n_content"),
						sd.format(rs.getTimestamp("n_time"))
					
					));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrn; // 확인하지 않은 알림
	}
	*/
	
	
	
	
	
	
}
