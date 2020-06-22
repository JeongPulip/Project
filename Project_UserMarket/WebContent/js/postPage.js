/**
 * 
 */
function updateScreen(arrcmt){ // 댓글DB 변경시 최신 정보로 화면 업데이트 (추가만 하는 부분)
      
      $.each(arrcmt, function(i, vo){
      if(vo.parent_id == '0'){ // 댓글 먼저 출력하고
         
      var reply = '<tbody>' // 대댓글을 append하기 위한 용도
             +   '<tr reply_type="main" name="' + vo.cmt_id + '" id="f' + vo.cmt_id + '">'
            + '   <td><div class="comment_nick">' + vo.nick;
            if($('#post_user_id').val() == vo.user_id){ //게시글 작성자 표시
            reply += '<br>(작성자)';
            }

      reply += '</div><span class="comment_date">' + vo.w_date + '   </span></td>';
      
            if(vo.secret == '1'
                &&
            $('#user_id').val() != $('#post_user_id').val()
                &&
            $('#user_id').val() != vo.user_id){
           reply += '   <td><div class="comment_ballon"> <i class="fas fa-lock"></i> 비밀댓글입니다. </div></td>';
           } else{
              
           reply += '   <td><div class="comment_ballon"> ';
              if(vo.secret == '1'){
                reply += ' <i class="fas fa-lock"></i> ';
               }
              
           reply += vo.content + '</div></td>';
           }
            
      reply += '   <td>';
      
            
     reply += '       <span class="comment_button"><button name="reply_reply" reply_id="' + vo.cmt_id + '"><i class="fas fa-arrow-circle-down fa-lg"></i></button>';
            
          //이하 if문으로 로그인 세션 검사해서 해당하는 것만 보이게 할 것  (닉네임이 같으면 답글/수정/삭제, 다르면 답글 버튼만 나오도록)
            if($('#user_id').val() == vo.user_id){
       reply += '       <button name="reply_modify" r_type="main" secret="' + vo.secret + '" reply_id="' + vo.cmt_id + '"><i class="fas fa-pencil-alt fa-lg"></i></button>      '
              + '       <button name="reply_del" reply_id="' + vo.cmt_id + '"><i class="fas fa-trash-alt fa-lg"></i></button>      '         
            }
          //====================================================================================
             
      reply += '   </span></td>' + '</tr>' + '</tbody>';
         
            
               $('#reply_area').append(reply);
               
      }
      });

        $.each(arrcmt, function(i, vo){
         if(vo.parent_id != '0'){ // 대댓글을 출력한다
            reply_id = vo.cmt_id;
         
             var reply = '<tr reply_type="sub" name="' + vo.cmt_id + '">'
             + '   <td><div class="comment_re_nick"><i class="fas fa-arrow-right"></i> ' + vo.nick;
             
             if($('#post_user_id').val() == vo.user_id){ //게시글 작성자 표시
       reply += '<br>(작성자) ';            
             }
             
       reply += '</div><span class="comment_date">' + vo.w_date + '   </span></td>';
       
                 if(vo.secret == '1'
                       &&
                   $('#user_id').val() != $('#post_user_id').val()
                       &&
                   $('#user_id').val() != vo.user_id){
             reply += '   <td><div class="comment_re_ballon"><i class="fas fa-arrow-right"></i> <i class="fas fa-lock"></i> 비밀댓글입니다. </div></td>';
              } else{
             reply += '   <td><div class="comment_re_ballon"><i class="fas fa-arrow-right"></i> ';
             
             if(vo.secret == '1'){
           reply += ' <i class="fas fa-lock"></i> ';
             }
             
             reply += vo.content + '</div></td>';
              }
                 
       reply += '   <td>';
             
             if($('#user_id').val() == vo.user_id){
       reply += '       <span class="comment_button"><button name="reply_modify" r_type = "sub" secret="' + vo.secret + '" reply_id = "' + vo.cmt_id + '"><i class="fas fa-pencil-alt fa-lg"></i></button>      '
             + '       <button name="reply_del" reply_id = "' + vo.cmt_id + '"><i class="fas fa-trash-alt fa-lg"></i></button>   ';
             }
             
       reply += '   </span></td>' + '</tr>';
             
             $('[reply_id="' + vo.parent_id + '"]').parent().parent().parent().parent().children('tr:last').after(reply);
             
      }}); 
}//===============================================================================================
   
   
   
   
   
