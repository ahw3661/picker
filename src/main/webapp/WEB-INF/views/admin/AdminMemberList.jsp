<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminUserList_wrap">
			<div class="search_div">
				<div class="select_input_div">
					<input type="hidden" name="stype" value="${s_type }" id="sType">
					<select name="s_type" id="searchType">
						<option value="m_id">아이디</option>
						<option value="m_phone">전화번호</option>
						<option value="m_email">이메일</option>
					</select>
					<input type="text" name="m_keyword" value="${m_keyword}" id="mKeyword">
					<input type="button" name="s_btn" value="검색" id="sBtn">
				</div>
				<h3>회원정보</h3>
				<div class="select_div">
					<input type="hidden" name="mtype" value="${m_type }" id="m_Type">
					<select name="m_type" id="mType" onchange="changeType();">
						<option value="-1">- 전체 -</option>
						<option value="1">일반회원</option>
						<option value="2">탈퇴회원</option>
					</select>
				</div>
			</div>
			<div class="AdminUserList_table_wrap">
				<table>
					<tr>
						<th>아이디</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>회원유형</th>
					</tr>
					<c:forEach var="mdto" items="${mdto }">
						<tr>
							<td>
								<c:if test="${mdto.m_type == 1 }">
									<a href="javascript:goOneList('${mdto.m_id }', ${pgdto.pageNum });"><span>${mdto.m_id }</span></a>
								</c:if>
								<c:if test="${mdto.m_type == 2 }"><span>${mdto.m_id }</span></c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">${mdto.m_phone }</c:if>
								<c:if test="${mdto.m_type == 2 }">
									<c:set var="phone" value="${mdto.m_phone}" />
									<c:set var="totalLength" value="${fn:length(phone) }" />
									<c:forEach begin="1" end="${totalLength-1 }">*</c:forEach>
								</c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">${mdto.m_email }</c:if>
								<c:if test="${mdto.m_type == 2 }">
									<c:set var="email" value="${mdto.m_email}" />
									<c:set var="totalLength" value="${fn:length(email) }" />
									<c:forEach begin="1" end="${totalLength-1 }">*</c:forEach>
								</c:if>
							</td>
							<td>
								<c:if test="${mdto.m_type == 1 }">일반회원</c:if>
								<c:if test="${mdto.m_type == 2 }">탈퇴회원</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">
	 				<div class="prev_div"><a href="javascript:goMemberList(${pgdto.startPage - pgdto.pageSize}, '${s_type }', '${m_keyword}');"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:goMemberList(${page}, '${s_type }', '${m_keyword}')">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:goMemberList(${page}, '${s_type }', '${m_keyword}')">${page}</a></div>
					</c:if>
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:goMemberList(${pgdto.startPage + pgdto.pageSize }, '${s_type }', '${m_keyword}');"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	// 검색
	$("#sBtn").click(function() {
		$.ajax({
			url : "goMemberList",
			type : "post",
			data : { "s_type" : $("#searchType").val(), "m_keyword" : $("#mKeyword").val() },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	});
	
	// 회원유형 검색
	function changeType() {
		$.ajax({
			url : "goMemberList",
			type : "post",
			data : { "m_type" : $("#mType option:selected").val() },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	// 검색 select box
	$("#searchType option").each(function() {
		if($("#sType").val() == $(this).val()) {
			$(this).prop("selected", true);
		}
	});
	
	// 회원유형 검색 select box
	$("#mType option").each(function() {
		if($("#m_Type").val() == $(this).val()) {
			$(this).prop("selected", true);
		}
	});
	
	// 회원정보 상세
	function goOneList(id, pn) {
		$.ajax({
			url : "goOneList",
			type : "post",
			data : { "m_id" : id, "pageNum" : pn },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	// 페이징
	function goMemberList(pn, st, kw) {
		$.ajax({
			url : "goMemberList",
			type : "post",
			data : { "pageNum" : pn, "s_type" : st, "m_keyword" : kw },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	}
</script>
</html>