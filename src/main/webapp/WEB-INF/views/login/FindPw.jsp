<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="find_wrap">
			<div class="find_info">
				<div class="title_div">비밀번호 찾기</div>
					<c:if test="${not empty m_password }">
						<p>입력하신 정보와 일치하는 비밀번호를 발견했습니다.</p>
						<div class="info">
							<c:set var="password" value="${m_password}" />
							<c:set var="totalLength" value="${fn:length(password) }" />
							<c:set var="first" value="${fn:substring(password, 0, 3) }" />
							<c:set var="last" value="${fn:substring(password, 4, totalLength) }" />
							${first}<c:forEach begin="1" end="${totalLength-1 }">*</c:forEach>
							<%-- ${m_password } --%>
						</div>
						<div class="login_btn"><input type="button" value="로그인" onclick="location.href='loginPage'"></div>
					</c:if>
					<c:if test="${empty m_password }">
						<p class="msg_p">${msg }</p>
						<div class="login_btn"><input type="button" value="비밀번호 찾기" onclick="location.href='findIdPw'"></div>
					</c:if>
			</div>
		</div>
	</section> 
</body>
</html>