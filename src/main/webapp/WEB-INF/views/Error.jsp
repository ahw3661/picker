<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<c:if test="${not empty msg}">
      <script type="text/javascript">
         window.alert("${msg}");
      </script>
   </c:if>
   <c:if test="${not empty loc}">
      <script type="text/javascript">
         var loc = parseInt("${loc}");
         switch(loc){
         case 1:
            location.href="loginPage";
            break;
         case 2:
            location.href=history.go(-2);
            break;
         }
      </script>
   </c:if>
</body>
</html>