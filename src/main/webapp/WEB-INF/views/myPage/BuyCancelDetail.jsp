<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="buy_info_detail_wrap">
			<h3>주문상세</h3>
			<h4>주문정보</h4>
			<div class="buy_info">
				<div class="buy_date">
					<div class="buy_info_title"><b>주문일자</b></div>
					<div class="buy_info_detail">
						<fmt:parseDate var="bdate" value="${bdto.b_date }" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="b_date" value="${bdate }" pattern="yyyy-MM-dd" />${b_date }
					</div>
				</div>
				<div class="buy_num">
					<div class="buy_info_title"><b>주문번호</b></div>
					<div class="buy_info_detail">${bdto.b_code }</div>
				</div>
				<div class="buy_price">
					<div class="buy_info_title"><b>총 결제금액</b></div>
					<c:if test="${total < 50000 }">
						<div class="buy_info_detail"><fmt:formatNumber var="totalPrice" value="${total+3000 }" pattern="#,###"/>${totalPrice }원</div>
					</c:if>
					<c:if test="${total >= 50000 }">
						<div class="buy_info_detail"><fmt:formatNumber var="totalPrice" value="${total }" pattern="#,###"/>${totalPrice }원</div>
					</c:if>
				</div>
			</div>
			<div class="item_info">
				<h4>주문상품</h4>
				<div class="item_detail">
					<table>
						<tr>
							<th class="item_th"></th>
							<th class="item_th">상품명</th>
							<th class="item_th">수량</th>
							<th class="item_th">상품 금액</th>
							<th class="item_th">배송비</th>
						</tr>
						<c:set var="no" value="1"/>
						<c:forEach var="bidto" items="${bidto }">
							<tr>
								<td class="item_td"><c:out value="${no }"></c:out></td>
								<td class="item_td">${bidto.i_name }</td>
								<td class="item_td">${bidto.bi_cnt }개</td>
								<td class="item_td"><fmt:formatNumber var="i_price" value="${bidto.i_price*bidto.bi_cnt }" pattern="#,###"/>${i_price }원</td>
								<td class="item_td bprice">
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
			<div class="take_info">
				<h4>배송지</h4>
				<div class="take_detail">
					<div class="take_detail_left"><b>받는사람</b></div>
					<div class="take_detail_right">
						<p>${bdto.b_take_name }</p>
						<p>(${bdto.b_take_zipcode }) ${bdto.b_take_roadaddr } ${bdto.b_take_detailaddr }</p>
						<p>/ ${bdto.b_take_phone }</p>
					</div>
				</div>
			</div>
			<div class="list_btn">
				<input type="button" value="주문취소" id="buy_Cancel">
				<input type="button" value="목록" onclick="javascript:buyCancel();">
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	$(function() {
		// 같은 배송비 td 병합
		$(".bprice").each(function() {
			var price = $(".bprice:contains('" + $(this).text() + "')");
			
			if(price.length > 1) {
				price.eq(0).attr("rowspan", price.length);
				price.not(":eq(0)").remove();
			}
		});
	});
	
	//주문목록
	function buyCancel() {
		$.ajax({
			url : "buyCancel",
			type : "post",
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
			}
		});
	}
</script>
</html>