package kr.co.web.comment.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.comment.model.CommentDAO;
import kr.co.web.comment.model.CommentVO;
import kr.co.web.etc.service.InterfaceService;

public class DeleteComment implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		Set<String> keySet = request.getParameterMap().keySet();
//		for(String key: keySet) {System.out.println(key + ": " + request.getParameter(key));}
		
		ArrayList<CommentVO> r = CommentDAO.getInstance().deleteComment(
						
						request.getParameter("post_id"),
						request.getParameter("cmt_id")
				
				);
		
		String result = new Gson().toJson(r);
		
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