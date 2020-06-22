package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class FindUserPWD implements InterfaceService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("serName2");
		String id = request.getParameter("serID");
		String tel = request.getParameter("serTel2");
//		System.out.println(name+"\t"+id+"\t"+tel);
	
		String pwd = MemberDAO.getInstance().findUserPWD(name, id, tel);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		
		try {
			pw = response.getWriter();
			if(pwd != null) {
				pw.write
				("<script>alert('비밀번호: " + pwd + "'); location.href='idpwSearch.jsp'</script>");
			} else {
				pw.write
				("<script>alert('입력하신 정보가 올바르지 않습니다'); location.href='idpwSearch.jsp'</script>");
			}
				pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		} // finally-end
		
	}
	
}