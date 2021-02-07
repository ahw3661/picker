<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminUserList_wrap">
			<h3>회원 정보</h3>
			<div class="AdminUserList_table_wrap">
				<table>
					<tr id="info_title">
						<td>아이디</td>
						<td>전화번호</td>
						<td>이메일</td>
						<td>회원유형</td>
						<td>가입일자</td>
					</tr>
					<c:forEach var="mdto" items="${mdto }">
					<tr id="list_Info">
						<td>
							<a href="javascript:goOneList('${mdto.m_id }');">${mdto.m_id }</a>
						</td>
						<td>${mdto.m_phone }</td>
						<td>${mdto.m_email }</td>
						<td>
							<c:if test="${mdto.m_type == 1 }">
								일반회원
							</c:if>
							<c:if test="${mdto.m_type == 2 }">
								탈퇴회원
							</c:if>
						</td>
						<td><fmt:parseDate var="m_date" value="${mdto.m_date }" pattern="yyyy-mm-dd"/>
						<fmt:formatDate var="mb_date" value="${m_date }" pattern="yyyy-mm-dd"/>
						${mb_date }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">
	 				<div class="prev_div"><a href="javascript:goMemberList(${pgdto.startPage - pgdto.pageSize});"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:goMemberList(${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:goMemberList(${page})">${page}</a></div>
					</c:if>
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:goMemberList(${pgdto.startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	function goOneList(id) {
		$.ajax({
			url : "goOneList",
			type : "post",
			data : { "m_id" : id },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data) {
				alert("ajax 실패");
			}
		});
	}
	
	// 페이징
	function goMemberList(pn) {
		$.ajax({
			url : "goMemberList",
			type : "post",
			data : { "pageNum" : pn },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data) {
				alert("ajax 실패");
			}
		});
	}
</script>
</html>