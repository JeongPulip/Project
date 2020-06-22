<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>클로버마켓 :: 행운을 찾는 가장 빠른 방법!</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)"/>
<link rel="stylesheet" href="./css/home.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_home.css?after" type="text/css"  media="all and (max-width:720px)"/>	

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트-->

<script src="./js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="./js/mainHomePage.js?ver1"></script>

</head>

<body>
	<jsp:include page="header.jsp" />

	<div id="wrap">
		<div id="container">
			<div class="slideshow-container">
				<div class="mySlides">
					<div class="numbertext">1 / 3</div>
					<img src="img/main_1.png" style="width: 100%" />
				</div>
				<div class="mySlides">
					<div class="numbertext">2 / 3</div>
					<a href="noticePage.jsp"><img src="img/notice.png" style="width: 100%" /></a>
				</div>
				<div class="mySlides">
					<div class="numbertext">3 / 3</div>
					<a href="https://thecheat.co.kr/rb/?mod=_search" target="_blink" ><img src="img/cheat.png"  style="width: 100%" /></a>
				</div>

					<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
					<a class="next" onclick="plusSlides(1)">&#10095;</a> <br />
				<div style="text-align: center">
					<span class="dot" onclick="currentSlide(1)"></span>
					<span class="dot" onclick="currentSlide(2)"></span>
					<span class="dot" onclick="currentSlide(3)"></span>
				</div>
			</div>
			<!-- class slideshow-container end -->
			<!-- //////// 여기까지 배너  -->
			<!-- 인기게시물  -->
			<div class="section_Popular">
				<div class="mainHome_Popular_Title">인기 게시물</div>
				<%-- ajax 게시글 리스트 삽입 공간 --%>
			</div>
		</div>
		<!-- container end -->
	</div>
	<!-- wrap end -->
	<jsp:include page="footer.jsp" />
</body>
<script>
	// Automatic Slideshow - change image every 3 seconds
	var myIndex = 0;
	carousel();

	function carousel() {
		var i;
		var x = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("dot");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		myIndex++;
		if (myIndex > x.length) {
			myIndex = 1
		}
		for (i = 0; i < dots.length; i++) {
			dots[i].className = dots[i].className.replace(" active", "");
		}
		x[myIndex - 1].style.display = "block";
		dots[myIndex - 1].className += " active";
		setTimeout(carousel, 3000);
	}
</script>
<script>
	var slideIndex = 1;
	showSlides(slideIndex);

	function plusSlides(n) {
		showSlides(slideIndex += n);
	}

	function currentSlide(n) {
		showSlides(slideIndex = n);
	}

	function showSlides(n) {
		var i;
		var slides = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("dot");
		if (n > slides.length) {
			slideIndex = 1
		}
		if (n < 1) {
			slideIndex = slides.length
		}
		for (i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		for (i = 0; i < dots.length; i++) {

			dots[i].className = dots[i].className.replace(" active", "");
		}
		slides[slideIndex - 1].style.display = "block";
		dots[slideIndex - 1].className += " active";
	}
</script>

</html>