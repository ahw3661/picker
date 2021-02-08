var cnt = document.getElementById("cnt");
var total_price = document.getElementById("total_price");
var item_price_info = document.getElementById("item_price_info");
var cnted = 1;
var code = $("input[name=i_code]").val();
var saving_point = document.getElementById("saving_point");

function plus(){
	if(cnt.value >= 99){
		alert("최대 수량은 99입니다.");
	}else{
		cnted += 1;
		cnt.value = cnted;
		price = item_price.value * cnt.value;
		saving_point = (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		total_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		console.log("total_price= " + total_price.value);
	}
}

function minus(){
	if(cnt.value < 2){
		alert("최소 수량은 1입니다.");
	}else{
		cnted -= 1;
		cnt.value = cnted;
		price = item_price.value * cnt.value;
		saving_point = (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		total_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		console.log("total_price= " + total_price.value);
	}
}

$(function(){
   var point = ($("#item_price").val()*0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
   $("#saving_point").html(point);
   $("#basket_btn").click(function(){
      var fm = $("#fm").serialize();
      $.ajax({
         url:'insertCart', // RequestMapping 값 입력
         type:'POST', // 전송방식 GET, POST
         data: fm, // controller에게 전달하는 파라미터 값
         dataType: "json",
         success:function(data){
            if(data.msg == "suc") {
               if(confirm("장바구니에 담겼습니다. 장바구니로 이동할까요?") == true) {
                  location.href="cartList";
               }
            }
         },
         error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
   });
});

function getEvalList(num) {
	console.log($("input[name=i_code]").val());
	$.ajax({
		url : "itemEvalList",
		type : "post",
		datatype : "html",
		data : {"i_code" : $("input[name=i_code]").val(), "pageNum" : num },
		success : function(data) {
			$("#item_eval").html(data);
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
}

function closeEvalPop(){
	$("aside").remove();
	$("body").css("overflow-y", "auto");
	$.ajax({
		url : "endSessionEval"
	})
}

function getQnaList(num){
	$.ajax({
		url : "itemQnaList",
		type : "post",
		datatype : "html",
		data : { "code" : code, "num" : num },
		success : function(data){
			$("#item_qna").html(data);
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
}

// TOP 링크
$(function() {
	$(window).scroll(function() {
		if($(this).scrollTop() > 1200) {
			$("#toplink").fadeIn();
		}else {
			$("#toplink").fadeOut("fast");
		}
	});
	
	$("#toplink").click(function() {
		$("html, body").animate({
			scrollTop : 0
		}, 400);
		return false;
	});
});
// 리뷰 작성 창
function evalPop() {
	var ele = "<aside><div id='evalWriteWrap'><h3 class='stretchBlock'><span>리뷰 작성</span>"
			+ "<a href='javascript:closeEvalPop()'><img src='resources/image/icon/closeBtn.svg' alt='닫기' class='btnClose'></a>"
			+ "</h3><div id='evalCon'></div></div></aside>"
	$("body").append(ele);
	$("body").css("overflow-y", "hidden");
	$.ajax({
		url : "evalPop?i_code=" + $("input[name=i_code]").val(),
		type : "get",
		dataType : "html",
		success : function(data) {
			$("#evalCon").html(data);
		},
		error:function(request,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
	})
}

// 비회원 주문 확인
function noneMemberEval(){
	if($("input[name=b_code]").val() != "" && $("input[name=phone]").val() != ""){
		$.ajax({
			url : "evalAuthen",
			type : "post",
			dataType : "html",
			data : { "i_code" : $("input[name=i_code]").val(), 
					 "b_code" : $("input[name=b_code]").val(),  
					 "phone" : $("input[name=phone]").val() },
			success : function(data){
				$("#evalCon").html(data);
			}
		})
	} else {
		window.alert("구매 정보를 모두 입력해주세요.")
	}
}

// 리뷰 작성 및 수정
function evalWrite(proc) {
	if($("input[name=e_level]").val() != null && $("textarea[name=e_content]").val() != ""){
		var formData = new FormData($("#evalForm")[0]);
		formData.append("i_code", $("input[name=i_code]").val());
		for(var pair of formData){
			console.log(pair[0] + " : " + pair[1]);
		}
 		$.ajax({
 			url : "evalWrite",
 			type : "post",
 			dataType : "json",
 			contentType : false,
 			processData : false,
 			data : formData,
 			success : function(json) {
 				if(json.chk){
 					alert("리뷰가 " + proc + "되었습니다.");
 					$("aside").remove();
 					$("body").css("overflow-y", "auto");
 					getEvalList(1);
 				} else {
 					alert("[에러] 리뷰 " + proc + " 오류");
 				}
 			},
 			error:function(request,error){
 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 			}
 		})
 	} else {
		window.alert("작성란을 채워주세요.");
 	}
}

// 리뷰 삭제
function evalErase(){
	if(window.confirm("리뷰를 삭제하시겠습니까?")){
		$.ajax({
			url : "evalErase",
			dataType : "json",
			success : function(json){
 				if(json.chk){
 					alert("리뷰가 삭제되었습니다.");
 					$("aside").remove();
 					$("body").css("overflow-y", "auto");
 					getEvalList(1);
 				} else {
 					alert("[에러] 리뷰 삭제 오류");
 				}
 			},
 			error:function(request,error){
 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 			}
		})
	}
}

// 세션 종료
$(window).on("load unload", function(){
	$.ajax({
		url : "endSessionEval"
	})
})