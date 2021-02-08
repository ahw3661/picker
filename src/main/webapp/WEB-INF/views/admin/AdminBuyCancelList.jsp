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
			<h3>취소완료</h3>
			<div class="search_div">
				<div class="date_search_div">
					<span>조회기간</span> <input type="text" name="start_date" value="${start_date }" id="from"> ~ <input type="text" name="end_date" value="${end_date }" id="to">
					<input type="button" name="ds_btn" value="검색" id="dsBtn">
				</div>
			</div>
			<div class="count_div">총 ${cnt }건</div>
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
					<c:if test="${cnt == 0 }">
						<tr>
							<td colspan="7" class="buy_cancel_td">주문 취소 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${cnt > 0 }">
						<c:forEach var="list" items="${allBuyCancel }">
							<c:forEach var="item" items="${buyitem }">
								<c:if test="${list.b_code == item.b_code }">
									<tr>
										<td class="date_td">
											<fmt:parseDate var="bdate" value="${list.b_date }" pattern="yyyy-MM-dd HH:mm:ss" />
											<fmt:formatDate var="b_date" value="${bdate }" pattern="yyyy-MM-dd" />${b_date }
										</td>
										<td class="code_td">
											<a href="javascript:oneBuyCancel('${item.b_code }', ${pgdto.pageNum }, '${start_date }', '${end_date }');">${item.b_code }</a>
											<c:if test="${list.m_id == 'none' }"><span>비회원</span></c:if>
											<c:if test="${list.m_id != 'none' }"><span>회원</span></c:if>
										</td>
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
	 				<div class="prev_div"><a href="javascript:allBuyCancel(${pgdto.startPage - pgdto.pageSize}, '${start_date }', '${end_date }');"><b>《</b></a></div>
	 			</c:if>
				<c:forEach var="page" begin="${pgdto.startPage}" end="${pgdto.endPage}">
					<c:if test="${page >= 1}">
						<c:if test="${page != pgdto.pageNum}">
							<div class="page_div"><a href="javascript:allBuyCancel(${page}, '${start_date }', '${end_date }')">${page}</a></div>
						</c:if>
						<c:if test="${page == pgdto.pageNum}">
							<div class="curr_div"><a href="javascript:allBuyCancel(${page}, '${start_date }', '${end_date }')">${page}</a></div>
						</c:if>
					</c:if>
				</c:forEach>
	 			<c:if test="${pgdto.endPage < pgdto.pageCount}">
	 				<div class="next_div"><a href="javascript:allBuyCancel(${pgdto.startPage + pgdto.pageSize }, '${start_date }', '${end_date }');"><b>》</b></a></div>
	 			</c:if>
			</div>
		</div>
	</section>	
</body>
<script type="text/javascript">
	$(function() {
		var option = {
			// datepicker 애니메이션 타입
			showAnim : "fadeIn", // option 종류
			changeMonth: true, // 년 월이 셀렉트 박스로 표현 되어서 선택할 수 있다.
			changeYear: true,
			dateFormat: "yy-mm-dd", // 데이터 포멧
			stepMonths: 1, // 달력에서 좌우 선택시 이동할 개월 수
			yearRange: "c-5:c+10", // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
			//minDate: -20, // 선택 가능한 날짜(수 형식) - 현재 기준 -20일
			maxDate: "0", // 선택 가능한 최대 날짜(문자 형식) - 현재 기준 +1월 +20일. 0은 현재날짜까지만 선택 가능
		};
		
		var optionFrom = option; // 시작일자
		$("#from").datepicker(optionFrom);
		var from = $("#from").datepicker(optionFrom).on("change", function() {
			// 시작일이 선택이 되면 종료일은 시작일 보다 앞을 선택할 수 없다.
			to.datepicker("option", "minDate", getDate(this));
		});
		
		var optionTo = option; // 종료일자
		$("#to").datepicker(optionTo);
		var to = $("#to").datepicker(optionTo).on("change", function() {
			// 종료일이 선택이 되면 시작일은 시작일 보다 앞을 선택할 수 없다.
			from.datepicker("option", "maxDate", getDate(this));
		});
		
		function getDate(element) {
			return moment(element.value).toDate();
		}
	});
	
	$("#dsBtn").click(function() {
		$.ajax({
			url : "allBuyCancel",
			type : "post",
			data : { "start_date" : $("#from").val(), "end_date" : $("#to").val() },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(data) {
				$(".menu_info").children().remove();
				$(".menu_info").html(data);
			},
			error : function(data, error) {
				alert("code : "+data.status+"\n"+"message : "+data.responseText+"\n"+"error : "+error);
			}
		});
	});
	
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
	function oneBuyCancel(cd, pn, sd, ed) {
		$.ajax({
			url : "oneBuyCancel",
			type : "post",
			data : { "b_code" : cd, "pageNum" : pn, "start_date" : sd, "end_date" : ed },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
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
	function allBuyCancel(pn, sd, ed) {
		$.ajax({
			url : "allBuyCancel",
			type : "post",
			data : { "pageNum" : pn, "start_date" : sd, "end_date" : ed },
			datatype : "html",
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
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