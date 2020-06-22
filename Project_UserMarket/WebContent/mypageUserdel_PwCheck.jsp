<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원탈퇴 확인</title>
<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/m_mypageUserdel_pwCheck.css?after" media="all and (max-width:720px)" />
<link rel="stylesheet" href="css/userDel_PWCheck.css?after" media="all and (min-width:720px)" />

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrap">
      <div id="container">
         <div class="pwcheck_block">
            <div class="pwcheck_Title">회원탈퇴</div>
            <form action="" method="post" onsubmit="return checkPWD();">
               <div class="userDle">비밀번호 입력시 회원이 <p>탈퇴</p>됩니다</div>
               <div class="pwcheck_input_block">
                  <div class="pwcheck_Input_Title">비밀번호 확인</div>
                  <div class="pwcheck_Input">
                     <input type="password" id="chkpwd" autofocus />
                  </div>
               </div>
               <div class="pwcheck_button">
                  <input type="button" onclick="location.href='myPage.jsp'" value="뒤로" />
                  <input type="submit" value="확인" />
               </div>
            </form>
         </div>
         
      </div>
      <!-- container end -->
   </div>
   <!--  wrap end -->
   <jsp:include page="footer.jsp"></jsp:include>
   
   <script src="js/jquery-3.4.1.js"></script>
   <script type="text/javascript">
   function checkPWD() {
		var chkpwd = $('#chkpwd').val();
		
		if(!chkpwd) {// 공백 검사
			alert('탈퇴를 원하시면 비밀번호를 입력하세요');
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
	        	  location.replace('deleteMember.member');
	          } else {
	        	  alert('비밀번호가 맞지 않습니다');
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