package kr.co.web.comment.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.PostVO;
import kr.co.web.comment.model.CommentDAO;
import kr.co.web.comment.model.CommentVO;
import kr.co.web.etc.model.NoticeDAO;
import kr.co.web.etc.service.InterfaceService;

public class InsertComment implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		Set<String> keySet = request.getParameterMap().keySet();
//		for(String key: keySet) {System.out.println(key + ": " + request.getParameter(key));}
		String post_id = request.getParameter("post_id"); // 게시글 번호
		String id = (String) request.getSession().getAttribute("id"); // 접속유저 ID
		
		PostVO pvo = PostDAO.getInstance().contentBoard(Integer.parseInt(post_id));
		String p_user_id = pvo.getUserId(); //알림타겟
		String p_title = pvo.getTitle(); // 글제목
		
		ArrayList<CommentVO> r = CommentDAO.getInstance().insertComment(
						post_id,
						id,
						request.getParameter("secret"),
						request.getParameter("reply_content")
		);
//		System.out.println(r.get(r.size()-1).getCmt_id()); // 입력된 댓글 번호 확인	
		
		if(id.equals(p_user_id)) {
			System.out.println("나한테는 알림 생성 안함~");
		} else {
			if(NoticeDAO.getInstance().insertNoticeP(post_id, p_user_id, p_title)) {
				System.out.println("게시글 - 댓글 알림 생성 완료");
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
