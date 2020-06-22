$('input').keydown(function() {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}); // 엔터 입력시 자동 submit 막기

//========================== 정보 입력받기 ==================================
$('#id').blur(id_check); // 아이디 유효성 검사 - 5~20자(영문 소문자, 숫자) + 중복확인
$('#pwd').blur(pwd_check); // 비밀번호 유효성 검사 - 8~16자(영문, 숫자, 특수문자)
$('#chkpwd').blur(chkpwd); // 비밀번호 확인
$('#nick').blur(nick_check); // 닉네임 유효성 검사 - 2~10자(한글, 영문, 숫자) + 중복확인

$('#name').blur(name_check); // 이름 유효성 검사 - 한글, 영문
$('#b_date').blur(date_check); // 생년월일 입력 검사
$('#gender').change(gender_check); //성별 입력 검사

$('#email01').blur(email_check); // email 유효성 검사
$('#email02').blur(email_check); // email 유효성 검사
$('#tel').change(function(){
	$('#ser').val('인증받기');
}); // 인증여부를 확인하여 인증값이 바뀌면 재인증을 요구

$('#region').change(dynamicSB); // 내동네 설정 - 동적 select box
$('#city').change(city_check);
//========================== 정보 입력받기-end ===============================









//======================================================================================



   function readURL(input) {

      if (input.files && input.files[0]) {

         var reader = new FileReader();

         reader.onload = function(e) {

            $('#user').attr('src', e.target.result);

         }

         reader.readAsDataURL(input.files[0]);

      }

   }

   $("#picture").change(function() {

      readURL(this);

   });

function myFunction() {

   var x = document.getElementById("myLinks");

   if (x.style.display === "block") {

      x.style.display = "none";

   } else {

      x.style.display = "block";

   }

}


$('#selectEmail').change(function() {

   $("#selectEmail option:selected").each(function() {

      if ($(this).val() == '1') {

         $("#email02").val('');

         $("#email02").attr("disabled", false);

      } else {

         $("#email02").val($(this).text());

         $("#email02").attr("disabled", true);

      }

   });
   
   email_check();

});


// 이미지 정보들을 담을 배열  

var sel_files = [];

$(document).ready(function() {

   $("#picture").on("change", handleImgFileSelect);

});

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




//================================================================================






