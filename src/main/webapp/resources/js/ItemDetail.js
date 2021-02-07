var cnt = document.getElementById("cnt");
var total_price = document.getElementById("total_price");
var item_price_info = document.getElementById("item_price_info");
var cnted = 1;
var code = $("input[name=i_code]").val();

function plus(){
	cnted += 1;
	cnt.value = cnted;
	price = item_price.value * cnt.value;
	total_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	console.log("total_price= " + total_price.value);
}

function minus(){
	if(cnt.value > 1){
		cnted -= 1;
		cnt.value = cnted;
		price = item_price.value * cnt.value;
		total_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		console.log("total_price= " + total_price.value);
	}
}

$(function(){
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

$(document).on("click", ".replySub", function(event){
	var formData = new FormData(event.target.closest("form"));
	formData.append("q_num", $("input[name=q_num]").val());
	$.ajax({
		url : "replyWriteItemDetail",
		type : "post",
		datatype : "html",
		data : formData,
		contentType : false, 
		processData : false,
		success : function(data){
			$("aside").html(data);
			setReplyPop();
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
})

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

function setReplyPop(){
	$(".replyPopBtn").each(function(){
		$(this).data("pop", true);
		$(this).click(function(){
			if($(this).data("pop")){
				var ele = "<form class='popWriteReply'><input type='hidden' name='ref' value='"
						+ $(this).parent().find("input[name=r_num]").val() + "'>"
						+ "<textarea rows='5' cols='100' maxlength='500' placeholder='댓글' name='r_content'></textarea>"
						+ "<div class='rightBtnDiv'><input type='button' value='등록' class='btnBlack replySub'></div></form>"
				$(this).parent().after(ele);
				$(this).data("pop", false);
			} else {
				$(this).closest(".replyInner").find(".popWriteReply").remove();
				$(this).data("pop", true);
			}
		})
	})
	$(".replyModifyBtn").each(function(){
		$(this).data("pop", true);
		$(this).click(function(){
			console.log($(this).data("pop"));
			var txtarea = $(this).closest(".replyInner").find(".replyContent");
			if($(this).data("pop")){
				txtarea.html("<textarea rows='5' cols='100' maxlength='500' name='r_content' class='replyModifyArea'>" 
					+ txtarea.html() + "</textarea>");
				$(this).data("pop", false);
			} else {
				$.ajax({
					url : "replyModify", 
					type : "post",
					datatype : "text",
					data : {"returnText" : true, "r_num" : $(this).closest(".qnaOpt").find("input[name=r_num]").val(),
						"r_content" : $(this).closest(".replyInner").find(".replyModifyArea").val()},
					context: this,
					success : function(text){
						txtarea.html(text);
						$(this).data("pop", true);
					}
				})
			}
		})
	})
}

function getQnaPop(num){
	$.ajax({
		url : "qnaPop?num=" + num,
		type : "get",
		datatype : "html",
		success : function(data){
			$("body").append(data);
			$("body").css("overflow-y", "hidden");
			setReplyPop();
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
}

function closePop(){
	$("aside").remove();
	$("body").css('overflow-y', 'auto');
}

function qnaDelete(){
	if(window.confirm("해당 글을 삭제하시겠습니까?")){
		$.ajax({
			url : "qnaDelete?code=" + $("input[name=i_code]").val() + "&num=" + $("input[name=q_num]").val(),
			type : "get",
			datatype : "html",
			success : function(data){
				window.alert("해당 글이 삭제되었습니다.");
				$("#item_qna").html(data);
				closePop();
			}
		})
	}
}


function replyDelete(num){
	if(window.confirm("댓글을 삭제하시겠습니까?")){
		$.ajax({
			url : "replyDelete?q_num=" + $("input[name=q_num]").val() + "&r_num=" + num,
			type : "get",
			datatype : "html",
			success : function(data){
				$("aside").html(data);
				setReplyPop();
			}
		})
	}
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