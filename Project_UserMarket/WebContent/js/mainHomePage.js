/**
 * 
 */

  	 
$(document).ready(function(){  	 
	$.ajax({
		type: 'post',
		url: 'ajaxMainPostInfo.board',
		dataType: 'json',
		async: false,
		success: function(result) {
			$.each(result, function(i, pa2VO){
				
		    /*alert("성공"+ pa2VO.postVO.postId);*/
				console.log("통신성공");
				var sProgress;
				if(pa2VO.sPostVO.progress == 0){
					sProgress = "판매(구매) 중";
				}else if(pa2VO.sPostVO.progress == 1){
					sProgress = "거래 진행 중";
				}else{
					sProgress = "거래 완료";
				}
				
				var regionCity = pa2VO.cityVO.region + " " + pa2VO.cityVO.city;
				
				var mainImg = "./img/a.jpg";
				$.each(pa2VO.listImageVO, function(i, imageVO) {
					
					if(i == 0){
						mainImg = imageVO.imgPath;
						console.log(mainImg);
					}
					
				});
				
				$('.section_Popular').append(
						'<article onclick="contentInfo(' + pa2VO.postVO.postId + ')">' +
							'<div class="info">' + 
								'<span class="list_Num">' + pa2VO.postVO.postId + '</span>' +
								'<span class="list_State">' + sProgress + '</span>' +
							'</div>' +
							'<img src="' + mainImg + '" alt="이미지" />' + 
							'<div class="ListText">' +
								'<div class="list_Title">' + pa2VO.postVO.title + '</div>' +
								'<div class="list_Location">' + regionCity + '</div>' +
								'<div class="list_Price">' + pa2VO.sPostVO.price + '원</div>' +
							'</div>' + 
						'</article>'	
							
				)
	        	  
			});
		},
		error: function() {
			alert("다시 시도해주세요.");
		}	        			        			        			        		        		
	})
});

function contentInfo(postId) {
	
      location.href="content.board?postId=" + postId;
      
}








