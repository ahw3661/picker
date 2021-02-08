<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
<body>
	<div class="qnaAdminList">
		<h3>문의 목록</h3>
		<div id="qnaAdminCount">문의글 : <span>${qnacnt}</span>건</div>
		<div class="stretchBlock">
			<div>
				<select id="selRchk" name="rchk">
					<option value="-1">- 답변여부 -</option>
					<option value="0">답변대기</option>
					<option value="1">답변완료</option>
				</select>
				<select id="selItem" name="code">
					<option value="">- 관련 상품 -</option>
					<c:forEach var="item" items="${itemList}">
						<option value="${item.i_code}">${item.i_name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="stretchBlock">
				<select id="selCol" name="column">
					<option value="title">제목</option>
					<option value="id">아이디</option>
				</select>
				<div id="srhBox" class="stretchBlock">
					<input type="text" id="srhInput" name="keyword">
					<a href="javascript:qnaSearch()" id="srhIcon"><img src="resources/image/icon/search.png" alt="검색"></a>
				</div>
			</div>
		</div>
		<c:if test="${qnacnt > 0}">
			<div id="qnaTable">
				<table>
					<thead>
						<tr>
							<th class="celRchk">답변여부</th>
							<th class="celItem">관련상품</th>
							<th class="celTitle">제목</th>
							<th class="celId">아이디</th>
							<th class="celDate">문의 일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="qna" items="${qnalist}">
							<tr>
								<td class="celRchk">
									<c:if test="${qna.q_rchk == 0}"><b>답변대기</b></c:if>
									<c:if test="${qna.q_rchk == 1}">답변완료</c:if>
								</td>
								<td class="celItem">
									<div class="centerBlock">
										<c:if test="${qna.i_img != null}"><a href="" class="centerInline"><img 
										title="${qna.i_name}" src="resources/image/category_img/${qna.i_img}" alt="관련 상품"></a></c:if>
										<c:if test="${qna.i_img == null}"><img src="resources/image/icon/user_round.png" alt="관련 상품"></c:if>
									</div>
								</td>
								<td class="celTitle">
									<a href="javascript:getQnaPop(${qna.q_num})">${qna.q_title}<c:if test="${qna.re_cnt > 0}"><span>${qna.re_cnt}</span></c:if></a>
								</td>
								<td class="celId">
									<c:if test="${qna.m_id != null}">${qna.m_id}</c:if>
									<c:if test="${qna.m_id == null}">${qna.m_name}</c:if>
								</td>
								<td class="celDate">${qna.q_date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${paging.rowSize < qnacnt}">
				<div class="centerBlock">
		 			<c:if test="${paging.startPage > 1}">
						<a class="pageArrow centerInline" href="javascript:goQnaList(${paging.startPage - paging.pageSize})">
							<img alt="이전" src="resources/image/icon/arrow_left.svg">
						</a>
		 			</c:if>
					<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
						<c:if test="${page != paging.pageNum}">
							<a class="pageBtn pageNumBtn centerInline" href="javascript:goQnaList(${page})">${page}</a>
						</c:if>
						<c:if test="${page == paging.pageNum}">
							<a class="pageBtnStd pageNumBtn centerInline" href="javascript:goQnaList(${page})">${page}</a>
						</c:if>
					</c:forEach>
		 			<c:if test="${paging.endPage < paging.pageCount}">
						<a class="pageArrow centerInline" href="javascript:goQnaList(${paging.endPage + 1})">
							<img alt="다음" src="resources/image/icon/arrow_right.svg">
						</a>
		 			</c:if>
				</div>
			</c:if>
		</c:if>
		<c:if test="${qnacnt == 0}">
			<div class="centerBlock nonlist">해당하는 문의 내역이 없습니다.</div>
		</c:if>
	</div>
	<c:if test="${not empty rchk}"><script type="text/javascript">$("select[name=rchk]").val("${rchk}");</script></c:if>
	<c:if test="${not empty code}"><script type="text/javascript">$("select[name=code]").val("${code}");</script></c:if>
	<c:if test="${not empty column && not empty keyword}">
		<script type="text/javascript">
			$(".qnaAdminList select[name=column]").val("${column}");
			$(".qnaAdminList input[name=keyword]").val("${keyword}");
			$(".qnaAdminList").prepend("<input type='hidden' name='colHid' value='${column}'><input type='hidden' name='keyHid' value='${keyword}'>");
		</script>
	</c:if>
	<script type="text/javascript">
 		$("input[name=keyword]").keypress(function(e){
			if(e.keyCode == 13){
				e.preventDefault();
				qnaSearch();
			}
		});
		$("select[name=rchk]").change(function(){
			qnaChangeOpt(1);
		});
		$("select[name=code]").change(function(){
			qnaChangeOpt(1);
		});
	</script>
	<script type="text/javascript" src="resources/js/QnaPop.js"></script>
</body>
</html>