<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty authenFail}">
	<script type="text/javascript">window.alert("입력하신 구매정보가 확인되지 않습니다.");</script>
</c:if>
<p>구매평은 상품을 구매한 경우에만 작성할 수 있습니다.</p>
<c:if test="${empty u_id}">
	<div class="divAuthenCon">
		<div class="divAuthen">
			<input type="button" value="로그인 페이지로 이동" class="btnLogin" onclick="location.href='loginPage'">
		</div>
		<div class="divAuthen">
			<label>비회원 리뷰 작성</label>
			<input type="text" name="b_code" class="iptAuthen" placeholder="주문번호">
			<input type="text" name="phone" class="iptAuthen" placeholder="휴대폰번호">
			<input type="button" value="비회원 리뷰 작성" class="btnAuthen" onclick="noneMemberEval()">
		</div>
	</div>
	<script type="text/javascript">
		var reg = /^\d+$/;
		$(".iptAuthen").each(function(){
			$(this).on("input", function(){
				var txt = $(this).val();
				if(!reg.test(txt) && reg != ""){
					txt = txt.slice(0, txt.length - 1);
					$(this).val(txt);
				}	
			})
		})
	</script>
</c:if>