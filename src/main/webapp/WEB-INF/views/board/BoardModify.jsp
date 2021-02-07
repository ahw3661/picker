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
			<h3>문의 수정</h3>
			<c:if test="${not empty i_code}">
				<p>관련상품 : <input type="text" name="i_name" value="${i_name}" readonly></p>
				<input type="hidden" name="i_img" value="${i_img}">
				<input type="hidden" name="i_code" value="${i_code}">
			</c:if>
			<input type="text" name="q_title" id="iptTitle" value="${dto.q_title}" placeholder="제목을 입력해주세요">
			<input type="hidden" name="q_num" value="${dto.q_num}">
			<div>
				<textarea id="contentEditor" name="q_content">${dto.q_content}</textarea>
			</div>
			<div class="rightBtnDiv">
				<input type="reset" value="취소" class="btnWhite"><input type="button" value="수정" id="modifyBtn" class="btnBlack">
			</div>
		</form>
	</section>
	<script type="text/javascript" src="resources/js/BoardWrite.js"></script>
</body>
