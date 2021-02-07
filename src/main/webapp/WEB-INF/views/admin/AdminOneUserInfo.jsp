<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<link rel="stylesheet" href="resources/css/AdminOneUserInfo.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminOneUserList_wrap">
			<h3>${mdto.m_id } 님 회원 정보</h3>
			<table>
				<tr class="OneList_info">
					<th>아이디</th>
					<td>${mdto.m_id }</td>
				</tr>
				<tr class="OneList_info">
					<th>이메일</th>
					<td>${mdto.m_email }</td>
				</tr>
				<tr class="OneList_info">
					<th>이름</th>
					<td>${mdto.m_name }</td>
				</tr>
				<tr class="OneList_info">
					<th>휴대폰번호</th>
					<td>${mdto.m_phone }</td>
				</tr>
				<tr class="OneList_info">
					<th>주소</th>
					<td>(${mdto.m_zipcode })${mdto.m_roadaddr } ${mdto.m_detailaddr }</td>
				</tr>
				<tr class="OneList_info">
					<th>가입일자</th>
					<td>
						<fmt:parseDate var="m_date" value="${mdto.m_date }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="mb_date" value="${m_date }" pattern="yyyy-MM-dd"/>
						${mb_date }
					</td>
				</tr>
				<tr class="OneList_info">
					<th>회원유형</th>
					<td>
						<c:if test="${mdto.m_type == 1 }">
							일반회원
						</c:if>
						<c:if test="${mdto.m_type == 2 }">
							탈퇴회원
						</c:if>
					</td>
				</tr>
				<tr class="OneList_info">
					<th>포인트</th>
					<td><fmt:formatNumber var="m_point" value="${mdto.m_point }" pattern="#,###"/>${m_point }</td>
				</tr>
				<tr class="OneList_info">
					<th>회원가입동의여부</th>
					<td>
						<c:if test="${mdto.m_terms eq 'Y' }">
							동의
						</c:if>
					</td>
				</tr>
				<tr class="OneList_info">
					<th>개인정보동의여부</th>
					<td>
						<c:if test="${mdto.m_personal eq 'Y' }">
							동의
						</c:if>
					</td>
				</tr>
			</table>
			<br>
			<div id="List_btn">
			 <input type="button" value="목록보기" onclick="javascript:goMemberList(1);">
			</div>
		</div>
	</section>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function goMemberList(pn) {
		$.ajax({
			url : "goMemberList",
			type : "post",
			data : { "pageNum" : pn },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove(); // 자식 노드 삭제
				$(".menu_info").html(data);
			},
			error : function(data) {
				alert("ajax 실패");
			}
		});
	}
</script>
</html>