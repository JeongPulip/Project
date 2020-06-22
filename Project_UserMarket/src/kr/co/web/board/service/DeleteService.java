package kr.co.web.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.etc.service.InterfaceService;

public class DeleteService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int postId = Integer.parseInt(request.getParameter("postId"));
		
		boolean b1 = ImageDAO.getInstance().deleteImg(postId);
		boolean b2 = SPostDAO.getInstance().deleteSPost(postId);
		
		if(b1&&b2) {
			PostDAO.getInstance().deleteBoard(postId);
		}else {
			System.out.println("DeleteService Exception");
		}	
	}

}
