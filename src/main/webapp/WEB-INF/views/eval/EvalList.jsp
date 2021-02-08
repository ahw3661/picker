<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
<body>
<h3>상품 리뷰</h3>
<c:if test="${totCnt == 0 }">
	<p class="nonlist centerBlock">구매평이 없습니다</p>
</c:if>
<c:if test="${totCnt != 0 }">
	<div id="info_Main">
		<c:forEach var="edtoarr" items="${edtoarr}">
			<div id="value_Div">
				<div class="evalTitle"></div>
				<span>
				<c:forEach begin="1" end="${ edtoarr.e_level }">
					<img src="resources/image/icon/star.svg" class="starIcon">
				</c:forEach>
				</span>
				<span></span>
				<input type="hidden" value="${ edtoarr.e_num }">
				<p>${ edtoarr.e_content }</p>
				<p>
					<fmt:parseDate var="e_date" value="${ edtoarr.e_date }" pattern="yyyy-mm-dd HH:mm"/>
					<fmt:formatDate var="eb_date" value="${e_date }" pattern="yyyy-mm-dd HH:mm"/>${eb_date }
				</p>
				<c:if test="${ edtoarr.m_id ne null }">
					<p>${ edtoarr.m_id }</p>
				</c:if>
				<c:if test="${ edtoarr.m_id eq null }">
					<p>${edtoarr.m_name}</p>
				</c:if>
		</div>
		</c:forEach>	
	</div>
		<c:if test="${pgdto.rowSize < totCnt}">
		<div class="centerBlock">
 			<c:if test="${pgdto.startPage > 1}">
				<a class="pageArrow centerInline" href="javascript:getQnaList(${pgdto.startPage - pgdto.pageSize})">
					<img alt="이전" src="resources/image/icon/arrow_left.svg">
				</a>
 			</c:if>
			<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
				<c:if test="${page != pgdto.pageNum}">
					<a class="pageBtn pageNumBtn centerInline" href="javascript:getQnaList(${page})">${page}</a>
				</c:if>
				<c:if test="${page == pgdto.pageNum}">
					<a class="pageBtnStd pageNumBtn centerInline" href="javascript:getQnaList(${page})">${page}</a>
				</c:if>
			</c:forEach>
 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
				<a class="pageArrow centerInline" href="javascript:getQnaList(${pgdto.endPage + 1})">
					<img alt="다음" src="resources/image/icon/arrow_right.svg">
				</a>
 			</c:if>
		</div>
	</c:if>
</c:if>
<input id="writeBtn" type="button" value="리뷰 작성" onclick="goInsert()">
<script type="text/javascript">
	function goInsert() {
		$.ajax({
			url : "goEvalWrite?i_code=" + $("input[name=i_code]").val(),
			type : "get",
			datatype : "html",
			success : function(data) {
				$("body").append(data);
			},
			error:function(request,error){
 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 			}
		})
	}
</script>
</body>
</html>