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
	<form method="post" id="payform">
		<div class="order_form">
			<div id="order_wrap">
				<div id="order_item_info" class="order_info">
					<h4>주문 상품 정보</h4>
					<table id="t1">		
						<c:forEach var="payList" items="${payList }">
							<tr class="trs" onclick="location.href='goDetail?i_code=${payList.i_code}'">
								<td><img src="resources/image/category_img/${payList.i_img}" id="imgs"></td>
								<td class="tds">
									<input type="text" name="item_name" class="i_name" value="${payList.i_name }"><br>
									<input type="hidden" name="quantity" class="cnt_hidden" value="${payList.c_cnt }">
									<input type="text" name="c_cnt" class="c_cnt" value="${payList.c_cnt }개"><br>
									<input type="hidden" name="price_hidden" class="price_hidden" value="${payList.i_price }">
									<fmt:formatNumber var = "i_pricefm" value = "${payList.i_price*payList.c_cnt }"/>
									<input type="text" class="i_price" value="${i_pricefm}원">
									<input type="hidden" name="item_code" value="${payList.i_code }">
								</td>						
							</tr>
						</c:forEach>
					</table>
				</div>	
					<c:if test="${u_id!=null }">
						<div id="order_user_info" class="order_info">
						<h4>주문자 정보</h4>
							<input type="text" name="b_order_name" id="mname" value="${mdto.m_name }" class="user_info" readonly="readonly"><br>
							<input type="text" name="b_order_phone" id="mphone" value="${mdto.m_phone }" class="user_info" readonly="readonly"><br>
							<input type="text" name="b_order_email" id="memail" value="${mdto.m_email }" class="user_info" readonly="readonly">
							</div>
						<div id="order_delivery_info" class="order_info">
						<h4>배송 정보</h4>
							<input type="checkbox" id="delivery_chk"> <span class="chk_span">주문자 정보와 동일</span><br>
							<input type="text" name="b_take_name" id="m_name" placeholder="수령인"> <input type="text" name="b_take_phone" id="m_phone" placeholder="연락처"><br>
							<input type="text" name="b_take_email" id="m_email" placeholder="이메일"><br>
							<input type="text" name="b_take_zipcode" id="m_zipcode" placeholder="우편번호"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
							<input type="text" name="b_take_roadaddr" id="m_roadaddr" placeholder="주소"><br>
							<input type="text" name="b_take_detailaddr" id="m_detailaddr" placeholder="상세주소">
						</div>
						<div id="order_point_info" class="order_info">
							<h4>포인트</h4>
							<input type="hidden" name="minus_point" id="point_use" value="${mdto.m_point }">
							<input type="text" name="point" id="point"><input type="button" id="point_use_btn" value="사용"><input type="button" id="point_btn" value="전액사용">
							<p id="point_P">보유 포인트<input type="text" name="m_point" id="m_point" value="${mdto.m_point }"></p>
							<p id="point_Pinfo">1,000 포인트 이상 보유 및 10,000원 이상 구매시 사용가능</p>
						</div>
					</c:if>
					<c:if test="${u_id==null }">
						<div id="order_user_info" class="order_info">
						<h4>주문자 정보</h4>
							<input type="text" name="b_order_name"  value="${mdto.m_name }" class="null_user" placeholder="이름을 입력해주세요"> <input type="text" name="b_order_phone" value="${mdto.m_phone }" class="null_user" placeholder="연락처를 입력해주세요"><br>
							<input type="text" name="b_order_email" value="${mdto.m_email }" class="null_user" placeholder="E-mail을 입력해주세요">
						</div>
						<div id="order_delivery_info" class="order_info">
						<h4>배송 정보</h4>
							<input type="text" name="b_take_name" id="m_name" placeholder="수령인"> <input type="text" name="b_take_phone" id="m_phone" placeholder="연락처"><br>
							<input type="text" name="b_take_email" id="m_email" placeholder="이메일"><br>
							<input type="text" name="b_take_zipcode" id="m_zipcode" placeholder="우편번호"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
							<input type="text" name="b_take_roadaddr" id="m_roadaddr" placeholder="주소"><br>
							<input type="text" name="b_take_detailaddr" id="m_detailaddr" placeholder="상세주소">	
						</div>
					</c:if>
					<input type="hidden" id="zipcode" value="${mdto.m_zipcode}">
					<input type="hidden" id="roadaddr" value="${mdto.m_roadaddr}">
					<input type="hidden" id="detailaddr" value="${mdto.m_detailaddr}">
			</div>
			<div id="fixed_wrap">
				<div id="pay_wrap" class="order_info">
					<h4>최종 결제금액</h4>
					<p class="pay_info"><span id="itemPrice_span">상품가격</span><input type="text" name="itemPrice" id="itemPrice"></p>
					<input type="hidden" name="usePoint_hidden" id="usePoint_hidden" value="0">
					<p class="pay_info"><span id="usePoint_span">사용포인트 </span><input type="text" name="usePoint" id="usePoint"></p>
					<p class="pay_info"><span id="deiliveryPrice_span">배송비 </span><input type="text" name="deilivery" id="deilivery"></p>
					<input type="hidden" name="b_price" id="hidden_delivery">
					<hr>
					<p id="totalP"><span id="totalPrice_span">총 결제금액</span><input name="totalPrice" id="totalPrice">
					<input type="hidden" name="tot" id="tot">
					</p>
				</div>
				<div class="order_info bg-gray">
				<c:if test="${u_id==null }">
					<p>포인트 적립은 회원만 가능합니다.</p>
				</c:if>
				<c:if test="${u_id!=null }">
					<p><input type="text" name="saving_point" class="saving_point" id="saving_point">포인트 적립예정</p>
				</c:if>
				</div>
				<div id="pay_option" class="order_info">
					<h4>결제방법</h4>
					<input type="radio" id="kakaopay_btn"> 카카오페이
				</div>
				<div id="agree_div" class="order_info">
					<input type="checkbox" id="agree"> 구매조건 확인 및 결제진행에 동의
				</div>
				<div id="pay_btn">
						<input type="button" id="pay_abtn" value="결제하기" formaction="kakaoPay">
						<input type="hidden" id="quantity" name="quantity">
						<input type="hidden" id="m_id" value="${u_id }">
				</div>
			</div>
		</div>
		</form>
	</div>
