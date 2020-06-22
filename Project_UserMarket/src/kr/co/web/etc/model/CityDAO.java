package kr.co.web.etc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webprojectDBConn.DBConn;

public class CityDAO {
	private CityDAO(){}

	
	public static CityDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	private static class LazyHolder{
		private static final CityDAO INSTANCE = new CityDAO();
	}

	public ArrayList<CityVO> getRegion(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CityVO> rlist = new ArrayList<CityVO>();
		String sql = "select distinct region_id, region"
				+ " from city order by region_id";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rlist.add(new CityVO(
						
						null,
						null,
						rs.getString("region_id"),
						rs.getString("region")
						
						));
			}// while-end
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}// finally-end
		
		return rlist;
	}// getRegion-end
	
	
	
	
	
	public ArrayList<CityVO> getCity(String region_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CityVO> clist = new ArrayList<CityVO>();
		String sql = "select city_id, city from city"
				+ " where region_id = ? order by city_id";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, region_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				clist.add(new CityVO(
						
						rs.getString("city_id"),
						rs.getString("city"),
						region_id,
						null
						
						));
			}// while-end
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}// finally-end
		
		return clist;
	}// getCity-end
	
	
	
//==============================================================================	
	public ArrayList<CityListVO> getAllCity(){
		
		ArrayList<CityListVO> allcity = new ArrayList<CityListVO>();
		
		ArrayList<CityVO> rlist = new CityDAO().getRegion();
		for (CityVO vo : rlist) {// 2글자로 표현하기
			
			if (vo.getRegion().length() == 4) {//지역(시/도) 이름이 4글자면 첫글자랑 세번째 글자만으로 표현
				vo.setRegion(vo.getRegion().substring(0, 1) + vo.getRegion().substring(2, 3));
				
			}
			else {// 나머지는 앞에 2글자만으로 표현
				
				vo.setRegion(vo.getRegion().substring(0, 2));
				
			}
			
			ArrayList<CityVO> clist = new CityDAO().getCity(vo.getRegion_id()); // 시도별 시군구 리스트로 분류해서
			allcity.add(new CityListVO(vo.getRegion_id(), vo.getRegion(), clist)); // 전체 정보를 가질 리스트에 추가한다
		} // for-end
			
		return allcity;	
		
	}// getCity-end
//====================================================================================	
	
	
	
	
	public CityVO getCityInfo(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		CityVO vo = new CityVO();
		String sql = "select * from city where city_id = "
				+ "(select city_id from members where id = ?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			vo = new CityVO(
					
					rs.getString("city_id"),
					rs.getString("city"),
					rs.getString("region_id"),
					rs.getString("region")
					
					);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}// finally-end
		
		return vo;
	}// getCity-end
	
	
	
	
	
	
}
