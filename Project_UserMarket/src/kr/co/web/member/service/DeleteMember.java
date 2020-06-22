package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class DeleteMember implements InterfaceService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)request.getSession().getAttribute("id");
		
		boolean result = MemberDAO.getInstance().deleteMember(id);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		
		try {
			pw = response.getWriter();
			if(result) {
				request.getSession().invalidate(); //세션정보 삭제
				pw.write
				("<script>alert('회원탈퇴 완료..'); location.replace('mainHomePage.jsp')</script>");
			} else {
				pw.write
				("<script>alert('회원탈퇴를 완료하지 못했습니다.'); location.replace('mpPage.jsp')</script>");
			}
				pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		} // finally-end
		
	}
	
}
