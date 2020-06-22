/**
 * 
 */


$('#region').change(dynamicSB); // 내동네 설정 - 동적 select box








//이미지 정보들을 담을 배열   

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

      

    filesArr.forEach(function(f) {   

        if(!f.type.match("image.*")) {   

            alert("확장자는 이미지 확장자만 가능합니다.");   

            return;   

        }   



        sel_files.push(f);   



        var reader = new FileReader();   



        reader.onload = function(e) {   

            var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+index+")\" id=\"img_id_"+index+"\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove' width=\"150px\" ></a>";   

              

            $(".imgs_wrap").append(html);   

            index++;   

        }   

        reader.readAsDataURL(f);   

    });   

}   



function deleteImageAction(index) {   

    console.log("index : "+index);   

    console.log("sel length : "+sel_files.length);   



    sel_files.splice(index, 1);   



    var img_id = "#img_id_"+index;   

    $(img_id).remove();    

}   



function fileUploadAction() {   

    console.log("fileUploadAction");   

    $("#picture").trigger('click');   

}   



function submitAction() {   

    console.log("업로드 파일 갯수 : "+sel_files.length);   

    var data = new FormData();   



    for(var i=0, len=sel_files.length; i<len; i++) {   

        var name = "image_"+i;   

        data.append(name, sel_files[i]);   

    }   

    data.append("image_count", sel_files.length);   

    if(sel_files.length < 1) {   

        alert("한개이상의 파일을 선택해주세요.");   

        return;   

    }              

    var xhr = new XMLHttpRequest();   

    xhr.open("POST","./register4.jsp");   

    xhr.onload = function(e) {   

        if(this.status == 200) {   

            console.log("Result : "+e.currentTarget.responseText);   

        }   

    }   

    xhr.send(data);   

}

//==============================================================================

   
//=========================== 동적 select box 구현 ==================================
   function dynamicSB() {
      var region_id = $('#region').val();
      
      if(!region_id){ // 박스1이 선택 전(시/도)이면 박스2는 (시/군/구)
         /*$('#city').find('option').remove().end().append('<option value="">시/군/구</option>');*/
         $('#city').find('option').remove().end().append('<option value="">선택</option>');
         $("#city_check").html("&nbsp;<i class=\"fas fa-times-circle fa-lg\"></i> 지역을 선택하세요.");
         $("#city_check").css("color", "red");
         $("#city_check").css("font-size", "80%");
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
   
//===========================  유효성 검사 ===================================
   
   function validate() {
		
		var title = frm.articleTitle;
		var price = frm.articlePrice;
		var ctgId = frm.ctgId;
		var region = frm.region;

		
		
		var b1 = false;
		
		if(title.value ==""){
			
			alert("제목을 입력하세요.");
			$(title).focus();
			
		}else if(price.value==""){
			
			alert("가격을 입력하세요.");
			$(price).focus();
			
		}else if(!($.isNumeric($(price).val()))){
			
			alert("가격은 숫자로만 입력하세요.");
			$(price).val('');
			$(price).focus();
			
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

   //=========================== /유효성 검사 ===================================

   
