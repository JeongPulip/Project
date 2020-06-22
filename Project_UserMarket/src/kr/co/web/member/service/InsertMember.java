package kr.co.web.member.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class InsertMember implements InterfaceService {

   public void execute(HttpServletRequest request, HttpServletResponse response) {

      String defaultPath = request.getServletContext().getRealPath("/");
      //파일 기본경로 _ 상세경로
      String path = defaultPath + "profile" + File.separator;
      File file = new File(path);
      
      if (!file.exists()) {
         file.mkdirs();
      }
      // 총 100M 까지 저장 가능하게 함
      int maxSize = 1024 * 1024 * 100;
      String encoding = "UTF-8";

      
      MultipartRequest multipartRequest;
      String id = null;
      String pwd = null;
      String nick = null;
      
      String name = null;
      String b_date = null;
      String gender = null;
      
      String email01 = null;
      String email02 = null;
      String email03 = null;      
      
      String tel = null;
      String city_id = null;
      
      // 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임
      String fileName = null;
      // 실제 서버에 업로드 된 파일시스템 네임
      String fileRealName = null;

      try {
         multipartRequest = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
         
         id = multipartRequest.getParameter("id");
         pwd = multipartRequest.getParameter("pwd");
         nick = multipartRequest.getParameter("nick");
         
         name = multipartRequest.getParameter("name");
         b_date = multipartRequest.getParameter("b_date");
         gender = multipartRequest.getParameter("gender");
         
         email01 = multipartRequest.getParameter("email01");
         email02 = multipartRequest.getParameter("email02");
         email03 = multipartRequest.getParameter("email03");      
         
         tel = multipartRequest.getParameter("tel");
         city_id = multipartRequest.getParameter("city");
         
         // 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임
         fileName = multipartRequest.getOriginalFileName("picture");
         // 실제 서버에 업로드 된 파일시스템 네임
         fileRealName = multipartRequest.getFilesystemName("picture");

         
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      String imgPath = request.getContextPath() + "/profile/" + fileRealName;
      System.out.println(imgPath);

//      System.out.println("사용자파일명 : " + fileName);
//
//      System.out.println("실제DB파일명 : " + fileRealName);
      
      
//=================== 폼에서 넘어온 값들을 받아주고요 =======================
      if(email02 == null) { // 선택박스로 이메일 뒷주소를 입력받았다면
         email02 = email03; // 그 값을 쓰겠다
      }
      
      String prf_path = imgPath;
      boolean result = MemberDAO.getInstance().insertMember
            (id, pwd, nick, name, b_date, gender,
            email01, email02, tel, city_id, prf_path);
      
      response.setContentType("text/html; charset=utf-8");
      PrintWriter pw = null;
      try {
         pw = response.getWriter();
         if(result) { //쿼리문을 실행하는 과정에서 오류가 없었다면
            System.out.println("성공적으로 회원정보를 입력했습니다..^^");
            pw.write
("<script>alert('회원가입을 완료했습니다.^^'); location.href='loginPage.jsp'</script>");
         }
         else {
            pw.write
("<script>alert('회원가입을 완료하지 못했습니다..'); location.href='signupPage'.jsp</script>");
         }
            pw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if(pw != null) pw.close();
      } // finally-end
   }// execute-end

}