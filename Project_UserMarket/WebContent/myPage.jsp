<%@page import="kr.co.web.etc.model.MyListVO"%>
<%@page import="kr.co.web.etc.model.MyListDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원정보</title>
<%
   String tel = "";

   if(session.getAttribute("id") == null){
      %>
<script>
         alert("로그인 후 이용해주세요.");
         location.replace("loginPage.jsp");
      </script>
<%
   } else {
      tel = (String)session.getAttribute("tel");
      tel = tel.substring(0,3) + "-" + tel.substring(3,tel.length()-4) + "-" + tel.substring(tel.length()-4);
      String id =(String) session.getAttribute("id");
     MyListVO mlvo = MyListDAO.getInstance().getMyList(id);
     pageContext.setAttribute("mlvo", mlvo);
   }
%>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/mypage.css?after1" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_mypage.css?after" media="all and (max-width:720px)" />
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
<!-- 폰트 -->
</head>
<body>
   <%@ include file="header.jsp"%>
   <div id="wrap">
      <div id="container">
      
      <!-- 내 정보 페이지 -->
         <div class="mypage_Title">내정보</div>
         <div class="dropuser_Btn">
            <input type="button" onclick="location.href='mypageUserdel_PwCheck.jsp'" value="회원탈퇴">
         </div>
         <div class="mypage_User">
            <!-- user의 사진과 아이디 등의 정보를 입력 -->
            <div class="mypage_User_Img"> <!-- 사용자의 사진  -->
                        
               <c:choose>
                  
                  <c:when test="${empty prf_path}">
                     <img src="img/default.png" alt="없음.jpg" />
                  </c:when>
                  
                  <c:otherwise>
                     <img src="${prf_path}"/>
                  </c:otherwise>
                  
               </c:choose>
               
            </div>
            
            <div class="mypage_User_Info">
               <!-- 사용자의 아이디등의 정보 출력 -->
               <div class="mypage_User_Info_ID">${name }님의 회원정보</div>
               <div class="mypage_User_Info_Block">
                  <div class="mypage_User_Info_Title">닉네임</div>
                  <div class="mypage_User_Info_Input">${nick }</div>
               </div>
               <div class="mypage_User_Info_Block">
                  <div class="mypage_User_Info_Title">전화번호</div>
                  <div class="mypage_User_Info_Input">${tel2 }</div>
               </div>
               <div class="mypage_User_Info_Block">
                  <div class="mypage_User_Info_Title">이메일</div>
                  <div class="mypage_User_Info_Input">${email }</div>
               </div>
               <div class="mypage_User_Info_Block">
                  <div class="mypage_User_Info_Title">내 동네</div>
                  <div class="mypage_User_Info_Input">${region} ${city}</div>
               </div>
               <div class="mypage_Modify_Btn">
                  <button onclick="location.href='mypagePwCheck.jsp'">수정하기</button>
                  <button onclick="location.href='pwmodify.jsp'">비밀번호변경</button>
               </div>
            </div>
            <!-- mypage_User_Info end -->
         </div>
         
         
         <!-- tabMenu -->
         <div class="mypage_User_Board">
            <div class="mypage_User_tab">
               <button class="mypage_tablink" onclick="openPage(event,'myBoard')">
                  <i class="far fa-list-alt"></i> 내 게시글(${mlvo.p_count })
               </button>
               <button class="mypage_tablink" onclick="openPage(event,'myComment')">
                  <i class="far fa-comment-alt"></i> 내 댓글(${mlvo.c_count })
               </button>
               <button class="mypage_tablink" onclick="openPage(event,'myList')">
                  <i class="far fa-heart"></i> 찜목록(<span id="p_count">${mlvo.j_count }</span>)
               </button>
               <button class="mypage_tablink" onclick="openPage(event,'notice')">
                  <span class="notice_Num">${mlvo.n_count }</span> 알림
               </button>
            </div>
         </div>
         <!-- tabs메뉴 script -->
         <script>
         function openPage(event, mypage_list) {
            var i, tabcontent,tablink;
            tabcontent= document.getElementsByClassName("mypage_tabcontent");
            for(i=0; i < tabcontent.length;i++){
               tabcontent[i].style.display="none";
            }
            
            tablink = document.getElementsByClassName("mypage_tablink");
            for(i=0 ; i < tablink.length; i++){
               tablink[i].className = tablink[i].className.replace(" active","");
            }
            document.getElementById(mypage_list).style.display = "block";
            event.currentTarget.className+= " active";
         }   
         </script>
         
         <!-- 내 게시물 tab  -->
         
         <div id="myBoard" class="mypage_tabcontent">
            <!-- 내 게시물 테이블 해더 -->
              <table class="mypage_Default_Table">
               <tr class="mypage_MyBoard_Block">
                  <th class="myboard_I">순번</th>
                  <th class="myboard_Num">게시글번호</th>
                  <th class="myboard_Title">글제목</th>
                  <th class="myboard_CNum">댓글</th>
                  <th class="myboard_Hit">조회</th>
                  <th class="myboard_JNum">찜</th>
                  <th class="myboard_WDate">작성일</th>
                  <th class="myboard_P">상태</th>
               </tr>
               
               <c:set var="i" value="${mlvo.p_count }" />
               <c:forEach var="vo" items="${mlvo.my_post}">
               <tr class="tr_Hover" onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;" > 
                     <td class="myboard_I">${i}</td>
                     <td class="myboard_Num">${vo.post_id}</td>
                     <td class="myboard_Title">${vo.title}</td>
                     <td class="myboard_CNum">${vo.c_num}</td>
                     <td class="myboard_Hit">${vo.hit}</td>
                     <td class="myboard_JNum">${vo.j_num}</td>
                     <td class="myboard_WDate">${vo.w_date}</td>
                     <td class="myboard_P">${vo.p}</td>
               </tr>
                <c:set var="i" value="${i-1}" />
               </c:forEach>
            </table>
         </div>
         
            <!-- 내 댓글  tab-->
            
         <div id="myComment" class="mypage_tabcontent">
         
            <table class="mypage_Default_Table">
               <tr class="mypage_MyBoard_Block">                        
                         <th class="commnet_num">번호 </th>                
                         <th class="commnet_value">내용</th>               
                         <th class="commnet_date">작성일</th>            
                         <th class="commnet_title">원문: 제목 [댓글수]</th>      
              </tr>
                   <c:set var="i" value="${mlvo.c_count }" />      
            <c:forEach var="vo" items="${mlvo.my_comment}">
               <tr onclick="location.href='content.board?postId=${vo.post_id}&c=${vo.cmt_id}'"
                class="tr_Hover">
                  <td class="commnet_num">${i}</td>
                  <td class="commnet_value">${vo.content}</td>
                  <td class="commnet_date">${vo.w_date}</td>
                  <td class="commnet_title">원문: ${vo.p_title} <span class="myboard_CNum">[${vo.c_num}]</span></td>
               </tr>
               <c:set var="i" value="${i-1}" />
            </c:forEach>   
            </table>
         </div>
         
         
         <!-- 찜목록  tab-->
         
         <div id="myList" class="mypage_tabcontent">
            
             <table class="mypage_Default_Table">
               <tr class="mypage_MyBoard_Block">
                  <th class="myboard_I_a">순번</th>
                  <th class="myboard_Num_a">게시글번호</th>
                  <th class="myboard_Title_a">글제목</th>
                  <th class="myboard_CNum_a">댓글</th>
                  <th class="myboard_Hit_a">조회</th>
                  <th class="myboard_JNum_a">찜</th>
                  <th class="myboard_WDate_a">판매가격</th>
                  <th class="myboard_P_a">상태</th>
                  <th class="myboard_pop">찜</th>
               </tr>
               
               <c:set var="i" value="${mlvo.j_count }" />
               <c:forEach var="vo" items="${mlvo.my_jjim}">
               <tr class="tr_Hover"> 
                     <td class="myboard_I_a" onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${i}</td>
                     <td class="myboard_Num_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.post_id}</td>
                     <td class="myboard_Title_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.title}</td>
                     <td class="myboard_CNum_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.c_num}</td>
                     <td class="myboard_Hit_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.hit}</td>
                     <td class="myboard_JNum_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.j_num}</td>
                     <td class="myboard_WDate_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.price}원</td>
                     <td class="myboard_P_a"onclick="location.href='content.board?postId=${vo.post_id}'"
                style="cursor: pointer;">${vo.p}</td>
                     <td class="myboard_pop"> <button class="delJJim" name="${vo.post_id}">찜 취소</button> </td>
               </tr>
                <c:set var="i" value="${i-1}" />
               </c:forEach>
            </table>
            </div>
         
      <!-- 내 알림 -->
         <div id="notice" class="mypage_tabcontent">
               <div class="mypage_myBoard_All_Check">
                  <button id="mypage_Notice_All_Check" name="checkAll"> 전체 확인</button>
               </div>
               <table class="mypage_Default_Table">
                     <tr class="mypage_MyBoard_Block">
                        <th class="myboard_I">번호</th>
                        <th class="myboard_Title">내용</th>
                        <th class="myboard_Ntime">시간</th>
                        <th class="myboard_Check">확인</th>
                     </tr>
                  	<c:set var="i" value="${mlvo.n_count }" />
             		<c:forEach var="vo" items="${mlvo.my_notice}">
                     <tr class="tr_Hover">
                        <td class="myboard_I" onclick="location.href='content.board?postId=${vo.post_id}&c=${vo.cmt_id}'">${i}</td>
                        <td class="myboard_Title_b" onclick="location.href='content.board?postId=${vo.post_id}&c=${vo.cmt_id}'">
                        ${vo.content}&nbsp;<span id="notice_Point">댓글[${vo.count}]</span>&nbsp;이 달렸습니다.</td>
                        <td class="myboard_Ntime" onclick="location.href='content.board?postId=${vo.post_id}&c=${vo.cmt_id}'">${vo.ntime }</td>
                        <td><button class="checkN" id="${vo.post_id}" name="${vo.cmt_id}"> 확인</button></td>
                     </tr>
               <c:set var="i" value="${i-1}" />
               </c:forEach>
               </table>
            </div>
         </div>
      </div>
   
   <%@ include file="footer.jsp"%>
   <script src="js/jquery-3.4.1.js"></script>
