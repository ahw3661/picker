<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/NonePage.css" type="text/css">
</head>
<body>
	<section>
		<div class="none_buy_info_wrap">
			<h3>주문상세</h3>
			<h4>주문정보</h4>
			<div class="none_buy_info">
				<div class="none_buy_date">
					<div class="none_buy_info_title"><b>주문일자</b></div>
					<div class="none_buy_info_detail">
						<fmt:parseDate var="bdate" value="${bdto.b_date }" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="b_date" value="${bdate }" pattern="yyyy-MM-dd" />${b_date }
					</div>
				</div>
				<div class="none_buy_num">
					<div class="none_buy_info_title"><b>주문번호</b></div>
					<div class="none_buy_info_detail">${bdto.b_code }<input type="hidden" name="b_code" value="${bdto.b_code }" id="bCode"></div>
				</div>
				<div class="none_buy_item_price">
					<div class="none_buy_info_title"><b>주문금액</b></div>
					<div class="none_buy_info_detail">
						<fmt:formatNumber var="item_price" value="${total }" pattern="#,###"/>${item_price }원
					</div>
				</div>
				<div class="none_buy_price">
					<div class="none_buy_info_title"><b>총 결제금액</b></div>
					<c:if test="${total < 50000 }">
						<div class="none_buy_info_detail"><fmt:formatNumber var="totalPrice" value="${total+3000 }" pattern="#,###"/>${totalPrice }원</div>
					</c:if>
					<c:if test="${total >= 50000 }">
						<div class="none_buy_info_detail"><fmt:formatNumber var="totalPrice" value="${total }" pattern="#,###"/>${totalPrice }원</div>
					</c:if>
				</div>
			</div>
			<div class="none_item_info">
				<h4>주문상품</h4>
				<div class="none_item_detail">
					<table>
						<tr>
							<th class="none_item_th"></th>
							<th class="none_item_th">상품명</th>
							<th class="none_item_th">수량</th>
							<th class="none_item_th">상품 금액</th>
							<th class="none_item_th">배송비</th>
						</tr>
						<c:set var="no" value="1"/>
						<c:forEach var="bidto" items="${bidto }">
							<tr>
								<td class="none_item_td"><c:out value="${no }"></c:out></td>
								<td class="none_item_td"><img alt="img" src="resources/image/category_img/${bidto.i_img }">&nbsp;${bidto.i_name }</td>
								<td class="none_item_td">${bidto.bi_cnt }개</td>
								<td class="none_item_td"><fmt:formatNumber var="i_price" value="${bidto.i_price*bidto.bi_cnt }" pattern="#,###"/>${i_price }원</td>
								<td class="none_item_td none_bprice">
									<c:if test="${bdto.b_price == 0}">
										<fmt:formatNumber var="b_price" value="${bdto.b_price }"/>무료
									</c:if>
									<c:if test="${bdto.b_price > 0}">
										<fmt:formatNumber var="b_price" value="${bdto.b_price }" pattern="#,###"/>${b_price }원
									</c:if>
								</td>
							</tr>
							<c:set var="no" value="${no + 1 }"/>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="none_take_info">
				<h4>배송지</h4>
				<div class="none_take_detail">
					<div class="none_take_detail_left"><b>받는사람</b></div>
					<div class="none_take_detail_right">
						<p>${bdto.b_take_name }</p>
						<p>(${bdto.b_take_zipcode }) ${bdto.b_take_roadaddr } ${bdto.b_take_detailaddr }</p>
						<p>/ ${bdto.b_take_phone }</p>
					</div>
				</div>
			</div>
			<div class="btn_div">
				<c:if test="${chk == 1 }"><input type="button" value="주문취소" id="buy_Cancel"></c:if>
				<input type="button" value="닫기" onclick="location.href='closed'">
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	$(function() {
		// 배송비 td 병합
		$(".none_bprice").each(function() {
			var price = $(".none_bprice:contains('" + $(this).text() + "')"); // .none_bprice를 기준으로 text가 포함된 row 가져옴
			
			if(price.length > 1) {
				price.eq(0).attr("rowspan", price.length); // 중복되는 첫번째 td에 rowspan 값 추가
				price.not(":eq(0)").remove(); // 중복되는 td 삭제
			}
		});
	});
	
	$("#buy_Cancel").click(function() {
		if(confirm("해당 구매 건에 대해 구매 취소를 하시겠습니까?") == true) {
			$.ajax({
				url : "buyCancelRun",
				type : "post",
				data : { "b_code" : $("#bCode").val() },
				datatype : "json",
				success : function(data) {
					if(data.msg == "success") {
						alert("구매 취소가 완료되었습니다.");
						location.href="section";
					}
				},
				error : function(data, error) {
					alert("code : " + data.status + "\n"+"message : " + data.responseText + "\n" + "error : " + error);
				}
			});
		}else {
			return false;
		}
		
	});
</script>
</html>