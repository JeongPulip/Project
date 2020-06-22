package kr.co.web.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import kr.co.web.etc.model.CityDAO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;
import kr.co.web.member.model.MemberVO;

public class Login implements InterfaceService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		boolean login = MemberDAO.getInstance().loginGo(id, pwd);
		
		if(login) {// id와 pwd가 일치하면 페이지 이동 전에 session값을 설정
			MemberVO user = MemberDAO.getInstance().getUserInfo(id);
			CityVO user_city = CityDAO.getInstance().getCityInfo(id);
			String tel = user.getTel();
			String tel2 = tel.substring(0,3) + "-" + 
			tel.substring(3,tel.length()-4) + "-" + 
			tel.substring(tel.length()-4);

			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			session.setAttribute("nick", user.getNick());
			session.setAttribute("name", user.getName());
			session.setAttribute("b_date", user.getB_date());
			session.setAttribute("gender", user.getGender());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("tel", tel); // 숫자만 표현 (DB 저장값)
			session.setAttribute("tel2", tel2); // '-'이 있는 표현
			session.setAttribute("city_id", user.getCity_id());
			session.setAttribute("prf_path", user.getPrf_path());
			session.setAttribute("j_date", user.getJ_date());
			
			session.setAttribute("region_id", user_city.getRegion_id());
			session.setAttribute("region", user_city.getRegion());
			session.setAttribute("city", user_city.getCity());
		}
		
		String result = new Gson().toJson(login);
		response.setContentType("application/json; charset=utf-8");
		
		// 결과를 전송해서 ajax가 받으면 location.replace()로 페이지 이동.
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
