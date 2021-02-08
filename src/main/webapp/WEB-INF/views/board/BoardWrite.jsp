<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script type="text/javascript" src="resources/js/summernote/summernote-lite.min.js"></script>
<script type="text/javascript" src="resources/js/summernote/summernote-ko-KR.min.js"></script>
<link type="text/css" rel="stylesheet" href="resources/css/summernote-lite-dark.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/Board.css">
</head>
<body>
	<section class="wrap">
		<form id="writeForm" class="writeWrap" method="post">
			<h3>문의 작성</h3>
			<c:if test="${not empty item}">
				<div id="itemCon">
					<div id="itemTitle" class="centerInline">관련상품</div>
					<div id="itemInfo">
						<img alt="상품이미지" src="resources/image/category_img/${item.i_img}">
						<input type="text" name="i_name" value="${item.i_name}" readonly>
					</div>
				</div>
				<input type="hidden" name="i_img" value="${item.i_img}">
				<input type="hidden" name="i_code" value="${item.i_code}">
			</c:if>
			<input type="text" name="q_title" id="iptTitle" placeholder="제목을 입력해주세요">
			<div>
				<textarea id="contentEditor" name="q_content"></textarea>
			</div>
			<div class="rightBtnDiv">
				<input type="reset" value="취소" id="resetBtn" class="btnWhite"><input type="button" value="작성" id="writeBtn" class="btnBlack">
			</div>
		</form>
	</section>
	<script type="text/javascript" src="resources/js/BoardWrite.js"></script>
</body>
