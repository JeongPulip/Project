package kr.co.web.etc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.CheckJJim;
import kr.co.web.etc.service.DeleteJJim;
import kr.co.web.etc.service.DeleteNotice;
import kr.co.web.etc.service.DoJJim;
import kr.co.web.etc.service.GetCity;
import kr.co.web.etc.service.GetMyList;

@WebServlet("*.etc")
public class EtcFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EtcFrontController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String c = uri.substring(conPath.length());
		
		switch(c) {
		
		case "/getCity.etc":
			new GetCity().execute(request, response);
			
			break;
		
		case "/checkJJim.etc":
			new CheckJJim().execute(request, response);
			
			break;
			
		case "/doJJim.etc":
			new DoJJim().execute(request, response);
			
			break;
			
		case "/getMyList.etc":
			new GetMyList().execute(request, response);
			
			break;
			
		case "/deleteJJim.etc":
			new DeleteJJim().execute(request, response);
			
			break;
			
		case "/deleteNotice.etc":
			new DeleteNotice().execute(request, response);
			
			break;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		/*case "/test.etc":
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String s = request.getParameter("val_in");
			System.out.println(s);
			String sql = "select city from city where city_id in ("
					+ s + ")";
			try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			String result = rs.getString("city");
			while(rs.next()) {
					result += ", " + rs.getString("city");
			}
				System.out.println(result);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			break;*/
		
			
			
		
		
		
		
		}// switch-end
		
		
	}

}
