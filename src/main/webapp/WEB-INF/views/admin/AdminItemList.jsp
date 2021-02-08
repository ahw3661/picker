<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="AdminItemList_wrap">
			<h3>상품목록</h3>
		 	<div class="search_div">
				<div class="select_input_div">
					<input type="hidden" name="stype" value="${s_type }" id="sType">
					<select name="s_type" id="searchType">
						<option value="i_code">상품코드</option>
						<option value="i_name">상품명</option>
					</select>
					<input type="text" name="m_keyword" value="${m_keyword}" id="mKeyword">
					<input type="button" name="s_btn" value="검색" id="sBtn">
				</div>
				<div class="select_div">
					<input type="hidden" name="category" value="${i_category }" id="iCg">
					<select name="i_category" id="i_cg" onchange="changeCg();">
						<option value="all">카테고리</option>
						<option value="living">living</option>
						<option value="kitchen">kitchen</option>
						<option value="bathroom">bathroom</option>
						<option value="office">office</option>
						<option value="market">market</option>
						<option value="travel">travel</option>
					</select>
					<input type="hidden" name="ichk" value="${i_chk }" id="iChk">
					<select name="i_chk" id="i_Chk" onchange="changeChk();">
						<option value="-1">- 상태 -</option>
						<option value="0">판매중</option>
						<option value="1">판매중지</option>
						<option value="2">품절</option>
					</select>
				</div>
			</div>
			<div class="count_div">총 ${cnt }건</div>
		 	<div class="AdminItemList_div">
			 	<table>
				 	<tr>
				 	 	<th>상품 코드</th>
				 	 	<th>상품 명</th>
				 	 	<th>상품 카테고리</th>
				 	</tr>
				 	<c:if test="${cnt == 0 }">
				 		<tr>
							<td colspan="3">검색된 내용이 없습니다.</td>
						</tr>
				 	</c:if>
				 	<c:if test="${cnt > 0 }">
					 	<c:forEach var="itemlist" items="${itemList }">
							<tr>
								<td>${itemlist.i_code }</td>
								<td><a href="javascript:goAdminItemDetail('${itemlist.i_code }', ${pgdto.pageNum });">${itemlist.i_name }</a></td>
								<td>${itemlist.i_category }</td>
							</tr>
					 	</c:forEach>
				 	</c:if>
			 	</table>
		 	</div>
		 	<!-- 페이징 -->
		 	<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">																											  
	 				<div class="prev_div"><a href="javascript:goItemList(${pgdto.startPage - pgdto.pageSize}, '${s_type }', '${m_keyword}', '${i_category }', ${i_chk });"><b>《</b></a></div>
	 			</c:if>						 
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page >= 1}">
						<c:if test="${page != pgdto.pageNum}">																							  
							<div class="page_div"><a href="javascript:goItemList(${page}, '${s_type }', '${m_keyword}', '${i_category }', ${i_chk })">${page}</a></div>
						</c:if>
						<c:if test="${page == pgdto.pageNum}">
							<div class="curr_div"><a href="javascript:goItemList(${page}, '${s_type }', '${m_keyword}', '${i_category }', ${i_chk })">${page}</a></div>
						</c:if>
					</c:if>
				</c:forEach>						
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:goItemList(${pgdto.startPage + pgdto.pageSize }, '${s_type }', '${m_keyword}', '${i_category }', ${i_chk });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	//검색
	$("#sBtn").click(function() {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "s_type" : $("#searchType").val(), "m_keyword" : $("#mKeyword").val() },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	});
	
	// 상품카테고리 검색
	function changeCg() {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "i_category" : $("#i_cg option:selected").val() },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	// 상품상태 검색
	function changeChk() {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "i_chk" : $("#i_Chk option:selected").val() },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
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
	
	// 상품카테고리 검색 select box
	$("#i_cg option").each(function() {
		if($("#iCg").val() == $(this).val()) {
			$(this).prop("selected", true);
		}
	});
	
	// 상품 상태 검색 select box
	$("#i_Chk option").each(function() {
		if($("#iChk").val() == $(this).val()) {
			$(this).prop("selected", true);
		}
	});
	
	// 상품상세
	function goAdminItemDetail(cd, pn) {
		$.ajax({
			url : "goAdminItemDetail",
			type : "post",
			data : { "i_code" : cd, "pageNum" : pn },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
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
	function goItemList(pn, st, kw, cg, chk) {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "pageNum" : pn, "s_type" : st, "m_keyword" : kw, "i_category" : cg, "i_chk" : chk },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
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