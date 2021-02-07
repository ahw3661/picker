<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="qnaInfoWrap">
	<h3>1:1 문의 게시판<span id="qnaCnt">${qnacnt}</span></h3>
	<c:if test="${qnacnt != 0}">
		<ul>
		<c:forEach var="qna" items="${qnalist}">
			<li>
				<p class="qnaTitle">
					<label>${qna.q_title}</label>
					<span>${qna.q_date}</span>
				</p>
				<c:if test="${qna.i_code != null}">
					<p class="qnaItem"><a href="goDetail?i_code=${qna.i_code}">
						<img src="resources/image/category_img/${qna.i_img}" alt="제품 이미지">
						<span>${qna.i_name}</span>
					</a></p>
				</c:if>
				<div class="qnaContent">${qna.q_content}</div>
				<p class="qnaOpt">
					<a href="javascript:replyPop(${qna.q_num}, null)">답글</a> /
					<a href="qnaModify?num=${qna.q_num}">수정</a> /
					<a href="javascript:qnaDelete(${qna.q_num})">지우기</a>
				</p>
				<c:forEach var="reply" items="${replylist}">
					<c:if test="${reply.q_num == qna.q_num}">
						<div class="replyOuter" style="padding-left: ${(reply.r_dep - 1) * 30 + 8}px">
							<div class="replyIcon"><img src="resources/image/icon/mypage_reply.png" alt="댓글표시" class="replyIcon"></div>
							<div class="replyInner">
								<p>
									<label>${reply.m_id}</label>
									<span>${reply.r_date}</span>
								</p>
								<c:if test="${reply.r_chk == 0}">
									<p class="replyContent">${reply.r_content}</p>
									<p class="qnaOpt">
										<a href="javascript:replyPop(${qna.q_num}, ${reply.r_num})">답글</a> /
										<a href="javascript:replyModPop(${reply.r_num})">수정</a> / 
										<a href="javascript:replyDelete(${reply.r_num})">지우기</a>
									</p>
								</c:if>
								<c:if test="${reply.r_chk == 1}">
									<p class="replyContent">삭제된 댓글입니다.</p>
								</c:if>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</li>
		</c:forEach>
		</ul>
		<c:if test="${qnacnt > row}">
			<div class="qnaMore centerBlock"><a href="javascript:qnaInfo(${row + 5})" class="centerInline">
				<img src="resources/image/icon/arrow_down.png" alt="더 보기"><span>&nbsp;더 보기</span>
			</a></div>
		</c:if>
	</c:if>
	<c:if test="${qnacnt == 0}">
		<ul><li><p class="nonlist">등록된 문의가 없습니다</p></li></ul>
	</c:if>
	<div class="rightBtnDiv"><input type="button" onclick="location.href='qnaWrite'" value="문의 작성"></div>
</div>