//===================== 첫 화면 댓글 출력 ===========================
$(document).ready(function() {
  $.ajax({
         type: 'post',
           url: 'showComment.cmt',
           data: 'post_id=' + $('#post_id').text(),
           dataType: 'json',
           async: false,
           success: function(arrcmt) {
              updateScreen(arrcmt);
           },
           error: function() {
             alert("통신 실패..");
           }                                                                                  
      });// ajax-end
      
  var ft = $('#ft').val();
 // document.getElementById('"f' + ft + '"').scrollIntoView(true);
  if(!ft){
	  return false;
  }else{
	  document.getElementById('comment_focus').scrollIntoView(true);
  }
});   
//=============================================================
   
   
   
   
   
   
//============================= 댓글 입력 ======================================
   $(document).on('click', '#reply_save' , function() {
      
    //널 검사   
    if ($("#reply_content").val().trim() == "") {
       alert("내용을 입력하세요.");
       $("#reply_content").focus();
       return false;
    }
    var reply_content =          // 개행 처리
       $("#reply_content").val().replace(/(\n|\r\n)/g, '<br>'); 
       
    var sval = '0'
    var lock = $('#lock').is(':checked');
        if(lock){
           var sval = '1';
        }
      //값 셋팅  
    var objParams = {
         post_id : $('#post_id').text(),
          secret : sval,   // check box로 비밀댓글 여부
          reply_content : reply_content
          };
    
    //ajax 호출 (여기에 댓글을 저장하는 로직을 개발)
    $.ajax({
         type: 'post',
           url: 'insertComment.cmt',
           data: objParams,
           dataType: 'json',
//           async: false,
           success: function(arrcmt) {
              $('#reply_area').contents().remove(); // 댓글 초기화
              updateScreen(arrcmt); // 최신 DB정보 출력
             $("#reply_content").val(""); // input area 초기화
//              alert('댓글 등록 성공');
           },
           error: function() {
             alert("통신 실패..");
           }                                                                                  
      })// ajax-end
 });
//========================================================================
   
   
   
   
   
   
       
//======================== 댓글 삭제 ========================================
          $(document).on("click", "button[name='reply_del']", function() {
             if(confirm("정말로 삭제하시겠습니까?")){ // 확인창 띄우기
                
             var reply_id = $(this).attr("reply_id");
            
             //값 셋팅   
             var objParams = {
                   post_id : $('#post_id').text(),
                   cmt_id : reply_id
             };
             
//             alert(reply_id); // 댓글번호 확인
             //ajax 호출   
             $.ajax({
                   type: 'post',
                  url: 'deleteComment.cmt',
                  data: objParams,
                  dataType: 'json',
//                  async: false,
                  success: function(arrcmt) {
                     $('#reply_area').contents().remove(); // 댓글 초기화
                     updateScreen(arrcmt); // 최신 DB정보 출력
//                     alert('댓글 삭제 성공');
                  },
                  error: function() {
                    alert("통신 실패..");
                  }                                                                                  
           })// ajax-end
           
             }
          });
//===================================================================================          

          

//===================================== 댓글 수정 입력 ============================================ 
          $(document).on("click", "button[name='reply_modify']", function() {
            var sval = $(this).attr("secret");
             var reply_id = $(this).attr("reply_id");
             var r_type = $(this).attr("r_type");
             var user_nick = $('#user_nick').val();

                var txt_reply_content = $(this).parent().parent().prev().html();
                txt_reply_content = txt_reply_content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
                txt_reply_content = txt_reply_content.replace('<div class="comment_ballon">', "");
                txt_reply_content = txt_reply_content.replace('</div>', "");

                if (r_type == "sub") {
                   txt_reply_content = txt_reply_content.replace('<i class="fas fa-arrow-right"></i>', "");//대댓글의 뎁스표시(화살표) 없애기   
                   txt_reply_content = txt_reply_content.replace('<div class="comment_re_ballon">', "");//대댓글의 뎁스표시(화살표) 없애기   
                }
                
                //입력받는 창 등록   
                var replyEditor = ' <tr id="reply_add" class="reply_modify"><div class="comment_modify_area">'
                   + '   <td><span class="comment_modify_nick">' + user_nick + '</span></td>'
                   + '   <td><span class="comment_modify_content">'
                   + '       <textarea name="reply_modify_content" id="reply_modify_content" rows="3" cols="50">'
                   + txt_reply_content + '</textarea>' + '</span></td>'
                   + '   <td><input type="checkbox" id="lock2" name="comment_lock" /><i class="fas fa-lock"></i><br><br>'
                   + '    <span class="comment_button">'
                   + '       <button name="reply_modify_save" r_type = "'+r_type+'" reply_id="'+reply_id+'"><i class="fas fa-check-circle fa-lg"></i></button>'
                   + '       <button name="reply_modify_cancel" r_type = "'+r_type+'" r_content = "'+txt_reply_content+'" r_writer = "'+user_nick+'" reply_id="'+reply_id+'"><i class="fas fa-times-circle fa-lg"></i></button>'
                   + '   </span></td>' + '</div></tr>';
                 
                   
                   $.ajax({
                    type: 'post',
                      url: 'showComment.cmt',
                      data: 'post_id=' + $('#post_id').text(),
                      dataType: 'json',
                     async: false,   // 다음 처리할 로직을 위해 비동기로
                      success: function(arrcmt) {
                         $('#reply_area').contents().remove(); // 댓글 초기화
                         updateScreen(arrcmt); // 댓글 갱신
                      },
                      error: function() {
                        alert("통신 실패..");
                      }                                                                                  
                 })// ajax-end 
                 
                 
                   // 자기 아래 붙이고   
                   $('[name="' + reply_id + '"]').after(replyEditor);
                   if(sval == '1'){ // 비댓이면 체크
                      $('#lock2').prop('checked', true);
                   }
                   // 자기 자신 삭제
                   $('[name="' + reply_id + '"]').remove();
                   $('#reply_modify_content').focus();
             });
