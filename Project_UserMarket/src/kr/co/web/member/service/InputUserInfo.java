package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;

public class InputUserInfo implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String tel = (String)request.getSession().getAttribute("tel");
		String ri = (String)request.getSession().getAttribute("region_id");
		String ci = (String)request.getSession().getAttribute("city_id");
		String result =
		"{\"tel\":\"" + tel + "\",\"ri\":\"" + ri + "\",\"ci\":\"" + ci + "\"}";
		
//		System.out.println(result);
		
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
	}
	
}