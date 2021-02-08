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