//===========================================================================================================
          
          
   
   
   
   
//================================ 댓글 수정 취소 ====================================================
             $(document).on("click", "button[name='reply_modify_cancel']", function() {
                $.ajax({
                   type: 'post',
                     url: 'showComment.cmt',
                     data: 'post_id=' + $('#post_id').text(),
                     dataType: 'json',
         //           async: false,   // 다음 처리할 로직을 위해 비동기로
                     success: function(arrcmt) {
                        $('#reply_area').contents().remove(); // 댓글 초기화
                        updateScreen(arrcmt); // 댓글 갱신
                     },
                     error: function() {
                       alert("통신 실패..");
                     }                                                                                  
                })// ajax-end 
             });
//==================================================================================================        
   
   
   
   
   
//================================= 댓글 수정 저장 ==================================================
             $(document).on("click", "button[name='reply_modify_save']", function() {
                var reply_id = $(this).attr("reply_id");
                if ($("#reply_modify_content").val().trim() == "") {
                   alert("내용을 입력하세요.");
                   $("#reply_modify_content").focus();
                   return false;
                }
                
             //DB에 업데이트 하고   
             //ajax 호출 (여기에 댓글을 저장하는 로직을 개발)
             var reply_content =
                $("#reply_modify_content").val().replace(/(\n|\r\n)/g, '<br>'); // 개행 처리
             var sval = '0'
                var lock = $('#lock2').is(':checked');
                    if(lock){
                       var sval = '1';
                    }
                    //값 셋팅   
             var objParams = {
                 post_id : $('#post_id').text(),
                 secret : sval,
                 cmt_id : reply_id,
                   reply_content : reply_content
                };
             
             $.ajax({
                   type: 'post',
                  url: 'updateComment.cmt',
                  data: objParams,
                  dataType: 'json',
//                  async: false,
                  success: function(arrcmt) {
                     $('#reply_area').contents().remove(); // 댓글 초기화
                     updateScreen(arrcmt); // 최신 DB정보 출력
//                     alert('댓글을 수정하였습니다');
                  },
                  error: function() {
                    alert("통신 실패..");
                  }                                                                                  
                })// ajax-end
          });
//====================================================================================================
   
   
   
   
             
//=============================== 대댓글 입력창 ===========================================================
          $(document).on("click", "button[name='reply_reply']", function() { //동적 이벤트   
             $("#reply_add").remove();
             var reply_id = $(this).attr("reply_id");
             
             //입력받는 창 등록   
             var replyEditor = '<tr id="reply_add" class="reply_reply"><td></td>'
             + '   <td><span class="comment_modify_content">' + '       <textarea name="reply_reply_content" rows="3" cols="50"></textarea>' + '   </span></td>'
             + '  <td><input type="checkbox" id="lock2" name="comment_lock" /><i class="fas fa-lock"></i><br><br>'   
             + '   <span class="comment_button">'
             + '       <button name="reply_reply_save" reply_id="'+reply_id+'"><i class="fas fa-check-circle fa-lg"></i></button>'
             + '       <button name="reply_reply_cancel"><i class="fas fa-times-circle fa-lg"></i></button>' + '   </span></td>' + '</tr>';
             
             
//             $('[reply_id="' + reply_id + '"]').parent().parent().parent().after(replyEditor);
             $('[reply_id="' + reply_id + '"]').parent().parent().parent().parent().after(replyEditor);
             $('textarea[name="reply_reply_content"]').focus();
          });
