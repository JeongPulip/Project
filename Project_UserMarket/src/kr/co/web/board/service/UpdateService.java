package kr.co.web.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.etc.service.InterfaceService;

public class UpdateService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int postId = Integer.parseInt(request.getParameter("postId"));
		//System.out.println(postId);

//		String writer = request.getParameter("Writer1");
		String title = request.getParameter("articleTitle");
		String content = request.getParameter("upload_Textarea");
		content = content.replace("&nbsp;", "nbsp");
		
		PostDAO.getInstance().updateBoard(postId, title, content);
		
		// 재입력을 위한 삭제
		ImageDAO.getInstance().deleteImg(postId);
		
		///////////이미지 태그 내부의 이미지 경로 추출//////////
		//System.out.println(content);
		
		String startImgTag = "<img src=\"";
		String endImgTag = "\" title=";
		content = content.replace("\r\n", "");
		String[] imgPathArr;	//업로드된 이미지들의 경로 배열
		
		imgPathArr = content.split(startImgTag);
		
		for(int i=1; i<imgPathArr.length; i++) {
			imgPathArr[i] = imgPathArr[i].substring(0, imgPathArr[i].indexOf(endImgTag));
		}
		
		for(int i=1;i<imgPathArr.length;i++) {
//			System.out.println();
//			System.out.println(imgPathArr[i]);
			ImageDAO.getInstance().updateImg(postId, imgPathArr[i]);
		}
		
		// 재입력을 위한 삭제
		SPostDAO.getInstance().deleteSPost(postId);
		
		
		///////////<거래 정보 입력>///////////////////////

		String ctgId = request.getParameter("ctgId");		//카테고리 번호	ctg_id
		String cityId = request.getParameter("cityId");		//지역 번호		city_id
		String sType = request.getParameter("deliver");		//거래방식		s_type
		String progress = request.getParameter("progress");	//진행상태		progress
		int price = Integer.parseInt(request.getParameter("articlePrice"));		//가격		price
		
		System.out.println("카테고리 번호 : " + ctgId);
		System.out.println("지역 번호 : " + cityId);
		System.out.println("거래방식 : " + sType);		// 1: 직거래	2: 택배거래	0: 무관
		System.out.println("진행상태 : " + progress);	// 0: 팝니다	1: 거래진행중	2: 거래완료	3: 삽니다
		System.out.println("가격 : " + price);
		
		SPostDAO.getInstance().updateSPost(postId, ctgId, cityId, sType, progress, price);
		///////////</거래 정보 입력>//////////////////////
		
		
	}

}
