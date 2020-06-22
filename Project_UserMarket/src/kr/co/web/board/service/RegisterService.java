package kr.co.web.board.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.model.ImageDAO;
import kr.co.web.board.model.PostDAO;
import kr.co.web.board.model.SPostDAO;
import kr.co.web.etc.service.InterfaceService;

public class RegisterService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		///////////<게시슬 기본 입력 정보>///////////////////
		String userId = request.getParameter("Writer1");	//아이디 -> 닉네임으로 바꿈
		String title = request.getParameter("articleTitle");
		String content = request.getParameter("upload_Textarea");
		content = content.replace("&nbsp;", "nbsp");
				
		PostDAO.getInstance().regist(userId, title, content);	//게시글 등록

		///////////</게시슬 기본 입력 정보>//////////////////
		
		///////////<이미지 태그 내부의 이미지 경로 추출>//////////

		String startImgTag = "<img src=\"";
		String endImgTag = "\" title=";
		content = content.replace("\r\n", "");
		String[] imgPathArr;	//업로드된 이미지들의 경로 배열
		
		imgPathArr = content.split(startImgTag);

		
		for(int i=1; i<imgPathArr.length; i++) {
			imgPathArr[i] = imgPathArr[i].substring(0, imgPathArr[i].indexOf(endImgTag));
		}
		
		for(int i=1;i<imgPathArr.length;i++) {
			ImageDAO.getInstance().registImg(imgPathArr[i]);
		}
		
		///////////</이미지 태그 내부의 이미지 경로 추출>/////////
		
//		String cityId = request.getParameter("cityId");
//		
//		System.out.println(cityId);
		
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
		
		SPostDAO.getInstance().registSPost(ctgId, cityId, sType, progress, price);
		///////////</거래 정보 입력>//////////////////////

		
			
		
		
		
		
		

		// ==> write.jsp에서 enctype="multipart/form-data" 으로 넘어왔기 때문에 일반 request.getParameter로 못받음
		// ==> MultipartRequest 사용

		
		// 해당 폴더에 이미지를 저장시킨다
 		
//		String uploadDir = this.getClass().getResource("").getPath();
//		
//		String imgPath = uploadDir.substring(1, uploadDir.indexOf(".metadata")) + "WebProjectMarket/WebContent/uploadImage";
//		
//		System.out.println("절대경로 : " + imgPath);
//		
//		// 후에 이미지 출력 경로를 위해 '/'를 '\'로 바꿔줌
//		imgPath = imgPath.replace("/","\\");
//		
//		// 총 100M 까지 저장 가능하게 함
//
//		int maxSize = 1024 * 1024 * 100;
//		String encoding = "UTF-8";
//
//		// 사용자가 전송한 파일정보 토대로 업로드 장소에 파일 업로드 수행할 수 있게 함
//		MultipartRequest multipartRequest;
//
//		try {
//			multipartRequest = new MultipartRequest(request, imgPath, maxSize, encoding, new DefaultFileRenamePolicy());
//			// DefaultFileRenamePolicy : 파일 이름이 동일한게 나오면 자동으로 다른걸로 바꿔주고 그런 행동 해주는것
//			// ex) 파일명1.jpg
//
//			String writer = multipartRequest.getParameter("nWriter");
//			String title = multipartRequest.getParameter("nTitle");
//			String content = multipartRequest.getParameter("nContent");
//
//			System.out.println(writer + title + content);
//				
//			// 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임
//
//			String fileName = multipartRequest.getOriginalFileName("itemimg");
//
//			// 실제 서버에 업로드 된 파일시스템 네임
//
//			String fileRealName = multipartRequest.getFilesystemName("itemimg");
//
//			// 디비에 업로드 메소드
//
//			PostDAO.getInstance().upload(writer, title, content, fileRealName, imgPath);
//			
//			System.out.println("사용자파일명 : " + fileName);	// fineName은 사용자가 올린 파일의 이름이다
//
//			System.out.println("실제DB파일명 : " + fileRealName);
//			// 파일 이름이 동일한게 나오면 자동으로 다른걸로 바꿔주고 그런 행동 해주는것 
//			// 중복된 파일이름이 있기에 fileRealName이 실제로 서버에 저장된 경로이자 파일
//
//			System.out.println("DB저장 절대경로 : " + imgPath);
//			
//		} catch (IOException e) {
//			System.out.println("파일업로드 실패");
//			e.printStackTrace();
//		}
		
	
	}

}
