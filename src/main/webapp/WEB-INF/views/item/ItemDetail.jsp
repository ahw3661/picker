<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<link rel="stylesheet" href="resources/css/ItemDetail.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
	<div class="wrap itemDetail_wrap">
		 <div id="head_name">
			<a href="#" class="menuBtn">Home</a>&nbsp;&nbsp;》&nbsp;&nbsp;
		    <a href="goList?i_category=${idto.i_category }" class="menuBtn">${idto.i_category }</a>&nbsp;&nbsp;》&nbsp;&nbsp;
			<strong>${idto.i_name }</strong>
			<br><br> 
			<hr>
		 </div>
	  	 <div class="info_div">
			<div class="img_div">
				<img alt="img" src="resources/image/category_img/${idto.i_img }">
			</div>&nbsp;
			<div class="item_info">
			<form method="post" id="fm">
				<div id="first_info">
					<h3>${idto.i_name }</h3>
					<input type="hidden"  name="i_price"  value="${idto.i_price}" id="item_price">
		            <fmt:formatNumber var="item_price_info" value="${idto.i_price}" pattern="#,###원"/>
		            <input type="text" name="item_price_info" id=item_price_info value="${item_price_info }"><hr>
					<p id="purchase_info"><strong>구매혜택</strong><br> 140 포인트 적립 예정</p><br>
					<p><strong>배송비</strong><br> 3,000원(50,000원 이상 무료 배송)</p>
				</div>
				<div id="cnt_info">		
					<div class="info_bottom">
						<p id="item_cnt">수량</p><hr> 
						<div id="cnt_price_info">
							<div id="cnt_button">
								<input type="button"  class="pm_Btn"  value="-" onclick="minus()">
								<input type="text" name="c_cnt"  value="1" id="cnt" readonly="readonly">
								<input type="button" value="+"  class="pm_Btn"  onclick="plus()">																
							</div>
							<div><input type="text" name="total_price" id="total_price" value="${item_price_info }" readonly="readonly"></div>
						</div>
						<div id="purchase_bottun"><input type="submit" id="purchase_btn" value="구매하기" formaction="gobuyPage_FromDetail">
						<input type="button" id="basket_btn" value="장바구니"></div>
					</div>
				</div>
					<input type="hidden" name="i_code" value="${idto.i_code }">
					<input type="hidden" name="i_name" value="${idto.i_name }">
					<input type="hidden" name="i_img" value="${idto.i_img }">
					<input type="hidden" name="m_id" value="${u_id }">
				</form>
			</div>
	  	</div>		
	  	<div class="fixed_info"><a href="#">상세정보</a>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<a href="#item_evel" >구매평</a>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<a href="#item_qna" >Q &amp; A</a><br><br><hr></div>
		<div class="detail_div">
			<img alt="img" src="resources/image/detail_img/${idto.i_detailimg }">
		</div>
		<div id="item_contents">
			<div class="contents">
				<p><b>■ 교환 및 반품 안내</b></p>
				<p> - 전자상거래법에 의거하여 교환 및 반품 요청은 수령일로부터 7일 이내 입니다.</p>
				<p> - 고객의 단순 변심이나 실수로 인한 교환 및 반품의 경우 왕복 배송비는 고객이 부담하셔야 합니다.</p>
				<p> - 상품을 사용하였을 경우는 교환 및 반품이 불가합니다. (*상품 하자의 경우 예외)</p>
				<br>
				<p><b>■ 제품불량에 따른 교환 및 반품 정책</b></p>
				<p> - 제품 수령일을 기준으로 7일이내 가능하며 뚜렷한 파손 및 제품 기능에 중대한 영향이 있는 경우에 한하여 가능합니다.</p>
				<br>
				<p><b>■ 단순 변심에 의한 교환 및 환불 정책</b></p>
				<p> - 제품 공급을 위한 운송비 전액(왕복 및 재발송)을 제한 금액이 환불됩니다.</p>
				<br>
				<p><b>■ 교환 및 반품이 불가능한 경우</b></p>
				<p> - 포장을 개봉하였거나 훼손되어 상품 가치가 상실된 경우</p>
				<p> - 고객님의 사용 또는 일부 소비에 의해 상품의 가치가 현저히 감소한 경우</p>
				<p> - 교환 및 반품 접수 기간(7일)의 경과 혹은 시간의 경과에 의해 재판매가 곤란할 정도로 상품등의 가치가 현저히 감소한 경우</p>
			</div>
		</div>
		<div id="item_evel"></div>
		<div id="item_qna"></div>
		<a id="toplink" href="#">TOP</a>
	</div>
</section>
</body>
<script type="text/javascript" src="resources/js/ItemDetail.js"></script>
<script type="text/javascript">
	getQnaList(1);
</script>
</html>