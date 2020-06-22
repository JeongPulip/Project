<%@page import="kr.co.web.etc.model.CategoryDAO"%>
<%@page import="kr.co.web.etc.model.CategoryVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.web.etc.model.CityVO"%>
<%@page import="kr.co.web.etc.model.CityDAO"%>
<%@page import="kr.co.web.etc.model.CityListVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");

   String inputSearch = request.getParameter("inputSearch");
   
   if(session.getAttribute("postInfo") != null){
      session.removeAttribute("postInfo");
      System.out.println("postInfo 세션 삭제");
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>물품 검색</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/searchList.css?after"media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_searchList.css?after" media="all and (max-width:720px)"/>

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->
</head>
<%
   ArrayList<CityListVO> allcity = CityDAO.getInstance().getAllCity();
   pageContext.setAttribute("allcity", allcity);
   
   ArrayList<CategoryVO> ctglist = CategoryDAO.getInstance().getCategory();
   pageContext.setAttribute("ctglist", ctglist);
%>

<body>
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrap">
      <div id=" container">
<!--          <div class="boardText">
            게시글
         </div> -->
         <div id="category">
        <form action="#"><!-- reset을 위한 form -->
               <!-- 지역별 카테고리 -->
               <input type="button" class="accordion" value="지역별">
               <div class="panel">
                  <ul>
                     <li>
                     <!-- <a href="http://localhost:8081/project_userMaket/searchListPage.jsp#"> --> 
                     <input type="checkbox" id="all" value="">전체
                     <!-- </a> -->
                     </li>
                     <c:forEach var="a1" items="${allcity}">
                        <li>
                        <!-- <a href="http://localhost:8081/project_userMaket/searchListPage.jsp#"> -->
                        <input type="checkbox" name="region" id="${a1.region_id}" value="${a1.region_id}">${a1.region}
                        <!-- </a> -->
                           <ul>
                              <c:forEach var="c1" items="${a1.clist}">
                                 <li>
                                 <!-- <a href="http://localhost:8081/project_userMaket/searchListPage.jsp#"> -->
                                    <input type="checkbox" class="city_id" name="${a1.region_id}" value="${c1.city_id}">${c1.city}
                                 <!-- </a> -->
                                 </li>                     <!-- 실질적으로 넘겨주는 값은 요놈만 쓴다 -->
                              </c:forEach>
                           </ul>
                        </li>
                     </c:forEach>
                  </ul>
               </div>
               <input type="button" class="accordion" value="제품별">
               <div class="panel">
                  <label class="search_Product_Id">
                     <input type="radio" name="product" value="ZZ" checked="checked" />
                     <span>전체보기</span>
                  </label>
                  <br />
                  <c:forEach var="ctg1" items="${ctglist}">
                     <label class="search_Product_Id">
                        <input type="radio" name="product" value="${ctg1.ctg_id}"/>
                        <span>${ctg1.ctg_name}</span>
                     </label>
                     <br />
                  </c:forEach>
                  <!-- DB에서 카테고리 추출 -->
                  <!-- <input type="radio" name="" /> -->
               </div>
               <input type="button"class="accordion" value="거래방식"/>
               <div class="panel">
                  <label class="search_Product_Id">
                     <input type="radio" name="deal" value="0" checked="checked"/>
                     <span>무관</span>
                  </label>
                  <br />
                  <label class="search_Product_Id">
                     <input type="radio" name="deal" value="1" />
                     <span>직거래</span>
                  </label>
                  <br />
                  <label class="search_Product_Id">
                     <input type="radio" name="deal" value="2" />
                    <span>택배</span>
                 </label>
                 <br />
               </div>
               <%-- 결과내검색을 위한 추가 --%>
               <!-- <div class="panel"> -->
               <input type="text" id="filterSearch" name="filterSearch" placeholder="검색어를 입력해주세요"><br />
               <!-- </div> -->
               <%-- 결과내검색을 위한 추가 --%>
               <input id="search_Category_Btn" type="button" value="적용하기" onclick="checked_value(1);" /><%-- 필터시 초기값 1  --%> 
               <input type="reset" value="취소" /> 
           </form> 
         </div>
         <div class="mainList">
            <div class="boardText">
               <!-- 게시글 -->
               <%
                  if (inputSearch != null) {
               %>
               "<%=inputSearch%>" 으로 검색한 결과입니다.
               <br>
               <%
                  }
               %>
            </div>
            <div id="section_search">
            
               <c:choose>
               <c:when test="${empty listPa2VO}">
                     <br>
                    <strong><font color="green" size="7">게시물이 존재하지 않습니다.</font></strong>
                    <br>
               </c:when>
               <c:otherwise>
                     
                     <c:forEach var="pa2VO" items="${listPa2VO}">
                     <%--ListService로 부터 게시글리스트가 넘어옴 --%>
                     <article
                        onclick="location.href='content.board?postId=${pa2VO.postVO.postId}'">
                        <div class="info">
                           <span class="list_Num"> 글번호 : ${pa2VO.postVO.postId} </span>
                           <span class="list_State">
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
   
   
                           </span>
                        </div>
                        <%-- <img src="../img/a.jpg${이미지경로}" alt="이미지" /> --%>
                        <c:choose>
                        
                     <c:when test="${empty pa2VO.listImageVO[0].imgPath}">
                        <img src="./img/footer_logo.png" alt="이미지" />
                     </c:when>
   
                     <c:otherwise>
                        <img src="${pa2VO.listImageVO[0].imgPath}" alt="이미지" />
                     </c:otherwise>
                        
                        </c:choose>
   
                        <div class="ListText">
                           <div class="list_Title">${pa2VO.postVO.title}</div>
                           <div class="list_Location">${pa2VO.cityVO.region}
                              ${pa2VO.cityVO.city}</div>
                           <div class="list_Price">${pa2VO.sPostVO.price}원</div>
                        </div>
                     </article>
                  </c:forEach>
                     
               </c:otherwise>
               </c:choose>
            </div>
            <!--  section_search end -->
            <!-- <div class="moreList">
               <a href="">1</a> <a href="">2</a> <a href="">3</a> <a href="">4</a> <a href="">5</a>
            </div> -->
            <div class="pager" align="center">
                  <%-- 페이징 --%>
               <c:choose>
                  <c:when test="${empty paging}">
                  
                  </c:when>
                  <c:otherwise>   
                     <table>                   
                        <tr>
                           <c:if test="${paging.startPage > 1}">
                              <td><a href="list.board?page=1">&nbsp;[처음]&nbsp;</a></td>
                           </c:if>
                           <c:if test="${paging.pageGroup > 1}">
                              <td><a href="list.board?page=${paging.startPage - 1}">&nbsp;[이전]&nbsp;</a></td>
                           </c:if>
      
                           <c:forEach var="iCount" begin="${paging.startPage}" end="${paging.endPage}">
      
                              <c:choose>
                                 <c:when test="${iCount eq paging.page}">
                                    <td>&nbsp;<font color="red" size="4">${iCount}</font>&nbsp;
                                    </td>
                                 </c:when>
                                 <c:otherwise>
                                    <td><a href="list.board?page=${iCount}">&nbsp;${iCount}&nbsp;</a></td>
                                 </c:otherwise>
                              </c:choose>
      
                           </c:forEach>
      
                           <c:if test="${paging.pageGroup < paging.totalGroup}">
                              <td>&nbsp;<a href="list.board?page=${paging.endPage + 1}">[다음]</a>&nbsp;
                              </td>
                           </c:if>
                           <c:if test="${paging.endPage < paging.totalPage}">
                              <td>&nbsp;<a href="list.board?page=${paging.totalPage}">[끝]</a>&nbsp;
                              </td>
                           </c:if>
                        </tr>
      
                        <%-- 페이징 --%>
                     </table>
                  </c:otherwise>
               </c:choose>
            </div>
            <div id="addBtn">
               <button onclick="location.href='write.board'">등록하기</button>
            </div>
         </div>
         <!--  mainList end  -->
      </div>
      <!--  container end -->
   </div>
   <!-- wrap end -->
   <jsp:include page="footer.jsp" />
   
   <script>
   $('input[type="checkbox"]').change(function(){
      var val = $(this).val(); // 지역 id의
      var length = $(this).val().length; // 글자수로 구분하자
      var checked = $(this).is(':checked'); // 클릭 후의 상태를 변수로.

      if(!length){ /////////// 전체 눌렀을 때
         var chkbox_all = $('input[type="checkbox"]');
            if(checked){ 
               chkbox_all.prop('checked', true); // 모두 체크
            } else {   
               chkbox_all.prop('checked', false); // 모두 해제
            }   return false;
      } 
      if(length == 2){ /////////// 시도 눌렀을 때
         var checked_region = $('[name="region"]:checked').length; // 체크된 시도박스의 수
          var chkbox_city = $('[name="'+ val +'"]'); // 특정 시도에 속한 시군구 체크박스
            if(checked){
               chkbox_city.prop('checked', true); //해당 도시들 체크
               if(checked_region == 17){ // 시도박스가 모두 체크됐다면
                  $('#all').prop('checked', true); // 전체선택 체크로 표시
               }
            } else {
               chkbox_city.prop('checked', false);
               $('#all').prop('checked', false); // 전체선택 체크해제
            }   return false;
      }
      if(length == 5){
         var region_id = $(this).attr("name"); // 시군구 박스의  name속성에는 해당 region_id를 넣어놨다
          var city_length = $('[name="'+ region_id +'"]').length; // 같은 시도에 속한 시군구박스의 수
          var checked_city =$('[name="'+ region_id +'"]:checked').length; // 체크된 시군구박스의 수
          var rbox = $("input:checkbox[id='" + region_id + "']");
//         alert(region_id);
//         alert(rbox.length);
           if(checked){
            if(checked_city == city_length) {
               rbox.trigger('click');
            }
         } 
           else {
                  rbox.prop('checked', false);
            $('#all').prop('checked', false);
         }
      }
   })
   
   
   
   
   function checked_value(page){
      var length = $('input:checkbox[class="city_id"]:checked').length;
      var search = $('#filterSearch').val();
      var category = $(":input:radio[name=product]:checked").val();
      var sType = $(":input:radio[name=deal]:checked").val();
      
//         alert(length);
      var val_in = "";
      
      
         $('input:checkbox[class="city_id"]:checked').each(function(i){
         if(!i){
            /* val_in += $(this).val(); */
            val_in += "'" + $(this).val() + "'";

         } else {
            val_in += ", '" + $(this).val() + "'";
         }
//         alert($(this).val());
//         console.log($(this).val());
      }); // 반복문 - end
      if(length == 261 || length == 0){
//         alert('select * 으로 검색..');
         val_in = "ALL";
      }
      
      console.log(length);
      
      console.log(val_in); // 웹브라우저 콘솔창으로 결과값 확인 가능
      console.log(search); // 검색값 가져오기 확인
      console.log(category);
      console.log(sType);
   
   
   // 보낼 변수 들 모아서 전송
   // {"val_in": val_in, "category": category, "sType": sType, "search": search}
   
   // ajax 호출하여 처리..
   $.ajax({
      type: 'post',
      url: 'ajaxList.board',
      data: {"val_in": val_in, "category": category, "sType": sType, "search": search ,"page" : page},
      dataType: 'json',
      async: false,
      success: function(result) {
   
         //alert("통신 성공");
         //기존 게시물리스트 지움
         $('#section_search').empty();
         $('.pager').empty();
         $('.boardText').empty();

         // 받아온 검색어 출력
         if(result.search != ""){
            $('.boardText').append(
                     ' "' + result.search + '" 으로 검색한 결과입니다. ' + 
                     '<br>'
            );
         }
         
         if(result.listPa2VO == ""){
            $('#section_search').append(
                        '<br>' + 
                       '<strong><font color="green" size="7">게시물이 존재하지 않습니다.</font></strong>' + 
                       '<br>'
                  );
         }else{
            
         
         // 받아온 (필터적용된)게시물 출력
            $.each(result.listPa2VO, function(i, pa2VO){
               
                /*alert("성공"+ pa2VO.postVO.postId);*/
                  var sProgress;
                  if(pa2VO.sPostVO.progress == 0){
                     sProgress = "판매(구매) 중";
                  }else if(pa2VO.sPostVO.progress == 1){
                     sProgress = "거래 진행 중";
                  }else{
                     sProgress = "거래 완료";
                  }
                  
                  var regionCity = pa2VO.cityVO.region + " " + pa2VO.cityVO.city;
                  
                  var mainImg = "./img/footer_logo.png";
                  $.each(pa2VO.listImageVO, function(i, imageVO) {
                     
                     if(i == 0){
                        mainImg = imageVO.imgPath;
                        console.log(mainImg);
                     }
                     
                  });
   
                  
                  $('#section_search').append(
                        '<article onclick="contentInfo(' + pa2VO.postVO.postId + ')">' +
                           '<div class="info">' + 
                              '<span class="list_Num"> 글번호 : ' + pa2VO.postVO.postId + '</span>' +
                              '<span class="list_State">' + sProgress + '</span>' +
                           '</div>' +
                           '<img src="' + mainImg + '" alt="이미지" />' + 
                           '<div class="ListText">' +
                              '<div class="list_Title">' + pa2VO.postVO.title + '</div>' +
                              '<div class="list_Location">' + regionCity + '</div>' +
                              '<div class="list_Price">' + pa2VO.sPostVO.price + '원</div>' +
                           '</div>' + 
                        '</article>'   
                           
                  );
                      
               });
            }
            // 페이징
            
            // 처음 버튼
            var beginning = "";
            if(result.paging.startPage > 1){
               beginning = "<td><a href='#' onclick='checked_value(1);'>&nbsp;[처음]&nbsp;</a></td>";
            }
            
            // 이전 버튼
            var prev = "";
            var prevPage = result.paging.startPage-1;
            if(result.paging.pageGroup > 1){
               prev = "<td><a href='#' onclick='checked_value(" + prevPage + ");'>&nbsp;[이전]&nbsp;</a></td>";
            }
            
            // 페이지들
            var pages = "";
            for(var i= result.paging.startPage; i<=result.paging.endPage; i++) {
                              
               if(i == result.paging.page){
                  pages += "<td>&nbsp;<font color='red' size='4'>" + i + "</font>&nbsp;</td>";
               }else{
                  pages += "<td><a href='#' onclick='checked_value(" + i + ");'>&nbsp;" + i + "&nbsp;</a></td>";
               }
               
            }
            
            // 다음 버튼
            var next = "";
            var nextPage = result.paging.endPage + 1;
            if(result.paging.pageGroup < result.paging.totalGroup){
               next = "<td><a href='#' onclick='checked_value(" + nextPage + ");'>&nbsp;[다음]&nbsp;</a></td>";
            }
            
            // 끝 버튼
            var ending = "";
            if(result.paging.endPage < result.paging.totalPage){
               ending = "<td><a href='#' onclick='checked_value(" + result.paging.totalPage + ");'>&nbsp;[끝]&nbsp;</a></td>";
            }
            
            $('.pager').append(
                     
                     '<table>' + 
                        '<tr>' +
                           beginning + 
                           prev + 
                           pages +
                           next + 
                           ending +
                        '</tr>' +
                     '</table>'   
                     
                     );
         // 페이징         
         
                
         },
         error: function() {
            alert("다시 시도해주세요.");
         }                                                                                  
      })
   
   
   }
   
   function contentInfo(postId) {

         location.href="content.board?postId=" + postId;
     
   }
      
   
   
   
   
   
   </script>
</body>
<script>
   var acc = document.getElementsByClassName("accordion");
   var i;

   for (i = 0; i < acc.length; i++) {
      acc[i].addEventListener("click", function() {
         this.classList.toggle("active");
         var panel = this.nextElementSibling;
         if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
         } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
         }
      });
   }
</script>

<script>
   function valueSend() {
      
   }
      
      </script>
</html>