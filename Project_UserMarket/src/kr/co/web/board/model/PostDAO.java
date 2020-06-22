package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webprojectDBConn.DBConn;


public class PostDAO implements IPostDAO {

	//CRUD 작성
	
	//전역
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private static PostDAO postDAO = new PostDAO();
	
	private PostDAO() {
	
		con = DBConn.getConnection();

	}//end constr
	
	public static PostDAO getInstance() {
		
		if(postDAO == null) {
			postDAO = new PostDAO();
		}
		return postDAO;
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

//	@Override
	public boolean regist(String userId, String title, String content) {
		// INSERT INTO post(board_id, user_id, title, content, w_date, hit) VALUES('a1','abc1234','제목2','내용2',SYSDATE,111);
		// a1은 거래글, b2를 공지 글
		String sql = "INSERT INTO post(board_id, user_id, title, content, w_date, hit) VALUES('a1',?,?,?,SYSDATE,0)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();			
			
			
		} catch (Exception e) {
			System.out.println("INSERT Exception");
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	public List<PostVO> listBoard() {
		
		List<PostVO> articles = new ArrayList<PostVO>();
		String sql = "SELECT * FROM post WHERE board_id = 'a1' ORDER BY post_id DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				PostVO article = new PostVO(
						rs.getInt("post_id"),
						rs.getString("board_id"),
						rs.getString("user_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("w_date"),
						rs.getInt("hit")
						);

				articles.add(article);
				
			}
			
		} catch (Exception e) {
			System.out.println("SELECT LIST Exception");
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public PostVO contentBoard(int postId) {

		String sql = "SELECT * FROM post WHERE post_id = ?";
		PostVO article = null;
		try {		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				article = new PostVO(
						
						rs.getInt("post_id"),
						rs.getString("board_id"),
						rs.getString("user_id"),
						rs.getString("title"),
						rs.getString("content").replace("nbsp", "&nbsp;"),
						rs.getDate("w_date"),
						rs.getInt("hit")
						//.replace로 &nbsp; 번역
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("contentBoard EXCEPTION");
		}
		//getAllInfoClose();

		return article;
	}

	@Override
	public void updateBoard(int postId, String title, String content) {
		String sql = "UPDATE post SET title=?, content=? WHERE post_id=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, postId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteBoard(int postId) {

		String sql = "DELETE FROM post WHERE post_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteBoard Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<PostVO> searchList(String search) {
		
		String sql = "SELECT * FROM post WHERE board_id = 'a1' AND title LIKE ? ORDER BY post_id DESC";
		List<PostVO> articles = new ArrayList<>();
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostVO vo = new PostVO(
						
						rs.getInt("post_id"),
						rs.getString("board_id"),
						rs.getString("user_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("w_date"),
						rs.getInt("hit")
						);

				articles.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articles;
	}

	@Override
	public boolean hitUp(int postId) {

		String sql = "UPDATE post SET hit = hit + 1 WHERE post_id = ?";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("hitUp EXCEPTION");
		}
		
		
		
		
		return false;
	}

	@Override
	public List<PostVO> orderByList(int N) {

		String sql = null;
		
		if(N == 0) {
			sql = "SELECT * FROM post WHERE ROWNUM <=4 AND board_id = 'a1' ORDER BY hit DESC";	//조회수 많은순
		}else if(N == 1) {
			sql = "SELECT * FROM post WHERE ROWNUM <=4 AND board_id = 'a1' ORDER BY post_id DESC";	//게시글번호 내림차순
		}
		
		List<PostVO> articles = new ArrayList<PostVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				PostVO article = new PostVO(
						rs.getInt("post_id"),
						rs.getString("board_id"),
						rs.getString("user_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("w_date"),
						rs.getInt("hit")
						);

				articles.add(article);
			}
			
		}catch (Exception e) {
			System.out.println("orderByList EXCEPTION");
		}
		
		
		return articles;
	}
	
//	// 이미지 관련
//	@Override
//	public boolean registImg(String imgPath) {
//		
//		String sql = "INSERT INTO image(post_id, img_path) VALUES(SEQ_post_post_id.CURRVAL, ?)";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, imgPath);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("INSERT image EXCEPTION");
//			return false;
//		}
//		
//		return true;
//	}
//
//	@Override
//	public List<String> searchImg(String postId) {
//		
//		List<String> imgPathList = new ArrayList<>();
//		String sql = "SELECT img_path FROM image WHERE post_id = ? ORDER BY img_id";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, postId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				String imgPath = rs.getString("img_path");
//				imgPathList.add(imgPath);
//			} 
//		} catch (Exception e) {
//			System.out.println("SEARCH IMG EXCEPTION");
//			e.printStackTrace();
//		}
//		
//		return imgPathList;
//	}
//
//	@Override
//	public void deleteImg(String postId) {
//
//		String sql = "DELETE FROM image WHERE post_id=?";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, postId);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("DELETE image EXCEPTION");
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public boolean updateImg(String postId, String imgPath) {
//
//		String sql = "INSERT INTO image(post_id, img_path) VALUES(?, ?)";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, postId);
//			pstmt.setString(2, imgPath);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("updateImg EXCEPTION");
//			return false;
//		}
//		
//		return true;
//	}
//
//	@Override
//	public boolean registSPost(String ctgId, String cityId, String sType, String progress, Integer price) {
//
//		String sql = "INSERT INTO s_post VALUES(SEQ_post_post_id.CURRVAL,?,?,?,?,?)";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			
//			pstmt.setString(1, ctgId);
//			pstmt.setString(2, cityId);
//			pstmt.setString(3, sType);
//			pstmt.setString(4, progress);
//			pstmt.setInt(5, price);
//			
//			pstmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			System.out.println("registSPost EXCEPTION");
//			e.printStackTrace();
//			return false;
//
//		}
//		
//		return true;
//	}

	
	
	
	///////////////////////////////추가 실험
//	@Override
//	public boolean upload(String writer, String title, String content) {
//
//		String sql = "INSERT INTO all_board3 VALUES(SEQ_NUM3.NEXTVAL, ?, ?, ?)";
//
//		try {
//
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, writer);
//			pstmt.setString(2, title);
//			pstmt.setString(3, content);			
//			pstmt.setString(4, fileRealName);	// img_name
//			pstmt.setString(5, imgPath);
//			
//			pstmt.executeUpdate();
//			
//
//		} catch(Exception e) {
//
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//		
//	}
	
	
}
