/**
 * 
 */

$('#region').change(dynamicSB); // 내동네 설정 - 동적 select box




/*
//=========================== 동적 select box 구현 ==================================//upload꺼
   function dynamicSB() {
      var region_id = $('#region').val();
      
      if(!region_id){ // 박스1이 선택 전(시/도)이면 박스2는 (시/군/구)
         $('#city').find('option').remove().end().append('<option value="">시/군/구</option>');
      }
      else{ // 박스1이 선택되면
         $.ajax({
              type: 'post',
              url: 'getCity.etc',
              data: 'region_id=' + region_id,
              dataType: 'json',
              success: function(result) { //DB에서 박스1의 선택값에 해당하는 박스2의 값들을 가져와서
                $('#city').find('option').remove().end().append('<option value="">선택</option>');
                                              // 기본 '선택'옵션에
                 $.each(result, function(i, vo){    // 추가로 가져온 값들을 추가해준다
                  $('#city').append('<option value="'+vo.city_id+'">'+vo.city+'</option>')
                 });
                },
              error: function() {
                alert("통신 실패..");
              }
            })// ajax-end
      }// else-end
      
   }
//=========================== 동적 select box-end ===================================
*/   
 //=========================== 동적 select box 구현 ==================================// mypage꺼
   function dynamicSB() {
      var region_id = $('#region').val();
      
      if(!region_id){ // 박스1이 '선택'이면 박스2도 '선택'옵션만
         $('#city').find('option').remove().end().append('<option value="">선택</option>');
           $("#city_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 지역을 선택하세요.");
         $("#city_check").css("color", "red");
         $("#city_check").css("font-size", "80%");
      }
      
      else{ // 박스1이 '선택' 외 값을 가지면
         $.ajax({
              type: 'post',
              url: 'getCity.etc',
              data: 'region_id=' + region_id, //'='를 붙여써야 함..!
   //디폴트       contentType: 'application/x-www-form-urlencoded; charset=utf-8',
              dataType: 'json',
              success: function(result) { //DB에서 박스1의 선택값에 해당하는 박스2의 값들을 가져와서
                $('#city').find('option').remove().end().append('<option value="">선택</option>');
                                              // 기본 '선택'옵션에
                 $.each(result, function(i, vo){    // 추가로 가져온 값들을 추가해준다
                  $('#city').append('<option value="'+vo.city_id+'">'+vo.city+'</option>')
                 });
                    $('#city_check').text("");
                },
              error: function() {
                alert("통신 실패..");
              }
            })// ajax-end
      }// else-end
      
   }
   //=========================== 동적 select box-end ===================================
   
 //========================= 기존 정보로 자동 인증, 선택 =====================================
   $(document).ready(function(){
   	 $.ajax({
   	        type: 'post',
   	        url: 'ajaxLoadPostInfo.board',
   	        dataType: 'json',
   	        success: function(result) {
   	        	
   	        	$('#ctgId').val(result.ca);
   	        	$('#progress').val(result.pr);
   	        	
   	        	$('#region').val(result.ri);
   	        		        	
   	        	$.ajax({
   	        		type: 'post',
   	                url: 'getCity.etc',
   	                data: 'region_id=' + result.ri,	                
   	                dataType: 'json',
   	                async: false,
   	                success: function(result) { //DB에서 박스1의 선택값에 해당하는 박스2의 값들을 가져와서
   	                  $('#city').find('option').remove().end().append('<option value="">선택</option>');
   	                                                // 기본 '선택'옵션에
   	                   $.each(result, function(i, vo){    // 추가로 가져온 값들을 추가해준다
   	                    $('#city').append('<option value="'+vo.city_id+'">'+vo.city+'</option>')
   	                   });
   	                },
   	                error: function() {
   	                  alert("통신 실패..2");
   	                }	        			        			        			        		        		
   	        	})// ajax2-end
   	        	
   	        	$('#city').val(result.ci);	        	
   	        },
   	        error: function() {
   	          alert("통신 실패..");
   	        }      
   	   })// ajax-end	
   });
   //=================================================================================
   
   function validate() {
		
		var title = frm.articleTitle;
		var price = frm.articlePrice;
		var ctgId = frm.ctgId;
		var region = frm.region;
		var progress = frm.progress;
		
		var b1 = false;
		
		if(title.value ==""){
			
			alert("제목을 입력하세요.");
			$(title).focus();
			
		}else if(price.value==""){
			
			alert("가격을 입력하세요.");
			$(price).focus();
			
		}else if(progress.value==""){
			
			alert("거래 상태을 입력하세요.");
			$(progress).focus();
			
		}else if(ctgId.value==""){
			
			alert("카테고리를 선택하세요.");
			$(ctgId).focus();
			
		}else if(region.value==""){
			
			alert("지역을 선택하세요.");
			$(region).focus();
			
		}else if(	frm.deliver[0].checked == false &&
					frm.deliver[1].checked == false &&
					frm.deliver[2].checked == false) {
			alert("거래 방식을 선택하세요.");
			$(frm.deliver).focus();
			
		}else 
			b1 = true;
		
		return b1;
	}
   