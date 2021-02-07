<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/AdminPage.css" type="text/css">
</head>
<body>
	<section>
		<div class="wrap">
			<div class="admin_page_wrap">
				<div class="menu_list_left">
					<input type="hidden" name="m_id" value="${u_id }" id="mid">
					<ul>
						<li><a href="javascript:allBuyList(1)" class="admin_a" id="buy_list">주문내역 관리</a></li>
						<li><a href="#" class="admin_a" id="buy_cancel">주문취소 관리</a></li>
						<li><a href="javascript:allPointList(1);" class="admin_a" id="point_list">포인트 관리</a></li>
						<li><a href="#" class="admin_a">1:1문의 관리</a></li>
						<li><a href="javascript:goItemList(1);" class="admin_a" id="item_list">상품 관리</a></li>
						<li><a href="javascript:goItemInsert();" class="admin_a">상품 등록</a></li>
						<li><a href="javascript:goMemberList(1);" class="admin_a" id="member_list">회원 관리</a></li>
					</ul>
				</div>
				<div class="menu_list_right">
					<div class="right_top">
						<div class="user"><p>${u_name}</p></div>
						<div class="part_line"></div>
						<div class="user_count">
							<p>회원수</p>
							<div>
								<c:if test="${memberCount == 0 }">${memberCount }</c:if>
								<c:if test="${memberCount > 0 }">
									<fmt:formatNumber var="memberCount" value="${memberCount }" pattern="#,###"/>${memberCount }
								</c:if>
							</div>
						</div>
						<div class="part_line"></div>
						<div class="item_count">
							<p>상품수</p>
							<div>
								<c:if test="${itemCount == 0 }">${itemCount }</c:if>
								<c:if test="${itemCount > 0 }">
									<fmt:formatNumber var="itemCount" value="${itemCount }" pattern="#,###"/>${itemCount }
								</c:if>
							</div>
						</div>
					</div>
					<div class="right_bottom">
						<div class="menu_info"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	// 메뉴 선택 css
	$(".admin_a").click(function() {
		$(".admin_a").removeClass("on");
		$(".admin_a").addClass("off");
		$(this).removeClass("off");
		$(this).addClass("on");
	});
	
	/* $(document).ready(function() {
		if(document.location.hash) {
			var hash = document.location.hash.replace("#", "");
			var target = $("#" + hash);
			
			
		}
	}); */
	
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
</script>
</html>