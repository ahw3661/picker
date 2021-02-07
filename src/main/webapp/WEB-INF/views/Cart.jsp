<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Cart.css">
</head>
<body>
<section>
	<div class="wrap">
		<h2>장바구니</h2>
		<form id="fm" method="post">
		<div id="list_div">
			<table>
				<tr>
					<th><input type="checkbox" name="chkall" id="chkall" value="${i_code }"></th>
					<th colspan="2">item</th>
					<th>수량</th>
					<th>상품가격</th>
					<th>예정포인트</th>
					<th>가격</th>
				</tr>
				<c:choose>
					<c:when test="${cnt==0 }">
						<tr>
							<td colspan="7">장바구니가 비었습니다.</td>
						</tr>
						<tr id="continue_bu_tr">
							<td colspan="7"><input type="submit" value="계속 쇼핑하기" name="continue_bu" id="continue_bu" formaction="section"></td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:set var="idx" value="0"/>
						<c:forEach var="cartlist" items="${cartlist }">
							<tr>
								<td><input type="checkbox" name="i_code" id="chk" class="chk" value="${cartlist.i_code }">
								<input type="hidden" class="c_num" name="c_num" value="${cartlist.c_num }">
								</td>
								<td class="img_td"><img src="resources/image/category_img/${cartlist.i_img}" class="imgs"></td>
								<td>${cartlist.i_name }</td>
								<td>
									<input type="button" class="pm_Btn" value="-" onclick="plma(-1,${idx})">
									<input type="text" name="c_cnt" value="${cartlist.c_cnt }" id="c_cnt" class="c_cnt" readonly="readonly">
									<input type="button" class="pm_Btn" value="+" onclick="plma(+1,${idx})">
									<c:set var="idx" value="${idx+1 }"/>
								</td>
								<td>
									<input type="hidden" id="i_price" class="i_price" value="${cartlist.i_price}">
									<fmt:formatNumber var="pricefmt" value="${cartlist.i_price}" pattern="#,##0원"/>
									<input type="text" id="price" name="price" value="${pricefmt}">
								</td>
								<td>
									<input type="text" class="point" id="point" name="point">
								</td>
								<td>
									<input type="text" id="sum_price" class="sum_price" name="sum_price">
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7" class="price_bottom">
								<label>상품가격 </label>:<span id="totalfmt"></span><br>
								<label>배송비</label>:<span id="deliveryPrice"></span><br>
								<label>예정 포인트</label>:<span id="pointfmt"></span>
							</td>
						</tr>
						<tr id="total_price_tr">
							<td colspan="2"><input type="button" name="del_one" class="del_one" value="선택삭제"></td>
							<td id="del_all_td"><input type="button" name="del_all" class="del_all" value="전체삭제"></td>
							<td colspan="4" class="total_price">
								<b><label>결제금액</label></b> :<span id="sum_totalfmt"></span>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<input type="hidden" name="m_id" id="m_id" value="${u_id }">
								<input type="hidden" name="sessionCnt" id="sessionCnt" value="${cnt}">
								<input type="submit" value="계속 쇼핑하기" name="continue_bu" id="continue_bu" formaction="section">
								<input type="button" value="주문하기" name="order_bu" id="order_bu">
							</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</table>		
		</div>
		</form>
	</div>
</section>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(function(){
	calculate();
	$("#chkall").click(function(){
		var chkall=$(this).is(":checked"); // 전체 체크박스를 체크하면
		if(chkall == true){
			$(".chk").prop('checked', true); // 체크박스 모두 체크
		}else{
			$(".chk").prop('checked', false); // 체크박스 모두 해제
		}
		calculate();
	});

	$(".chk").click(function() { // 체크박스 하나 체크
    	var chek = $(this).is(":checked");
    	if(chek == false) { 
        	$("#chkall").prop('checked', false); // 일부만 체크되면 전체 체크 해제
        }else{
        	if($(".chk:checked").length == $(".chk").length) { // 체크된 것과 체크박스 전체 길이가 같으면 전체 체크
	    		$("#chkall").prop('checked', true);
	    	}
        }
    	calculate();
    });	
});

