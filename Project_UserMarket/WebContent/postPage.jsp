<!--Copyright (c) 2020 by miyataku (https://codepen.io/miyataku_jp/pen/gObLrvB)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  -->
<%@page import="kr.co.web.board.model.PostAll2VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%
   if(session.getAttribute("id") == null){ %>
<script>
         alert("로그인 후 이용해주세요.");
         location.replace("loginPage.jsp");
      </script>
<% } %>
<%
   PostAll2VO postInfo = (PostAll2VO)request.getAttribute("pa2VO");

   if(session.getAttribute("postInfo") != null){
      session.removeAttribute("postInfo");
      System.out.println("postInfo 세션 삭제");
   }
   session.setAttribute("postInfo", postInfo);   // 게시글 정보 세션 생성 ==> 나중에 list에서 세션 여부확인후 제거
   System.out.println("postInfo 세션 생성");
%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>물품 페이지</title>
<link href="css/main.css?after" type="text/css" rel="stylesheet" media="all and (min-width:720px)">
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)"/>
<link rel="stylesheet" href="css/post.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_post.css?after" media="all and (max-width:720px)" />
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
<!--폰트 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
/>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="wrap">
		<div id="container">
			<div class="post_Title">
				게시글 정보 <font id="post_Pop_search" color="blue" size="5"> NO.<span id="post_id">${pa2VO.postVO.postId}</span>&nbsp;조회수
					: ${pa2VO.postVO.hit}
				</font>
			</div>
			<!-- 게시글의 정보  -->
			<%-- <div class="post_Title">게시글 정보 no.<span id="post_id">${pa2VO.postVO.postId}</span></div> <!-- 게시글의 정보  --> --%>
			<div class="post_Article">
				<div class="slideshow-container">
					<c:forEach var="imageVO" items="${pa2VO.listImageVO}">
						<ul class="mySlides1">
							<li><img src="${imageVO.imgPath}" class="one" width="400px"></li>
						</ul>
					</c:forEach>
					<div class="post_Img_click">
						<a class="prev" onclick="plusSlides(-1, 0)">&#10094;</a> 
						<a class="next" onclick="plusSlides(1, 0)">&#10095;</a>
					</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 제목 ]</div>
					<div class="post_User_Value">${pa2VO.postVO.title}</div>
					<div class="like-btn"></div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 작성자 ]</div>
					<div class="post_User_Value">${writerNick}</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 가격 ]</div>
					<div class="post_User_Value">${pa2VO.sPostVO.price} 원</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 거래 상태 ]</div>
					<div class="post_User_Value">
						<c:set var="prog" value="${pa2VO.sPostVO.progress}" />
						<c:choose>
							<c:when test="${prog eq '0'}">
                        판매(구매) 중
                     </c:when>
							<c:when test="${prog eq '1'}">
                        거래 진행 중
                     </c:when>
							<c:when test="${prog eq '2'}">
                        거래 완료
                     </c:when>
							<c:otherwise>
                        ???
                     </c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 카테고리 ]</div>
					<div class="post_User_Value">${pa2VO.categoryVO.ctg_name}</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 지역 ]</div>
					<div class="post_User_Value">${pa2VO.cityVO.region} ${pa2VO.cityVO.city}</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 거래종류 ]</div>
					<div class="post_User_Value">
						<c:set var="sType" value="${pa2VO.sPostVO.sType}" />
						<c:choose>
							<c:when test="${sType eq '0'}">
								<input type="radio" name="deliver" value="0" checked="checked" disabled="disabled" /> 무관
                        <input type="radio" name="deliver" value="1" disabled="disabled" />직거래
                        <input type="radio" name="deliver" value="2" disabled="disabled" />택배 거래
                     </c:when>
							<c:when test="${sType eq '1'}">
								<input type="radio" name="deliver" value="0" disabled="disabled" /> 무관
                        <input type="radio" name="deliver" value="1" checked="checked"
									disabled="disabled"
								/>직거래
                        <input type="radio" name="deliver" value="2" disabled="disabled" />택배 거래
                     </c:when>
							<c:when test="${sType eq '2'}">
								<input type="radio" name="deliver" value="0" disabled="disabled" /> 무관
                        <input type="radio" name="deliver" value="1" disabled="disabled" />직거래
                        <input type="radio" name="deliver" value="2" checked="checked"
									disabled="disabled"
								/>택배 거래
                     </c:when>
						</c:choose>
					</div>
				</div>
				<div class="post_User_Block">
					<div class="post_User_Title">[ 내용 ]</div>
				</div>
				<div class="post_User_Block">
					<%-- <textarea  disabled="disabled">${article.content}</textarea> --%>
					<div class="post_User_Value">${pa2VO.postVO.content}</div>
				</div>
				<div class="post_User_Block">
					<!--
                  <input type="button" onclick="location.href='editPage.jsp'" value="수정" /> 
                  <input type="button" onclick="location.href='#'" value="삭제"/>
                  -->
					<input type="button" onclick="location.href='list.board'" value="목록" />
					<c:set var="userId" value="${id}" />
					<%--로그인한 접속자(세션) --%>
					<c:set var="writer" value="${pa2VO.postVO.userId}" />
					<%--작성자 --%>
					<c:if test="${userId eq writer}">
						<input type="button" onclick="location.href='modify.board?postId=${pa2VO.postVO.postId}'" value="수정"	/>
						<input type="button" value="삭제" onclick="return delchk(${pa2VO.postVO.postId})" />
					</c:if>
					<input type="button" onclick="location.href='write.board'" value="새글 작성" />
				</div>
			</div>
			<!-- class post_User end -->
			<hr>
			<br>
			<div class="comment">
				<!-- 댓글관리 -->
				<div class="comment_Title" id="comment_focus">댓글</div>
				<br> <input type="hidden" id="board_id" name="board_id" value="test" /> <input
					type="hidden" id="post_user_id" value="${pa2VO.postVO.userId}"
				/> <input type="hidden" id="user_id" value="${id}" /> <input type="hidden" id="user_nick"
					value="${nick}"
				/> <input type="hidden" id="ft" value="${param.c}" />
				<table id="reply_area">
					<tr reply_type="all">
						<td colspan="4"></td>
					</tr>
				</table>
				<br>
				<table id="reply_input_area">
					<tr>
						<td rowspan="2">${nick}</td>
						<td rowspan="2"><textarea id="reply_content" name="reply_content"
								placeholder="댓글을 입력하세요."
							></textarea></td>
						<td><input type="checkbox" id="lock" name="comment_lock" /><i class="fas fa-lock"></i><br>
						<br>
						<span class="comment_button"><button id="reply_save" name="reply_save">
									<i class="fas fa-arrow-alt-circle-up fa-2x"></i>
								</button></span></td>
					</tr>
				</table>
			</div>
			<!-- class comment end -->
		</div>
	</div>
	<!-- id wrap end -->
	<jsp:include page="footer.jsp" />
	<script src="./js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="js/postPage.js?ver=1.1"></script>
</body>
</html>