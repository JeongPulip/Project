<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%   if (session.getAttribute("id") != null) { %>
   <script>
   alert("이미 로그인하셨습니다.");
   location.href = "mainHomePage.jsp";
   </script>
<% } %>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/login.css?after" media="all and (min-width:720px)"/>
<link rel="stylesheet" href="css/m_login.css?after" media="all and (max-width:720px)"/>

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
<title>로그인</title>
</head>

<body>
<%@ include file="header.jsp"%>

   <div id="wrap">
      <div id="container">
         <div id=loginTitle>로그인</div>
         <div class="login">
            <br />
            <div id="input">
               <form action="" method="post" onsubmit="return doLogin()">
                  <!-- 회원정보가 맞다면 이동 -->
                  <input id="id" type="text" name="id" placeholder="아이디" autofocus /> <br /> 
                  <input id="pwd" type="password" name="pwd" placeholder="비밀번호" /> <br /> 
                  <br>
                  <div id="login_check"></div>
                  <input id="loginBtn" type="submit" width="200" value="로그인" />
               </form>
            </div>
            <br>
         
            <br>
            <div id="userSupport">
               <button id="search" onclick="location.href='idpwSearch.jsp'">ID/PW찾기</button>
               <button id="userAdd" onclick="location.href='signupPage.jsp'">회원가입</button>
            </div>
            <br>
         </div> <!-- class login end -->
      </div> <!-- id container end -->
   </div> <!--  id wrap end -->
   
<%@ include file="footer.jsp"%>
      
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/loginPage.js"></script>

</body>
</html>