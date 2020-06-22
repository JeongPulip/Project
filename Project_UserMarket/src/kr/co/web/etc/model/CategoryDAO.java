package kr.co.web.etc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webprojectDBConn.DBConn;

public class CategoryDAO {
	private CategoryDAO(){}

	
	public static CategoryDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final CategoryDAO INSTANCE = new CategoryDAO();
	}
	
	
	public ArrayList<CategoryVO> getCategory() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CategoryVO> ctglist = new ArrayList<CategoryVO>();
		String sql = "select * from category";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String ctg_id = rs.getString("ctg_id");
				String ctg_name = rs.getString("ctg_name");
				
				CategoryVO vo = new CategoryVO(ctg_id, ctg_name);
				ctglist.add(vo);
			}// while-end
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}// finally-end
		
		return ctglist;
	}// getCategory-end
	
	
	
}
