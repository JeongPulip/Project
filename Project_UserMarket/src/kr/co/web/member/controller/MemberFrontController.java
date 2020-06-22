package kr.co.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.member.service.CheckID;
import kr.co.web.member.service.CheckNick;
import kr.co.web.member.service.CheckPWD;
import kr.co.web.member.service.DeleteMember;
import kr.co.web.member.service.FindUserID;
import kr.co.web.member.service.FindUserPWD;
import kr.co.web.member.service.InputUserInfo;
import kr.co.web.member.service.InsertMember;
import kr.co.web.member.service.Login;
import kr.co.web.member.service.Logout;
import kr.co.web.member.service.UpdateMember;
import kr.co.web.member.service.UpdatePWD;

@WebServlet("*.member")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberFrontController() {}

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
		
			// 아이디 중복검사
		case "/checkID.member":
			
			new CheckID().execute(request, response);
			
			break;
			
			// 닉네임 중복검사
		case "/checkNick.member":
			
			new CheckNick().execute(request, response);
			
			break;
			
			// 회원정보 입력(회원가입)
		case "/insertMember.member":
			/*========= 폼으로부터 넘겨받은 정보(request)를 확인 =================
			Set<String> keySet = request.getParameterMap().keySet();
			for(String key: keySet) {
				System.out.println(key + ": " + request.getParameter(key));}*/
			
			new InsertMember().execute(request, response);
			
			break;
			
			// 로그인
		case "/doLogin.member":
			/*Set<String> keySet = request.getParameterMap().keySet();
			for(String key: keySet) {
				System.out.println(key + ": " + request.getParameter(key));}*/
			
			new Login().execute(request, response);
			
			break;
			
			// 로그아웃
		case "/doLogout.member":
			
			new Logout().execute(request, response);
			
			break;
			
			// 아이디 찾기
		case "/findUserID.member":
			
			new FindUserID().execute(request, response);
			
			break;
			
			// 비밀번호 찾기
		case "/findUserPWD.member":
			
			new FindUserPWD().execute(request, response);
			
			break;
			
			// 비밀번호 확인
		case "/checkPWD.member":
			
			new CheckPWD().execute(request, response);
			
			break;
		
			// 회원정보 수정
		case "/updateMember.member":
			
			new UpdateMember().execute(request, response);
			
			break;
			
			// 비밀번호 변경
		case "/updatePWD.member":
			
			new UpdatePWD().execute(request, response);
			
			break;
			
			// 회원정보 삭제(회원탈퇴)
		case "/deleteMember.member":
		
			new DeleteMember().execute(request, response);
			
			break;
			
			// 수정페이지에서 표시할 기존의 회원정보(전화번호, 지역) 가져오기
		case "/inputUserInfo.member":
			
			new InputUserInfo().execute(request, response);
			
			break;
			
			
			
			
			
			
		}// switch-end
		
		
	}

}
