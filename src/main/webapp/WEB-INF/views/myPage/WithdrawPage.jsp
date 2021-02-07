<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="withdraw_wrap">
			<h3>회원탈퇴</h3>
			<!-- <form action="withdraw" method="post" name="wi" onsubmit="return wiSubmit();"> -->
			<form action="withdraw" method="post" name="wi" id="wifrm">
				<div>
					<p>가입된 회원정보가 모두 삭제됩니다. 작성하신 게시물은 삭제되지 않습니다.</p>
					<p>탈퇴 후 같은 계정으로 재가입 시에 기존에 가지고 있던 적립금은 복원되지 않습니다.</p>
					<p>탈퇴 후 같은 계정으로 재가입 시에 기존에 사용 및 다운로드 했던 쿠폰은 사용이 불가능합니다.</p>
					<p>비밀번호를 입력하면 회원탈퇴가 완료됩니다.</p>
					<p>회원 탈퇴를 진행하시겠습니까?</p>
					<div class="withdraw_input">
						<input type="password" name="m_password" id="Pw">
						<span id="pw_chk"></span>
					</div>
					<div class="withdraw_btn">
						<input type="button" value="취소" onclick="location.href='myPage'">
						<input type="submit" value="탈퇴하기" id="sbt_btn">
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
<script type="text/javascript">
	$(function() {
		$("#Pw").keyup(function() {
			if($("#Pw").val() == "") {
				$('#pw_chk').empty();
			}
		});
		
		$("#sbt_btn").click(function(event) {
			event.preventDefault(); // 기존 submit 막기
			
			if($("#Pw").val() == "") {
				$("#pw_chk").text("비밀번호를 입력하세요.");
				$('#pw_chk').css("color", "red");
				return false;
			}else {
				$.ajax({
					url:"withdraw",
					type:"POST",
					data: { "m_password" : $("#Pw").val() },
					datatype:"json",
					success:function(data){
						if(data.msg == "fail") {
							$("#pw_chk").text("비밀번호가 일치하지 않습니다.");
							$('#pw_chk').css("color", "red");
						}else {
							alert("탈퇴되었습니다.");
							location.href="section";
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