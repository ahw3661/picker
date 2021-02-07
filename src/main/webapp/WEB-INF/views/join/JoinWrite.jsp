<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Join.css" type="text/css">
</head>
<body>
	<section>
		<div class="wrap">
			<div class="join_write_wrap">
				<!-- <form action="joinSave" method="post" name="jw" onsubmit="return jwSubmit();"> -->
				<form action="joinSave" method="post" name="jw" id="jwfrm">
					<div class="input_div">
						<p>아이디<span>⁎</span></p>
						<input type="text" name="m_id" placeholder="영문자, 숫자 조합 5~10자리" id="Id">
						<span id="idchk"></span>
					</div>
					<div class="input_div">
						<p>비밀번호<span>⁎</span></p>
						<input type="password" name="m_password" placeholder="영문자, 숫자, 특수기호(!@#*-) 조합 6~12자리" id="Pw">
						<span id="pwchk"></span>
					</div>
					<div class="input_div">
						<p>비밀번호 확인<span>⁎</span></p>
						<input type="password" name="m_repassword" placeholder="비밀번호 확인" id="Repw">
						<span id="repwchk"></span>
					</div>
					<div class="input_div">
						<p>이메일<span>⁎</span></p>
						<input type="email" name="m_email" placeholder="이메일" id="Email">
						<span id="emailchk"></span>
					</div>
					<div class="input_div">
						<p>이름<span>⁎</span></p>
						<input type="text" name="m_name" placeholder="이름" id="Name">
						<span id="namechk"></span>
					</div>
					<div class="input_div">
						<p>연락처<span>⁎</span></p>
						<input type="text" name="m_phone" placeholder="연락처" id="Phone">
						<span id="phonechk"></span>
					</div>
					<div class="zipcode_div">
						<p>주소<span>⁎</span></p>
						<input type="text" name="m_zipcode" id="zipCode" readonly="readonly">
						<input type="button" value="우편번호 찾기" class="zipbtn" onclick="postcode();">
						<br>
						<input type="text" name="m_roadaddr" id="roadAddress" readonly="readonly">
						<input type="text" name="m_detailaddr" id="detailAddress">
						<span id="addresschk"></span>
					</div>
					<input type="hidden" name="m_terms" value="${mdto.m_terms }">
					<input type="hidden" name="m_personal" value="${mdto.m_personal }">
					<div class="join_btn"><input type="submit" value="가입하기" id="joinbtn"></div>
				</form>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	function postcode() {
    	new daum.Postcode({	
            oncomplete: function(data) {	
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("zipCode").value = data.zonecode;	
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
	 
	$(function() {		
		$("#Id").keyup(function() { // 아이디 체크
			var regid = /^[A-za-z0-9]{5,10}/g; // 영문자, 숫자 조합 5~10자리
			
			$.ajax({
				url:"idChecking", // RequestMapping 값 입력
				type:"GET", // 전송방식 GET, POST
				data: {"m_id" : $("#Id").val()}, // controller에게 전달하는 파라미터 값
				datatype:"json",
				success:function(data){
					if(!regid.test($("#Id").val())){
						$("#idchk").text("아이디는 영문자, 숫자 조합 5~10자리만 가능합니다.");
						$("#idchk").css("color", "red");
					}else {
						if(data == 0) {
							$("#idchk").text("사용 가능한 아이디입니다.");
							$("#idchk").css("color", "black");
						}else {
							$("#idchk").text("이미 사용중인 아이디입니다.");
							$("#idchk").css("color", "red");
						}
					}
					
					if($("#Id").val() == "") {
						$("#idchk").empty();
					}
				},
				error:function(){
					alert("ajax 실패");
				}
			});
		});
		
		$("#Pw").keyup(function() { // 비밀번호 체크
			var regpw = /^(?=.*[a-zA-Z])(?=.*[!@#*-])(?=.*[0-9]).{6,12}$/; // 영문자, 숫자, 특수기호(!@#*-) 조합 6~12자리
			
			if(!regpw.test($("#Pw").val())){
				$("#pwchk").text("비밀번호는 숫자, 특수기호(!@#*-), 영문자 조합 6~12자리만 가능합니다.");
				$("#pwchk").css("color", "red");
            }else {
            	$("#pwchk").text("사용 가능한 비밀번호입니다.");
            	$("#pwchk").css("color", "black");
            }
			
			if($("#Pw").val() == "") {
				$("#pwchk").empty();
			}
		});
		
		$("#Repw").keyup(function() { // 비밀번호 확인 체크
			if($("#Pw").val() != $("#Repw").val()) {
				$("#repwchk").text("비밀번호가 일치하지 않습니다.");
				$("#repwchk").css("color", "red");
			}else {
				$("#repwchk").text("비밀번호가 일치합니다.");
				$("#repwchk").css("color", "black");
			}
		
			if($("#Repw").val() == "") {
				$("#repwchk").empty();
			}
		});
		
		$("#Email").blur(function() { // 이메일 작성 후 메시지 비우기
			if($("#Email").val() != "") {
				$("#emailchk").empty();
			}
		});
		
		$("#Name").blur(function() { // 이름 작성 후 메시지 비우기
			if($("#Name").val() != "") {
				$("#namechk").empty();
			}
		});
		
		$("#Phone").blur(function() { // 전화번호 작성 후 메시지 비우기
			if($("#Phone").val() != "") {
				$("#phonechk").empty();
			}
		});
		
		$("#detailAddress").blur(function() { // 주소 작성 후 메시지 비우기
			if($("#zipCode").val() != "" && $("#roadAddress").val() != "" && $("#detailAddress").val() != "") {
				$("#addresschk").empty();
			}
		});
		
		$("#joinbtn").click(function(event) {
			event.preventDefault(); // 기존 submit 막기
			var frm = $("#jwfrm").serialize(); // form 정보
			
			if($("#Id").val() == "") {
				$("#idchk").text("아이디를 입력하세요.");
				$("#idchk").css("color", "red");
				return false;
			}else if($("#Pw").val() == "") {
				$("#pwchk").text("비밀번호를 입력하세요.");
				$("#pwchk").css("color", "red");
				return false;
			}else if($("#Repw").val() == "") {
				$("#repwchk").text("비밀번호 확인을 입력하세요.");
				$("#repwchk").css("color", "red");
				return false;
			}else if($("#Email").val() == "") { // 이메일 체크
				$("#emailchk").text("이메일을 입력하세요.");
				$("#emailchk").css("color", "red");
				return false;
			}else if($("#Name").val() == "") { // 이름 체크
				$("#namechk").text("이름을 입력하세요.");
				$("#namechk").css("color", "red");
				return false;
			}else if($("#Phone").val() == "") { // 연락처 체크
				$("#phonechk").text("전화번호를 입력하세요.");
				$("#phonechk").css("color", "red");
				return false;
			}else if($("#zipCode").val() == "" || $("#roadAddress").val() == "" || $("#detailAddress").val() == "") { // 주소 체크
				$("#addresschk").text("주소를 입력하세요.");
				$("#addresschk").css("color", "red");
				return false;
			}else {
				$.ajax({
					url:"joinSave",
					type:"POST",
					data: frm,
					datatype:"json",
					success:function(data){
						alert("회원가입이 완료되었습니다.");
						location.href="section";
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