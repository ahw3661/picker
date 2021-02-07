<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Login.css" type="text/css">
</head>
<body>
	<section>
		<div class="wrap">
			<div class="login_wrap">
				<form action="login" method="post" name="login" id="logfrm">
					<div class="input_div">
						<p>아이디</p>
						<input type="text" name="m_id" id="Id">
						<span id="idchk"></span>
					</div>
					<div class="input_div">
						<p>비밀번호</p>
						<input type="password" name="m_password" id="Pw">
						<span id="pwchk"></span>
					</div>
					<div class="chk_div"><input type="checkbox" name="loginCookie" id="logchk"> 로그인 상태 유지</div>
					<div class="login_btn"><input type="submit" value="로그인" id="login_btn"></div>
				</form>
				<div class="link_div">
					<div class="link_left"><a href="joinAgree">회원가입</a></div>
					<div class="link_right"><a href="findIdPw">아이디·비밀번호 찾기</a></div>
				</div>
				<div class="line_div">
					<div class="border"></div> <span class="also">또는</span> <div class="border"></div>
				</div>
				<div class="none_member_btn"><input type="button" value="비회원 주문 조회" id="none_member_search_btn"></div>
				<div class="none_member_search">
					<p>비회원 주문 조회</p>
					<div class="search_input">
						<input type="text" name="b_code" id="buy_code" placeholder="주문번호">
						<span id="codeChk"></span>
						<input type="text" name="b_order_phone" id="phone" placeholder="휴대폰번호">
						<span id="phoneChk"></span>
					</div>
					<div class="none_search_btn"><input type="button" value="조회" id="none_search"></div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	var url = ${not empty url} ? "${url}" : null;
	
	$(function() {
		$("#Id").keyup(function() {
			$("#idchk").empty();
		});
		
		$("#Pw").keyup(function() {
			$("#pwchk").empty();
		});
		
		$("#login_btn").click(function() {
			event.preventDefault(); // 기존 submit 막기
			var log = $("input[id='logchk']").is(":checked"); // 로그인 사용 유지 체크 여부 true/false
			
			if($("#Id").val() == "") {
				$("#idchk").text("아이디를 입력하세요.");
				$("#Id").focus();
				$('#idchk').css("color", "red");
				return false;
			}else if($("#Pw").val() == "") {
				$("#pwchk").text("비밀번호를 입력하세요.");
				$("#Pw").focus();
				$('#pwchk').css("color", "red");
				return false;
			}else {
				$.ajax({
					url:"login",
					type:"POST",
					data: {
						"m_id" : $("#Id").val(),
						"m_password" : $("#Pw").val(),
						"log" : log
					},
					datatype:"json",
					success:function(data){
						if(data.msg == "fail") {
							$("#pwchk").text("아이디 또는 비밀번호를 확인하세요.");
							$('#pwchk').css("color", "red");
						}else {
							//location.href = (url != null ? url : document.referrer);
							location.href = (url != null ? url : "${referrer}");
							console.log("referrer : "+"${referrer}");
							//location.href = (url != null ? url : "section");
							/* if(document.referrer != null) {
								history.back();
							}else {
								location.href = url;
							} */
						}
					},
					error:function(request, status, error){
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			 }
		});
	});
	
	$(function() {
		$("#none_member_search_btn").click(function() {
			if($(".none_member_search").css("display") == "none") {
				$(".none_member_search").show();
			}else {
				$(".none_member_search").hide();
				$("#buy_code").val("");
				$("#codeChk").empty();
				$("#phone").val("");
				$("#phoneChk").empty();
			}			
		});
		
		$("#buy_code").keyup(function() {
			$("#codeChk").empty();
		});
		
		$("#phone").keyup(function() {
			$("#phoneChk").empty();
		});
		
		$("#none_search").click(function() {
			if($("#buy_code").val() == "") {
				$("#codeChk").text("주문번호를 입력하세요.");
				$("#buy_code").focus();
				$("#codeChk").css("color", "red");
				return false;
			}else if($("#phone").val() == "") {
				$("#phoneChk").text("휴대전화번호를 입력하세요.");
				$("#phone").focus();
				$("#phoneChk").css("color", "red");
				return false;
			}else {
				$.ajax({
					url:"noneSearch",
					type:"POST",
					data: {
						"b_code" : $("#buy_code").val(),
						"b_order_phone" : $("#phone").val()
					},
					datatype:"json",
					success:function(data){
						if(data.msg == "fail") {
							$("#phoneChk").text("주문번호 또는 휴대전화번호를 확인하세요.");
							$('#phoneChk').css("color", "red");
						}else {
							location.href = "nonePage";
						}
					},
					error:function(request, status, error){
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}
		});
	});
</script>
</html>