/**
 * 
 */
//=================== 로그인 검사 =========================
function doLogin() {
	var sw = false;
	var id = $('#id').val();
	var pwd = $('#pwd').val();
	
	if(!id) {
	      $("#login_check").html("아이디를 입력해 주세요.");
	      $("#login_check").css("color", "red");
	      $("#login_check").css("font-size", "80%");
	      $('#id').focus();
		return sw;
	}
	if(!pwd) {
	      $("#login_check").html("비밀번호를 입력해 주세요.");
	      $("#login_check").css("color", "red");
	      $("#login_check").css("font-size", "80%");
	      $('#pwd').focus();
		return sw;
	}
				
	$.ajax({
        type: 'post',
        url: 'doLogin.member',
        data: {"id": id, "pwd": pwd},
        dataType: 'json',
        async: false, // 동기식으로 처리하겠다.
        //결과 값을 받아 그에 따른 결과를 모두 처리하고 다음의 코드를 실행하겠다
        success: function(result) {
        	if(result) { // DB의 값과 일치(true)하면
				sw = true; // 페이지 이동을 위해 true값을 넣어준다
			}
        	else { // DB의 값과 로그인정보가 일치하지 않으면
      	      $("#login_check").html("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
    	      $("#login_check").css("color", "red");
    	      $("#login_check").css("font-size", "80%");
    	      $('#pwd').focus();
        	}
        },
        error: function() {
          alert("통신 실패..");
        }
      });// ajax-end
	// 동기식 처리로 ajax가 모두 끝나고 코드가 실행된다(결과값을 제대로 반영할 수 있다)
	
		if(sw) {//replace()를 사용해서 로그인페이지로 뒤로가기 못하게 한다
			location.replace('mainHomePage.jsp')
		//	location.href='mainHomePage.jsp'
		}
		
		return false; // false값을 리턴해서 submit기능이 작동 안하게.(엔터로 로그인 기능만..)
								
}
//=================== 로그인 검사-end ========================