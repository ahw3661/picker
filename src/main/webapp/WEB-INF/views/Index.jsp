<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Index.css" type="text/css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<c:if test="${empty section}"> <!-- section == null -->
 		<c:set var = "section" value="Section.jsp"/>
	</c:if>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="${section }"></jsp:include>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>