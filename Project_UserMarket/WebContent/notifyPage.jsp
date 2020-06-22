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
		
		<script>
			function notify_Check() {
				alert("접수되었습니다.")
			}
		</script>
		
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>고객센터</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/notify.css?after" media="all and (min-width:720px)"/>
<link rel="stylesheet" href="css/m_notify.css?after" media="all and (max-width:720px)"/>

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 --->
</head>

<body>
<%@include file="header.jsp"%>

	<div id="wrap">
		<div id="container">
			<div class="notify_Title">신고접수</div>
			<div class="notify_Ptag">
				신고센터는 소비자가 직접
				<p>신고</p>
				하는 것입니다.<br> 홈페이지 관련 이용및 기타 관련상황은
				<p>전화상담</p>
				을 통해서 해주시길 바랍니다<br>
				<p>1년이하의 징역또는 300만원이하의 벌금</p>
				에 처해질수 있습니다<br>
			</div>
			
			<div class="notify_Report">
			<div class="notify_SubTitle">신고내용</div>
				<form action="">
					<div class="notify_Report_Select">
						<span class="notify_Report_Title">신고분류</span>
						<select name="" id="">
							<option value="가구">가구</option>
							<option value="가전/디지털">가전/디지털</option>
							<option value="여성의류">여성의류</option>
							<option value="남성의류">남성의류</option>
							<option value="신발/가방/잡화">신발/가방/잡화</option>
							<option value="출산/유아/완구">출산/유아/완구</option>
							<option value="반려동물용품">반려동물용품</option>
							<option value="도서/문구">도서/문구</option>
							<option value="예술/문화">예술/문화</option>
							<option value="생활/여행">생활/여행</option>
							<option value="스포츠/레저">스포츠/레저</option>
						</select>
					</div>
					<textarea name="report" placeholder="신고할 내용을 입력해주세요"></textarea><br />
					<input type="submit" value="보내기" onclick="notify_Check()"/> 
					<input type="reset" value="취소" />
				</form>
			</div>
			
		</div> <!--  container end -->
	</div> <!--  wrap end -->

<%@include file="footer.jsp"%>
</body>
</html>
