package kr.co.web.comment.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.comment.model.CommentDAO;
import kr.co.web.comment.model.CommentVO;
import kr.co.web.etc.model.NoticeDAO;
import kr.co.web.etc.service.InterfaceService;

public class InsertReComment implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		Set<String> keySet = request.getParameterMap().keySet();
//		for(String key: keySet) {System.out.println(key + ": " + request.getParameter(key));}
		String post_id = request.getParameter("post_id"); // 관련 게시글 번호
		String id = (String) request.getSession().getAttribute("id"); //접속유저 ID
		String parent_id = request.getParameter("parent_id"); // 관련 댓글 번호
		
		CommentVO cvo = CommentDAO.getInstance().getComment(parent_id);
		String c_user_id = cvo.getUser_id(); // 알림타겟
		String c_content = cvo.getContent(); // 댓글내용
		
		ArrayList<CommentVO> r = CommentDAO.getInstance().insertReComment(
							post_id,
							parent_id,
							id,
							request.getParameter("secret"),
							request.getParameter("reply_content")
		);
//		System.out.println(r.get(r.size()-1).getCmt_id()); // 입력된 댓글 번호 확인		
		
		if(id.equals(c_user_id)) {
			System.out.println("나한테는 알림 생성 안함~");
		} else {
			if(NoticeDAO.getInstance().insertNoticeC(post_id, parent_id, c_user_id, c_content)) {
				System.out.println("댓글 - 댓글 알림 생성 완료");
			}
		}
		
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
