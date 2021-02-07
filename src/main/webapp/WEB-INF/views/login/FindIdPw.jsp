<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/FindIdPw.css" type="text/css">
</head>
<body>
	<section>
		<div class="wrap">
			<div class="find_idpw_wrap">
				<!-- <form action="findId" method="post"> -->
					<div class="find_all">
						<div class="find_id">
							<div class="title_div">아이디 찾기</div>
							<div class="id_input_div">
								<p>아이디를 찾을 방법을 선택해주세요.</p>
								<div class="id_div">
									<input type="radio" name="find" value="1" class="radio_input" id="email_find" checked> 가입한 이메일로 찾기
									<div id="email_input"><input type="text" name="m_email" id="find_email" placeholder="이메일"></div>
									<div class="line"></div>
									<input type="radio" name="find" value="2" class="radio_input" id="phone_find"> 가입한 휴대폰으로 찾기
									<div id="phone_input">
										<input type="text" name="m_name" id="find_name" placeholder="이름">
										<input type="text" name="m_phone" id="find_phone" placeholder="휴대폰 번호">
									</div>
								</div>
							</div>
							<div id="id_btn"><input type="button" value="아이디 찾기" id="find_id_btn"></div>
						</div>
						<div class="find_pw">
							<div class="title_div">비밀번호 찾기</div>
							<div class="pw_input_div">
								<p></p>
								<div class="pw_div">
									<div id="id_input"><input type="text" name="m_id" placeholder="가입한 아이디" id="find_id"></div>
								</div>
							</div>
							<div id="pw_btn"><input type="button" value="비밀번호 찾기" id="find_pw_btn"></div>
						</div>
					</div>
				<!-- </form> -->
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	$(function() {
		$(".radio_input").change(function() {
			if($("input:radio[name=find]:checked").val() == "1") {
				$("#email_input").show();
				$("#phone_input").hide();
				$("#find_email").val("");
			}else {
				$("#email_input").hide();
				$("#phone_input").show();
				$("#find_name").val("");
				$("#find_phone").val("");
			}
		});
	});
	
	$(function() {
		$("#find_id_btn").click(function() { // 아이디 찾기
			if($("input:radio[name=find]:checked").val() == "1") {
				if($("#find_email").val() == "") {
					alert("이메일을 입력하세요.");
				}else {
					$.ajax({
						url:"findId", // RequestMapping 값 입력
						type:"GET", // 전송방식 GET, POST
						data: {
							"m_email" : $("#find_email").val()
						}, // controller에게 전달하는 파라미터 값
						datatype:"html",
						success:function(data){
							$(".find_all").children().remove(); // 자식 노드 삭제
							$(".find_all").html(data);
						},
						error:function(){
							alert("ajax 실패");
						}
					});
				}
			}else {
				if($("#find_name").val() == "") {
					alert("이름을 입력하세요.");
				}else if($("#find_phone").val() == "") {
					alert("연락처를 입력하세요.");
				}else {
					$.ajax({
						url:"findId", // RequestMapping 값 입력
						type:"POST", // 전송방식 GET, POST
						data: {
							"m_name" : $("#find_name").val(),
							"m_phone" : $("#find_phone").val()
						}, // controller에게 전달하는 파라미터 값
						datatype:"html",
						success:function(data){
							$(".find_all").children().remove(); // 자식 노드 삭제
							$(".find_all").html(data);
						},
						error:function(){
							alert("ajax 실패");
						}
					});
				}	
			}
		});
		
		$("#find_pw_btn").click(function() {
			if($("#find_id").val() == "") {
				alert("아이디를 입력하세요.");
			}else {
				$.ajax({
					url:"findPw", // RequestMapping 값 입력
					type:"GET", // 전송방식 GET, POST
					data: {
						"m_id" : $("#find_id").val()
					}, // controller에게 전달하는 파라미터 값
					datatype:"html",
					success:function(data){
						$(".find_all").children().remove(); // 자식 노드 삭제
						$(".find_all").html(data);
					},
					error:function(){
						alert("ajax 실패");
					}
				});
			}
		});
	});
</script>
</html>