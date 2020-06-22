package kr.co.web.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.service.AjaxListService;
import kr.co.web.board.service.AjaxLoadPostInfoService;
import kr.co.web.board.service.AjaxMainPostInfoService;
import kr.co.web.board.service.ContentService;
import kr.co.web.board.service.DeleteService;
import kr.co.web.board.service.ListService;
import kr.co.web.board.service.ModifyService;
import kr.co.web.board.service.MyPageInfoService;
import kr.co.web.board.service.RegisterService;
import kr.co.web.board.service.SearchService;
import kr.co.web.board.service.UpdateService;
import kr.co.web.etc.service.InterfaceService;

@WebServlet("*.board")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InterfaceService sv;
	private RequestDispatcher dp;

	public BoardFrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length() + 1, uri.lastIndexOf("."));
		System.out.println(uri);

		switch (uri) {

		case "write":
			System.out.println("글쓰기 페이지 이동");
			response.sendRedirect("uploadPage.jsp");
			break;
		case "regist":
			System.out.println("글 등록 요청");
			sv = new RegisterService();
			sv.execute(request, response);
//			System.out.println(request.getParameter("Content1"));	//게시글 content가 html코드로 제대로 들어오는지 확인
			response.sendRedirect("list.board"); // 등록완료 후 리스트로 이동
			break;
		case "list":
			System.out.println("글 목록 요청");
			sv = new ListService();
			sv.execute(request, response);

			// request 객체를 다음 화면까지 운반하기 위한 forward 이동을 지원하는 객체 RequestDispatcher
			dp = request.getRequestDispatcher("searchListPage.jsp");
			dp.forward(request, response);
			break;
		case "content":
			System.out.println("글 상세보기 요청");
			sv = new ContentService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("postPage.jsp");
			dp.forward(request, response);
			break;
		case "modify":
			System.out.println("글 수정 페이지 이동");
			sv = new ModifyService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("editPage.jsp");
			dp.forward(request, response);
			break;
		case "update":
			System.out.println("글 수정 요청");
			sv = new UpdateService();
			sv.execute(request, response);
			response.sendRedirect("list.board"); // 갱신후 목록으로 이동
			break;
		case "delete":
			System.out.println("글 삭제 요청");
			sv = new DeleteService();
			sv.execute(request, response);
			response.sendRedirect("list.board"); // 삭제후 목록으로 이동
			break;
		case "search":
			System.out.println("글 검색 요청");
//			System.out.println(request.getParameter("inputSearch"));
			sv = new SearchService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("searchListPage.jsp");
			dp.forward(request, response);
			break;
//		case "myPageInfo":
//			System.out.println("마이 페이지 정보 요청");
//			sv = new MyPageInfoService();
//			sv.execute(request, response);
//			dp = request.getRequestDispatcher("myPage.jsp");
//			dp.forward(request, response);
//			break;
		case "ajaxLoadPostInfo":
			System.out.println("게시물 정보 AJAX요청");
			sv = new AjaxLoadPostInfoService();
			sv.execute(request, response);
			break;
		case "ajaxMainPostInfo":
			System.out.println("메인페이지 AJAX 요청");
			sv = new AjaxMainPostInfoService();
			sv.execute(request, response);
			break;
		case "ajaxList":
			System.out.println("리스트페이지 AJAX 요청");
			sv = new AjaxListService();
			sv.execute(request, response);
			break;

		}

	}// end doRequest

}// end class
