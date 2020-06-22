package kr.co.web.board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.ImageVO;
import kr.co.web.board.model.PostAll2DAO;
import kr.co.web.board.model.PostAll2VO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.PostVO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.board.model.SPostVO;
import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;

public class ContentService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int postId = Integer.parseInt(request.getParameter("postId"));
		
		
		// 조회수 중복 방지를 위한 쿠키를 하나 생성.
		Cookie hitCookie = new Cookie((postId+""), (postId+""));
		hitCookie.setMaxAge(60*60);	// 수명 1시간
		response.addCookie(hitCookie);
		
		// 중복방지용 로직 쿠키 얻기.
		Cookie[] cookies = request.getCookies();
		String bNum = "";
		for(Cookie c:cookies) {
			if(c.getName().equals(postId+"")) {
				bNum = c.getValue();
			}
		}
		
		// 조회수 업
		if(!bNum.equals(postId+"")) {
			PostDAO.getInstance().hitUp(postId);
		}
		
		PostVO postVO = PostDAO.getInstance().contentBoard(postId);
		
		SPostVO sPostVO = SPostDAO.getInstance().selectSPost(postId);
		
		CityVO cityVO = PostAll2DAO.getInstance().getCityRegion(sPostVO.getCityId()); //region 2글자로 줄여옴

		CategoryVO categoryVO = PostAll2DAO.getInstance().getCtgName(sPostVO.getCtgId());
				
		List<ImageVO> listImageVO = ImageDAO.getInstance().selectImage(postId);
		
		
		// 게시글내부 모든 정보를 담은 객체생성
		PostAll2VO pa2VO = new PostAll2VO(postVO, sPostVO, cityVO, categoryVO, listImageVO);
		request.setAttribute("pa2VO", pa2VO);
		
		String writerNick = PostAll2DAO.getInstance().idToNick(postVO.getUserId());
		request.setAttribute("writerNick", writerNick);


	}

}
