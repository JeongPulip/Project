<%@page import="kr.co.web.etc.model.MyListDAO"%>
<%@page import="kr.co.web.etc.model.MyListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   /*    session.setAttribute("id", "asd");
      session.setAttribute("nick", "가나다라"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="main.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" 
integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
crossorigin="anonymous">

<title>header</title>
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
   if (!'${id}') { //세션 값이 없으면
      $(function() {
         $("#userlogin")
               .html(
                     "<a href=\"loginPage.jsp\">로그인</a> | <a href=\"signupPage.jsp\">회원가입</a>");
      })
   } else {
      <%     
         String id =(String) session.getAttribute("id");
        MyListVO mlvo = MyListDAO.getInstance().getMyList(id);
        pageContext.setAttribute("mlvo", mlvo);
   %>

      $(function() {
         $("#userlogin")
               .html(
               "<a href='myPage.jsp'class='heder_Icon_Notice' ><i class='fas fa-bell' id='header_Icon_Label'></i><span id='header_New_Notice'>${mlvo.n_count } | </span></a><a href=\"myPage.jsp\"> ${nick} 님이 접속하셨습니다.</a> | <a href=\"doLogout.member\">로그아웃</a>");
      })
   }
</script>

</head>
<body>
   <div id="header">
      <div id="headerUp">
         <div id="title">
            <a href="mainHomePage.jsp"><img class="title_img" src="img/logo.png" alt="" /></a>
         </div>
         <div id="inputSearch">
            <form action="search.board" id="searchForm" name="search" method="post">
               <div id="header_Search_Block">
                  <input type="text" id="textSearch" name="inputSearch" placeholder="검색어를 입력해주세요" />
                  <input type="submit" id="header_Search_Icon" value="&#xf002" />
               </div>
            </form>
         </div>
         
         <div id="userlogin"></div>
      </div>
      
      <div id="headerRow">
         <div id="nav">
            <a href="mainHomePage.jsp">
               <div class="icon">
                  <i class="fa fa-home"></i> <i class="fa fa-home"></i>
               </div> 홈
            </a> <a href="list.board" >
               <div class="icon">
                  <i class="fa fa-book"></i> <i class="fa fa-book"></i>
               </div> 카테고리
            </a> <a href="noticeListPage.jsp">
               <div class="icon">
                  <i class="fa fa-bullhorn"></i> <i class="fa fa-bullhorn"></i>
               </div> 공지사항
            </a> <a href="notifyPage.jsp">
               <div class="icon">
                  <i class="fa fa-question-circle"></i> <i class="fa fa-question-circle"></i>
               </div> 고객센터
            </a> <a href="myPage.jsp">
               <div class="icon">
                  <i class="fa fa-id-badge"></i><i class="fa fa-id-badge"></i>
               </div> 회원정보
            </a>
         </div>
      </div>
   </div>
</body>
</html>