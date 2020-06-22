package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class CheckID implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		boolean joongbok = MemberDAO.getInstance().checkID(id);
		String result = new Gson().toJson(joongbok);
		
//		System.out.println(id); // 값을 제대로 받았나 확인
//		System.out.println(result); // 요청에 대한 답(중복확인 결과)을 확인
		
//============= 이것도 안써주면 받아온 값이 제대로 출력이 안된다 ============
		response.setContentType("application/json; charset=utf-8");
//=============================================================	
		
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
