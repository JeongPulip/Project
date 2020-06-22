package kr.co.web.board.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.ImageVO;
import kr.co.web.board.model.Paging;
import kr.co.web.board.model.PostAll2DAO;
import kr.co.web.board.model.PostAll2VO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.PostVO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.board.model.SPostVO;
import kr.co.web.board.model.ToJson;
import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;

public class AjaxListService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// ajax로 부터 받은 값
		String valIn = request.getParameter("val_in");		//  
		String category = request.getParameter("category");	// ctg_id, 전체보기는'ZZ'로 들어옴
		String sType = request.getParameter("sType");	// s_type
		String search = request.getParameter("search");	//title, 검색  %search%
		
		List<Integer> postIdList = new ArrayList<Integer>();
		String[] valInArr = null;
		
		// 카테고리 전체 값
		if(category.equals("ZZ")) {
			category = "%%";
		}
		
//		// 지역 전체 값
//		if(valIn.equals("ALL")) {
//			System.out.println("전체 조회");
//			cityIds = "ALL";
//		}else {
//		
//			valInArr = valIn.split(", ");
//		}
		
		// 거래방식 무관
		if(sType.equals("0")) {
			sType = "%%";
		}
		
		
		postIdList = PostAll2DAO.getInstance().searchPostId(valIn, category, sType, search);
		
//		for(int i : postIdList) {
//			System.out.println("pi:" + i);
//		}
		// DB에서 조건에 맞는 postId 가져옴
		/*
		 조건 city_id, ctg_id, s_type, title( 검색 ) 으로 postId 가져옴
		 */

		
		// 페이징
		int totalCount = postIdList.size();
		int page;
		
		String getPage = request.getParameter("page");	//이동 페이지 받기
		
		if(getPage == null) {
			page = 1;
		}else {
			page = Integer.parseInt(getPage);
		}
		Paging paging = new Paging(totalCount, page);
		
		List<PostAll2VO> listPa2VO = new ArrayList<PostAll2VO>();	// 각 게시글 썸네일 리스트
		
		int countList = paging.getCountlist();
		int endPost = page * countList;
		if(endPost > postIdList.size()) {
			endPost = postIdList.size();
		}
		int startPost = (page - 1) * countList + 1;
		
		// 페이징에 맞는 게시글 객체(postAll2VO) 생성
		for(int i = startPost - 1;i<=endPost - 1;i++) {
			
			int postId = postIdList.get(i);
			
			PostVO postVO = PostDAO.getInstance().contentBoard(postId);
			
			SPostVO sPostVO = SPostDAO.getInstance().selectSPost(postId);
			
			CityVO cityVO = PostAll2DAO.getInstance().getCityRegion(sPostVO.getCityId()); //region 2글자로 줄여옴

			CategoryVO categoryVO = PostAll2DAO.getInstance().getCtgName(sPostVO.getCtgId());
					
			List<ImageVO> listImageVO = ImageDAO.getInstance().selectImage(postId);
			
			PostAll2VO paVO = new PostAll2VO(postVO, sPostVO, cityVO, categoryVO, listImageVO);
			
			listPa2VO.add(paVO);
			
		}
		
		
//		for(int postId : postIdList) {
//			System.out.println("postId : " + postId);
			
			
			
		
		// 보낼 값
		
		ToJson toJson = new ToJson(listPa2VO, paging, search);
		
		String result = new Gson().toJson(toJson);


		
		
		// ajax로 전송부
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
