package kr.co.web.etc.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.etc.model.MyListDAO;
import kr.co.web.etc.model.NoticeDAO;

public class DeleteNotice implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		Set<String> keySet = request.getParameterMap().keySet();
//		for(String key: keySet) {System.out.println(key + ": " + request.getParameter(key));}
		
		String user_id =(String) request.getSession().getAttribute("id");
		String post_id = request.getParameter("post_id");
		String cmt_id = request.getParameter("cmt_id");
		String check = request.getParameter("check");
		
		boolean b = false;
		if(check.equals("all")) {
			b = NoticeDAO.getInstance().deletetNoticeAll(user_id);
		} else {
				
				if(cmt_id.equals("0")) {
					b = NoticeDAO.getInstance().deletetNoticeP(post_id, user_id);
				} else {
					b = NoticeDAO.getInstance().deletetNoticeC(cmt_id, user_id);
				}
		}
		
		String result = null;
		if(b) { // 알림 제대로 삭제했으면 갱신리스트를 담는다
			result = new Gson().toJson(MyListDAO.getInstance().getMyNotice(user_id));
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
		
	}
}