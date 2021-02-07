<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>구매하시려는 상품에 대해 궁금한 점이 있으면 문의주세요.</p>
<c:if test="${qnacnt != 0}">
	<table>
		<thead>
			<tr>
				<td>상태</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qna" items="${qnalist}">
				<tr>
					<td>
						<c:if test="${qna.q_rchk == 0}"><span>답변대기</span></c:if>
						<c:if test="${qna.q_rchk == 1}"><span>답변완료</span></c:if>
					</td>
					<td>
						<a href="javascript:getQnaPop(${qna.q_num})">${qna.q_title}
						<c:if test="${qna.re_cnt > 0}"><span class="replyCount">${qna.re_cnt}</span></c:if>
						</a>
					</td>
					<td>
						<c:if test="${qna.m_id != null}">${qna.m_id}</c:if>
						<c:if test="${qna.m_id == null}">${qna.m_name}</c:if>
					</td>
					<td>${qna.q_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${paging.rowSize < qnacnt}">
		<div class="centerBlock">
 			<c:if test="${paging.startPage > 1}">
				<a class="pageArrow centerInline" href="javascript:getQnaList(${paging.startPage - paging.pageSize})">
					<img alt="이전" src="resources/image/icon/arrow_left.svg">
				</a>
 			</c:if>
			<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
				<c:if test="${page != paging.pageNum}">
					<a class="pageBtn pageNumBtn centerInline" href="javascript:getQnaList(${page})">${page}</a>
				</c:if>
				<c:if test="${page == paging.pageNum}">
					<a class="pageBtnStd pageNumBtn centerInline" href="javascript:getQnaList(${page})">${page}</a>
				</c:if>
			</c:forEach>
 			<c:if test="${paging.endPage < paging.pageCount}">
				<a class="pageArrow centerInline" href="javascript:getQnaList(${paging.endPage + 1})">
					<img alt="다음" src="resources/image/icon/arrow_right.svg">
				</a>
 			</c:if>
		</div>
	</c:if>
</c:if>
<c:if test="${qnacnt == 0}">
	<div class="nonlist centerBlock">등록된 문의가 없습니다</div>
</c:if>
<div class="rightBtnDiv">
	<input type="button" value="상품문의" class="btnBlack" onclick="location.href='qnaWrite?code=${code}'">
</div>