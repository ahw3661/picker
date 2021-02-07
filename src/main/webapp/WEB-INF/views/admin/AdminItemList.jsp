<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminItemList_wrap">
		 	<h3>상품목록</h3>
		 	<div class="AdminItemList_div">
			 	<table>
				 	<tr>
				 	 	<th>상품 코드</th>
				 	 	<th>상품 명</th>
				 	 	<th>상품 카테고리</th>
				 	</tr>
				 	<c:forEach var="itemlist" items="${itemList }">
						<tr>
							<td>${itemlist.i_code }</td>
							<td><a href="javascript:goAdminItemDetail('${itemlist.i_code }', ${pgdto.pageNum });">${itemlist.i_name }</a></td>
							<td>${itemlist.i_category }</td>
						</tr>
				 	</c:forEach>
			 	</table>
		 	</div>
		 	<!-- 페이징 -->
		 	<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">																											  
	 				<div class="prev_div"><a href="javascript:goItemList(${pgdto.startPage - pgdto.pageSize});"><b>《</b></a></div>
	 			</c:if>						 
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">																							  
						<div class="page_div"><a href="javascript:goItemList(${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:goItemList(${page})">${page}</a></div>
					</c:if>
				</c:forEach>						
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:goItemList(${pgdto.startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	function goAdminItemDetail(cd, pn) {
		$.ajax({
			url : "goAdminItemDetail",
			type : "post",
			data : { "i_code" : cd, "pageNum" : pn },
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
	
	function goItemList(pn) {
		$.ajax({
			url : "goItemList",
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