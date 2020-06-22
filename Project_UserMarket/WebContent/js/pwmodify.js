/**
 * 
 */

function checkPWD() {
	   var chkpwd = $('#chkpwd').val();
	   var npwd = $('#npwd').val();
	   var chknpwd = $('#chknpwd').val();
	   var re_pwd = RegExp(/^[A-Za-z0-9~!@#$%^&*()_+|<>?:{}]{8,16}$/);
	   var sw = false;
	   
	   if(!chkpwd){
			alert('기존의 비밀번호를 입력해주세요.');
			$('#chkpwd').focus();
			return false;
		}
		
		$.ajax({
			type: 'post',
	        url: 'checkPWD.member',
	        data: 'chkpwd=' + chkpwd,
	        dataType: 'json',
	        async: false,
	        success: function(result) { 
	        	if(result) {
	        		sw = true;
	        	} else {
	        		alert('기존의 비밀번호가 맞지 않습니다.');
	        		$('#chkpwd').focus();
	        	}
	        },
	        error: function() {
	          alert("통신 실패..");
	        }	        			        			        			        		        		
		})// ajax-end
		
		if(!sw){
			return false;
		}// ajax 결과가 false면 false반환하고 빠져나감 아니면 나머지 검사
		
		if(!npwd){
			alert('새 비밀번호를 입력해주세요.');
			$('#npwd').focus();
			return false;
		}
		
		if(!re_pwd.test(npwd)) {
			alert('8~16자의 영문, 숫자, 특수문자만 사용 가능합니다. 사용 가능 특수문자 : ~!@#$%^&*()_+|<>?:{}');
			$('#npwd').focus();
			return false;
		}
		
		if(!chknpwd) {
		    alert('새 비밀번호 확인을 입력하세요.');
		    $('#chknpwd').focus();
		    return false;
			}
		   
		if(npwd != chknpwd) { // 새 비밀번호와 확인이 일치하지 않으면
			alert('새 비밀번호와 일치하지 않습니다.');
			$('#chknpwd').focus();
			return false;
		}
		
		return sw;   
   }