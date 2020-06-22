package kr.co.web.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import webprojectDBConn.DBConn;

public class CommentDAO {
	private CommentDAO(){}

	
	public static CommentDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final CommentDAO INSTANCE = new CommentDAO();
	}
	

	
	
	public ArrayList<CommentVO> insertComment // 댓글 등록하기
	(String post_id, String user_id, String secret, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into comments"
				+ "(post_id, user_id, secret, content, w_date)"
				+ " values(?, ?, ?, ?, sysdate)";
		
		ArrayList<CommentVO> arrcmt = new ArrayList<CommentVO>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setString(2, user_id);
			pstmt.setString(3, secret);
			pstmt.setString(4, content);
		//	pstmt.setString(4, content.replace("&nbsp;", " "));
			pstmt.executeUpdate(); // 댓글 정보를 DB에 입력하고
			
			arrcmt = getCommentInfo(post_id); // 갱신된 DB의 정보를 담는다
		} catch (SQLException e) {
			System.out.println("댓글 정보 입력 실패..");
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrcmt;
	}
	
	
	
	
	
	
	
	public ArrayList<CommentVO> insertReComment // 대댓글 등록하기
	(String post_id, String parent_id, String user_id, String secret, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into comments"
				+ "(post_id, parent_id, user_id, secret, content, w_date)"
				+ " values(?, ?, ?, ?, ?, sysdate)";
		
		ArrayList<CommentVO> arrcmt = new ArrayList<CommentVO>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setInt(2, Integer.parseInt(parent_id));
			pstmt.setString(3, user_id);
			pstmt.setString(4, secret);
			pstmt.setString(5, content);
	//		pstmt.setString(5, content.replace("&nbsp;", " "));
			pstmt.executeUpdate(); // 댓글 정보를 DB에 입력하고
			
			arrcmt = getCommentInfo(post_id); // 갱신된 DB의 정보를 담는다
		} catch (SQLException e) {
			System.out.println("대댓글 정보 입력 실패..");
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrcmt;
	}
	
	
	
	
	
	
	
	
	public ArrayList<CommentVO> getCommentInfo(String post_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select c.*, m.nick from comments c join members m"
				+ " on c.user_id = m.id where post_id = ? order by cmt_id";
		
		ArrayList<CommentVO> arrcmt = new ArrayList<CommentVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. hh:mm");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				arrcmt.add(new CommentVO(
						
						"" + rs.getInt("cmt_id"),
						post_id,
						"" + rs.getInt("parent_id"),
						rs.getString("user_id"),
						rs.getString("nick"),
						rs.getString("secret"),
						rs.getString("content").replace(" ", "&nbsp;"),
						sd.format(rs.getTimestamp("w_date"))
						
						));
			}
		} catch (SQLException e) {
			System.out.println("댓글 정보 받아오기 실패..");
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrcmt;
	}
	
	
	
	
	
	public ArrayList<CommentVO> deleteComment(String post_id, String cmt_id) { // 댓글 삭제하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from comments where cmt_id = ?";
		
		ArrayList<CommentVO> arrcmt = new ArrayList<CommentVO>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(cmt_id));
			pstmt.executeUpdate();
			
			arrcmt = getCommentInfo(post_id); // 갱신된 DB의 정보를 담는다
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrcmt;
	}
	
	
	
	
	public ArrayList<CommentVO> updateComment(String post_id, String cmt_id, String content, String secret) { // 댓글 수정하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update comments set content = ?, secret = ? where cmt_id = ?";
		
		ArrayList<CommentVO> arrcmt = new ArrayList<CommentVO>();
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.replace("&nbsp;", " "));
			pstmt.setString(2, secret);
			pstmt.setInt(3, Integer.parseInt(cmt_id));
			pstmt.executeUpdate();
			
			arrcmt = getCommentInfo(post_id); // 갱신된 DB의 정보를 담는다
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arrcmt;
	}
	

	
	
	public CommentVO getComment(String cmt_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from comments where cmt_id = ?";
		
		CommentVO vo = new CommentVO();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. hh:mm");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(cmt_id));
			rs = pstmt.executeQuery();
			
			rs.next();
				vo = (new CommentVO(
						
						cmt_id,
						"" + rs.getInt("post_id"),
						"" + rs.getInt("parent_id"),
						rs.getString("user_id"),
						null,
						rs.getString("secret"),
						rs.getString("content").replace(" ", "&nbsp;"),
						sd.format(rs.getTimestamp("w_date"))
						
						));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return vo;
	}
	
	
	
	public int getCNum(String post_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from comments where post_id = ?";
		
		int num = 0;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			rs = pstmt.executeQuery();
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return num;
	}

	
	
	
	
	
}
