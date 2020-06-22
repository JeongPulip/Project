package kr.co.web.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.comment.service.DeleteComment;
import kr.co.web.comment.service.InsertComment;
import kr.co.web.comment.service.InsertReComment;
import kr.co.web.comment.service.ShowComment;
import kr.co.web.comment.service.UpdateComment;

@WebServlet("*.cmt")
public class CommentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentFrontController() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String c = uri.substring(conPath.length());
		
		switch(c) {
		
		case "/showComment.cmt": // 초기화면 댓글 출력
			new ShowComment().execute(request, response);
			
			break;
			
		case "/insertComment.cmt": // 댓글 입력
			new InsertComment().execute(request, response);
			
			break;
			
		case "/insertReComment.cmt": // 대댓글 입력
			new InsertReComment().execute(request, response);
			
			break;
			
		case "/deleteComment.cmt": // 댓글 or 대댓글 삭제
			new DeleteComment().execute(request, response);
			
			break;
			
		case "/updateComment.cmt":
			new UpdateComment().execute(request, response);
			
			break;
			
		
			
			
		
		
		
		
		}// switch-end
		
		
		
		
		
	}

}
