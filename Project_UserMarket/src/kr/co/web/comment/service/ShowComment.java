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

public class ShowComment implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<CommentVO> r = CommentDAO.getInstance().getCommentInfo(
				
				request.getParameter("post_id")
				
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