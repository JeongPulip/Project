<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	if(session.getAttribute("id") != null){
		%>
		<script>
			alert("로그인 후 이용해주세요.");
			location.replace("loginPage.jsp");
		</script>
		<%
	} %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>아이디 / 비밀번호 찾기</title>
<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)"/>
<link rel="stylesheet" href="./css/m_idpwSearch.css?after" type="text/css" media="all and (max-width:720px)"/>
<link rel="stylesheet" href="css/idpwSearch.css?after" media="all and (min-width:720px)" />
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"/>
<!-- 폰트 -->
</head>

<body>
<%@ include file="header.jsp"%>

	<div id="wrap">
		<div id="container">
			<div class="research_IDPW">
				<div class="research_Title">아이디 찾기</div>
				<form action="findUserID.member" method="post">
					<div class="research_Block">
						<div class="research_Label">이름</div>
						<input class="research_Input" type="text" name="serName1" />
					</div>
					<div class="research_Block">
						<div class="research_Label">전화번호</div>
						<input type="text" name="serTel1" id="serTel1" /><br>
					</div>
					<input class="research_Btn" type="submit" value="아이디 찾기" />
				</form>
			</div>
			<div class="research_IDPW">
				<form action="findUserPWD.member" method="post">
					<div class="research_Title">비밀번호 찾기</div>
					<div class="research_Block">
						<div class="research_Label">이름</div>
						<input type="text" name="serName2" />
					</div>
					<div class="research_Block">
						<div class="research_Label">아이디</div>
						<input type="text" name="serID" />
					</div>
					<div class="research_Block">
						<div class="research_Label">전화번호</div>
						<input type="text" name="serTel2" id="serTel2" />
					</div>
					<input class="research_Btn" type="submit" value="비밀번호 찾기" />
				</form>
			</div>
		</div>
	</div>
	
<%@ include file="footer.jsp"%>
	
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
   $('input').keydown(function() {
	   if (event.keyCode === 13) {
	     event.preventDefault();
	   };
	 }); // 엔터 입력시 자동 submit 막기
	 
	$('#serTel1').blur(function() { //휴대전화 입력란의 '-' 제거
		var dtel = $('#serTel1').val().replace(/\-/g, '');
		$('#serTel1').val(dtel);
	})
	
	$('#serTel2').blur(function() { //휴대전화 입력란의 '-' 제거
		var dtel = $('#serTel2').val().replace(/\-/g, '');
		$('#serTel2').val(dtel);
	})
</script>

</body>
</html>
