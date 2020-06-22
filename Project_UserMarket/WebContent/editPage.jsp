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
<title>게시글 수정</title>

<link rel="stylesheet" href="./css/main.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="./css/m_main.css?after" type="text/css" media="all and (max-width:720px)">
<link rel="stylesheet" href="css/edit.css?after" media="all and (min-width:720px)" />
<link rel="stylesheet" href="css/m_edit.css?after" media="all and (max-width:720px)" />
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"><!--폰트 -->
</head>

<script type="text/javascript" src="./editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>


<%
	if(session.getAttribute("id") == null){
		%>
		<script>
			alert("로그인 후 이용해주세요.");
			location.replace("loginPage.jsp");
		</script>
		<%
	}

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
			<div class="edit_Title">판매글 수정</div>
			<div class="edit_article">
				<form id="frm" action="update.board" method="post">
				<input type="hidden" id="postId" name="postId"  value = "${pa2VO.postVO.postId}"/>
					<!-- <div class="edit_User_Block">
						<div class="edit_User_Title">사진(대표사진 임시설정공간)</div>
							<div class="edit_User_Value">
						<div class="input_wrap">
							<a href="javascript:" onclick="fileUploadAction();" class="my_button"></a> <br> <input
								type="file" id="picture" multiple="multiple"
							/><br>
							<div>
								<div class="imgs_wrap">
									<img id="img" />
								</div>
							</div>
						</div>
						</div>
					</div> -->
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">제목</div>
						<div class="edit_User_Value">
							<!-- <input type="text" name="articleTitle" placeholder="기존 제목" /> -->
							<input type="text" id="title" name="articleTitle"  value = "${pa2VO.postVO.title}"/>							
						</div>
					</div>
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">작성자</div>
						<div class="edit_User_Value">
							<input id="writer" type="text" name="Writer1" value = "${nick}" readonly="readonly">							
							<%-- ${nick} --%>
							<%-- 
							게시글 작성자 출력에 닉네임을 해야하나 DB에 각 테이블 마다 닉네임 컬럼이 없어서 일단 id로 출력
							또한, 수정페이지는 기존에 작성된 페이지에서 작성자id를 받아오기 때문에 세션이 필요없다고 판단.
							--%>
						</div>
					</div>
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">가격</div>
						<div class="edit_User_Value">
							<input type="text" name="articlePrice" value="${pa2VO.sPostVO.price}" />
						</div>
					</div>
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">거래 상태</div>

						<div class="edit_User_Value">
							<select id="progress" name="progress">
							
								<option value="">거래상태</option> 
								
								<option value="0">팝니다</option> 
								
								<option value="1">거래진행중</option> 
								
								<option value="2">거래완료</option>
								
								<option value="3">삽니다</option>
								
							</select>
						</div>
					</div>
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">카테고리</div>
						<div class="edit_User_Value">
							<select id="ctgId" name="ctgId">
								<option value="">선택</option><!-- DB -->
                        		<c:forEach var="ctg1" items="${ctglist}">
               			 			<option value="${ctg1.ctg_id}">${ctg1.ctg_name}</option>
               					</c:forEach>
							</select>
						</div>
					</div>
					<%-- 추후 수정 --%>
					<div class="edit_User_Block">
                     <div class="edit_User_Title">지역</div>
                     <div class="edit_User_Value">
                        <select id="region" name="region">
                           <option value="">선택</option>
                           <c:forEach var="r1" items="${rlist}">
                              <option value="${r1.region_id}">${r1.region}</option>
                           </c:forEach>
                        </select>
                        <select id="city" name="cityId" >
                           <option value="">선택</option>
                        </select>
                     </div>
                  </div>
					
					
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">거래 방식</div>
						<div class="edit_User_Value">

						<c:set var="sType" value="${pa2VO.sPostVO.sType}"/>	
						<c:choose>
								
							<c:when test="${sType eq '0'}">
								<input type="radio" name="deliver" value="0" checked="checked"/> 무관
								<input type="radio" name="deliver" value="1"/>직거래
								<input type="radio" name="deliver" value="2"/>택배 거래
							</c:when>
							<c:when test="${sType eq '1'}">
								<input type="radio" name="deliver" value="0"/> 무관
								<input type="radio" name="deliver" value="1" checked="checked" />직거래
								<input type="radio" name="deliver" value="2"/>택배 거래
							</c:when>
							<c:when test="${sType eq '2'}">
								<input type="radio" name="deliver" value="0"/> 무관
								<input type="radio" name="deliver" value="1" />직거래
								<input type="radio" name="deliver" value="2" checked="checked"/>택배 거래
							</c:when>
								
						</c:choose>	
							
						</div>
					</div>
					
					
					<div class="edit_User_Block">
						<div class="edit_User_Title">내용</div>
					</div>
					<div class="edit_User_Block">
						<!-- <textarea rows="10" cols="50" placeholder="기존 내용"></textarea> -->
						<textarea name='upload_Textarea' id='ir1' rows='10' style='width:100%; min-width:260px; height:30em; display:none;'>
							${pa2VO.postVO.content}
						</textarea>
					</div>
					
					<div class="edit_User_Block">
						<!-- <input type="submit" value="수정" /> -->
						<input type="button" id="save" value="수정" onclick="return update()"/>
						<!-- <input type="button" value="취소" onclick="history.back();"/> -->
						<input type="button" value="취소" onclick="location.href='list.board'"/>
						
					</div>
				</form>
			</div>
		</div>
		<!-- class article end -->
	</div>
	<!-- id wrap end -->

<%@ include file="footer.jsp"%>
	
<script src="jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/uploadPage.js"></script>
   									<!-- uploadPage.js를 같이 쓴다 -->

</body>

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
				/* var org = new Array();
   				org.push("${article.content}");
 
				oEditors.getById["ir1"].exec("PASTE_HTML", org); 
				*/
				oEditors.getById["ir1"].exec("LOAD_CONTENTS_FIELD");
			},
			fCreator : "createSEditor2"
		});
	});
	
	function update() {
		
		var b2 = validate();
		if(b2){
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			var ir1 = $("#ir1").val();
			
			if( ir1 == ""  || ir1 == null || ir1 == '&nbsp;' || ir1 == '<p>&nbsp;</p>')  {
	            alert("내용을 입력하세요.");
	            oEditors.getById["ir1"].exec("FOCUS"); //포커싱
			}else if(confirm("수정하시겠습니까?")){
				$("#frm").submit();
			}
			
		}
		return false;
	}

</script>

</html>

