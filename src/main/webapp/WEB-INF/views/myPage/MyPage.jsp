<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/MyPage.css" type="text/css">
</head>
<body>
	<section>
		<div class="wrap">
			<div class="my_page_wrap">
				<div class="menu_list_left">
					<input type="hidden" name="m_id" value="${u_id }" id="mid">
					<ul>
						<li><a href="javascript:buyInfo(1);" class="my_a">주문조회</a></li>
						<li><a href="javascript:buyCancel();" class="my_a">주문취소</a></li>
						<li><a href="javascript:pointInfo(1);" class="my_a">포인트</a></li>
						<li><a href="javascript:qnaInfo(5);" class="my_a">1:1문의</a></li>
						<li><a href="javascript:myInfo();" class="my_a">정보수정</a></li>
						<li><a href="javascript:withdrawPage();" class="my_a">회원탈퇴</a></li>
					</ul>
				</div>
				<div class="menu_list_right">
					<div class="right_top">
						<div class="user"><img alt="user_image" src="resources/image/icon/user_round.png"><p>${u_name}님 안녕하세요.</p></div>
						<div class="part_line"></div>
						<div class="user_point">
							<p>포인트</p>
							<div>
								<c:if test="${point < 0 }">0</c:if>
								<c:if test="${point >= 0 }">
									<fmt:formatNumber var="point" value="${point }" pattern="#,###"/>${point }
								</c:if>
							</div>
						</div>
					</div>
					<div class="right_bottom">
						<div class="menu_info"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	if(${not empty msg}) {
		alert("${msg}");
		history.back();
	}	
</script>
<script type="text/javascript" src="resources/js/MyPage.js"></script>
</html>