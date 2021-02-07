<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<tr>
						<th>아이디</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>회원유형</th>
					</tr>
					<c:forEach var="mdto" items="${mdto }">
						<tr>
							<td>
								<c:if test="${mdto.m_type == 1 }">
									<a href="javascript:goOneList('${mdto.m_id }', ${pgdto.pageNum });"><span>${mdto.m_id }</span></a>
								</c:if>
								<c:if test="${mdto.m_type == 2 }"><span>${mdto.m_id }</span></c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">${mdto.m_phone }</c:if>
								<c:if test="${mdto.m_type == 2 }">
									<c:set var="phone" value="${mdto.m_phone}" />
									<c:set var="totalLength" value="${fn:length(phone) }" />
									<c:forEach begin="1" end="${totalLength-1 }">*</c:forEach>
								</c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">${mdto.m_email }</c:if>
								<c:if test="${mdto.m_type == 2 }">
									<c:set var="email" value="${mdto.m_email}" />
									<c:set var="totalLength" value="${fn:length(email) }" />
									<c:forEach begin="1" end="${totalLength-1 }">*</c:forEach>
								</c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">일반회원</c:if>
								<c:if test="${mdto.m_type == 2 }">탈퇴회원</c:if>
							</td>
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
	// 회원정보 상세
	function goOneList(id, pn) {
		$.ajax({
			url : "goOneList",
			type : "post",
			data : { "m_id" : id, "pageNum" : pn },
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