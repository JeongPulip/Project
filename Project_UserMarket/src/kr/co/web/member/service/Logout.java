package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;

public class Logout implements InterfaceService {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		
		try {
			pw = response.getWriter();
				pw.write
("<script>alert('정상적으로 로그아웃되었습니다.'); location.replace('loginPage.jsp')</script>");
				pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		} // finally-end
		
		
	}
	
}
