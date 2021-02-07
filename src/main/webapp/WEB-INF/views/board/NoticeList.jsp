<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="resources/css/Notice.css">
</head>
<body>
	<section class="wrap noticeList">
		<div class="stretchBlock">
			<div id="noticeCnt" class="centerBlock">더 피커 소식<span>${noticecnt}</span></div>
			<form class="stretchBlock">
				<input type="text" id="searchBox" name="keyword" placeholder="Search" value="${keyword}">
				<input type="image" src="resources/image/icon/search.png" alt="검색">
			</form>
		</div>
		<ul>
			<c:if test="${noticecnt > 0}">
				<c:set var="i" value="0"/>
				<c:forEach var="notice" items="${noticelist}">
					<li>
						<a href="noticeRead?no=${notice.n_num}">
							<span class="noticeImg">
								<img alt="공지이미지" src="resources/image/logo/logo_header.png">
							</span><br>
							<span class="noticeTitle"><font>공지사항</font>${notice.n_title}</span>
						</a>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${noticecnt == 0}">
				<li><p class="nonlist">해당하는 공지가 없습니다</p></li>
			</c:if>
		</ul>
		<c:if test="${paging.rowSize < noticecnt}">
			<div class="centerBlock">
	 			<c:if test="${paging.startPage > 1}">
					<a class="pageArrow centerInline" href="noticeList?num=${paging.startPage - paging.pageSize}&keyword=${keyword}">
						<img alt="이전" src="resources/image/icon/arrow_left.svg">
					</a>
	 			</c:if>
				<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
					<c:if test="${page != paging.pageNum}">
						<a class="pageBtn pageNumBtn centerInline" href="noticeList?num=${page}&keyword=${keyword}">${page}</a>
					</c:if>
					<c:if test="${page == paging.pageNum}">
						<a class="pageBtnStd pageNumBtn centerInline" href="noticeList?num=${page}&keyword=${keyword}">${page}</a>
					</c:if>		
				</c:forEach>
	 			<c:if test="${paging.endPage < paging.pageCount}">
					<a class="pageArrow centerInline" href="noticeList?num=${paging.endPage + 1}&keyword=${keyword}">
						<img alt="다음" src="resources/image/icon/arrow_right.svg">
					</a>
	 			</c:if>
			</div>
		</c:if>
		<c:if test="${u_type == 0}">
			<div class="rightBtnDiv"><input type="button" onclick="location.href='noticeWrite'" value="공지 작성" class="btnBlack"></div>
		</c:if>
	</section>
</body>