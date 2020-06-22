<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.web.etc.model.CityVO"%>
<%@page import="kr.co.web.etc.model.CityDAO"%>
<%@page import="kr.co.web.etc.model.CategoryVO"%>
<%@page import="kr.co.web.etc.model.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>물품 업로드</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/upload.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_upload.css?after" media="all and (max-width:720px)" />

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!-- 폰트 -->

<%-- 스마트에디터 --%>
<script type="text/javascript" src="./editor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>

</head>

<%
if(session.getAttribute("id") == null){
	%>
	<script>
		alert("로그인 후 이용해주세요.");
		location.replace("loginPage.jsp");
	</script>
	<% }
	
   ArrayList<CityVO> rlist = CityDAO.getInstance().getRegion();
  	for(CityVO vo : rlist) {// 2글자로 표현하기
	   if(vo.getRegion().length() == 4){//지역(시/도) 이름이 4글자면 첫글자랑 세번째 글자만으로 표현
	   	 vo.setRegion(vo.getRegion().substring(0,1) + vo.getRegion().substring(2, 3));
	   } else {// 나머지는 앞에 2글자만으로 표현
		 vo.setRegion(vo.getRegion().substring(0,2));
	   }
   }// for-end
   	pageContext.setAttribute("rlist", rlist);
		   
	ArrayList<CategoryVO> ctglist = CategoryDAO.getInstance().getCategory();
	pageContext.setAttribute("ctglist", ctglist);
%>

<body>
<%@ include file="header.jsp"%>

   <div id="wrap">
      <div id="container">
         <div class="upload_Title">판매글 작성</div>
         <div class="article">
            <form id="frm" name="frm" action="regist.board" method="post">
				<input type="hidden" name="imgPath" value="${img_path}">
				<input type="hidden" id="Writer1" name="Writer1"  value = "${id}"/><!-- 게시글에 userId가 저장 포스트 불러올때 nick 검색해오기 -->
				   
               <!-- <div class="upload_List_Block">
                  
                  <div class="upload_List_Title">사진(대표사진 임시설정공간)</div>
                  
                  <div class="upload_List_Input">
                     <div class="input_wrap">
                        <div class="imgs_wrap">
                           <img id="img" />
                        </div>
                        <a href="javascript:" onclick="fileUploadAction();" class="my_button"></a>  
                        <input type="file" id="picture" multiple="multiple"/>
                     </div>
                  </div>
               </div> -->
               
               <div class="upload_List_Block">
               
                  <div class="upload_List_Title">제목</div>
                  
                  <div class="upload_List_Input">
						<input type="text" id= "title" name="articleTitle" placeholder="글제목" />
                  </div>
               </div>
               
				<div class="upload_List_Block">
				<div class="upload_List_Title">작성자</div>
                  
				<%-- <div class="upload_List_Input"> ${nick}</div> --%>
				<div class="upload_List_Input">
					<input id="writer" type="text" name="Writer1" value = "${nick}" readonly="readonly">
				</div>
				<%-- 테스트용)
				<input id="writer" placeholder="작성자" type="text" name="Writer1" value="banana" readonly="readonly">
 --%>
               </div>
               <div class="upload_List_Block">
                  <div class="upload_List_Title">가격</div>
                  
                  <div class="upload_List_Input">
					<input type="text" id="price" name="articlePrice" placeholder="ex) 10000"/>
                  </div>
               </div>
               
               <%--거래 진행 상태 :  글 등록시 기본 0, 판매(구매) 중 --%>
					<input type="hidden" id= "progress" name="progress" value="0">
					<!-- 
					<div class="upload_List_Block">
						<div class="upload_List_Title">거래 상태</div>

						<div class="upload_List_Input">
							<select id="progress" name="progress">
							
								<option>거래상태</option> 
								
								<option value="0">팝니다</option> 
								
								<option value="1">거래진행중</option> 
								
								<option value="2">거래완료</option>
								
								<option value="3">삽니다</option>
							</select>
						</div>
					</div>
					-->
				
               <div class="upload_List_Block">
                  <div class="upload_List_Title">카테고리</div>
                  
                  <div class="upload_List_Input">
                     <select id="first" name="ctgId">
                         <option value="">선택</option><!-- DB -->
                 		 <c:forEach var="ctg1" items="${ctglist}">
               			 	<option value="${ctg1.ctg_id}">${ctg1.ctg_name}</option>
               			 </c:forEach>
                     </select>
                  </div>
               </div>
               <div class="upload_List_Block">
                  <div class="upload_List_Title">지역</div>
                  
                  <div class="upload_List_Input">
                      <select id="region" name="region">
                   		  	<option value="">시/도</option>
               	  	   <c:forEach var="r1" items="${rlist}">
            			  	<option value="${r1.region_id}">${r1.region}</option>
              	 	   </c:forEach>
                      </select>
                      <select id="city" name="cityId">
                           <option value="">시/군/구</option>
                     </select>
                  </div>
               </div>
              <div class="upload_List_Block">
                  <div class="upload_List_Title">거래 방식</div>
                  
                  <div class="upload_List_Input" id="sType">
                     <input type="radio" name="deliver" value="0"/> 무관
                     <input type="radio" name="deliver" value="1" />직거래
                     <input type="radio" name="deliver" value="2"/>택배 거래
                  </div>
               </div>
               <div class="upload_List_Block">
                  <div class="upload_List_Title">내용</div>
               </div>
               <div class="upload_List_Block">
					<textarea name='upload_Textarea' id='ir1' rows='10' style='width:100%; min-width:260px; height:30em; display:none;'></textarea>
               </div>
            	<!--
            	<input type="submit" value="등록" />
            	<input type="reset" value="초기화" />
            	-->
            	<input type="button" id="save" value="등록" onclick="return regist()"/> 
				<input type="button" value="취소" onclick="location.href='list.board'"/>
            </form>
         </div>
         <!-- class article end -->
      </div>
      <!-- id container end -->
   </div>
   <!-- id wrap end -->
<%@ include file="footer.jsp"%>
   
<script src="jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/uploadPage.js"></script>
 
</body>

<%-- 스마트에디터 --%>
<script type="text/javascript">
	var oEditors = [];
	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1",
			//SmartEditor2Skin.html 파일이 존재하는 경로 
			sSkinURI : "./editor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseModeChanger : true,
				fOnBeforeUnload : function() {

				}
			},
			fOnAppLoad : function() {
				//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용 
				oEditors.getById["ir1"].exec("PASTE_HTML",[""/* "기존 DB에 저장된 내용을 에디터에 적용할 문구" */]);
			},
			fCreator : "createSEditor2"
		});
	});
	
	function regist() {

		var b2 = validate();
		if(b2){
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			var ir1 = $("#ir1").val();
			
			if( ir1 == ""  || ir1 == null || ir1 == '&nbsp;' || ir1 == '<p>&nbsp;</p>')  {
	            alert("내용을 입력하세요.");
	            oEditors.getById["ir1"].exec("FOCUS"); //포커싱
			}else if(confirm("등록하시겠습니까?")){
				$("#frm").submit();
			}
			
		}
		return false;
		
	}

</script>
<%-- /스마트에디터 --%>

</html>





