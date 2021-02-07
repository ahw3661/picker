// 메뉴 선택 css
$(".admin_a").click(function() {
	$(".admin_a").removeClass("on");
	$(".admin_a").addClass("off");
	$(this).removeClass("off");
	$(this).addClass("on");
});

// 주문관리
function allBuyList(pn) {
	$.ajax({
		url : "allBuyList",
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
			if(data.status == -1) {
				var hostIndex = location.href.indexOf(location.host) + location.host.length; // http://localhost:8090/
				var ctxPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)); // picker
				location.href = ctxPath + "/loginPage";
			}else {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		}
	});
}

// 포인트관리
function allPointList(pn) {
	$.ajax({
		url : "allPointList",
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
			if(data.status == -1) {
				var hostIndex = location.href.indexOf(location.host) + location.host.length; // http://localhost:8090/
				var ctxPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)); // picker
				location.href = ctxPath + "/loginPage";
			}else {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		}
	});
}

// 상품관리
function goItemList(pn) {
	$.ajax({
		url : "goItemList",
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
			if(data.status == -1) {
				var hostIndex = location.href.indexOf(location.host) + location.host.length; // http://localhost:8090/
				var ctxPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)); // picker
				location.href = ctxPath + "/loginPage";
			}else {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		}
	});
}

//상품등록 화면 가는 함수
function goItemInsert() {
	$.ajax({
		url : "goItemInsert",
		type : "post",
		datatype : "html",
		success : function(data) {
			$(".menu_info").children().remove();
			$(".menu_info").html(data);
		},
		error : function(data) {
			alert("ajax 실패");
		}
	});
}

// 회원관리
function goMemberList(pn) {
	$.ajax({
		url : "goMemberList",
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
			if(data.status == -1) {
				var hostIndex = location.href.indexOf(location.host) + location.host.length; // http://localhost:8090/
				var ctxPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)); // picker
				location.href = ctxPath + "/loginPage";
			}else {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		}
	});
}