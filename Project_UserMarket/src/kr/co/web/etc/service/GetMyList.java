package kr.co.web.etc.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.etc.model.MyListDAO;

public class GetMyList implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String user_id =(String) request.getSession().getAttribute("id");
		String result = new Gson().toJson(MyListDAO.getInstance().getMyList(user_id));
		
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