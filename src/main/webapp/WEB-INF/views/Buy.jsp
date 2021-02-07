<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Buy.css">
</head>
<body>
<section>
	<div class="wrap buywrap">
	<h2>결제하기</h2>
		<div class="order_form">
			<div id="order_wrap">
				<div id="order_item_info" class="order_info">
					<h4>주문 상품 정보</h4>
					<table id="t1">		
						<c:forEach var="payList" items="${payList }">
							<tr class="trs" onclick="location.href='goDetail?i_code=${payList.i_code}'">
								<td><img src="resources/image/category_img/${payList.i_img}" id="imgs"></td>
								<td class="tds">
									<input type="text" class="i_name" value="${payList.i_name }"><br>
									<input type="hidden" class="cnt_hidden" value="${payList.c_cnt }">
									<input type="text" class="c_cnt" value="${payList.c_cnt }개"><br>
									<input type="hidden" class="price_hidden" value="${payList.i_price }">
									<fmt:formatNumber var = "i_pricefm" value = "${payList.i_price }"/>
									<input type="text" class="i_price" value="${i_pricefm}원">
								</td>						
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<c:if test="${u_id!=null }">
					<div id="order_user_info" class="order_info">
					<h4>주문자 정보</h4>
						<input type="text" id="mname" value="${mdto.m_name }" class="user_info" readonly="readonly"><br>
						<input type="text" id="mphone" value="${mdto.m_phone }" class="user_info" readonly="readonly"><br>
						<input type="text" value="${mdto.m_email }" class="user_info" readonly="readonly">
						</div>
					<div id="order_delivery_info" class="order_info">
					<h4>배송 정보</h4>
						<input type="checkbox" id="delivery_chk"> <span class="chk_span">주문자 정보와 동일</span><br>
						<input type="text" id="m_name" placeholder="수령인"><input type="text" id="m_phone" placeholder="연락처"><br>
						<input type="text" id="m_zipcode" placeholder="우편번호"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
						<input type="text" id="m_roadaddr" placeholder="주소"><br>
						<input type="text" id="m_detailaddr" placeholder="상세주소">
					</div>
				</c:if>
				<c:if test="${u_id==null }">
					<div id="order_user_info" class="order_info">
					<h4>주문자 정보</h4>
						<input type="text" value="${mdto.m_name }" class="null_user" placeholder="이름을 입력해주세요"> <input type="text" value="${mdto.m_phone }" class="null_user" placeholder="연락처를 입력해주세요"><br>
						<input type="text" value="${mdto.m_email }" class="null_user" placeholder="E-mail을 입력해주세요">
					</div>
					<div id="order_delivery_info" class="order_info">
					<h4>배송 정보</h4>
						<input type="text" id="m_name" placeholder="수령인"><input type="text" id="m_phone" placeholder="연락처"><br>
						<input type="text" id="m_zipcode" placeholder="우편번호"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
						<input type="text" id="m_roadaddr" placeholder="주소"><br>
						<input type="text" id="m_detailaddr" placeholder="상세주소">	
					</div>
				</c:if>
				<input type="hidden" id="zipcode" value="${mdto.m_zipcode}">
				<input type="hidden" id="roadaddr" value="${mdto.m_roadaddr}">
				<input type="hidden" id="detailaddr" value="${mdto.m_detailaddr}">
				
				<div id="order_point_info" class="order_info">
					<h4>포인트</h4>
					<input type="hidden" id="point_use" value="${mdto.m_point }">
					<input type="text" id="point"><input type="button" id="point_btn" value="전액사용">
					<p id="point_P">보유 포인트<span>${mdto.m_point }</span></p>
					<p id="point_Pinfo">1,000 포인트 이상 보유 및 10,000원 이상 구매시 사용가능</p>
				</div>
			</div>
			<div id="fixed_wrap">
				<div id="pay_wrap" class="order_info">
					<h4>최종 결제금액</h4>
					<p class="pay_info"><span id="itemPrice_span">상품가격</span><span id="itemPrice"></span></p>
					<p class="pay_info"><span id="deiliveryPrice_span">배송비 </span><span id="deilivery"></span></p>
					<hr>
					<p id="totalP"><span id="totalPrice_span">총 결제금액</span><span id="totalPrice"></span></p>
				</div>
				<div class="order_info bg-gray">
					<p><span class="saving_point"></span> 포인트 적립예정</p>
				</div>
				<div id="pay_option" class="order_info">
					<h4>결제방법</h4>
					<input type="radio" id="kakaopay"> 카카오페이
				</div>
				<div id="agree_div" class="order_info">
					<input type="checkbox" id="agree"> 구매조건 확인 및 결제진행에 동의
				</div>
				<div id="pay_btn">
					<a href="#" id="pay_abtn">결제하기</a>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function postcode() {
	new daum.Postcode({	
        oncomplete: function(data) {	
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("m_zipcode").value = data.zonecode;	
            document.getElementById("m_roadaddr").value = roadAddr;
            document.getElementById("m_detailaddr").focus();
        }
    }).open();
}


$(function(){
	calculate();
	$("#point_btn").click(function(){
		$("#point").val($("#point_use").val());
	});
	
	$("#delivery_chk").click(function() { // 주문자 동일 체크박스 선택시 이벤트
    	var chek = $(this).is(":checked");
    	if(chek == true){ 
    		$("#m_name").val($("#mname").val());
    		$("#m_phone").val($("#mphone").val());
    		$("#m_zipcode").val($("#zipcode").val());
        	$("#m_roadaddr").val($("#roadaddr").val());
        	$("#m_detailaddr").val($("#detailaddr").val());
        }else{
        	$("#m_name").val("");
    		$("#m_phone").val("");
    		$("#m_zipcode").val("");
        	$("#m_roadaddr").val("");
        	$("#m_detailaddr").val("");
        }
	});
});
	
function calculate(){ //계산만하는 함수
	var cnt_hidden = document.getElementsByClassName("cnt_hidden");
	var price_hidden = document.getElementsByClassName("price_hidden");
	var saving_point = document.getElementsByClassName("saving_point");
	var itemPrice = document.getElementById("itemPrice");
	var totalPrice = document.getElementById("totalPrice");
	var deiliveryPrice = document.getElementById("deilivery");
	var delivery = 0; // 조건별 배송비 담을 변수
	var price = 0; //  합계금액(단가*수량)계산값 담을 변수
	var total = 0; // 상품들 가격합계 계산 값 담을 변수
	
	for(var i=0;i<cnt_hidden.length;i++){
		console.log("price : "+parseInt(price_hidden[i].value));
		console.log("cnt : "+parseInt(cnt_hidden[i].value));
		price = parseInt(price_hidden[i].value) * parseInt(cnt_hidden[i].value);
		total = total + parseInt(price);
	}
	console.log("total : "+total);
	if(total>=50000){
		delivery = 0;
	}else{
		delivery = 3000;
	}
	console.log("delivery : "+delivery);
	saving_point.innerHTML = (total * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
	itemPrice.innerHTML = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	deiliveryPrice.innerHTML = delivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	totalPrice.innerHTML = (total+delivery).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
}
</script>
</html>