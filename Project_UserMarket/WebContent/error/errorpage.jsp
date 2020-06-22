<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% response.setStatus(HttpServletResponse.SC_OK); %>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>COMING SOON</title>
<style>

body, html {
	height: 100%;
	margin: 0;
}

.bgimg {
	background-image: url('./img/leaf.jpg');
	height: 100%;
	background-position: center;
	background-size: cover;
	position: relative;
	color: white;
	font-family: "Courier New", Courier, monospace;
	font-size: 25px;
}

.topleft {
	position: absolute;
	top: 0;
	left: 16px;
}

.bottomleft {
	position: absolute;
	bottom: 0;
	left: 16px;
}

.middle {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
}

hr {
	margin: auto;
	width: 40%;
}

img{
	width: 50%;
}

.error_btn{
	position: absolute;
	top: 70%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
}

.error_btn>button{
	  width: 200px;
    margin: 0 auto;
    height: 50px;
    background: #9BCE58;
    color: white;
    outline: none;
    border: 3px solid;
    font-size: 20px;
    font-weight: bold;
    transition: 0.3s;
}

.error_btn>button:hover{
	 background: white;
   color: #9BCE58;
}
</style>
<body>
	<div class="bgimg">
		<div class="topleft">
			<a href="mainHomePage.jsp"><img src="img/logo.png" alt="" /></a>
		</div>
		<div class="middle">
			<h1>COMING SOON</h1>
			<hr>
			<p id="demo" style="font-size: 30px"></p>
		</div>
		<div class="error_btn">
			<button onclick="location.href='mainHomePage.jsp'">돌아가기</button>
		</div>
	
	</div>
	<script>
// Set the date we're counting down to
var countDownDate = new Date("Jan 5, 2021 15:37:25").getTime();

// Update the count down every 1 second
var countdownfunction = setInterval(function() {

  // Get todays date and time
  var now = new Date().getTime();
  
  // Find the distance between now an the count down date
  var distance = countDownDate - now;
  
  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  
  // Output the result in an element with id="demo"
  document.getElementById("demo").innerHTML = days + "d " + hours + "h "
  + minutes + "m " + seconds + "s ";
  
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(countdownfunction);
    document.getElementById("demo").innerHTML = "EXPIRED";
  }
}, 1000);
</script>
</body>
</html>
