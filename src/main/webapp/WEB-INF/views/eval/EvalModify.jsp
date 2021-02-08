<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>리뷰를 이미 작성하신 경우에는 기존 리뷰의 수정 및 삭제만 가능합니다.</p>
<form id="evalForm">
	<div id="writeMain">
		<c:forEach var="level" begin="1" end="5">
			<span class="evalRank">
				<input type="radio"  name="e_level"  value="${level}">
				<c:forEach var="star" begin="1" end="${level}">
					<img src="resources/image/icon/star.svg" class="starIcon">
				</c:forEach>
			</span>
		</c:forEach>
	</div>
	<textarea rows="5" cols="50" maxlength="500" name="e_content">${eval.e_content}</textarea>
	<div class="rightBtnDiv">
		<input type="button" value="삭제" onclick="evalErase()" class="btnBlack">
		<input type="button" value="수정" onclick="evalWrite('수정')" class="btnBlack">
	</div>
	<script type="text/javascript">
		$("input[name=e_level]:input[value=${eval.e_level}]").attr("checked", true);		
	</script>
</form>