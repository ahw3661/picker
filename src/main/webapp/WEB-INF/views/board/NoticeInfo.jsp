<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="resources/css/Notice.css">
</head>
<body>
	<section class="wrap noticeInfo">
		<div class="noticeTitle">
			<h4>더 피커 소식</h4>
			<p></p>
			<h3>${notice.n_title}</h3>
		</div>
		<div id="noticeContent">${notice.n_content}</div>
		<c:if test="${not empty nextNum}">
			<p>
				<a href="noticeRead?no=${nextNum}" class="readRef">
					<img src="resources/image/icon/arrow_up.svg"><span>${nextTitle}</span>
				</a>
			</p>
		</c:if>
		<c:if test="${not empty prevNum}">
			<p>
				<a href="noticeRead?no=${prevNum}" class="readRef">
					<img src="resources/image/icon/arrow_down.svg"><span>${prevTitle}</span>
				</a>
			</p>
		</c:if>
		<c:choose>
			<c:when test="${u_type != null && u_type == 0}">
				<div class="stretchBlock readBot">
					<input type="button" value="목록" class="btnBlack" onclick="location.href='noticeList'">
					<div>
						<input type="button" value="수정" class="btnWhite" onclick="location.href='noticeModify?no=${notice.n_num}'">
						<input type="button" value="삭제" class="btnWhite" onclick="noticeErase(${notice.n_num})">
					</div>
				</div>
				<script type="text/javascript">
					function noticeErase(num){
						$.ajax({
							url : 'noticeErase?no=' + num,
							type : 'get',
							datatype : 'json',
							success : function(json){
								if(json.chk) {
									window.alert('공지글이 삭제되었습니다.');
									location.href='noticeList';
								} else {
									window.alert('공지 삭제 오류');
								}
							},
							error : function(){
								window.alert('ajax 에러');
							}
						})
					}
				</script>
			</c:when>
			<c:otherwise>
				<div class="readBot">
					<input type="button" value="목록" class="btnBlack" onclick="location.href='noticeList'">
				</div>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>