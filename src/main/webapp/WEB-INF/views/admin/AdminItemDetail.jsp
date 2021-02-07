<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminItemDetail_wrap">
			<h3>상품상세</h3>
			<table>
				<tr>
					<td colspan="2" class="info_title">상품 이미지</td>  
					<td colspan="2" class="info_title">상품 이름</td>
				</tr>
				<tr>
					<td colspan="2"><img alt="img" src="resources/image/category_img/${idto.i_img }"></td>
					<td colspan="2"><strong>${idto.i_name }</strong></td>
				</tr>    
				<tr>
					<th class="info_title">상품 가격</th>
					<td colspan="3"><fmt:formatNumber var="i_price" value="${idto.i_price }" pattern="#,###원"/>${i_price }</td>
				</tr>
				<tr>
					<th class="info_title">입고 날짜</th>
					<td colspan="3">
						<fmt:parseDate var="i_date" value="${idto.i_date }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="ib_date" value="${i_date }" pattern="yyyy-MM-dd"/>
						${ib_date }
					</td>
				</tr>
				<tr>
					<th class="info_title">카테고리</th>
					<td colspan="3">${idto.i_category }</td>
				</tr>
				<tr>
					<th class="info_title">상품게시여부</th>
					<td colspan="3">
						<c:if test="${idto.i_chk == 0 }">
							판매중
						</c:if>
						<c:if test="${idto.i_chk == 1 }">
							판매중지
						</c:if>
						<c:if test="${idto.i_chk == 2 }">
							품절
						</c:if>
					</td>
				</tr>
				<tr>
					<th class="info_title">상품 코드</th>
					<td colspan="3">${idto.i_code }</td>
				</tr>
			</table>
			<div id="List_btn">
			 <input type="button" value="목록보기" onclick="javascript:goItemList();">
			</div>
		</div>
	</section>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function goItemList() {
		$.ajax({
			url : "goItemList",
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
</script>
</html>