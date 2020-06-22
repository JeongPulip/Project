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
import kr.co.web.board.model.PostAll2DAO;
import kr.co.web.board.model.PostAll2VO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.PostVO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.board.model.SPostVO;
import kr.co.web.etc.model.CategoryVO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;

public class AjaxMainPostInfoService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<PostAll2VO> listPa2VO = new ArrayList<PostAll2VO>();	// 각 게시글 썸네일 리스트
		
		// 구분자(1: postId 내림차순, 0: hit 내림차순)
		List<PostVO> list = PostDAO.getInstance().orderByList(0);	//상위 4개만
		

//		List<PostAllVO> palist = new ArrayList<PostAllVO>();
		
		for(PostVO pVO : list) {
			
			int postId = pVO.getPostId();
			System.out.println("postId" + postId);
			
			PostVO postVO = PostDAO.getInstance().contentBoard(postId);
			
			SPostVO sPostVO = SPostDAO.getInstance().selectSPost(postId);
			
			CityVO cityVO = PostAll2DAO.getInstance().getCityRegion(sPostVO.getCityId()); //region 2글자로 줄여옴

			CategoryVO categoryVO = PostAll2DAO.getInstance().getCtgName(sPostVO.getCtgId());
					
			List<ImageVO> listImageVO = ImageDAO.getInstance().selectImage(postId);
			
//			PostAllVO paVO = PostAllDAO.getInstance().PostAll(postId);
//			System.out.println("paVO" + paVO);
//			palist.add(paVO);

			PostAll2VO pa2VO = new PostAll2VO(postVO, sPostVO, cityVO, categoryVO, listImageVO);
//			System.out.println(pa2VO);
			
			listPa2VO.add(pa2VO);
			
			/////////////////////

			
			/////////////////////

		}// end for
//		System.out.println("palist" + palist);
//		String result = new Gson().toJson(palist);
//		response.setContentType("application/json; charset=utf-8");
//		System.out.println("result" + result);
		
		String result = new Gson().toJson(listPa2VO);
		response.setContentType("application/json; charset=utf-8");
		System.out.println("result" + result);
		
		
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
