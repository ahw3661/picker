<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="evalForm">
	<div id="writeMain">
		<c:forEach var="level" begin="1" end="5">
			<span class="evalRank">
				<input type="radio" name="e_level" value="${level}">
				<c:forEach var="star" begin="1" end="${level}">
					<img src="resources/image/icon/star.svg" class="starIcon">
				</c:forEach>
			</span>
		</c:forEach>
	</div>
	<textarea rows="5" cols="50" maxlength="500" name="e_content"></textarea>
	<div class="rightBtnDiv">
		<input type="button" value="작성완료" onclick="evalWrite('작성')" class="btnBlack">
	</div>
</form>