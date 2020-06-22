<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.io.*"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	/* //파일정보 
	String sFileInfo = "";
	//파일명을 받는다 - 일반 원본파일명 
	String filename = request.getHeader("file-name");
	//파일 확장자
	String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
	//확장자를소문자로 변경 
	filename_ext = filename_ext.toLowerCase();
	//이미지 검증 배열변수 
	String[] allow_file = { "jpg", "png", "bmp", "gif" };
	//돌리면서 확장자가 이미지인지 
	int cnt = 0;
	for (int i = 0; i < allow_file.length; i++) {
		if (filename_ext.equals(allow_file[i])) {
			cnt++;
		}
	}
	//이미지가 아님 
	if (cnt == 0) {
		out.println("NOTALLOW_" + filename);
	} else {
		//이미지이므로 신규 파일로 디렉토리 설정 및 업로드 
		//파일 기본경로 
		String dftFilePath = request.getServletContext().getRealPath("/");
		//파일 기본경로 _ 상세경로 
		String filePath = dftFilePath + "editor" + File.separator + "multiupload" + File.separator;
		// dftFilePath : http://localhost:8081/WebPJ1/
		// File.separator : OS에 따라 파일구분자가 '/' 또는 '\'로 다르기 때문에 OS에 따라 수정하는 번거로움을 없애기 위해 사용 =>> 그냥 '/'로 생각
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
	
		}
		String realFileNm = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new java.util.Date());
		realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
		String rlFileNm = filePath + realFileNm;
		System.out.println(rlFileNm);	//프로젝트 경로 이하 이미지 경로
		
		
		///////////////// 서버에 파일쓰기 ///////////////// 
		InputStream is = request.getInputStream();
		OutputStream os = new FileOutputStream(rlFileNm);
		int numRead;
		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		while ((numRead = is.read(b, 0, b.length)) != -1) {
			os.write(b, 0, numRead);
		}
		if (is != null) {
			is.close();
		}
		os.flush();
		os.close();
		
		///////////////// 서버에 파일쓰기 ///////////////// 
		// 정보 출력 
		sFileInfo += "&bNewLine=true";
		//sFileInfo += "&sFileName="+ realFileNm;; 
		// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함 
		sFileInfo += "&sFileName=" + filename;
		sFileInfo += "&sFileURL=" + "../editor/multiupload/" + realFileNm;
		out.println(sFileInfo);
	} */

	///////////////////////////////////
	
	String sFileInfo = "";
	//파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴
	String name = request.getHeader("file-name");
	String ext = name.substring(name.lastIndexOf(".") + 1);
	//파일 기본경로
	String defaultPath = request.getServletContext().getRealPath("/");
	//파일 기본경로 _ 상세경로
	//String path = defaultPath + "upload" + File.separator;
	//String path = defaultPath + "editor" + File.separator +"multiupload" + File.separator;
	String path = defaultPath + "upload" + File.separator;
	//System.out.println("이미지 경로:" + path);	//이미지 경로 확인
	
	File file = new File(path);
	if (!file.exists()) {
		file.mkdirs();
	}
	String realname = UUID.randomUUID().toString() + "." + ext;
	
	//////////////	
	
	// ArrayList<String> list = (ArrayList)session.getAttribute("imageList");
	
	
	//////////////
	
	//System.out.println(path + realname);	//이미지 경로 확인
	/*
	// 이러한 식으로 출력..
	//	C:\Users\gjals\Desktop\Java_res\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebPJ1\
	//	upload\a173eec9-c54d-4c7a-8cbc-66e1303d0e93.png
	*/
	//System.out.println("../upload/" + realname);	// 이미지 태그에 들어갈 경로
	
	/////DB에 들어갈 이미지 경로
	//String img_path = "../upload/" + realname;
	//request.setAttribute("img_path", img_path);
	
	/////
	
	
	InputStream is = request.getInputStream();
	OutputStream os = new FileOutputStream(path + realname);
	int numRead;
	// 파일쓰기
	byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	while ((numRead = is.read(b, 0, b.length)) != -1) {
		os.write(b, 0, numRead);
	}
	if (is != null) {
		is.close();
	}
	
/*
	// 이미지 태그에 들어갈 경로
	String img_path = "../upload/" + realname;
	request.setAttribute("img_path", img_path);
 */
	os.flush();
	os.close();
	sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + request.getContextPath() + "/upload/" + realname;
	//System.out.println("경로 :"+request.getContextPath()+"/upload/" + realname);
	out.println(sFileInfo);
	
	
%>





