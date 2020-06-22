package kr.co.web.etc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kr.co.web.comment.model.CommentDAO;
import webprojectDBConn.DBConn;

public class MyListDAO {
	private MyListDAO(){}

	
	public static MyListDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final MyListDAO INSTANCE = new MyListDAO();
	}
	
	
	public MyListVO getMyList(String user_id) {
		ArrayList<AllOfPostVO> myPostList = getMyPost(user_id);
		ArrayList<MyCommentVO> myCommentList = getMyComment(user_id);
		ArrayList<AllOfPostVO> myJJimList = getMyJJim(user_id);
		ArrayList<MyNoticeVO> myNoticeList = getMyNotice(user_id);
		
		MyListVO vo = new MyListVO(
				
				myPostList,
				"" + myPostList.size(),
				myCommentList,
				"" + myCommentList.size(),
				myJJimList,
				"" + myJJimList.size(),
				myNoticeList,
				"" + myNoticeList.size()
				
				);
		
		return vo;
	}
	
	
	
	public ArrayList<AllOfPostVO> getMyPost(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = 
				"select * from post p join s_post sp on p.post_id = sp.post_id"
				+ " join city on sp.city_id = city.city_id"
//				+ " join image i on p.post_id = i.post_id"
				+ " where p.user_id = ? order by p.post_id desc";
		
		ArrayList<AllOfPostVO> arr = new ArrayList<AllOfPostVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
		DecimalFormat f = new DecimalFormat("###,###");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String post_id = "" + rs.getInt("post_id");
				
				String region = rs.getString("region");
				if(region.length() == 4){//지역(시/도) 이름이 4글자면 첫글자랑 세번째 글자만으로 표현
				   	 region = region.substring(0,1) + region.substring(2, 3);
				   } else {// 나머지는 앞에 2글자만으로 표현
					 region = region.substring(0,2);
				   }
				String city = region + " " + rs.getString("city");
				
				String s_type = rs.getString("s_type");
				if(s_type.equals("0")) {
					s_type = "무관";
				} else if(s_type.equals("1")) {
					s_type = "직거래";
				} else {
					s_type = "택배 거래";
				}
				
				String price = f.format(rs.getInt("price"));
				
				String progress = rs.getString("progress");
				if(progress.equals("0")) {
					progress = "판매(구매) 중";
				} else if(progress.equals("1")) {
					progress = "거래 진행 중";
				} else {
					progress = "거래 완료";
				}
				
				String comment_num =
				"" + CommentDAO.getInstance().getCNum(post_id);
					arr.add(new AllOfPostVO(
						
						post_id,
						rs.getString("title").replace(" ", "&nbsp;"),
						sd.format(rs.getTimestamp("w_date")),
						"" + rs.getInt("hit"),
						
						city,
						s_type,
						price,
						progress,
						
			//			rs.getString("img_path"),
						null,
						
						comment_num,
						"" + JJimDAO.getInstance().getJJimNum(post_id),
						null
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arr;
	}
	
	
	
	
	
	
	
	
	
	public ArrayList<MyCommentVO> getMyComment(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from comments c join post p on"
				+ " c.post_id = p.post_id where c.user_id = ?"
				+ " order by cmt_id desc";
		
		ArrayList<MyCommentVO> arr = new ArrayList<MyCommentVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. hh:mm");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String content = rs.getString("content");
				if(content.length() > 15) {
					content = content.substring(0, 15) + "..";
				}
				
				String post_id = "" + rs.getInt("post_id");
				
				String comment_num =
						"" + CommentDAO.getInstance().getCNum(post_id);
					arr.add(new MyCommentVO(
						
						post_id,
						"" + rs.getInt("cmt_id"),
						content.replace(" ", "&nbsp;"),
						sd.format(rs.getTimestamp("w_date")),
						rs.getString("title").replace(" ", "&nbsp;"),
						comment_num
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arr;
	}
	
	
	
	
	
	
	

	public ArrayList<AllOfPostVO> getMyJJim(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from jjim j join post p on j.post_id = p.post_id"
				+ " join s_post sp on p.post_id = sp.post_id"
				+ " join city on sp.city_id = city.city_id"
		//		+ " join image i on p.post_id = i.post_id"
				+ " where j.user_id = ? order by jjim_id desc";
		
		ArrayList<AllOfPostVO> arr = new ArrayList<AllOfPostVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
		DecimalFormat f = new DecimalFormat("###,###");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String post_id = "" + rs.getInt("post_id");
				
				String region = rs.getString("region");
				if(region.length() == 4){//지역(시/도) 이름이 4글자면 첫글자랑 세번째 글자만으로 표현
				   	 region = region.substring(0,1) + region.substring(2, 3);
				   } else {// 나머지는 앞에 2글자만으로 표현
					 region = region.substring(0,2);
				   }
				String city = region + " " + rs.getString("city");
				
				String s_type = rs.getString("s_type");
				if(s_type.equals("0")) {
					s_type = "무관";
				} else if(s_type.equals("1")) {
					s_type = "직거래";
				} else {
					s_type = "택배 거래";
				}
				
				String price = f.format(rs.getInt("price"));
				
				String progress = rs.getString("progress");
				if(progress.equals("0")) {
					progress = "판매(구매) 중";
				} else if(progress.equals("1")) {
					progress = "거래 진행 중";
				} else {
					progress = "거래 완료";
				}
				
				String comment_num =
						"" + CommentDAO.getInstance().getCNum(post_id);
					arr.add(new AllOfPostVO(
						
						post_id,
						rs.getString("title").replace(" ", "&nbsp;"),
						sd.format(rs.getTimestamp("w_date")),
						"" + rs.getInt("hit"),
						
						city,
						s_type,
						price,
						progress,
						
			//			rs.getString("img_path"),
						null,
						
						comment_num,
						"" + JJimDAO.getInstance().getJJimNum(post_id),
						"" + rs.getInt("jjim_id")
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arr;
	}
	
	
	
	
	
	public ArrayList<MyNoticeVO> getMyNotice(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select post_id, cmt_id, n_content,"
				+ " count(*) as c, max(n_time) as last_ntime from notice"
				+ " where user_id = ? group by post_id, cmt_id, n_content"
				+ " order by last_ntime desc";
		
		ArrayList<MyNoticeVO> arr = new ArrayList<MyNoticeVO>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. hh:mm");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
					String post_id = "" + rs.getInt("post_id");
					String cmt_id = "" + rs.getInt("cmt_id");
					
					String comment_num =
						"" + CommentDAO.getInstance().getCNum(post_id);
					
					String content = rs.getString("n_content");
					if(cmt_id.equals("0")) { // 게시글 - 댓글
						content = "게시글 - " + content
									+ " [" + comment_num + "]에 ";
					} else {
						content = "댓글 - [" + content + "]에 ";
					}
				
					arr.add(new MyNoticeVO(
						
						post_id,
						cmt_id,
						content.replace(" ", "&nbsp;"),
						comment_num,
						"" + rs.getInt("c"),
						sd.format(rs.getTimestamp("last_ntime"))
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return arr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
