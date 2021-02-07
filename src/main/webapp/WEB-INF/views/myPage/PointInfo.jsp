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
		<div class="point_info_wrap">
			<h3>포인트</h3>
			<div class="point_info_table_wrap">
				<table>
					<tr>
						<th class="point_th">날짜</th>
						<th class="point_th">사유/내용</th>
						<th class="point_th">포인트 내역</th>
					</tr>
					<c:forEach var="list" items="${list }">
						<tr>
							<td class="point_td">
								<fmt:parseDate var="pdate" value="${list.p_date }" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="p_date" value="${pdate }" pattern="yyyy-MM-dd" />${p_date }
							</td>
							<td class="point_td">${list.p_history }</td>
							<td class="point_td"><span><fmt:formatNumber var="p_point" value="${list.p_point }" pattern="#,###"/>${p_point } 포인트</span></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">
	 				<div class="prev_div"><a href="javascript:pointInfo(${pgdto.startPage - pgdto.pageSize});"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:pointInfo(${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:pointInfo(${page})">${page}</a></div>
					</c:if>
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:pointInfo(${pgdto.startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	// 페이징
	function pointInfo(pn) {
		$.ajax({
			url : "pointInfo",
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