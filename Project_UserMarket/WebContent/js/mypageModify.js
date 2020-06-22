/**
 * 
 */
$('input').keydown(function() {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}); // 엔터 입력시 자동 submit 막기




$('#region').change(dynamicSB); // 내동네 설정 - 동적 select box

$('#tel').change(function(){ // 전화번호 입력 값이 바뀌면 인증이 풀린다
	$('#ser').val('인증받기');
});





//=================== email 유효성 검사 =========================
function email_check() {
   var email = $('#email').val();
   var re_e = RegExp
   (/^[A-Za-z0-9]([-_\.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_\.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i);
   
   if(!email) {// 이메일 입력 안했으면
      alert('이메일을 입력하세요.');
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_e.test(email)) {// 정규 표현식을 만족하지 않으면
	  alert('이메일을 확인하세요.');
      return true; //문제 있음 - 탈락!!
   }
      return false; //문제 없음 - 통과~^^x
}
//============== email 유효성 검사-end =========================






//==================== 휴대전화번호 유효성 검사 ==========================
function tel_check() {
   var tel = $('#tel').val();
   var re_tel = RegExp(/^01[016789]-?([1-9][0-9]{2,3})-?([0-9]{4})$/);
   
   if($('#ser').val() == '인증완료'){
		return false;
	}
   
   if(!tel) {// 입력하지 않으면
	  alert('전화번호를 입력하세요.');
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_tel.test(tel)) {// 정규 표현식을 만족하지 않으면
	  alert('전화번호 형식에 맞지 않습니다.');
      return true; //문제 있음 - 탈락!!
   }
   
   // 만족하면 '-'을 제거해준다
	var dtel = tel.replace(/\-/g, '');
	$('#tel').val(dtel);
	
	alert("인증되었습니다.");
	$('#ser').val('인증완료');
	
	return false;
	
}// tel_check-end
//==================== 휴대전화번호 유효성 검사-end ==========================






//=============== 지역선택 완료 여부 검사 ======================
function city_check() {
   var c = $('#city').val();
   
   if(!c){
      alert('지역을 선택하세요.')
      return true; // 문제 발견..!
   }
      return false; // 문제 없음!
}// city_check-end
//============== 지역선택 완료 여부 검사-end ===================






//=================== form 전체를 검사 ===========================
function form_check() {
	if($('#ser').val() != '인증완료'){
		alert('휴대전화 인증을 받아주세요');
		return false;
	}

   if(email_check()) {
	   $('#email').focus();
	   return false;
   }
      
   if(city_check()) {
	   if(!$('#region').val()) { // region박스가 선택이면
           $('#region').focus(); // region박스로
        } else { // 아니면
           $('#city').focus(); // city박스로
        }
        return false;
     }
   
   return true;
}
//==================== form 검사-end ======================












//=========================== 동적 select box 구현 ==================================
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
	        url: 'inputUserInfo.member',
	        dataType: 'json',
	        success: function(result) {
	        	if($('#tel').val() == result.tel) {
	        		$('#ser').val('인증완료');
	        	}
	        	
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



//=====================이미지 업로드===========================================


function readURL(input) {
	 if (input.files && input.files[0]) {
	  var reader = new FileReader();
	  
	  reader.onload = function (e) {
	   $('#image_section').attr('src', e.target.result);  
	  }
	  
	  reader.readAsDataURL(input.files[0]);
	  }
	}
	  
$("#picture").change(function(){
   readURL(this);
});

//이미지 정보들을 담을 배열  

var sel_files = [];

$(document).ready(function() {

   $("#picture").on("change", handleImgFileSelect);

});

/////////////////////////////////////

function fileUploadAction() {

   console.log("fileUploadAction");

   $("#picture").trigger('click');

}

function handleImgFileSelect(e) {

   // 이미지 정보들을 초기화  

   sel_files = [];

   $(".imgs_wrap").empty();

   var files = e.target.files;

   var filesArr = Array.prototype.slice.call(files);

   var index = 0;

   filesArr
         .forEach(function(f) {

            if (!f.type.match("image.*")) {

               alert("확장자는 이미지 확장자만 가능합니다.");

               return;

            }

            sel_files.push(f);

            var reader = new FileReader();

            reader.onload = function(e) {

               var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
                     + index
                     + ")\" id=\"img_id_"
                     + index
                     + "\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove' width=\"200px\" height=\"200px\" ></a>";

               $(".imgs_wrap").append(html);

               index++;

            }

            reader.readAsDataURL(f);

         });

}

function deleteImageAction(index) {

   console.log("index : " + index);

   console.log("sel length : " + sel_files.length);

   sel_files.splice(index, 1);

   var img_id = "#img_id_" + index;

   $(img_id).remove();

}

function fileUploadAction() {

   console.log("fileUploadAction");

   $("#input_imgs").trigger('click');

}

function submitAction() {

   console.log("업로드 파일 갯수 : " + sel_files.length);

   var data = new FormData();

   for (var i = 0, len = sel_files.length; i < len; i++) {

      var name = "image_" + i;

      data.append(name, sel_files[i]);

   }

   data.append("image_count", sel_files.length);

   if (sel_files.length < 1) {

      alert("한개이상의 파일을 선택해주세요.");

      return;

   }

   var xhr = new XMLHttpRequest();

   xhr.open("POST", "./register4.jsp");

   xhr.onload = function(e) {

      if (this.status == 200) {

         console.log("Result : " + e.currentTarget.responseText);

      }

   }

   xhr.send(data);

}




//=====================이미지 업로드===========================================



















