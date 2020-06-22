package kr.co.web.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.etc.service.InterfaceService;

public class MyPageInfoService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String MyId = request.getParameter("myId");
		// 내 게시글들
		
		
		
		// 내가 찜한 게시글들
		
	}

}
