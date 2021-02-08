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
									<input type="hidden" name="i_img" value="${payList.i_img}">
									<input type="hidden" name="c_num" value="${payList.c_num }">
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
							<input type="text" name="b_order_name" id="mname" value="${mdto.m_name }" class="text_inputs" readonly="readonly"><br>
							<input type="text" name="b_order_phone" id="mphone" value="${mdto.m_phone }" class="text_inputs" readonly="readonly"><br>
							<input type="text" name="b_order_email" id="memail" value="${mdto.m_email }" class="text_inputs" readonly="readonly">
							</div>
						<div id="order_delivery_info" class="order_info">
						<h4>배송 정보</h4>
							<input type="checkbox" id="delivery_chk"> <span class="chk_span">주문자 정보와 동일</span><br>
							<input type="text" name="b_take_name" id="m_name" class="text_inputs" placeholder="수령인" ><input type="text" name="b_take_phone" id="m_phone" class="text_inputs" placeholder="연락처"><br>
							<input type="text" name="b_take_email" id="m_email" class="text_inputs" placeholder="이메일"><br>
							<input type="text" name="b_take_zipcode" id="m_zipcode" class="text_inputs" placeholder="우편번호"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
							<input type="text" name="b_take_roadaddr" id="m_roadaddr" class="text_inputs" placeholder="주소"><br>
							<input type="text" name="b_take_detailaddr" id="m_detailaddr" class="text_inputs" placeholder="상세주소">
						</div>
						<div id="order_point_info" class="order_info">
							<h4>포인트</h4>
							<input type="text" name="point" id="point"><input type="button" id="point_use_btn" value="사용"><input type="button" id="point_btn" value="전액사용"><input type="button" id="cancel_btn" value="사용취소" style="display:none">
							<p id="point_P">보유 포인트<input type="text" name="m_point" id="m_point" value="${mdto.m_point }"></p>
							<input type="hidden" name="point_use" id="point_use" value="${mdto.m_point }">
							<p id="point_Pinfo">※ 1,000 포인트 이상 보유 및 10,000원 이상 구매시 사용가능</p>
						</div>
					</c:if>
					<c:if test="${u_id==null }">
						<div id="order_user_info" class="order_info">
						<h4>주문자 정보</h4>
							<input type="text" name="b_order_name" id="mnameN" class="text_inputs" placeholder="이름을 입력해주세요"> <input type="text" name="b_order_phone" id="mphoneN" class="text_inputs" placeholder="연락처를 입력해주세요"><br>
							<input type="text" name="b_order_email" id="memailN" class="text_inputs" placeholder="E-mail을 입력해주세요">
						</div>
						<div id="order_delivery_info" class="order_info">
						<h4>배송 정보</h4>
							<input type="text" name="b_take_name" id="m_name" class="text_inputs" placeholder="수령인"><input type="text" name="b_take_phone" id="m_phone" class="text_inputs" placeholder="연락처"><br>
							<input type="text" name="b_take_email" id="m_email" class="text_inputs" placeholder="이메일"><br>
							<input type="text" name="b_take_zipcode" id="m_zipcode" class="text_inputs" placeholder="우편번호" readonly="readonly"> <input type="button" id="find_btn" value="주소찾기" onclick="postcode()"> <br>
							<input type="text" name="b_take_roadaddr" id="m_roadaddr" class="text_inputs" placeholder="주소" readonly="readonly"><br>
							<input type="text" name="b_take_detailaddr" id="m_detailaddr" class="text_inputs" placeholder="상세주소">	
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
					<input type="hidden" name="saving_point" class="saving_point" id="saving_point">
				</c:if>
				<c:if test="${u_id!=null }">
					<p><input type="text" name="saving_point" class="saving_point" id="saving_point">포인트 적립예정</p>
					<input type=hidden name="saving_P" id="saving_P">
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
	parseInt($("#point").val("0"));
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
		if(parseInt($("#point_use").val()) < 1000){
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
			$("#usePoint_hidden").val($("#point").val());
			if($("#point_use").val()>total){
				$("#point").val(total);
				$("#usePoint").val(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P");
			}else{
				$("#point").val($("#point_use").val());
				$("#usePoint").val($("#point").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P");
			}
			$("#usePoint_hidden").val($("#point").val());
			var tot_price = total-$("#point").val()+delivery;
			$("#tot").val(tot_price);
			$("#totalPrice").val(tot_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
			$("saving_P").val((total-$("#point").val())*0.02);
			$("#saving_point").val((total-$("#point").val())*0.02.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		}
		$("#point_btn").hide();
		$("#point_use_btn").hide();
		$("#cancel_btn").show();
	});
	
	$("#point_use_btn").click(function(){
		if(parseInt($("#point").val()) > parseInt($("#point_use").val())){ // 포인트 인풋값 > 보유포인트
			alert("보유포인트가 부족합니다.다시 입력해주세요");
		}else if(parseInt($("#point").val()) > 0 && parseInt($("#point").val()) < 1000){ // 0 < 포인트 인풋값 < 1000
			alert("1000P 부터 사용가능합니다");
 		}else if($("#point").val()==""){ // 포인트 인풋값이 0 이거나 비어있을경우
			alert("사용포인트를 입력해주세요");
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
			$("#usePoint_hidden").val($("#point").val());
			$("#usePoint").val($("#point").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P");
			var tot_price = total-$("#usePoint_hidden").val()+delivery;
			$("#tot").val(tot_price);
			$("#totalPrice").val(tot_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
			$("saving_P").val((total-$("#point").val())*0.02);
			$("#saving_point").val((total-$("#point").val())*0.02.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		}
		$("#point_btn").hide();
		$("#point_use_btn").hide();
		$("#cancel_btn").show();
	});

	$("#cancel_btn").click(function(){
		$("#point_btn").show();
		$("#point_use_btn").show();
		$("#cancel_btn").hide();
		$("#point").val(parseInt("0"));
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
		$("#usePoint_hidden").val($("#point").val());
		$("#usePoint").val($("#point").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P");
		var tot_price = total-$("#usePoint_hidden").val()+delivery;
		$("#tot").val(tot_price);
		$("#totalPrice").val(tot_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
		$("saving_P").val((total-$("#point").val())*0.02);
		$("#saving_point").val((total-$("#point").val())*0.02.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		
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
	var pointCal_hidden = document.getElementById("pointCal_hidden");
	var saving_P = document.getElementById("saving_P");
	var m_id = document.getElementById("m_id");
	
	for(var i=0;i<cnt_hidden.length;i++){
		price = parseInt(price_hidden[i].value) * parseInt(cnt_hidden[i].value); //상품가격
		total = total + parseInt(price); // 상품가격 합계
	}
	
	if(total>=50000){
		delivery = 0; //배송비
	}else{
		delivery = 3000;
	}
	
	if(m_id.value!=null){
		usePoint_hidden.value = parseInt(usePoint_hidden.value);
	}else{
		usePoint_hidden.value = 0;
	}
	
	itemPrice.value = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원"; //상품가격 
	deiliveryPrice.value = delivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원"; // 배송비
	hidden_delivery.value = delivery; // 히든 배송비(계산 하기위한 숫자값)
	usePoint.value = parseInt(usePoint_hidden.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P"; // 사용할 포인트
	tot.value = total-parseInt(usePoint_hidden.value)+ delivery; // 배송비 포함 전체 합계 금액 
	totalPrice.value = (tot.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원"; // 배송비 포함 전체 합계 금액  천단위 구분 표시
	
	if(m_id.value==null){
		saving_P.value = 0; // 적립포인트
		saving_point.value = (saving_P.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 적립포인트 천단위 구분 표시
	}else{
		if(saving_P != null){
			saving_P.value = (parseInt(total)-parseInt(use_point))*0.02; // 적립포인트
			saving_point.value = (saving_P.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 적립포인트 천단위 구분 표시
		}
	}
	
	
}

function null_input(){
	$(".text_inputs").each(function(){
		if($.trim($(this).val()) == "") {
			alert("주문자정보와 배송정보를 확인해주세요");
			return false; //each문 탈출
		}
	});
}

 $(function(){
	calculate();
	$("#pay_abtn").click(function(){
		var payform = $("#payform").serialize();
		var isRight = true;
		
        $("#payform").find(".text_inputs").each(function(){
            // 아무값없이 띄어쓰기만 있을 때도 빈 값으로 체크되도록 trim() 함수 호출
            if ($(this).val().trim() == '') {
                /* alert("주문자정보와 배송정보를 확인해주세요"); */
                isRight = false;
                return false; //each문 탈출
            }
        });
		
		if($("#kakaopay_btn").is(":checked")==false && $("#agree").is(":checked")==false){
			if(!isRight){
				alert("동의여부와 결제방법 또는 주문자정보와 배송정보를 확인해주세요");
			}else{
				alert("결제방법과 동의 여부를 체크 후 이용해주세요");
			}
			
		}else if($("#kakaopay_btn").is(":checked")==false || $("#agree").is(":checked")==false){
			if(!isRight){
				alert("동의여부와 결제방법 또는 주문자정보와 배송정보를 확인해주세요");
			}else{
				alert("결제방법과 동의 여부를 체크 후 이용해주세요");
			}
		}else if(!isRight) {
			alert("주문자정보와 배송정보를 확인해주세요");
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