</section>
</body>
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
	$("#delivery_chk").click(function(){ // 주문자 동일 체크박스 선택시 이벤트
    	var chek = $(this).is(":checked");
    	if(chek == true){ 
    		$("#m_name").val($("#mname").val());
    		$("#m_phone").val($("#mphone").val());
    		$("#m_email").val($("#memail").val());
    		$("#m_zipcode").val($("#zipcode").val());
        	$("#m_roadaddr").val($("#roadaddr").val());
        	$("#m_detailaddr").val($("#detailaddr").val());
        }else{
        	$("#m_name").val("");
    		$("#m_phone").val("");
    		$("#m_email").val("");
    		$("#m_zipcode").val("");
        	$("#m_roadaddr").val("");
        	$("#m_detailaddr").val("");
        }
	});
	
	$("#point_btn").click(function(){
		var cnt_hidden = document.getElementsByClassName("cnt_hidden");
		var price_hidden = document.getElementsByClassName("price_hidden");
		var total=0;
		var price=0;
		for(var i=0;i<cnt_hidden.length;i++){
			console.log("price : "+parseInt(price_hidden[i].value));
			console.log("cnt : "+parseInt(cnt_hidden[i].value));
			price = parseInt(price_hidden[i].value) * parseInt(cnt_hidden[i].value);
			total = total + parseInt(price);
		}
		if(total>=50000){
			delivery = 0;
		}else{
			delivery = 3000;
		}
		$("#point").val($("#point_use").val());
		$("#usePoint").val($("#point").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
		var tot_price = total-$("#point_use").val()+delivery;
		$("#totalPrice").val(tot_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
		var saving = (total-$("#point_use").val())*0.02;
		$("#saving_point").val(saving.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	});
	
	$("#point_use_btn").click(function(){
		console.log("usep:"+$("#point_use").val());
		console.log("p:"+$("#point").val());
		if(parseInt($("#point").val()) > parseInt($("#point_use").val())){
			alert("보유포인트가 부족합니다.다시 입력해주세요");
		}else if(parseInt($("#point").val()) < 1000){
			alert("1000P 부터 사용가능합니다");
		}else{
			var cnt_hidden = document.getElementsByClassName("cnt_hidden");
			var price_hidden = document.getElementsByClassName("price_hidden");
			var total=0;
			var price=0;
				for(var i=0;i<cnt_hidden.length;i++){
					console.log("price : "+parseInt(price_hidden[i].value));
					console.log("cnt : "+parseInt(cnt_hidden[i].value));
					price = parseInt(price_hidden[i].value) * parseInt(cnt_hidden[i].value);
					total = total + parseInt(price);
				}
				if(total>=50000){
					delivery = 0;
				}else{
					delivery = 3000;
				}
			$("#usePoint").val($("#point").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
			var tot_price = total-$("#point").val()+delivery;
			$("#totalPrice").val(tot_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
			var saving = (total-$("#point").val())*0.02;
			$("#saving_point").val(saving.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		}
	});

});
	
function calculate(){ //계산만하는 함수
	var cnt_hidden = document.getElementsByClassName("cnt_hidden");
	var price_hidden = document.getElementsByClassName("price_hidden");
	var saving_point = document.getElementById("saving_point");
	var itemPrice = document.getElementById("itemPrice");
	var totalPrice = document.getElementById("totalPrice");
	var deiliveryPrice = document.getElementById("deilivery");
	var hidden_delivery = document.getElementById("hidden_delivery");
	var delivery = 0; // 조건별 배송비 담을 변수
	var price = 0; //  합계금액(단가*수량)계산값 담을 변수
	var total = 0; // 상품들 가격합계 계산 값 담을 변수
	var tot = document.getElementById("tot");
	var usePoint = document.getElementById("usePoint");
	var usePoint_hidden = document.getElementById("usePoint_hidden");
	var use_point = parseInt(usePoint_hidden.value);
	
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
	itemPrice.value = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	deiliveryPrice.value = delivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	hidden_delivery.value = delivery;
	usePoint.value = parseInt(usePoint_hidden.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	totalPrice.value = (total-parseInt(usePoint_hidden.value)+delivery).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	tot.value = total + delivery;
	saving_point.value = ((total-use_point)*0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

 $(function(){
	calculate();
	$("#pay_abtn").click(function(){
		var payform = $("#payform").serialize();
		if($("#kakaopay_btn").is(":checked")==false && $("#agree").is(":checked")==false){
			alert("결제방법과 동의 여부를 체크 후 이용해주세요");
		}else if($("#kakaopay_btn").is(":checked")==false){
			alert("결제방법을 체크 후 이용해주세요");
		}else if($("#agree").is(":checked")==false){
			alert("구매조건 확인 및 결제진행에 동의 후 이용해 주세요");
		}else{
			$.ajax({
				url:'kakaoPay', // RequestMapping 값 입력
				type:'POST', // 전송방식 GET, POST
				data: payform, // controller에게 전달하는 파라미터 값
				datatype:'json',
				success:function(data){
					window.open(data.pc_url,'결제창','left=400px,top=50px,width=483px,height=600px');
				},
				error:function(request,status,error){
		            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			});
		}
	});
 });

</script>
</html>