function plma(n,idx){
	var c_cnt = document.getElementsByClassName("c_cnt")[idx];
	console.log(c_cnt.value);
	if(parseInt(n)==-1 && parseInt(c_cnt.value)==1){
		alert("최소 수량은 1입니다.");
	}else if(parseInt(n)==+1 && parseInt(c_cnt.value)>=99){
		alert("최대 수량은 99입니다.");
	}else{
 		$.ajax({
			url:'updateCartCnt', // RequestMapping 값 입력
			type:'POST', // 전송방식 GET, POST
			data: {"m_id" : $("#m_id").val(), "i_code" : $(".chk").eq(idx).val(), "c_cnt" : parseInt(c_cnt.value) +  parseInt(n), "c_num" : $(".c_num").eq(idx).val()}, // controller에게 전달하는 파라미터 값
			datatype:'json',
			success:function(data){
				c_cnt.value = parseInt(data.cnt);
				calculate();
			},
			error:function(request,status,error){
	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         }
		});	
	}
}

function calculate(){ //계산만하는 함수
	var c_cnt = document.getElementsByClassName("c_cnt"); // 상품수량
	var i_price = document.getElementsByClassName("i_price"); //상품단가
	var point = document.getElementsByClassName("point"); // 포인트
	var sum_price = document.getElementsByClassName("sum_price"); // 합계금액(단가*수량)
	var totalfmt = document.querySelector("#totalfmt"); // 상품들 가격 합계
	var deliveryPrice = document.querySelector("#deliveryPrice"); //배송비
	var pointfmt = document.querySelector("#pointfmt"); // 상품들 포인트 합계
	var sum_totalfmt = document.querySelector("#sum_totalfmt"); // 전체 합계(배송비 + 상품가격)
	var delivery = 0; // 조건별 배송비 담을 변수
	var price = 0; //  합계금액(단가*수량)계산값 담을 변수
	var total = 0; // 상품들 가격합계 계산 값 담을 변수
	var chk = document.getElementsByClassName("chk"); // 체크박스
	
	for(var i=0;i<c_cnt.length;i++){
		price = parseInt(i_price[i].value) * parseInt(c_cnt[i].value);
		sum_price[i].value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		point[i].value =  (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
		console.log(chk[i].checked);
		if(chk[i].checked == true){
			total = total + parseInt(price);
		}
	}
	if(total==0){
		deliveryPrice.innerHTML = "-";
		totalfmt.innerHTML = "-";
		pointfmt.innerHTML = "-";
		sum_totalfmt.innerHTML = "0원";
	}else{
		if(total>=50000){
			delivery = 0;
		}else{
			delivery = 3000;
		}
		console.log(delivery);
		deliveryPrice.innerHTML = delivery.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		totalfmt.innerHTML = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		pointfmt.innerHTML = (total * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
		sum_totalfmt.innerHTML = (total + delivery).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
	}
}

/* 	var price = 0;
	function plus(n){
		var cnt = document.querySelector(".cnt"+n);
		var i_price = document.querySelector(".i_price"+n);
		var point = document.querySelector(".point"+n);
		var sum_price = document.querySelector(".sum_price"+n);
		var code = document.querySelector(".chk"+n);
		var m_id = document.querySelector("#m_id");
		cnt.value = parseInt(cnt.value) + 1;
		price = i_price.value * cnt.value;
		sum_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
		point.value = (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
		location.href="updateCartCnt?m_id="+m_id.value+"&i_code="+code.value+"&c_cnt="+cnt.value;
	}
	
	function minus(n){
		var cnt = document.querySelector(".cnt"+n);
		var i_price = document.querySelector(".i_price"+n);
		var point = document.querySelector(".point"+n);
		var sum_price = document.querySelector(".sum_price"+n);
		var code = document.querySelector(".chk"+n);
		var m_id = document.querySelector("#m_id");
		if(cnt.value > 1){
			cnt.value = parseInt(cnt.value) - 1;
			price = i_price.value * cnt.value;
			sum_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
			point.value = (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
			location.href="updateCartCnt?m_id="+m_id.value+"&i_code="+code.value+"&c_cnt="+cnt.value;
		}
	} */
	/* function cntChange(num, plmaNum){
		var cnt = document.querySelector(".cnt"+num);
		var i_price = document.querySelector(".i_price"+num);
		var point = document.querySelector(".point"+num);
		var sum_price = document.querySelector(".sum_price"+num);
		var code = document.querySelector("#chk"+num);
		var total_price = document.querySelector("#total_price");
		var totalfmt = document.querySelector("#totalfmt");
		var pointfmt = document.querySelector("#pointfmt");
		var m_id = document.querySelector("#m_id");
		var price = 0;
		var total = 0;
		if(parseInt(plmaNum)==-1 && parseInt($(".cnt"+num).val())==1){
			alert("최소 수량은 1입니다.");
		}else if(parseInt(plmaNum)==+1 && parseInt($(".cnt"+num).val())>=99){
			alert("최대 수량은 99입니다.");
		}else{
			$.ajax({
				url:'updateCartCnt', // RequestMapping 값 입력
				type:'POST', // 전송방식 GET, POST
				data: {"m_id" : $("#m_id").val(), "i_code" : $("#chk"+num).val(), "c_cnt" : parseInt($(".cnt"+num).val()) +  parseInt(plmaNum) }, // controller에게 전달하는 파라미터 값
				datatype:'json',
				success:function(data){
						cnt.value = parseInt(data.cnt);
						price = parseInt(i_price.value) * cnt.value;
						sum_price.value = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
						point.value = (price * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"P";
						if(parseInt(plmaNum)==+1){
							total_price.value = parseInt(total_price.value) + parseInt(i_price.value);
						}else{
							total_price.value = parseInt(total_price.value) - parseInt(i_price.value);
						}
						totalfmt.innerHTML = total_price.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
						pointfmt.innerHTML = (total_price.value * 0.02).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"포인트";
						sum_totalfmt.innerHTML = total_price.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";				
				},
				error:function(){
					alert("ajax실패");
				}
			});
		}
	} */

 	$(function(){
		$(".del_one").click(function(){
	 		var arr_code = new Array();
	 		var arr_num = new Array();
	 		$("input[class='chk']:checked").each(function(){
	 			arr_code.push($(this).val());
	 			arr_num.push($(this).next().val());
	 		})
			if($("input[class='chk']").is(":checked") == false){
				alert("선택된 상품이 없습니다.");
				return false;
			}else{
				$.ajax({
					url:'delOneCartList', // RequestMapping 값 입력
					type:'POST', // 전송방식 GET, POST
					data: {"m_id" : $("#m_id").val(), "i_code" : arr_code, "c_num" : arr_num }, // controller에게 전달하는 파라미터 값
					datatype:'html',
					success:function(data){
						$('section').html(data);
						$("#fs").html("("+ ($("#sessionCnt").val() != undefined ? $("#sessionCnt").val() : 0) +")");
					},
					error:function(request,status,error){
			            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			         }
				});
			}
		});
	}); 

 	$(function(){
		$(".del_all").click(function(){
			if(confirm("장바구니를 모두 삭제할까요?")){
				$.ajax({
					url:'delAllCartList', // RequestMapping 값 입력
					type:'POST', // 전송방식 GET, POST
					data: {"m_id" : $("#m_id").val()}, // controller에게 전달하는 파라미터 값
					datatype:'html',
					success:function(data){
						$('section').html(data);
						$("#fs").html("("+ ($("#sessionCnt").val() != undefined ? $("#sessionCnt").val() : 0) +")");
					},
					error:function(request,status,error){
			            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			         }
				});
			}
		});
	}); 
 	
 	$(function(){
		$("#order_bu").click(function(){
	 		if($("input[class='chk']").is(":checked") == false){
				alert("선택된 상품이 없습니다.");
				return false;
			}else{
				$("#fm").attr("action","gobuyPage_FromCart").submit();
			}
		});
	}); 
 	
</script>
</html>