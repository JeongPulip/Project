package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class UpdatePWD implements InterfaceService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)request.getSession().getAttribute("id");
		String npwd = request.getParameter("npwd");
	
		boolean result = MemberDAO.getInstance().updatePWD(id, npwd);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(result) {
				request.getSession().setAttribute("pwd", npwd); //세션에 변경된 정보를 저장
				pw.write
				("<script>alert('비밀번호 변경을 완료했습니다.^^'); location.replace('myPage.jsp')</script>");
			} else {
				pw.write
				("<script>alert('비밀번호 변경 실패..'); location.replace('pwmodify.jsp')</script>");
			}
				pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		} // finally-end
		
	}
		
}
