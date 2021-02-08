<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<link rel="stylesheet" href="resources/css/ItemSearch.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
	<div  class="wrap item_wrap">
	<form id="searchForm" method="post">
			<input type="text"  id="item_search"  name="item_search" placeholder="search" value="${item_search}">
			<input type="image"  id="searchbtn" src="resources/image/icon/search.png" alt="검색" >
		</form>
	<c:if test="${cnt > 0 }">
		<p id="search_result">${cnt }개의 검색결과</p>
		<hr>
		<br>
		<c:forEach var="idto" items="${idto }">
			  <div class="item_div">
			  		<div id="img_div">
						<img alt="img" src="resources/image/category_img/${idto.i_img }" onclick="location.href='goDetail?i_code=${idto.i_code}'">
			 		</div>
			 		<div id="category_info">
				 		<p id="i_category">${ idto.i_category  }</p>
				 		<p class="info">${idto.i_name }</p>
		  		 		<p class="info"><fmt:formatNumber value="${idto.i_price }" pattern="#,###" />원</p>	 
	  		 		</div>
		 	 </div>
		</c:forEach>
		</c:if>
		
		<c:if test="${cnt == 0 }">
			<p id="search_result">${cnt }개의 검색결과</p>
			<hr>
			<p id="zero_ment">상품이 없습니다.</p>
		</c:if>
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