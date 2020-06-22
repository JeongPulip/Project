package kr.co.web.etc.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.etc.model.JJimDAO;

public class DoJJim implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		Set<String> keySet = request.getParameterMap().keySet();
//		for(String key: keySet) {System.out.println(key + ": " + request.getParameter(key));}
		
		String user_id =(String) request.getSession().getAttribute("id");
		String post_id = request.getParameter("post_id");
		boolean r = JJimDAO.getInstance().checkJJim(user_id, post_id);
		String result = "";
		
		if(!r) { // 찜을 안했으면
			if(JJimDAO.getInstance().doJJim(user_id, post_id)) { // 성공적으로 처리하면
				result = "찜했다";
			}
		} else { // 찜을 했으면
			if(JJimDAO.getInstance().undoJJim(user_id, post_id)) { // 성공적으로 처리하면
				result = "찜 풀었다";
			}
		}
		
		result = new Gson().toJson(result);
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