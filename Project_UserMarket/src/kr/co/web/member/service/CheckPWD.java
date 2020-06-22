package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;

public class CheckPWD implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String chkpwd = request.getParameter("chkpwd"); // 입력받은 값
		String pwd = (String)request.getSession().getAttribute("pwd"); // 세션에 저장된 기존의 비밀번호
		
		String result;
		if(pwd.equals(chkpwd)) { // 일치하면  true값을 넣어줌
			result = "true";
		} else {
			result = "false";
		}
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = null; 
		try {
			pw = response.getWriter();
			pw.write(result);
			pw.flush();		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		}// finally-end
		
	}// CheckPWD-end
	
}
