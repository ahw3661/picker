<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h3>상품 리뷰</h3>
<c:if test="${totCnt == 0 }">
	<p class="nonlist centerBlock">구매평이 없습니다</p>
</c:if>
<c:if test="${totCnt != 0 }">
	<div id="EvalListMain">
		<c:forEach var="edto" items="${edtoarr}">
			<div class="evalColumn">
				<div class="evalCtt">
					<p>
						<c:forEach begin="1" end="${edto.e_level}">
							<img src="resources/image/icon/star.svg" class="starIcon">
						</c:forEach>
					</p>
					<p>${edto.e_content}</p>
				</div>
				<div class="evalDesc">
					<p>
						<c:if test="${ edto.m_id ne null }">${edto.m_id}</c:if>
						<c:if test="${ edto.m_id eq null }">${edto.m_name}</c:if>
					</p>
					<p>${edto.e_date}</p>
				</div>
			</div>
		</c:forEach>	
	</div>
		<c:if test="${pgdto.rowSize < totCnt}">
		<div class="centerBlock">
 			<c:if test="${pgdto.startPage > 1}">
				<a class="pageArrow centerInline" href="javascript:getEvalList(${pgdto.startPage - pgdto.pageSize})">
					<img alt="이전" src="resources/image/icon/arrow_left.svg">
				</a>
 			</c:if>
			<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
				<c:if test="${page != pgdto.pageNum}">
					<a class="pageBtn pageNumBtn centerInline" href="javascript:getEvalList(${page})">${page}</a>
				</c:if>
				<c:if test="${page == pgdto.pageNum}">
					<a class="pageBtnStd pageNumBtn centerInline" href="javascript:getEvalList(${page})">${page}</a>
				</c:if>
			</c:forEach>
 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
				<a class="pageArrow centerInline" href="javascript:getEvalList(${pgdto.endPage + 1})">
					<img alt="다음" src="resources/image/icon/arrow_right.svg">
				</a>
 			</c:if>
		</div>
	</c:if>
</c:if>
<input id="writeBtn" type="button" value="리뷰 작성" onclick="evalPop()">
<script type="text/javascript">

</script>
