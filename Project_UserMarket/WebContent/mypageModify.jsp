<%@page import="kr.co.web.etc.model.CityDAO"%>
<%@page import="kr.co.web.etc.model.CityVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%
   if(session.getAttribute("id") == null){
      %>
      <script>
         alert("로그인 후 이용해주세요.");
         location.replace("loginPage.jsp");
      </script>
      <% } %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원정보 수정</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/mypageMotify.css?after" media="all and (min-width:720px)"/>
<link rel="stylesheet" href="css/m_mypageMotify.css?after" media="all and (max-width:720px)"/>

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<%
   ArrayList<CityVO> rlist = CityDAO.getInstance().getRegion();
   pageContext.setAttribute("rlist", rlist);
   
   String gender = (String)session.getAttribute("gender");
   if(gender.equals("1")){
	   gender = "남";
   } else{
	   gender = "여";
   }
%>
<body>
<jsp:include page="header.jsp" />

   <div id="wrap">
      <div id="container">
      <div class="motify_Title">정보 수정</div>
         <form action="updateMember.member" method="post" id="form" enctype="multipart/form-data" onsubmit="return form_check();">
            <div class="motify_User">
               <!-- user의 사진과 아이디 등의 정보를 입력 -->
               <div class="motify_User_Img">
               
	           <c:choose>
					
					<c:when test="${empty prf_path}">
						<img id="image_section" src="img/ex.jpg" alt="없음.jpg" />
					</c:when>
					
					<c:otherwise>
						<img id="image_section" src="${prf_path}"/>
					</c:otherwise>
					
				</c:choose>
				   <input type='file' id="picture" name="picture"/>

               </div>
               <div class="motify_User_Info">
                  <!-- 사용자의 아이디등의 정보 출력 -->
                  <div class="motify_User_Info_ID">회원정보수정</div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">아이디</div>
                     <div class="motify_User_Info_Input">${id}</div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">닉네임</div>
                     <div class="motify_User_Info_Input">${nick}</div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">이름</div>
                     <div class="motify_User_Info_Input">${name}</div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">생년월일</div>
                     <div class="motify_User_Info_Input">${b_date}</div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">성별</div>
                     <div class="motify_User_Info_Input"><%= gender %></div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">전화번호</div>
                     <div class="motify_User_Info_Input">
                        <input type="tel" name="tel" id="tel" value="${tel}"> 
                       <input type="button" value="인증받기" id="ser" onclick="tel_check();" />
                     </div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">이메일</div>
                     <div class="motify_User_Info_Input">
                         <input type="text" name="email" id="email" value="${email}" />
                     </div>
                  </div>
            
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">내 동네</div>
                     <div class="motify_User_Info_Input">
                        <select id="region">
                           <option value="">선택</option>
                           <c:forEach var="r1" items="${rlist}">
                              <option value="${r1.region_id}">${r1.region}</option>
                           </c:forEach>
                        </select> <select name="city" id="city">
                           <option value="">선택</option>
                        </select>
                     </div>
                  </div>
                  <div class="motify_User_Info_Block">
                     <div class="motify_User_Info_Title">가입일</div>
                     <div class="motify_User_Info_Input">${j_date}</div>
                  </div>
                  <div class="motify_Btn">
                     <input type="button" value="취소" onclick="location.replace('myPage.jsp');" />
                     <input type="submit" value="수정"/>
                  </div>
               </div>
            </div>
         </form>
         
      </div> <!--  container end -->
   </div> <!--  wrap end -->

<jsp:include page="footer.jsp" />

<script type="text/javascript" src="js/mypageModify.js?ver=1"></script>
<script src="js/jquery-3.4.1.js"></script>
</body>
</html>