//====================== 아이디 유효성 검사 ===========================
function id_check() {
   var id = $('#id').val();
   var re_id = RegExp(/^[a-z0-9]{5,20}$/);// 5~20자(영문 소문자, 숫자)
   
   if(!id) {// 입력하지 않으면
      $("#id_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 아이디를 입력하세요.");
      $("#id_check").css("color", "red");
      $("#id_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_id.test(id)) {// 정규 표현식을 만족하지 않으면
      $("#id_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
      $("#id_check").css("color", "red");
      $("#id_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   $.ajax({// 서버와 통신하여 DB의 데이터와 비교, 중복여부를 검사한다
        type: 'post',
        url: 'checkID.member',
        data: 'id=' + id, //'='를 붙여써야 함..
//디폴트   contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        success: function(result) { //중복확인 값으로 boolean값을 받아서 처리
          if(result) { // 값이 true(중복)이면
            $("#id_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 이미 사용중인 아이디입니다.");
            $("#id_check").css("color", "red");
            $("#id_check").css("font-size", "80%");
            return true; //문제 있음 - 탈락!!
         }
                // 아니면
            $("#id_check").html("&nbsp;<i class=\"fas fa-check-circle fa-lg\"></i> 사용 가능한 아이디입니다.");
            $("#id_check").css("color", "blue");
            $("#id_check").css("font-size", "80%");
            return false; //문제 없음 - 통과~^^
          },
        error: function() {
          alert("통신 실패..");
        }         
      })// ajax-end
      
}// id_check-end
//================= 아이디 유효성 검사-end ===========================







//==================== 비밀번호 유효성 검사 =========================
function pwd_check() {
   var pwd = $('#pwd').val();
   var re_pwd = RegExp(/^[A-Za-z0-9~!@#$%^&*()_+|<>?:{}]{8,16}$/);
                  // 8~16자(영문 대 소문자, 숫자, 일부 특수문자)
   
   if(!pwd) {// 입력하지 않으면
      $("#pwd_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 비밀번호를 입력하세요.");
      $("#pwd_check").css("color", "red");
      $("#pwd_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_pwd.test(pwd)) {// 정규 표현식을 만족하지 않으면
      $("#pwd_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 8~16자의 영문, 숫자, 특수문자만 사용 가능합니다.<div align=\"right\">사용 가능 특수문자 : ~!@#$%^&*()_+|<>?:{}</div>");
      $("#pwd_check").css("color", "red");
      $("#pwd_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
      // 만족하면
      $("#pwd_check").html("&nbsp;<i class=\"fas fa-check-circle fa-lg\"></i> 사용 가능한 비밀번호입니다.");
      $("#pwd_check").css("color", "green");
      $("#pwd_check").css("font-size", "80%");
      chkpwd();
      return false; //문제 없음 - 통과~^^
   
} // pwd_check-end
//==================== 비밀번호 유효성 검사-end =======================







//======================== 비밀번호 확인 =============================
function chkpwd() {
   var pwd = $('#pwd').val();
   var chkpwd = $('#chkpwd').val();
   
   if(!chkpwd) {// 입력하지 않으면
      $("#chkpwd_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 비밀번호 확인을 입력하세요.");
      $("#chkpwd_check").css("color", "red");
      $("#chkpwd_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(pwd == chkpwd) {// 입력값이 비밀번호와 일치하면
      $("#chkpwd_check").html("&nbsp;<i class=\"fas fa-check-circle fa-lg\"></i> 비밀번호가 일치합니다.");
      $("#chkpwd_check").css("color", "green");
      $("#chkpwd_check").css("font-size", "80%");
      return false; //문제 없음 - 통과~^^
   }
      //그렇지 않으면
      $("#chkpwd_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 비밀번호가 일치하지 않습니다.");
      $("#chkpwd_check").css("color", "red");
      $("#chkpwd_check").css("font-size", "80%");
   
      return true; //문제 있음 - 탈락!!
      
} // chkpwd-end
//====================== 비밀번호 확인-end ===========================







//====================== 닉네임 유효성 검사 ===========================
function nick_check() {
   var nick = $('#nick').val();
   var re_nick = RegExp(/^[가-힣A-Za-z0-9]{2,10}$/); // 2~10자(한글, 영문, 숫자)
   
   if(!nick) {// 입력하지 않으면
      $("#nick_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 닉네임을 입력하세요.");
      $("#nick_check").css("color", "red");
      $("#nick_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_nick.test(nick)) {// 정규 표현식을 만족하지 않으면
      $("#nick_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 2~10자의 한글, 영문, 숫자만 가능합니다.");
      $("#nick_check").css("color", "red");
      $("#nick_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   $.ajax({// 서버와 통신하여 DB의 데이터와 비교, 중복여부를 검사한다
        type: 'post',
        url: 'checkNick.member',
        data: 'nick=' + nick, //'='를 붙여써야 함..
//디폴트   contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        success: function(result) { //중복확인 값으로 boolean값을 받아서 처리
          if(result) { // 값이 true(중복)이면
               $("#nick_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 이미 사용중인 닉네임입니다.");
             $("#nick_check").css("color", "red");
             $("#nick_check").css("font-size", "80%");
            return true; //문제 있음 - 탈락!!
         }
                 // 값이 false(중복 아님)이면
           $("#nick_check").html("&nbsp;<i class=\"fas fa-check-circle fa-lg\"></i> 사용 가능한 닉네임입니다.");
         $("#nick_check").css("color", "blue");
         $("#nick_check").css("font-size", "80%");
            return false; //문제 없음 - 통과~^^
          },
        error: function() {
          alert("통신 실패..");
        }         
      })// ajax-end
      
}// nick_check-end
//================= 닉네임 유효성 검사-end ===========================







//====================== 이름 유효성 검사 ===========================
function name_check() {
   var name = $('#name').val();
   var re_name = RegExp(/^[가-힣A-Za-z]+$/); // 한글, 영문 대 소문자만 사용
   
   if(!name) {// 입력하지 않으면
        $("#name_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 이름을 입력하세요.");
      $("#name_check").css("color", "red");
      $("#name_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_name.test(name)) { // 정규 표현식을 만족하지 않으면
        $("#name_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 한글과 영문 대/소문자를 사용하세요. (공백 사용 불가)");
      $("#name_check").css("color", "red");
      $("#name_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
      // 만족하면
      $("#name_check").html("");
      return false; //문제 없음 - 통과~^^
   
} // name_check-end
//=================== 이름 유효성 검사-end ===========================






//================== 생년월일 입력 검사 ======================
function date_check() {
   var bd = $('#b_date').val();
   var re_bd = RegExp(/^\d{4}-?\d{2}-?\d{2}$/);
   var by = parseInt(bd.substring(0,4));
   
   if(!bd){
        $("#date_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 생년월일을 입력하세요.");
      $("#date_check").css("color", "red");
      $("#date_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락~!
   }
   if(!re_bd.test(bd)){
        $("#date_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 생년월일을 확인하세요.");
      $("#date_check").css("color", "red");
      $("#date_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락~!
   }
   if(by > 2020) {
        $("#date_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 생년월일을 확인하세요.");
      $("#date_check").css("color", "red");
      $("#date_check").css("font-size", "80%");
      return false; // 미래인도 환영합니다 통과~
   }
   if(by < 1900) {
        $("#date_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 생년월일을 확인하세요.");
      $("#date_check").css("color", "red");
      $("#date_check").css("font-size", "80%");
      return false; // 와우 통과..
   }
      $('#date_check').html("");
      return false; // 통과!
}
//=============== 생년월일 입력 검사-end =====================






//================== 성별 입력 검사 ======================
function gender_check() {
   var g = $('#gender').val();
   if(!g) {
        $("#gender_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 성별을 입력하세요.");
      $("#gender_check").css("color", "red");
      $("#gender_check").css("font-size", "80%");
      return true; //문제 있네~!
   }
      $('#gender_check').html("");
      return false;
}
//================ 성별 입력 검사-end =====================





//=================== email 유효성 검사 =========================
function email_check() {
   var e1 = $('#email01').val();
   var re_e1 = RegExp(/^[A-Za-z0-9]([-_\.]?[A-Za-z0-9])*$/i);
   var e2 = $('#email02').val();
   var re_e2 = RegExp(/^[A-Za-z0-9]([-_\.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i);
   
   if(!e1) {// 입력하지 않으면
        $("#email_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 이메일을 입력하세요.");
      $("#email_check").css("color", "red");
      $("#email_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
   
   if(!re_e1.test(e1) || !re_e2.test(e2)) {// 정규 표현식을 만족하지 않으면
        $("#email_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 이메일을 확인하세요.");
      $("#email_check").css("color", "red");
      $("#email_check").css("font-size", "80%");
      return true; //문제 있음 - 탈락!!
   }
      // 만족하면
      $("#email_check").html("");
      return false; //문제 없음 - 통과~^^x
      
}// email02_check-end
//============== email 유효성 검사-end =========================







//==================== 휴대전화 인증하기 ==========================

function tel_check() {
	   var tel = $('#tel').val();
	   var re_tel = RegExp(/^01[016789]-?([1-9][0-9]{2,3})-?([0-9]{4})$/);
	   
	   if(!tel) {// 입력하지 않으면
	        $("#tel_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 전화번호를 입력하세요.");
	      $("#tel_check").css("color", "red");
	      $("#tel_check").css("font-size", "80%");
	      return; //문제 있음 - 탈락!!
	   }
	   
	   if(!re_tel.test(tel)) {// 정규 표현식을 만족하지 않으면
	      $("#tel_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 전화번호 형식에 맞지 않습니다.");
	      $("#tel_check").css("color", "red");
	      $("#tel_check").css("font-size", "80%");
	      return; //문제 있음 - 탈락!!
	   }

	   // 만족하면 '-'을 제거해준다
		var dtel = tel.replace(/\-/g, '');
		$('#tel').val(dtel);
	      
	   alert("인증되었습니다.");
	   $('#ser').val('인증완료');
	   $('#tel_check').text("");
	      
	}// tel_check-end

//=================== 휴대전화 인증하기-end =========================






//=============== 지역선택 완료 여부 검사 ======================
function city_check() {
   var r = $('#region').val();
   var c = $('#city').val();
   
   if(!r) { // 시도 선택이 안됐으면
       $("#city_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 지역을 선택하세요.");
      $("#city_check").css("color", "red");
      $("#city_check").css("font-size", "80%");
      return true;
   }
   
   if(!c){ // 시군구 선택이 안됐으면
        $("#city_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 지역을 선택하세요.");
      $("#city_check").css("color", "red");
      $("#city_check").css("font-size", "80%");
      return true; // 문제 발견..!
   }
      $('#city_check').html("");
      return false; // 문제 없음!
      
}// city_check-end
//============== 지역선택 완료 여부 검사-end ===================






//=================== form 전체를 검사 ===========================
function form_check() {
   var sw = true; // 켜져있는 스위치 하나를 이용한다
   // 스위치는 포커스 이동을 처음 한번만 해주게 하는 역할도 한다
   
   if(id_check()){ // 문제가 발견되면
      if(sw){ // 스위치가 켜져있나 확인해서 켜져있다면
         $('#id').focus(); // 포커스를 이동하고
         sw = false; // 스위치를 꺼준다.
      }
   }
   if(pwd_check()){ if(sw){ $('#pwd').focus(); sw = false; }}
   if(chkpwd()){ if(sw){ $('#chkpwd').focus(); sw = false; }}
   if(nick_check()){ if(sw){ $('#nick').focus(); sw = false; }}
   if(name_check()){ if(sw){ $('#name').focus(); sw = false; }}
   if(date_check()){ if(sw){ $('#b_date').focus(); sw = false; }}
   if(gender_check()){ if(sw){ $('#gender').focus(); sw = false; }}
   if(email_check()){ if(sw){ $('#email01').focus(); sw = false; }}
   
   if($('#ser').val() != '인증완료') { // 인증 버튼의 값으로 인증 여부를 검사
	   $("#tel_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 휴대전화 인증을 완료해주세요.");
		$("#tel_check").css("color", "red");
		$("#tel_check").css("font-size", "80%");
		
	   if(sw) { 
		   $('#tel').focus(); sw = false; 
	   }	   
   }
   
   if(city_check()) {
      if(sw) {
         if(!$('#region').val()) { // region박스가 선택이면
            $('#region').focus(); // region박스로
         } else { // 아니면
            $('#city').focus(); // city박스로
         }
         sw = false; 
      }
   }
   
   return sw; // 모든 검사를 문제 없이 통과하면 submit실행을 위한 true를 반환.
   
}// form_check-end
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












