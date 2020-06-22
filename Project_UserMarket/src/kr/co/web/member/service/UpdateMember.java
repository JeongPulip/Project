package kr.co.web.member.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.web.etc.model.CityDAO;
import kr.co.web.etc.model.CityVO;
import kr.co.web.etc.service.InterfaceService;
import kr.co.web.member.model.MemberDAO;

public class UpdateMember implements InterfaceService {

   public void execute(HttpServletRequest request, HttpServletResponse response) {
//      String id = (String)request.getSession().getAttribute("id");      
//      String tel = request.getParameter("tel");
//      String email = request.getParameter("email");      
//      String city_id = request.getParameter("city");
//      String prf_path = null;
      
      //////////////
      String id = (String)request.getSession().getAttribute("id");      
      String tel = null;
      String email = null;      
      String city_id = null;
      String prf_path = null;
      
      // 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임
      String fileName = null;
      // 실제 서버에 업로드 된 파일시스템 네임
      String fileRealName = null;
      
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
      try {
         multipartRequest = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
         
         // 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임
         fileName = multipartRequest.getOriginalFileName("picture");
         // 실제 서버에 업로드 된 파일시스템 네임
         fileRealName = multipartRequest.getFilesystemName("picture");
         
         tel = multipartRequest.getParameter("tel");
         email = multipartRequest.getParameter("email");      
         city_id = multipartRequest.getParameter("city");
         prf_path = request.getContextPath() + "/profile/" + fileRealName;

//         System.out.println("이미지 : " + prf_path);
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      
      //////////////
      
      
      boolean result = MemberDAO.getInstance().updateMember(id, tel, email, city_id, prf_path);
      if(result) {// 정상적으로 수정됐으면 세션값도 변경
         CityVO user_city = CityDAO.getInstance().getCityInfo(id);
         HttpSession session = request.getSession();
         session.setAttribute("tel", tel);
         session.setAttribute("email", email);
         session.setAttribute("city_id", city_id);
         session.setAttribute("prf_path", prf_path);
         
         session.setAttribute("region_id", user_city.getRegion_id());
         session.setAttribute("region", user_city.getRegion());
         session.setAttribute("city", user_city.getCity());
      }
      
      response.setContentType("text/html; charset=utf-8");
      PrintWriter pw = null;
      try {
         pw = response.getWriter();
         if(result) { //쿼리문을 실행하는 과정에서 오류가 없었다면
            pw.write("<script>alert('회원정보 수정을 완료했습니다.^^'); location.href='myPage.jsp'</script>");
         }
         else {
            pw.write("<script>alert('회원정보 수정을 완료하지 못했습니다..'); location.href='mypageModify.jsp'</script>");
         }
            pw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if(pw != null) pw.close();
      } // finally-end
   }// execute-end

}