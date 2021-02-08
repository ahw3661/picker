<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
</head>
<body>
<aside>
	<div id="evalWrite_wrap">
		<h3 class="stretchBlock">
			<span>리뷰 작성</span>
			<a href="javascript:closeEvalPop()"><img src="resources/image/icon/closeBtn.svg" alt="닫기" class="btnClose"></a>
		</h3>
		<div>
		<form id="write_form">
			<div id="write_Main">
				<c:forEach var="level" begin="1" end="5">
					<span class="evalRank">
						<input type="radio"  name="e_level"  value="${level}">
						<c:forEach var="star" begin="1" end="${level}">
							<img src="resources/image/icon/star.svg" class="starIcon">
						</c:forEach>
					</span>
				</c:forEach>
			</div>
			<textarea rows="5" cols="50" maxlength="500" name="e_content"></textarea>
			<div class="rightBtnDiv">
				<input type="button" value="작성완료" onclick="writeInsert()" class="btnBlack">
			</div>
			<input type="hidden" name="i_code" value="${ i_code }">
		</form>
		</div>
	</div>
</aside>
 <script type="text/javascript">
 	function writeInsert() {
 	 	if($("input[name=e_level]").val() != null && $("textarea[name=e_content]").val() != ""){
	 		$.ajax({
	 			url : "EvalInsert",
	 			type : "post",
	 			datatype : "json", // {"chk" : true }
	 			data : $("#write_form").serialize(),
	 			success : function(data) {
	 				if(data.chk){
	 					alert("리뷰가 작성되었습니다.");
	 					$("aside").remove();
	 					getEvalList(1);
	 				} else {
	 					alert("[에러] 리뷰 작성 오류");
	 				}
	 			},
	 			error:function(request,error){
	 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 			}
	 		})
 	 	} else {
			window.alert("작성란을 채워주세요.");
 	 	}
	}
 </script>
 </body>
</html>