<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">

<link rel="stylesheet" href="css/notice.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_noticeList.css?after" media="all and (max-width:720px)" />

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
<style>

</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="wrap">
		<div id="container">
			<div class="notice_Title">공지사항</div>
			
				<table class="notice_Table">
					<tr>
						<th class="notice_List_Title">내용</th>
						<th class="notice_List_Date">등록일</th>
					</tr>
					<tr class="notice_Hover">
						<td class="notice_List_Titel_In"><a href="noticePage.jsp">클로버마켓 오픈!</a></td>
						<td class="notice_List_Date_In">20-03-20</td>
					</tr>
					<tr class="notice_Hover">
						<td class="notice_List_Titel_In">코로나19 관련 폭리 및 매점매석 금지안내 </td>
						<td class="notice_List_Date_In">20-03-20</td>
					</tr>
					<tr class="notice_Hover">
						<td class="notice_List_Titel_In">클로버마켓 아이디 설정 안내</td>
						<td class="notice_List_Date_In">20-03-20</td>
					</tr>
					<tr class="notice_Hover">
						<td class="notice_List_Titel_In">직거래 사기 예방 수칙</td>
						<td class="notice_List_Date_In">20-03-20</td>
					</tr>
					<tr class="notice_Hover">
						<td class="notice_List_Titel_In">이용약관 변경 공지</td>
						<td class="notice_List_Date_In">20-03-20</td>
					</tr>
					
				</table>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
