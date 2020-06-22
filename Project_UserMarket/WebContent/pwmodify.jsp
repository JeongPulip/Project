<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>비밀번호 변경</title>

<link rel="stylesheet" href="css/main.css?after"  media="all and (min-width:720px)"/>
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/pwmotify.css?after"  media="all and (min-width:720px)"/>
<link rel="stylesheet" href="./css/m_pwmotify.css?after" type="text/css" media="all and (max-width:720px)">

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrap">
      <div id="container">
         <div class="pwcheck_block">
            <div class="pwcheck_Title">비밀번호변경</div>
            <form action="updatePWD.member" method="post" onsubmit="return checkPWD();">
               <div class="pwcheck_input_block">
                  <div class="pwcheck_Input_Title">현재 비밀번호</div>
                  <div class="pwcheck_Input">
                     <input type="password" id="chkpwd" autofocus />
                  </div>
               </div>
               <div class="pwcheck_input_block">
                  <div class="pwcheck_Input_Title">변경 비밀번호</div>
                  <div class="pwcheck_Input">
                     <input type="password" name="npwd" id="npwd" />
                  </div>
               </div>
               <div class="pwcheck_input_block">
                  <div class="pwcheck_Input_Title">비밀번호 확인</div>
                  <div class="pwcheck_Input">
                     <input type="password" id="chknpwd" />
                  </div>
               </div>
               <div class="pwcheck_button">
                  <input type="button" onclick="location.href='myPage.jsp'" value="뒤로" />
                  <input type="submit" value="확인" />
               </div>
            </form>
         </div>
      </div>
   </div>
   <jsp:include page="footer.jsp"></jsp:include>
   
   <script src="js/jquery-3.4.1.js"></script>
   <script type="text/javascript" src="js/pwmodify.js"></script>

</body>
</html>