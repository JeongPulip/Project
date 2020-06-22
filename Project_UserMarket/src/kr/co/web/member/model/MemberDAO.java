package kr.co.web.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import webprojectDBConn.DBConn;

public class MemberDAO {
	private MemberDAO(){}

	
	public static MemberDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final MemberDAO INSTANCE = new MemberDAO();
	}

//==================== 아이디 중복체크 ==========================
	public boolean checkID(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from members"
				+ " where id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			if(rs.getInt(1) != 0) { // 검색결과가 있으면
				return true; // true(중복)를 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return false; //여기까지 반환되지 않았으면 중복아님(false)을 반환
	}// checkID-end
//=============================================================	
	
	
	
	
	
	
	
//==================== 닉네임 중복체크 ==========================
	public boolean checkNick(String nick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from members"
				+ " where nick = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			if(rs.getInt(1) != 0) { // 검색결과가 있으면
				return true; // true(중복)를 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return false; //여기까지 반환되지 않았으면 중복아님(false)을 반환
	}// checkNick-end
//=============================================================	
	
	
	
	
	
	
	
	
//============= 회원정보 입력(회원가입시 최초 1번만 실행) =================
	public boolean insertMember
	(String id, String pwd, String nick,
	String name, String b_date, String gender,
	String email01, String email02, String tel,
	String city_id, String prf_path) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into members values"
				+ "(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'),"
				+ " ?, ?, ?, ?, ?, sysdate)";
		String email = email01 + "@" + email02;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, nick);
			pstmt.setString(4, name);
			pstmt.setString(5, b_date);
			pstmt.setString(6, gender);
			pstmt.setString(7, email);
			pstmt.setString(8, tel);
			pstmt.setString(9, city_id);
			pstmt.setString(10, prf_path);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return true;
	}// insertMember-end
//==================== 회원정보 입력-end =============================
	
	
//======================= 로그인 검사 ============================
	public boolean loginGo(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select pwd from members"
				+ " where id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			if(rs.getString("pwd").equals(pwd)){
				return true; // DB의 값과 일치하면 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return false; // 일치하지 않으면
	}
//==================== 로그인 검사-end =============================


	
	
//===================== 사용자 정보 제공 ============================	
	public MemberVO getUserInfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from members where id = ?";
		
		MemberVO vo = new MemberVO();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next(); //커서를 첫행으로 이동
			
			vo = new MemberVO(
					
					id,
					rs.getString("pwd"),
					rs.getString("nick"),
					rs.getString("name"),
					sd.format(rs.getDate("b_date")),
					rs.getString("gender"),
					rs.getString("email"),
					rs.getString("tel"),
					rs.getString("city_id"),
					rs.getString("prf_path"),
					sd.format(rs.getDate("j_date"))
					
					);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return vo;
	}
//==================== getUserInfo-end =========================

	
	
	
	
//==================== 아이디 찾기 =========================
	public String findUserID(String name, String tel) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select id from members where name = ? and tel = ?";
		
		String id = "";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();
			rs.next();
			
			id += rs.getString(1);
			
			while(rs.next()) {
				id += ", " + rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return id;
	}
//===================== 아이디 찾기-end ===========================	
	
	
	
	
	
	
//===================== 비밀번호 찾기 ============================	
	public String findUserPWD(String name, String id, String tel) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select pwd from members where "
					+ "name = ? and id = ? and tel = ?";
		
		String pwd = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, tel);
			rs = pstmt.executeQuery();
			rs.next();
			
			pwd = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return pwd;
	}
//====================== 비밀번호 찾기-end ==============================	
	
	
	
	
	
//====================== 비밀번호 변경 =================================
	public boolean updatePWD(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update members set pwd = ? where id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return true;
	}
//===================== 비밀번호 변경-end ============================
	
	
	
	
	
//======================= 회원정보 수정 ===========================
	public boolean updateMember
	(String id, String tel, String email, String city_id, String prf_path) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update members set tel = ?, email = ?, "
				+ "city_id = ?, prf_path = ? where id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, email);
			pstmt.setString(3, city_id);
			pstmt.setString(4, prf_path);
			pstmt.setString(5, id);
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}		
		
		return true;
	}// updateMember-end
//=============================================================	
	
	
	
	
	
	
//========================= 회원 탈퇴 ================================
	public boolean deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from members where id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return true;
	}
//======================== 회원 탈퇴-end =============================
	

}
