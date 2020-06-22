package kr.co.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webprojectDBConn.DBConn;

public class MyPageDAO {

	//전역
			private Connection con;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			private static MyPageDAO myPageDAO = new MyPageDAO();
			
			public MyPageDAO() {
			
				con = DBConn.getConnection();

			}//end constr
			
			public static MyPageDAO getInstance() {
				
				if(myPageDAO == null) {
					myPageDAO = new MyPageDAO();
				}
				return myPageDAO;
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
			
			public List<PostAllVO> myPostList(String id) {
				
				String sql = "";
				List<PostAllVO> listPaVO = new ArrayList<>();
				
				
				
				
				
				
				return listPaVO;
			}
			
	
}



















