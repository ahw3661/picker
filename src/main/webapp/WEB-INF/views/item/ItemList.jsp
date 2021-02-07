<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<link rel="stylesheet" href="resources/css/ItemList.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
	<div class="wrap item_wrap" >
	<div id="head_name">
			<a href="#" id="menuBtn">Home</a>&nbsp;&nbsp;》&nbsp;&nbsp;
			<a><strong>${cateName.i_category }</strong></a>
			<br><br> 
			<hr>	 
		 </div>
		<c:forEach var="itemlist" items="${itemlist }">
		
			 <div class="item_div">
			 		<img alt="img" src="resources/image/category_img/${itemlist.i_img }" onclick="location.href='goDetail?i_code=${itemlist.i_code}'">
			 		<p>${itemlist.i_name }</p>
			 		<p><fmt:formatNumber value="${itemlist.i_price }" pattern="#,###" />원</p>	 
			 </div>
		</c:forEach>
	</div>
</section>
</body>
</html>