<script>

$(document).on("click", "button[class='delJJim']", function() {
	  if(!confirm('찜목록에서 삭제하시겠습니까?')){
		return false;
		} 
	  
	  var pid = $(this).attr("name");

	  $.ajax({ //ajax 호출 
       type: 'post',
       url: 'deleteJJim.etc',
       data: 'post_id=' + pid,
       dataType: 'json',
//       async: false,
       success: function(arrj) {
     	  var k = arrj.length; // 갱신된 찜목록 수
          $('#myList').contents().remove(); // 찜목록 초기화
          
          var content =
        '	  <table class="mypage_Default_Table">	'
        +'  <tr class="mypage_MyBoard_Block">	'
        +'     <th class="myboard_I">순번</th>	'
        +'    <th class="myboard_Num">게시글번호</th>	'
        +'    <th class="myboard_Title">글제목</th>	'
        +'    <th class="myboard_CNum">댓글</th>	'
        +'    <th class="myboard_Hit">조회</th>	'
        +'    <th class="myboard_JNum">찜</th>	'
        +'    <th class="myboard_WDate">판매가격</th>	'
        +'    <th class="myboard_P">상태</th>	'
        +'    <th class="myboard_pop">찜</th>	'
        +' </tr>	'	;
          
          $.each(arrj, function(i, vo){
        	  var j = k - i;
   		 content +=      
   		'	 <tr class="tr_Hover"> 		'
        +'        <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
       	+'   style="cursor: pointer;">' + j + '</td>	'
       	+'         <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
    	+'   style="cursor: pointer;">' + vo.post_id + '</td>	'
      	+'         <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'    style="cursor: pointer;">' + vo.title + '</td>	'
        +'          <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'      style="cursor: pointer;">' + vo.c_num + '</td>	'
        +'          <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'      style="cursor: pointer;">' + vo.hit + '</td>	'
        +'         <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'       style="cursor: pointer;">' + vo.j_num + '</td>	'
        +'          <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'      style="cursor: pointer;">' + vo.price + '원</td>	'
        +'          <td class="myboard_I_a" onclick="location.href='+"'content.board?postId=" + vo.post_id + "'" + '"'
        +'      style="cursor: pointer;">' + vo.p + '</td>	'
        +'          <td class="myboard_pop"> <button class="delJJim" name="' + vo.post_id + '">찜 취소</button> </td> </td>'
        +'      </tr>	'	;
          }
          );
          
        content += ' </table> ';
       
         	 $('#myList').append(content);
         	$('#p_count').text(k);
       },
       error: function() {
         alert("통신 실패..");
       }                                                                                  
	})// ajax-end 
});


