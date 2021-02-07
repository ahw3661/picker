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
		<div class="point_list_wrap">
			<h3>포인트 내역</h3>
			<div class="point_list_table_wrap">
				<table>
					<tr>
						<th class="pointlist_th">회원아이디</th>
						<th class="pointlist_th">총 포인트</th>
						<th class="pointlist_th">상세내역</th>
					</tr>
					<c:forEach var="pdto" items="${pdto }">
						<tr>
							<td class="pointlist_td">${pdto.m_id }</td>
							<td class="pointlist_td"><fmt:formatNumber var="p_point" value="${pdto.p_point }" pattern="#,###"/>${p_point } 포인트</td>
							<td class="pointlist_td"><input type="button" name="m_id" value="상세보기" onclick="javascript:pointDetail('${pdto.m_id }');"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">
	 				<div class="prev_div"><a href="javascript:allPointList(${pgdto.startPage - pgdto.pageSize});"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:allPointList(${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:allPointList(${page})">${page}</a></div>
					</c:if>		
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:allPointList(${pgdto.startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	// 포인트내역 상세
	function pointDetail(id) {
		$.ajax({
			url : "pointDetail",
			type : "post",
			data : { "m_id" : id, "pageNum" : 1 },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(request, status, error) {
				//alert("ajax 실패");
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	// 페이징
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
</script>
</html>