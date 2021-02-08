<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Section.css" type="text/css">
</head>
<body>
	<section>
		<form id="searchForm" method="post">
			<input type="text"  id="item_search"  name="item_search" placeholder="SEARCH" value="${item_search}">
			<input type="image"  id="searchbtn" src="resources/image/icon/search.png" alt="검색" >
		</form>
		<div class="wrap">
			<div class="centerBlock">
				<div class="img_div"><img alt="main_image" src="resources/image/section/main_image.jpeg"></div>
			</div>
		</div>
	</section>
</body>
<script>
$(function(){
	$("#searchbtn").click(function(){
		if($("#item_search").val() == ""){
			alert("상품을 입력하세요");
			return false;
		}else{
			$("#searchForm").attr("action","searchItem");
		}
	});
});
</script>
</html>