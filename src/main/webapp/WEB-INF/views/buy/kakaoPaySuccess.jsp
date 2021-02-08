<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/kakaoPaySuccess.css">
</head>
<body>
<div class="wrap">
	<div id="info_div">
		<h3> - 카카오페이 결제가 정상적으로 완료되었습니다. - </h3><br>
		<p id="pmsg">※ 비회원은 <span>주문조회</span> 및 <span>구매평</span> 작성시 <span>주문번호</span>가 필요하오니 <span>주문번호</span>를 꼭 확인해 주세요.</p>
		<div id="info_msg_div">
			<c:set var="infoApprovedAt" value="${info.approved_at}" />
			<fmt:formatDate var="approved_at_fmt" value="${infoApprovedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<input type="hidden" name="approved_at" value="${info_approved_at}">
	            <p><label class="info_label">결제일시</label>: <input type="text" name="info_approved_at" class="info_msg" value="${approved_at_fmt}"></p>
	            <p><label class="info_label">주문번호</label>: <input type="text" name="partner_order_id" class="info_msg" value="${info.partner_order_id}"></p>
	            <p><label class="info_label">상품명</label>: <input type="text" name="item_name" class="info_msg" value="${info.item_name}"></p>
	            <p><label class="info_label">상품수량</label>: <input type="text" name="quantity" class="info_msg" value="${info.quantity}"></p>
            <c:set var="total" value="${info.amount.total}" />
            <fmt:formatNumber var="amount_total_fmt" value="${total }" pattern="#,##0원"/>
            <input type="hidden" name="amount_total" value="${info.amount.total}">  
	            <p><label class="info_label">결제금액</label>: <input type="text" name="info_amount_total" class="info_msg" value="${amount_total_fmt }"></p>
	            <p><label class="info_label">결제방법</label>: <input type="text" name="payment_method_type" class="info_msg" value="${info.payment_method_type}"></p><br>
	    </div>
		<div id="btn_div"><input type="button" id="closebtn" value="결제완료 / 닫기" onclick="close_window()"></div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function close_window(){
		window.open('','_self').close();
	}
	
	$(function(){
		window.opener.$("#payform").attr("action", "/picker/buy/insertBuyitems");
		window.opener.$("#payform").submit();
	});
	
</script>
</html>