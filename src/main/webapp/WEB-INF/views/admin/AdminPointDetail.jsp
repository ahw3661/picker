<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="point_detail_wrap">
			<h3>포인트</h3>
			<p><span>${m_id }님 포인트</span></p>
			<div class="point_detail_table_wrap">
				<table>
					<tr>
						<th class="pointdetail_th">날짜</th>
						<th class="pointdetail_th">사유/내용</th>
						<th class="pointdetail_th">포인트 내역</th>
					</tr>
					<c:forEach var="list" items="${list }">
						<tr>
							<td class="pointdetail_td">
								<fmt:parseDate var="pdate" value="${list.p_date }" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate var="p_date" value="${pdate }" pattern="yyyy-MM-dd" />${p_date }
							</td>
							<td class="pointdetail_td">${list.p_history }</td>
							<td class="pointdetail_td"><span><fmt:formatNumber var="p_point" value="${list.p_point }" pattern="#,###"/>${p_point } 포인트</span></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 3}">
	 				<div class="prev_div"><a href="javascript:pointDetail('${m_id }', ${startPage - pgdto.pageSize} });"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:pointDetail('${m_id }', ${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:pointDetail('${m_id }', ${page})">${page}</a></div>
					</c:if>		
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:pointDetail('${m_id }', ${startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
			<div class="list_btn"><input type="button" value="목록" onclick="javascript:allPointList(1);"></div>
		</div>
	</section>
</body>
<script type="text/javascript">
	//포인트 목록
	function allPointList(pn) {
		$.ajax({
			url : "allPointList",
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
	
	// 페이징
	function pointDetail(id, pn) {
		$.ajax({
			url : "pointDetail",
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
</script>
</html>