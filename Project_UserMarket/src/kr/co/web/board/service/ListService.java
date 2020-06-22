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

public class ListService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		boolean flag = true;
		//////////////////////////////////////////
		List<PostVO> list = PostDAO.getInstance().listBoard();		

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
		/* 
			1. 불러온 리스트에서 totalCount(총 게시물 수)를 받아옴
			2. countList에 따라 totalPage(총 페이지 수 = 마지막 페이지)를 계산.
			3. countPage에 따라 totalGroup(총 페이지그룹 수 = 마지막 그룹)을 계산.
			4. 현재 page(기본 page = 1)에 맞게 pageGroup(현재 그룹) 설정
			5. pageGroup(현재 그룹)에 맞게 startPage, endPage 계산

		 */
//		int totalCount = 20;
//		int page = 7;
//		
//		Paging paging = new Paging(totalCount, page);
//		
//		System.out.println(paging.toString());
//		
//		paging.example();
//		
//		request.setAttribute("paging", paging);
		
//		int countList = 5;
//		
//		int countPage = 5;
//
//		
//		int page = 21;
//		
//		System.out.println("현재 페이지 번호 : " + page);
//
//		int pageGroup = page / countPage;
//		if (page % countPage > 0) {
//
//			pageGroup++;
//
//		}		
//		System.out.println("현재 페이지 그룹 : " + pageGroup);
//
//		int totalPage = totalCount / countList;
//
//		if (totalCount % countList > 0) {
//
//		    totalPage++;
//
//		}
//		System.out.println("총 페이지 개수 : " + totalPage);
//
//		
//		int totalGroup = totalPage / countPage;
//		if (totalPage % countPage > 0) {
//
//			totalGroup++;
//
//		}
//		System.out.println("총 페이지그룹 개수 : " + totalGroup);
//
//
//
//		if (totalPage < page) {
//
//		    page = totalPage;
//
//		}
//
//
//
//		int startPage = ((page - 1) / countPage) * countPage + 1;
//
//		int endPage = startPage + countPage - 1;
//
//
//
//		if (endPage > totalPage) {
//
//		    endPage = totalPage;
//
//		}
//
//
//
//		if (startPage > 1) {
//
//		    System.out.print("<a href=\"?page=1\">[처음]</a>");
//
//		}
//
//
//
//		if (pageGroup > 1) {
//
//		    System.out.print("<a href=\"?page=" + (startPage - 1)  + "\">[이전]</a>");
//
//		}
//
//
//
//		for (int iCount = startPage; iCount <= endPage; iCount++) {
//
//		    if (iCount == page) {
//
//		        System.out.print(" <b>" + iCount + "</b>");
//
//		    } else {
//
//		        System.out.print(" " + iCount + " ");
//
//		    }
//
//		}
//
//
//		if (pageGroup < totalGroup) {
//		    
//		    System.out.print("<a href=\"?page=" + (endPage + 1)  + "\">[다음]</a>");
//
//		}
//
//
//
//		if (endPage < totalPage) {
//
//		    System.out.print("<a href=\"?page=" + totalPage + "\">[끝]</a>");
//
//		}
//		
//			request.setAttribute("totalCount", totalCount);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("totalCount", totalCount);
//			request.setAttribute("totalCount", totalCount);
//			request.setAttribute("totalCount", totalCount);
//			request.setAttribute("totalCount", totalCount);
		
		
		
	}

}