//=======================================================================================================                
                
                
                
                
//=============================== 대댓글 등록 ========================================================
          $(document).on("click", "button[name='reply_reply_save']", function() {
             var reply_id = $(this).attr("reply_id");
             var reply_reply_content = $("textarea[name='reply_reply_content']");
             var reply_reply_content_val =        // 개행 처리
                reply_reply_content.val().replace(/(\n|\r\n)/g, '<br>');
             if (reply_reply_content.val().trim() == "") {
                alert("내용을 입력하세요.");
                reply_reply_content.focus();
                return false;
             }
             var sval = '0'
                   var lock = $('#lock2').is(':checked');
                       if(lock){
                          var sval = '1';
                       }
             var objParams = { // 값 셋팅   
                post_id : $('#post_id').text(),
                parent_id : reply_id,
                secret : sval,   // check box로 비밀댓글 여부
                   reply_content : reply_reply_content_val
                };
             
             $.ajax({ //ajax 호출 
                   type: 'post',
                  url: 'insertReComment.cmt',
                  data: objParams,
                  dataType: 'json',
//                  async: false,
                  success: function(arrcmt) {
                     $('#reply_area').contents().remove(); // 댓글 초기화
                     updateScreen(arrcmt); // 최신 DB정보 출력
//                     alert('대댓글 등록 성공');
                  },
                  error: function() {
                    alert("통신 실패..");
                  }                                                                                  
           })// ajax-end
          });
//============================================================================================         
          
          

          //대댓글 입력창 취소
          $(document).on("click", "button[name='reply_reply_cancel']", function() {
             $("#reply_add").remove();
          });

          //글수정   
          $("#modify").click(function() {
             // 작성자랑 로그인자 일치하는지 확인  
             //값 셋팅   
             var objParams = {
                id : $("#board_id").val(),
             };   
          });

          //글 삭제   
          $("#delete").click(function() {
             //예/아니오 확인 한번 넣어주기  
             alert("삭제되었습니다.");

             //값 셋팅  
             var objParams = {
                id : $("#board_id").val(),
             };
          });

          
          
          
          
          const heartButton = document.querySelector(".like-btn");

          heartButton.addEventListener("click", function(){
           // heartButton.classList.toggle("like-btn-clicked");
           $.ajax({
                type: 'post',
                url: 'doJJim.etc',
                data: 'post_id=' + $('#post_id').text(),
                dataType: 'json',
           //     async: false,
                success: function(result) {
                   heartButton.classList.toggle("like-btn-clicked");
           //           alert(result); // 결과 출력
                },
                error: function() {
                   alert("통신 실패");
                }                                                                                  
             })
          });

          $(document).ready(function(){ //페이지 로딩시 찜한 글이면 표시
             $.ajax({
                   type: 'post',
                   url: 'checkJJim.etc',
                   data: 'post_id=' + $('#post_id').text(),
                   dataType: 'json',
                   async: false,
                   success: function(result) {
                      if(result){
                         heartButton.classList.toggle("like-btn-clicked");
             //            alert('찜 되어있음');
                      } else {
               //          alert('찜 안되어있음');
                      }
                   },
                   error: function() {
                      alert("통신 실패");
                   }                                                                                  
                });
                
             var objParams = {
          			check : 'no',
                     post_id : $('#post_id').text(),
                     cmt_id : '0'
                    };
          	
          	$.ajax({ //ajax 호출 
                type: 'post',
                url: 'deleteNotice.etc',
                data: objParams,
                   dataType: 'json',
              //     async: false,
                   success: function(result) {
             //     	 alert("게시글 관련알림 삭제.");
                   },
                   error: function() {
                      alert("통신 실패");
                   }                                                                                  
                });

          });
          
          
          
          
          
          // 이미지 슬라이드
          var slideIndex = [ 1, 1 ];
          var slideId = [ "mySlides1" ];
          
          showSlides(1, 0);
          showSlides(1, 1);

          function plusSlides(n, no) {
             showSlides(slideIndex[no] += n, no);
          }

          function showSlides(n, no) {
             var i;
             var x = document.getElementsByClassName(slideId[no]);
             
             if (n > x.length) {
                slideIndex[no] = 1
             }

             if (n < 1) {
                slideIndex[no] = x.length
             }

             for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
             }

             x[slideIndex[no] - 1].style.display = "block";
          }
          
          
          
          
          function delchk(postId) {
              
              var uri = "delete.board?postId=" + postId + "";
              var enc = encodeURI(uri);
              
             if(confirm("삭제하시겠습니까?")) {
                location.href=enc;
             }else{
                return false;
             }
          }