<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="buy_cancel_list_wrap">
			<h3>취소완료내역</h3>
			<div class="buy_cancel_list_table_wrap">
				<table>
					<tr>
						<th class="buyCancelList_th">주문일자</th>
						<th class="buyCancelList_th">주문번호</th>
						<th class="buyCancelList_th">주문 상품 정보</th>
						<th class="buyCancelList_th">상품 금액</th>
						<th class="buyCancelList_th">수량</th>
						<th class="buyCancelList_th">배송비</th>
						<th class="buyCancelList_th">상태</th>
					</tr>
					<c:if test="${empty allBuyCancel }">
						<tr>
							<td colspan="7" class="buy_cancel_td">주문 취소 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty allBuyCancel }">
						<c:forEach var="list" items="${allBuyCancel }">
							<c:forEach var="item" items="${buyitem }">
								<c:if test="${list.b_code == item.b_code }">
									<tr>
										<td class="date_td">
											<fmt:parseDate var="bdate" value="${list.b_date }" pattern="yyyy-MM-dd HH:mm:ss" />
											<fmt:formatDate var="b_date" value="${bdate }" pattern="yyyy-MM-dd" />${b_date }
										</td>
										<td class="code_td"><a href="javascript:oneBuyCancel('${item.b_code }', ${pgdto.pageNum });">${item.b_code }</a></td>
										<td class="item_td">
											<img alt="img" src="resources/image/category_img/${item.i_img }">&nbsp;
											<a href="goDetail?i_code=${item.i_code}">${item.i_name }</a>
										</td>
										<td class="iprice_td"><fmt:formatNumber var="i_price" value="${item.i_price }" pattern="#,###"/>${i_price }원</td>
										<td class="cnt_td">${item.bi_cnt }개</td>
										<td class="bprice_td bprice">
											<c:if test="${list.b_price == 0}">
												<fmt:formatNumber var="b_price" value="${list.b_price }"/>무료
											</c:if>
											<c:if test="${list.b_price > 0}">
												<fmt:formatNumber var="b_price" value="${list.b_price }" pattern="#,###"/>${b_price }원
											</c:if>
										</td>
										<td class="state_td">취소완료</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="centerBlock">
	 			<c:if test="${pgdto.startPage > 1}">
	 				<div class="prev_div"><a href="javascript:allBuyCancel(${pgdto.startPage - pgdto.pageSize});"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page != pgdto.pageNum}">
						<div class="page_div"><a href="javascript:allBuyCancel(${page})">${page}</a></div>
					</c:if>
					<c:if test="${page == pgdto.pageNum}">
						<div class="curr_div"><a href="javascript:allBuyCancel(${page})">${page}</a></div>
					</c:if>
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:allBuyCancel(${pgdto.startPage + pgdto.pageSize });"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>	
</body>
<script type="text/javascript">
	$(function() {
		// 같은 날짜 td 병합
		$(".date_td").each(function() {
			var date = $(".date_td:contains('" + $(this).text() + "')"); // .date_td를 기준으로 text가 포함된 row 가져옴
			
			if(date.length > 1) {
				date.eq(0).attr("rowspan", date.length); // 중복되는 첫번째 td에 rowspan 값 추가
				date.not(":eq(0)").remove(); // 중복되는 td 삭제
			}
		});
		
		// 같은 코드 td 병합
		$(".code_td").each(function() {
			var code = $(".code_td:contains('" + $(this).text() + "')");
			var price = code.siblings(".bprice"); // 같은 배송비 td 병합. 같은 tr 아래에 있는 td 중 지정.
			var state = code.siblings(".state_td"); // 같은 상태 td 병합. 같은 tr 아래에 있는 td 중 지정.
			
			if(code.length > 1) {
				code.eq(0).attr("rowspan", code.length);
				code.not(":eq(0)").remove();
				price.eq(0).attr("rowspan", code.length);
				price.not(":eq(0)").remove();
				state.eq(0).attr("rowspan", code.length);
				state.not(":eq(0)").remove();
			}
		});
	});
	
	// 주문조회상세
	function oneBuyCancel(cd, pn) {
		$.ajax({
			url : "oneBuyCancel",
			type : "post",
			data : { "b_code" : cd, "pageNum" : pn },
			datatype : "html",
			success : function(data) {
				$('.menu_info').children().remove();
				$('.menu_info').html(data);
			},
			error : function(data, error) {
				alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
			}
		});
	}
	
	// 페이징
	function allBuyCancel(pn) {
		$.ajax({
			url : "allBuyCancel",
			type : "post",
			data : { "pageNum" : pn },
			datatype : "html",
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
			}
		});
	}
</script>
</html>