
<%@page import="kr.co.web.etc.model.CityDAO"%>
<%@page import="kr.co.web.etc.model.CityVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<%	if (session.getAttribute("id") != null) { %>
	<script>
	alert("이미 로그인하셨습니다.");
	location.href = "mainHomePage.jsp";
	</script>
<% } %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>회원가입</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/signup.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_signup.css?after" media="all and (max-width:720px)" />

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"/>
</head>

<%
   ArrayList<CityVO> rlist = CityDAO.getInstance().getRegion();
   pageContext.setAttribute("rlist", rlist);
%>

<body>
  <!--  <div class="icon-bar">
      <a href="#" class="facebook"><i class="fa fa-facebook"></i></a> <a href="#" class="twitter"><i
         class="fa fa-twitter"
      ></i></a> <a href="#" class="google"><i class="fa fa-google"></i></a> <a href="#" class="linkedin"><i
         class="fa fa-linkedin"
      ></i></a> <a href="#" class="phone"><i class="fas fa-phone-alt"></i></a>
   </div> -->
   
<%@ include file="header.jsp"%>
   
   <div id="wrap">
      <div id="container">
         <div id="signupTitle"><i class="fas fa-clipboard-list"></i>&nbsp;회원가입</div>
      <form action="insertMember.member" method="post" enctype="multipart/form-data" onsubmit="return form_check()">
      <br>
       <hr>
      <br>
         <table class="type1">
               <tr>
                  <td class="signupText"><span class="symbol">*</span>아이디
                  <span id="id_check"></span>
                  </td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="text" name="id" id="id" autofocus />
                  </td>
               </tr>
               <tr>
                  <td class="signupText"><span class="symbol">*</span>비밀번호
                  <span id="pwd_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="password" name="pwd" id="pwd" />
                  </td>
               </tr>
               
               <tr>
                  <td class="signupText"><span class="symbol">*</span>비밀번호확인
                  <span id="chkpwd_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="password" id="chkpwd" />
              </td>
               </tr>

               <tr>
                  <td class="signupText"><span class="symbol">*</span>닉네임
                  <span id="nick_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="text" name="nick" id="nick" />
                   </td>
               </tr>

               <tr>
                  <td class="signupText"><span class="symbol">*</span>이름
                  <span id="name_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="text" name="name" id="name" maxlength="30" />
                     </td>
               </tr>
               <tr>
                  <td class="signupText"><span class="symbol">*</span>생년월일
                  <span id="date_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="date" name="b_date" id="b_date" />
                  </td>
               </tr>
               
               <tr>
                  <td class="signupText"><span class="symbol">*</span>성별
                   <span id="gender_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput">
                 <select name="gender" id="gender">
                        <option value="">선택</option>
                        <option value="1">남</option>
                        <option value="2">여</option>
                  </select>
            </td>
               </tr>

               <tr>
                  <td class="signupText"><span class="symbol">*</span>이메일
                  <span id="email_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput2">
                  <input type="text" name="email01" id="email01" maxlength="29" > @
                   <input type="text" name="email02" id="email02" value="naver.com" maxlength="20" disabled > <!--디폴트값이 naver.com  --> 
                   <select name="email03" id="selectEmail">                       
                   <option value="1">직접입력</option>
                        <option value="naver.com" selected>naver.com</option>
                        <option value="hanmail.net">daum.net</option>
                        <option value="nate.com">nate.com</option>
                        <option value="gmail.com">gmail.com</option>
                  </select>
                   </td>
               </tr>
               
               <tr>
                  <td class="signupText"><span class="symbol">*</span>전화번호
                  <span id="tel_check"></span></td>
               </tr>
               <tr>
                  <td class="signupinput"><input type="tel" name="tel" id="tel" />
                  <input type="button" id="ser" value="인증받기" onclick="tel_check();" />
                     </td>
               </tr>
               
               <tr>
                  <td class="signupText"><span class="symbol">*</span>내동네 설정
                   <span id="city_check"></span></td>
               </tr>
            
               <tr>
                  <td class="signupinput">
                    시/도
                     <select id="region">
                     <option value="">선택</option>
                    <c:forEach var="r1" items="${rlist}">
                <option value="${r1.region_id}">${r1.region}</option>
               </c:forEach>
                     </select>
                     
                      시/군/구
                     <select name="city" id="city">
                     <option value="">선택</option>
                   </select>
               </td>
               </tr>
               
               <tr>
                  <td class="signupText" ><span class="symbol">*</span>사진</td>
               </tr>
               <tr>
                  <td class="signupinput">
                     <div class="input_wrap">
                        <a href="javascript:" onclick="fileUploadAction();" class="my_button"></a>
                        <input type="file" id="picture" name="picture"/>
                        <br>
                     </div>
                     <div class="imgs_wrap">
                        <img id="img" />
                     </div>
                     
                  </td>
               </tr>
            </table>
            
           <br><hr><br>
           <input type="submit" value="회원가입" /> <input type="reset" value="취소" /><br>
         </form>
      </div> <!--  cotainer end -->
   </div>   <!--  wrap end -->
   
 <%@ include file="footer.jsp"%>
   
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/signupPage.js?ver=1"></script>

</body>
</html>