package kr.co.web.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.ImageVO;
import kr.co.web.board.model.Paging;
import kr.co.web.board.model.PostAll2DAO;
import kr.co.web.board.model.PostAll2VO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.PostVO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.board.model.SPostVO;
import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;

public class SearchService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputSearch = request.getParameter("inputSearch");
		boolean flag = true;
		
		List<PostVO> list = PostDAO.getInstance().searchList(inputSearch);
		
		if(list.isEmpty()) {
			flag = false;
		}
		
		int totalCount = list.size();
		int page;
		
		String getPage = request.getParameter("page");
		if(getPage == null) {
			page = 1;
		}else {
			page = Integer.parseInt(getPage);
		}
		Paging paging = new Paging(totalCount, page);
		
		List<PostAll2VO> listPa2VO = new ArrayList<PostAll2VO>();	// 각 게시글 썸네일 리스트

		int countList = paging.getCountlist();
		int endPost = page * countList;
		if(endPost > list.size()) {
			endPost = list.size();
		}
		int startPost = (page - 1) * countList + 1;
		
		
		for(int i = startPost - 1;i<=endPost - 1;i++) {
			
			int postId = list.get(i).getPostId();
			
			PostVO postVO = PostDAO.getInstance().contentBoard(postId);
			
			SPostVO sPostVO = SPostDAO.getInstance().selectSPost(postId);
			
			CityVO cityVO = PostAll2DAO.getInstance().getCityRegion(sPostVO.getCityId()); //region 2글자로 줄여옴

			CategoryVO categoryVO = PostAll2DAO.getInstance().getCtgName(sPostVO.getCtgId());
					
			List<ImageVO> listImageVO = ImageDAO.getInstance().selectImage(postId);
			
			PostAll2VO paVO = new PostAll2VO(postVO, sPostVO, cityVO, categoryVO, listImageVO);
			
			listPa2VO.add(paVO);
			
		}
		
		if(flag) {	//검색 결과가 없으면 보내지않음
			request.setAttribute("listPa2VO", listPa2VO);
			request.setAttribute("paging", paging);
		}
		
	}

}
