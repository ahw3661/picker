<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminItemDetail_wrap">
			<h3>상품상세</h3>
			<div class="AdminItemDetail_div">
				<div class="item_img_div">
					<img alt="img" src="resources/image/category_img/${idto.i_img }">
				</div>&nbsp;
				<div class="item_info_div">
					<p><label>상품명</label><span>${idto.i_name }</span></p>
					<p>
						<label>상품가격</label>
						<span><fmt:formatNumber var="i_price" value="${idto.i_price }" pattern="#,###원"/>${i_price }</span>
					</p>
					<p>
						<label>입고날짜</label>
						<fmt:parseDate var="i_date" value="${idto.i_date }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="ib_date" value="${i_date }" pattern="yyyy-MM-dd"/><span>${ib_date }</span>
					</p>
					<p>
						<label>카테고리</label>
						<span>${idto.i_category }</span>
					</p>
					<p>
						<label>상품게시여부</label>
						<c:if test="${idto.i_chk == 0 }"><span>판매중</span></c:if>
						<c:if test="${idto.i_chk == 1 }"><span>판매중지</span></c:if>
						<c:if test="${idto.i_chk == 2 }"><span>품절</span></c:if>
					</p>
					<p>
						<label>상품코드</label>
						<span>${idto.i_code }</span>
					</p>
				</div>
		  	</div>
			<div id="List_btn">
				<input type="button" value="목록보기" onclick="javascript:goItemList(${pageNum});">
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	function goItemList(pn) {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "pageNum" : pn },
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
</script>
</html>