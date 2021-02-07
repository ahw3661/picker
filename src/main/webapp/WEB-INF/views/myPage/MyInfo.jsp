<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="my_info_wrap">
			 <h3>정보수정</h3>
			 <!-- <form action="myInfoUpdate" method="post" name="miu" onsubmit="return miuSubmit();" id="frm"> -->
			 <form action="myInfoUpdate" method="post" name="miu" id="miufrm">
			 	<div class="input_div">
					<p>아이디</p>
					<input type="text" name="m_id" value="${mdto.m_id }" id="Id" readonly="readonly">
					<span id="idchk"></span>
				</div>
				<div class="input_div">
					<p>비밀번호<span>⁎</span></p>
					<input type="password" name="m_password" placeholder="기본 비밀번호 입력" id="Pw">
					<span id="pwChk"></span>
				</div>
				<div class="input_div">
					<p>비밀번호 변경</p>
					<input type="password" name="m_newpassword" placeholder="비밀번호를 변경하는 경우 입력하세요" id="newPw">
					<span id="newPwChk"></span>
				</div>
				<div class="input_div">
					<p>비밀번호 확인</p>
					<input type="password" name="re_password" placeholder="비밀번호 변경 확인" id="rePw">
					<span id="rePwChk"></span>
				</div>
				<div class="input_div">
					<p>이메일<span>⁎</span></p>
					<input type="email" name="m_email" value="${mdto.m_email }" id="Email">
					<span id="emailchk"></span>
				</div>
				<div class="input_div">
					<p>이름<span>⁎</span></p>
					<input type="text" name="m_name" value="${mdto.m_name }" id="Name">
					<span id="namechk"></span>
				</div>
				<div class="input_div">
					<p>연락처<span>⁎</span></p>
					<input type="text" name="m_phone" value="${mdto.m_phone }" id="Phone">
					<span id="phonechk"></span>
				</div>
				<div class="zipcode_div">
					<p>주소<span>⁎</span></p>
					<input type="text" name="m_zipcode" value="${mdto.m_zipcode }" id="zipCode" readonly="readonly">
					<input type="button" value="우편번호 찾기" class="zipbtn" onclick="postcode();">
					<br>
					<input type="text" name="m_roadaddr" value="${mdto.m_roadaddr }" id="roadAddress" readonly="readonly">
					<input type="text" name="m_detailaddr" value="${mdto.m_detailaddr }" id="detailAddress">
					<span id="addresschk"></span>
				</div>
				<div class="update_btn">
					<input type="submit" value="수정" id="sbm_btn">
					<input type="button" value="취소" onclick="location.href='myPage'">
				</div>
			</form>
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
		$("#Pw").keyup(function() {
			if($("#Pw").val() == "") {
				$('#pwChk').empty();
			}
		});
		
		$("#newPw").keyup(function() {
			if($("#newPw").val() == "") {
				$('#newPwChk').empty();
			}
		});
		
		$("#rePw").keyup(function() {
			if($("#rePw").val() == "") {
				$('#rePwChk').empty();
			}
		});
		
		$("#newPw").keyup(function() { // 비밀번호 체크
			var regpw = /^(?=.*[a-zA-Z])(?=.*[!@#*-])(?=.*[0-9]).{6,12}$/; // 영문자, 숫자, 특수기호(!@#*-) 조합 6~12자리
			
			if(!regpw.test($("#newPw").val())){
				$('#newPwChk').text("비밀번호는 숫자, 특수기호(!@#*-), 영문자 조합 6~12자리만 가능합니다.");
				$('#newPwChk').css("color", "red");
            }else {
            	$('#newPwChk').text("사용 가능한 비밀번호입니다.");
            	$('#newPwChk').css("color", "black");
            }
			if($("#newPw").val() == "") {
				$('#newPwChk').empty();
			}
		});
		
		$("#rePw").keyup(function() { // 비밀번호 확인 체크
			if($("#newPw").val() != $("#rePw").val()) {
				$('#rePwChk').text("비밀번호가 일치하지 않습니다.");
				$('#rePwChk').css("color", "red");
			}else {
				$('#rePwChk').text("비밀번호가 일치합니다.");
				$('#rePwChk').css("color", "black");
			}
			if($("#rePw").val() == "") {
				$('#rePwChk').empty();
			}
		});
		
		$("#sbm_btn").click(function(event) {
			event.preventDefault(); // 기존 submit 막기
			var frm = $("#miufrm").serialize(); // form 정보
			
			if($("#Pw").val() == "") {
				$("#pwChk").text("기본 비밀번호를 입력하세요.");
				$('#pwChk').css("color", "red");
				return false;
			}else if($("#newPw").val() != "" && $("#rePw").val() == "") {
				$("#rePwChk").text("비밀번호 확인을 입력하세요.");
				$('#rePwChk').css("color", "red");
				return false;
			}else if($("#Email").val() == "") { // 이메일 체크
				$("#emailchk").text("이메일을 입력하세요.");
				$('#emailchk').css("color", "red");
				return false;
			}else if($("#Name").val() == "") { // 이름 체크
				$("#namechk").text("이름을 입력하세요.");
				$('#namechk').css("color", "red");
				return false;
			}else if($("#Phone").val() == "") { // 연락처 체크
				$("#phonechk").text("전화번호를 입력하세요.");
				$('#phonechk').css("color", "red");
				return false;
			}else if($("#zipCode").val() == "" || $("#roadAddress").val() == "" || $("#detailAddress").val() == "") { // 주소 체크
				$("#addresschk").text("주소를 입력하세요.");
				$('#addresschk').css("color", "red");
				return false;
			}else {
				$.ajax({
					url:"myInfoUpdate",
					type:"POST",
					data: frm,
					datatype:"json",
					success:function(data){
						if(data.msg == "fail") {
							$("#pwChk").text("비밀번호가 일치하지 않습니다.");
							$('#pwChk').css("color", "red");
						}else {
							alert("수정되었습니다.");
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