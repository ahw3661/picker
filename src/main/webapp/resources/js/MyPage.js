// 메뉴 선택 css
$(".my_a").click(function() {
	$(".my_a").removeClass("on");
	$(".my_a").addClass("off");
	$(this).removeClass("off");
	$(this).addClass("on");
});

//var url = ${not empty url} ? "${url}" : null;

// 주문조회
function buyInfo(pn) {
	$.ajax({
		url : "buyInfo",
		type : "post",
		data : { "pageNum" : pn },
		datatype : "html",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
		error : function(data, error) {
			alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
		}
	});
}

// 포인트
function pointInfo(pn) {
	$.ajax({
		url : "pointInfo",
		type : "post",
		data : { "pageNum" : pn },
		datatype : "html",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
		error : function(data, error) {
			alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
		}
	});
}

// 내 정보수정
function myInfo() {
	$.ajax({
		url : "myInfo",
		type : "post",
		datatype : "html",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
		error : function(data, error) {
			alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
		}
	});
}

// 회원탈퇴
function withdrawPage() {
	$.ajax({
		url : "withdrawPage",
		type : "post",
		datatype : "html",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
		error : function(data, error) {
			alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
		}
	});
}

// 1:1문의
function qnaInfo(row) {
	if(row == undefined) row = 5;
	$.ajax({
		url : 'qnaMemberList?row=' + row,
		type : 'get',
		datatype : 'html',
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$('.menu_info').html(data);
		},
		error : function(){
			window.alert('ajax 오류');
		}
	})
}

function qnaDelete(num) {
	if(window.confirm("해당 글을 삭제하시겠습니까?")){
		$.ajax({
			url : 'qnaDelete?num=' + num,
			type : 'get',
			datatype : 'html',
			success : function(data) {
				window.alert('해당 글이 삭제되었습니다.');
				$('.menu_info').html(data);
			},
			error : function(){
				window.alert('ajax 오류');
			}
		})
	}
}

function replyPop(qna, reply){
	var ele = "<aside><form id='replyPop'><h4 class='stretchBlock'><span>댓글 작성</span>"
			+ "<a href='javascript:replyCancel()'><img alt='취소' src='resources/image/icon/closeBtn.svg'></a></h4>"
		 	+ "<textarea name='r_content'></textarea><input type='hidden' name='q_num' value='" + qna + "'>";
	if(reply != null) ele += "<input type='hidden' name='ref' value='" + reply + "'>";
	ele += 	"<div class='rightBtnDiv'><input type='button' value='취소' class='btnWhite' onclick='replyCancel()'>"
		+	"<input type='button' value='작성' class='btnBlack' onclick='replyWrite()'></form></aside>";
	$('body').append(ele);
	$('body').css('overflow-y', 'hidden');
}

function replyWrite(){
	$.ajax({
		url : 'replyWrite',
		type : 'post',
		data : $('#replyPop').serialize(),
		success : function(data){
			replyCancel();
			$('.menu_info').html(data);
		},
		error : function(){
			window.alert('ajax 오류');
		}
	})
}

function replyCancel(){
	$('aside').remove();
	$('body').css('overflow-y', 'auto');
}

function replyModPop(num){
	$.ajax({
		url : "replyGetContent?num=" + num,
		type : "get",
		datatype : "html",
		data : {"num" : num},
		success : function(text){
			var ele = "<aside><form id='replyPop'><h4 class='stretchBlock'><span>댓글 수정</span>"
					+ "<a href='javascript:replyCancel()'><img alt='취소' src='resources/image/icon/closeBtn.svg'></a></h4>"
				 	+ "<textarea name='r_content'>" + text + "</textarea><input type='hidden' name='r_num' value='" + num + "'>"
					+ "<div class='rightBtnDiv'><input type='button' value='취소' class='btnWhite' onclick='replyCancel()'>"
					+ "<input type='button' value='수정' class='btnBlack' onclick='replyModify()'></form></aside>";
			$('body').append(ele);
			$('body').css('overflow-y', 'hidden');
		}
	})
}

function replyModify(){
	$.ajax({
		url : 'replyModify',
		type : 'post',
		datatype : 'html',
		data : $('#replyPop').serialize(),
		success : function(data){
			replyCancel();
			$('.menu_info').html(data);
		},
		error : function(){
			window.alert('ajax 오류');
		}
	})
}

function replyDelete(num){
	if(window.confirm("댓글을 삭제하시겠습니까?")){
		$.ajax({
			url : 'replyDelete?r_num=' + num,
			type : 'get',
			datatype : 'html',
			success : function(data){
				$('.menu_info').html(data);
			}
		})
	}
}