$(document).on("click", "button[name='checkAll']", function() {
	$.ajax({ //ajax 호출 
        type: 'post',
       url: 'deleteNotice.etc',
       data: 'check=all',
       dataType: 'json',
//       async: false,
       success: function(result) {
     	  
          $('#notice').contents().remove(); // 알림 초기화
		  var content = 	                
				   	'       <div class="mypage_myBoard_All_Check">	'
				   +'   	<button id="mypage_Notice_All_Check" name="checkAll"> 전체 확인</button>	'
				   +'   	</div>	'
				   +' <table class="mypage_Default_Table">	'
				   +'   <tr class="mypage_MyBoard_Block">	'
				   +'    <th class="myboard_I">번호</th>	'
				   +'    <th class="myboard_Title">내용</th>	'
				   +'    <th class="myboard_Ntime">시간</th>	'
				   +'    <th class="myboard_Check">확인</th>	'
				   +'   </tr>	'
				   +'</table>	';
         
          $('#notice').append(content);
          $('.notice_Num').text('0');
       },
       error: function() {
         alert("통신 실패..");
       }                                                                                  
	})// ajax-end 
});


$(document).on("click", "button[class='checkN']", function() {
	var objParams = {
			check : 'no',
            post_id : $(this).attr("id"),
            cmt_id : $(this).attr("name")
           };
	
	$.ajax({ //ajax 호출 
       type: 'post',
       url: 'deleteNotice.etc',
       data: objParams,
       dataType: 'json',
//       async: false,
       success: function(arrn) {
     	  var k = arrn.length;
          $('#notice').contents().remove(); // 알림 초기화
          
		  var content = 	                
				   	'       <div class="mypage_myBoard_All_Check">	'
				   +'    <button id="mypage_Notice_All_Check" name="checkAll"> 전체 확인</button>	'
				   +'   </div>	'
				   +' <table class="mypage_Default_Table">	'
				   +'   <tr class="mypage_MyBoard_Block">	'
				   +'    <th class="myboard_I">번호</th>	'
				   +'    <th class="myboard_Title">내용</th>	'
				   +'    <th class="myboard_Ntime">시간</th>	'
				   +'    <th class="myboard_Check">확인</th>	'
				   +'   </tr>	'	;
         
				  $.each(arrn, function(i, vo){
					  var j = k - i;
				content +=
			 '       <tr class="tr_Hover">		'
			+'       <td class="myboard_I" onclick="location.href='+"'content.board?postId=" + vo.post_id + '&c=' + vo.cmt_id + "'" + '">'+ j +'</td>		'
			+'       <td class="myboard_Title_b" onclick="location.href='+"'content.board?postId=" + vo.post_id + '&c=' + vo.cmt_id + "'" + '">' + vo.content	
			+'       &nbsp;<span id="notice_Point">댓글[' + vo.count + ']</span>&nbsp;이 달렸습니다.</td>		'
			+'		 <td class="myboard_Ntime" onclick="location.href='+"'content.board?postId=" + vo.post_id + '&c=' + vo.cmt_id + "'" + '">' + vo.ntime + '</td>'
			+'       <td><button class="checkN" id="' + vo.post_id + '" name="' + vo.cmt_id + '"> 확인</button></td>		'
			+'   	 </tr>		' ;
				  });
			
					content += '	</table></div>	'	;
							   
          $('#notice').append(content);
          $('.notice_Num').text(k);
       },
       error: function() {
         alert("통신 실패..");
       }                                                                                  
	})// ajax-end 	
});




   </script>
</body>
</html>