<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>비밀번호 확인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/pwCheck.css?after"media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_pwCheck.css?after"media="all and (max-width:720px)" />

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrap">
      <div id="container">
         <div class="pwcheck_block">
            
            <div class="pwcheck_Title">비밀번호 확인</div>
            
            <form action="" method="post" onsubmit="return checkPWD();">
               <div class="pwcheck_input_block">
                  <div class="pwcheck_Input_Title">
                     비밀번호
                  </div>
                  <div class="pwcheck_Input">
                     <input type="password" id="chkpwd" autofocus />
                  </div>
               </div>
               <div class="pwcheck_button">
                  <input type="button" onclick="location.replace('myPage.jsp');" value="뒤로"/>
                  <input type="submit" value="확인" />
               </div>
            </form>
            
         </div>
      </div>
   </div>
   <jsp:include page="footer.jsp"></jsp:include>
   
   <script src="js/jquery-3.4.1.js"></script>
   <script type="text/javascript">
   function checkPWD() {
		var chkpwd = $('#chkpwd').val();
		
		if(!chkpwd) {// 공백 검사
			alert('비밀번호를 입력하세요');
			$('#chkpwd').focus();
			return false;
		}
		
		$.ajax({// 서버로 보내 기존의 비밀번호와 일치하나 검사
	        type: 'post',
	        url: 'checkPWD.member',
	        data: 'chkpwd=' + chkpwd,
	        dataType: 'json',
	        async: false,
	        success: function(result) {
	          if(result) { // 결과 값이 참이면(일치하면)
	        	  location.replace('mypageModify.jsp');
	          } else {
	        	  alert('비밀번호가 맞지 않습니다');
	        	  $('#chkpwd').val("");
	        	  $('#chkpwd').focus();
	          }
	        },
	        error: function() {
	          alert("통신 실패..");
	        }         
	      })// ajax-end
	      
	      return false;
	}
   </script>
   
</body>
</html>