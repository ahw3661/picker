<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside>
	<div id="qnaPop">
		<div class="popTop stretchBlock">
			<input type="hidden" name="q_num" value="${qna.q_num}">
			<div class="popPortrait centerBlock"><img src="resources/image/icon/user_round.png" alt="글쓴이"></div>
			<div class="popInfo">
				<p class="popTitle">${qna.q_title}</p>
				<p>
					<span>
						<c:if test="${qna.m_id != null}">${qna.m_id}</c:if>
						<c:if test="${qna.m_id == null}">${qna.m_name}</c:if>
					</span>
					<span>${qna.q_date}</span>
					<c:if test="${qna.m_id == u_id}">
						<a href="qnaModify?num=${qna.q_num}">수정</a>
						<a href="javascript:qnaDelete()">삭제</a>
					</c:if>
				</p>
			</div>
			<div class="popClose centerBlock"><a href="javascript:closePop()"><img src="resources/image/icon/closeBtn.svg" alt="닫기"></a></div>
		</div>
		<div class="popContent">${qna.q_content}</div>
		<p class="popReCnt"><img src="resources/image/icon/bubble.svg" alt="댓글"><span>${qna.re_cnt}</span></p>
		<div class="popReplyCon">
			<c:forEach var="reply" items="${replylist}">
				<div class="replyOuter" style="padding-left: ${(reply.r_dep - 1) * 30 + 8}px">
					<div class="replyIcon"><img src="resources/image/icon/reply.png" alt="댓글표시" class="replyIcon"></div>
					<div class="replyInner">
						<p>
							<label>${reply.m_id}</label>
							<span>${reply.r_date}</span>
						</p>
						<c:if test="${reply.r_chk == 0}">
							<div class="replyContent">${reply.r_content}</div>
							<p class="qnaOpt">
								<input type="hidden" name="r_num" value="${reply.r_num}">
								<input type="button" value="답글" class="btnBlackSmall replyPopBtn">
								<c:if test="${reply.m_id == u_id || reply.m_name == '더피커' && u_type == 0}">
									<input type="button" value="수정" class="btnWhiteSmall replyModifyBtn">
									<input type="button" value="삭제" class="btnWhiteSmall" onclick="replyDelete(${reply.r_num})">
								</c:if>
							</p>
						</c:if>
						<c:if test="${reply.r_chk == 1}">
							<p class="replyContent">삭제된 댓글입니다.</p>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
		<div>
			<form class="popWriteReply">
				<textarea rows="5" cols="100" maxlength="500" placeholder="댓글" name="r_content"></textarea>
				<div class="rightBtnDiv">
					<input type="button" value="등록" class="btnBlack replySub">
				</div>
			</form>
		</div>
	</div>
</aside>