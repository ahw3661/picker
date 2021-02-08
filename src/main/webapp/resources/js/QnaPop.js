// 글 내용 상세
function getQnaPop(num){
	$.ajax({
		url : "qnaPop?q_num=" + num,
		type : "get",
		dataType : "html",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "html");
			xmlHttpRequest.setRequestHeader("admin", "accessible");
		},
		success : function(data){
			$("body").append(data);
			if($("#qnaPop").length){
				$("body").css("overflow-y", "hidden");
				setReplyPop();
			}
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
}

// 댓글 파트 기능
function setReplyPop(){
	// 댓글 창 오픈
	$(".replyPopBtn").each(function(){
		$(this).data("pop", true);
		$(this).click(function(){
			if($(this).data("pop")){
				var ele = "<form class='popWriteReply'><input type='hidden' name='ref' value='"
						+ $(this).parent().find("input[name=r_num]").val() + "'>"
						+ "<textarea rows='5' cols='100' maxlength='500' placeholder='댓글' name='r_content'></textarea>"
						+ "<div class='rightBtnDiv'><input type='button' value='등록' class='btnBlack replySub'></div></form>"
				$(this).parent().after(ele);
				$(this).find(".replySub").on("click", function(){
					if($(this).find("textarea[name=r_content]").val() != ""){
						var formData = new FormData($(this).find(".popWriteReply")[0]);
						formData.append("q_num", $("input[name=q_num]").val());
						for(var pair of formData){
							console.log(pair[0] + " : " + pair[1]);
						}
						console.log("실행");
						$.ajax({
							url : "replyWrite",
							type : "post",
							dataType : "json",
							data : formData,
							contentType : false, 
							processData : false,
							beforeSend : function(xmlHttpRequest) {
								xmlHttpRequest.setRequestHeader("ajax", "json");
								xmlHttpRequest.setRequestHeader("admin", "accessible");
							},
							success : function(json){
								if(json.chk){
									var qnum = $("input[name=q_num]").val();
									window.alert("댓글이 등록되었습니다.");
									$("aside").remove();
									getQnaPop(qnum);
								} else if(json.logError != undefined && json.logError) {
									window.alert("[세션종료] 댓글은 작성자 본인 또는 관리자만 작성할 수 있습니다.");
								} else {
									window.alert("[오류] 댓글 작성 실패하였습니다.");
								}
							},
							error:function(request,error){
								console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							}
						})
					} else {
						window.alert("내용을 입력해주세요.");
					}
				});
				$(this).data("pop", false);
			} else {
				$(this).closest(".replyInner").find(".popWriteReply").remove();
				$(this).data("pop", true);
			}
		})
	})
	// 댓글 수정
	$(".replyModifyBtn").each(function(){
		$(this).data("pop", true);
		$(this).click(function(){
			var txtarea = $(this).closest(".replyInner").find(".replyContent");
			if($(this).data("pop")){
				txtarea.html("<textarea rows='5' cols='100' maxlength='500' name='r_content' class='replyModifyArea'>" 
					+ txtarea.html() + "</textarea>");
				$(this).data("pop", false);
			} else {
				if(txtarea.find(".replyModifyArea").val() != ""){
					$.ajax({
						url : "replyModify", 
						type : "post",
						datatype : "json",
						beforeSend : function(xmlHttpRequest) {
							xmlHttpRequest.setRequestHeader("ajax", "json");
						},
						data : {"r_num" : $(this).closest(".qnaOpt").find("input[name=r_num]").val(),
							"r_content" : $(this).closest(".replyInner").find(".replyModifyArea").val()},
						success : function(json){
							if(json.chk){
								var qnum = $("input[name=q_num]").val();
								window.alert("댓글이 수정되었습니다.");
								$("aside").remove();
								getQnaPop(qnum);
							} else if(json.logError != undefined && json.logError) {
								window.alert("[세션종료] 작성자 본인만 수정 가능합니다.");
							} else {
								window.alert("[오류] 댓글 수정 실패하였습니다.");
							}
						}
					})
				} else {
					window.alert("내용을 입력해주세요.");
				}
			}
		})
	})
}

// 댓글 등록
$(document).on("click", ".replySub", function(event){
	var formData = new FormData(event.target.closest("form"));
	formData.append("q_num", $("input[name=q_num]").val());
	for(var pair of formData){
		console.log(pair[0] + " : " + pair[1]);
	}
	console.log("실행");
	$.ajax({
		url : "replyWrite",
		type : "post",
		dataType : "json",
		data : formData,
		contentType : false, 
		processData : false,
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "json");
			xmlHttpRequest.setRequestHeader("admin", "accessible");
		},
		success : function(json){
			if(json.chk){
				var qnum = $("input[name=q_num]").val();
				window.alert("댓글이 등록되었습니다.");
				$("aside").remove();
				getQnaPop(qnum);
			} else if(json.logError != undefined && json.logError) {
				window.alert("[세션종료] 댓글은 작성자 본인 또는 관리자만 작성할 수 있습니다.");
			} else {
				window.alert("[오류] 댓글 작성 실패하였습니다.");
			}
		},
		error:function(request,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
})

// 글 내용 닫기
function closePop(){
	$("aside").remove();
	$("body").css('overflow-y', 'auto');
	if($(".qnaAdminList").length){
		var pn = $(".qnaAdminList .pageBtnStd").html();
		goQnaList(pn);
	}
	if($("#item_qna").length){
		var pn = $("#item_qna .pageBtnStd").html();
		getQnaList(pn);
	}
}

// 글 삭제
function qnaDelete(){
	if(window.confirm("해당 글을 삭제하시겠습니까?")){
		$.ajax({
			url : "qnaDelete?code=" + $("input[name=i_code]").val() + "&q_num=" + $("input[name=q_num]").val(),
			type : "get",
			dataType : "json",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "json");
				xmlHttpRequest.setRequestHeader("admin", "accessible");
			},
			success : function(json){
				if(json.chk){
					window.alert("해당 글이 삭제되었습니다.");
					closePop();
					getQnaList(1);
				} else if(json.logError != undefined && json.logError) {
					window.alert("[세션종료] 작성자 본인만 삭제 가능합니다.");
				} else {
					window.alert("[오류] 삭제 실패하였습니다.");
				}
			}
		})
	}
}

// 댓글 삭제
function replyDelete(num){
	if(window.confirm("댓글을 삭제하시겠습니까?")){
		$.ajax({
			url : "replyDelete?r_num=" + num,
			type : "get",
			dataType : "json",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "json");
				xmlHttpRequest.setRequestHeader("admin", "accessible");
			},
			success : function(json){
				if(json.chk){
					var qnum = $("input[name=q_num]").val();
					window.alert("댓글이 삭제되었습니다.");
					$("aside").remove();
					getQnaPop(qnum);
				} else if(json.logError != undefined && json.logError) {
					window.alert("[세션종료] 작성자 본인만 삭제 가능합니다.");
				} else {
					window.alert("[오류] 댓글 삭제 실패하였습니다.");
				}
			}
		})
	}
}
