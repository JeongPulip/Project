package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class FindUserID implements InterfaceService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("serName1");
		String tel = request.getParameter("serTel1");
	
		String id = MemberDAO.getInstance().findUserID(name, tel);
		
		/*System.out.println(name);
		System.out.println(tel);
		System.out.println(id);*/
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		
		try {
			pw = response.getWriter();
			if(!id.equals("")) {
				pw.write
				("<script>alert('아이디: " + id + "'); location.href='idpwSearch.jsp'</script>");
			} else {
				pw.write
				("<script>alert('입력하신 정보와 일치하는 아이디가 없습니다'); location.href='idpwSearch.jsp'</script>");
			}
				pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		} // finally-end
		
	}
	
}
