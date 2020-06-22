<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>공지사항</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link  rel="stylesheet"href="./css/m_notice.css?after" type="text/css"  media="all and (max-width:720px)">	
<link rel="stylesheet" href="css/noticeList.css?after" media="all and (min-width:720px)"/>

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<body>
<jsp:include page="header.jsp"/>

		<div id="wrap">
			<div id="container">
			
				<div class="noticelist_Block">
					<div class="noticelist_Title_Block">
						<div class="noticelist_Title"> 클로버마켓 오픈 안내!!</div>
						<div class="noticelist_Title_Date">2020-03-20</div>
					</div>
					<div class="noticelist_Value">
						<p> 행운을 찾는 가장 빠른 방법!! <br /><br /><br />
						
						<img src="img/logo.png" alt="" /><br /><br />
						클로버 마켓이 드디어 오픈했습니다! (짝짝짝짝짝!!)<br />
						많은 관심부탁드리고 앞으로도 노력하는 클로버 마켓이 되겠습니다! <br />
						<br /><br />
						사용중에 불편하신 사항이나 '이런 기능이 추가되었으면 좋겠다'라는 점은 <br /><br />
						고객센터로 연락바랍니다!  <br /><br />
						클로버마켓을 이용하시는 모든 사용자분들 <br /><br />
						저희가 가장 빨리 행운을 찾아드리겠습니다. <br /><br />
						감사합니다!!
						</p>
						
						<br />
						<br />
						<br />
					</div>
				</div>
				
				<div class="noticelist_Btn">
					<button onclick="location.href='noticeListPage.jsp'">목 록</button>
				</div>
				
			</div> <!--  end container -->
		</div> <!--  end wrap -->

<jsp:include page="footer.jsp"/>
</body>
</html>