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
		<div class="buy_cancel_detail_wrap">
			<h3>주문상세</h3>
			<h4>주문정보</h4>
			<div class="buy_cancel">
				<div class="buy_date">
					<div class="buy_cancel_title"><b>주문일자</b></div>
					<div class="buy_cancel_detail">
						<fmt:parseDate var="bdate" value="${bdto.b_date }" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="b_date" value="${bdate }" pattern="yyyy-MM-dd" />${b_date }
					</div>
				</div>
				<div class="buy_num">
					<div class="buy_cancel_title"><b>주문번호</b></div>
					<div class="buy_cancel_detail">${bdto.b_code }<input type="hidden" name="b_code" value="${bdto.b_code }" id="bCode"></div>
				</div>
				<div class="buy_item_price">
					<div class="buy_cancel_title"><b>주문금액</b></div>
					<div class="buy_cancel_detail">
						<fmt:formatNumber var="item_price" value="${total }" pattern="#,###"/>${item_price }원
					</div>
				</div>
				<div class="buy_point">
					<div class="buy_cancel_title"><b>사용 포인트</b></div>
					<c:if test="${empty point }">
						<div class="buy_cancel_detail">0포인트</div>
					</c:if>
					<c:if test="${not empty point }">
						<div class="buy_cancel_detail"><fmt:formatNumber var="usePoint" value="${point }" pattern="#,###"/>${usePoint }포인트</div>
					</c:if>
				</div>
				<div class="buy_price">
					<div class="buy_cancel_title"><b>총 결제금액</b></div>
					<c:if test="${(total+point) < 50000 }">
						<div class="buy_cancel_detail"><fmt:formatNumber var="totalPrice" value="${(total+point)+3000 }" pattern="#,###"/>${totalPrice }원</div>
					</c:if>
					<c:if test="${(total+point) >= 50000 }">
						<div class="buy_cancel_detail"><fmt:formatNumber var="totalPrice" value="${(total+point) }" pattern="#,###"/>${totalPrice }원</div>
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
								<td class="item_td"><fmt:formatNumber var="i_price" value="${(bidto.i_price*bidto.bi_cnt)+point }" pattern="#,###"/>${i_price }원</td>
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
			<c:if test="${pageNum == 0 }">
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
			</c:if>
			<c:if test="${pageNum > 0 }">
				<div class="cancel_info">
					<h4>취소정보</h4>
					<div class="cancel_info_date">
						<div class="buy_cancel_left"><b>취소일자</b></div>
						<div class="buy_cancel_right">
							<fmt:formatDate var="cancelDate" value="${cancelDate }" pattern="yyyy-MM-dd"/>${cancelDate }
						</div>
					</div>
				</div>
			</c:if>
			<div class="list_btn">
				<c:if test="${pageNum == 0}">
					<input type="button" value="주문취소" id="buy_Cancel">
					<input type="button" value="목록" onclick="javascript:buyCancel(0);">
				</c:if>
				<c:if test="${pageNum > 0}">
					<input type="button" value="목록" onclick="javascript:buyCancel(${pageNum}, '${start_date }', '${end_date }');">
				</c:if>
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
						location.href="myPage";
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
	
	//주문목록
	function buyCancel(pn, sd, ed) {
		$.ajax({
			url : "buyCancel",
			type : "post",
			data : { "pageNum" : pn, "start_date" : sd, "end_date" : ed },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
				
				if($("#start").val() != "" && $("#end").val() != "") {
					$("#from").val($("#start").val());
					$("#to").val($("#end").val());
					$(".date_search_div").show();
				}
				$("#count").val();
				$("#pagenum").val();
			},
			error : function(data, error) {
				alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
			}
		});
	}
</script>
</html>