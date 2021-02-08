// 메뉴 선택 css
$(".admin_a").click(function() {
	$(".admin_a").removeClass("on");
	$(".admin_a").addClass("off");
	$(this).removeClass("off");
	$(this).addClass("on");
});

// 주문관리
function allBuyList() {
	$.ajax({
		url : "allBuyList",
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

// 주문취소관리
function allBuyCancel() {
	$.ajax({
		url : "allBuyCancel",
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

// 포인트관리
function allPointList() {
	$.ajax({
		url : "allPointList",
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

// 상품관리
function goItemList() {
	$.ajax({
		url : "goItemList",
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

//상품등록 화면 가는 함수
function goItemInsert() {
	$.ajax({
		url : "goItemInsert",
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

// 회원관리
function goMemberList() {
	$.ajax({
		url : "goMemberList",
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

// 1:1 문의 - 페이징 이동
function goQnaList(pn){
	var formData = new FormData();
	formData.append("pageNum", pn);
	if($(".qnaAdminList input[name=colHid]").length && $(".qnaAdminList input[name=keyHid]").length){
		formData.append("column", $(".qnaAdminList input[name=colHid]").val());
		formData.append("keyword", $(".qnaAdminList input[name=keyHid]").val());
	}
	qnaAjax(formData);
}

// 1:1 문의 - 목록 옵션 변경
function qnaChangeOpt(pn){
	var formData = new FormData();
	formData.append("pageNum", pn);
	qnaAjax(formData);
}

// 1:1 문의 - 검색어 입력
function qnaSearch(){
	if($(".qnaAdminList input[name=keyword]").val() != ""){
		var formData = new FormData();
		formData.append("pageNum", 1);
		formData.append("column", $(".qnaAdminList select[name=column]").val());
		formData.append("keyword", $(".qnaAdminList input[name=keyword]").val());
		qnaAjax(formData);
	} else {
		window.alert("검색어를 입력해주세요.");
	}
}

// 1:1 문의 - 공통 ajax 함수
function qnaAjax(FormData){
	if($(".qnaAdminList select[name=rchk]").length) FormData.append("rchk", $(".qnaAdminList select[name=rchk]").val());
	if($(".qnaAdminList select[name=code]").length) FormData.append("code", $(".qnaAdminList select[name=code]").val());
	$.ajax({
		url : "allQnaList",
		type : "post",
		dataType : "html",
		data : FormData,
		contentType : false, 
		processData : false,
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("ajax", "true");
		},
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
	})
}