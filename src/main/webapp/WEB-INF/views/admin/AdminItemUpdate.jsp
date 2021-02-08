<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="item_update_wrap">
			<h3>상품수정</h3>
			<form enctype="multipart/form-data">
				<div class="item_update_div">
					<table>
						<tr>
							<th>상품명</th>
							<td>
								<input type="text" name="i_name" value="${idto.i_name }" class="Item_input" id="i_name">
							</td>
						</tr>
						<tr>
							<th>상품 가격</th>
							<td>
								<input type="text" name="i_price" value="${idto.i_price }" class="Item_input" id="i_price">
							</td>
						</tr>
						<tr>
							<th>상품이미지</th>
							<td>
								<label class="item_file" for="itemMainImg">업로드</label>
								<input class="upload_name" type="text" value="선택된 파일 없음" readonly="readonly">
								<input accept="image/*" id="itemMainImg" type="file" name="mainFile" >
								<div class="itemMainImg_div">
									<img alt="img" src="resources/image/category_img/${idto.i_img }">
									<input type="hidden" name="i_img" value="${idto.i_img }">
								</div>
							</td>
						</tr>
						<tr>
							<th>상세 이미지</th>
							<td>
								<label class="detail_file" for="detailFile">업로드</label>
								<input class="upload_name" type="text" value="선택된 파일 없음" readonly="readonly">
								<input type="file" name="detailFile" id="detailFile">
								<div class="detailFile_div">
									<img alt="img" src="resources/image/detail_img/${idto.i_detailimg }">
									<input type="hidden" name="i_detailimg" value="${idto.i_detailimg }">
								</div>
							</td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<input type="hidden" name="category" value="${idto.i_category }" id="iCate">
								<select name="i_category" id="i_cate">
									<option value="none">카테고리 선택
									<option value="living">living
									<option value="kitchen">kitchen
									<option value="bathroom">bathroom
									<option value="office">office
									<option value="market">market
									<option value="travel">travel
								</select>
							</td>
						</tr>
						<tr>
							<th>상품게시여부</th>
							<td>
								<input type="hidden" name="chk" value="${idto.i_chk }" id="ichk">
								<select name="i_chk" id="i_Chk">
									<option value="0">판매중
									<option value="1">판매중지
									<option value="2">품절
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div id="Update_Btn">
					<input type="hidden" name="i_code" value="${idto.i_code }" id="iCode">
					<input type="hidden" name="pageNum" value="${pageNum }" id="page_num">
					<input type="button" value="수정" onclick="itemUpdate()">
					<input type="button" value="취소" onclick="javascript:goItemList(${pageNum});">
				</div>
			</form>
		</div>
	</section>
</body>
<script type="text/javascript">
	// 카테고리 select box
	$("#i_cate option").each(function() {
		if($("#iCate").val() == $(this).val()) { // db에서 불러온 파일과 같은 파일이면 선택
			$(this).prop("selected", true);
		}
	});
	
	// 상품게시여부 select box
	$("#i_Chk option").each(function() {
		if($("#ichk").val() == $(this).val()) { // db에서 불러온 파일과 같은 파일이면 선택
			$(this).prop("selected", true);
		}
	});
	
	$(function() {
		$("#itemMainImg").change(function() {
			if(this.files && this.files[0]) {
				var fileName = this.files[0].name; // file 객체 배열 형태. 파일 1개의 파일명 추출
				$(this).siblings(".upload_name").val(fileName); // 추출한 파일명 삽입
				var reader = new FileReader;
				reader.onload = function(data) {
					$(".itemMainImg_div").attr("src", data.target.result).width(150);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});
		
		$("#detailFile").change(function() {
			if(this.files && this.files[0]) {
				var fileName = this.files[0].name; // file 객체  배열 형태. 파일 1개의 파일명 추출
				$(this).siblings(".upload_name").val(fileName); // 추출한 파일명 삽입
				var reader = new FileReader;
				reader.onload = function(data) {
					$(".detailFile_div").attr("src", data.target.result).width(150);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});
		
		/* var itemFile = $("#itemMainImg");
		
		itemFile.on("change", function() { // 값이 변경되면
			var fileName = this.files[0].name; // file 객체 배열 형태. 파일 1개의 파일명 추출
			$(this).siblings(".upload_name").val(fileName); // 추출한 파일명 삽입
		});
		
		var detailFile = $("#detailFile");
		
		detailFile.on("change", function() { // 값이 변경되면
			var fileName = this.files[0].name; // file 객체  배열 형태. 파일 1개의 파일명 추출
			$(this).siblings(".upload_name").val(fileName); // 추출한 파일명 삽입
		}); */
	});
	
	// 상품수정
	function itemUpdate() {
		var formData = new FormData($('form')[0]);
		$.ajax({
			url : "itemUpdate",
			type : "post",
			datatype : "html",
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			cache : false,
			success : function(data) {
				alert("상품 수정 완료");
				$(".menu_info").html(data);
			},
			error:function(request,status,error){	   
				//alert("수정할 내용이 없습니다.");
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	function goItemList(pn) {
		$.ajax({
			url : "goItemList",
			type : "post",
			data : { "pageNum" : pn },
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