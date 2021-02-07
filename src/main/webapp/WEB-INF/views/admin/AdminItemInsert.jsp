<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
<div class="wrap ItemInsert_wrap">
	<h3>상품등록</h3>
	<form enctype="multipart/form-data">
			<table>
				<%-- <tr>
					<th>상품 코드</th>
					<td>
						<input type="text" name="i_code" class="Item_input"  value="${i_code }">
					</td>
				</tr> --%>
				<tr>
					<th>상품 이름</th>
					<td><input type="text" name="i_name" class="Item_input" id="i_name"  value="${i_name }"></td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td><input type="text" name="i_price" class="Item_input" id="i_price" value="${i_price }"></td>
				</tr>
				<tr>
					<th>상품이미지</th>
						<td>
							<input accept="image/*"  id="itemMainImg"  multiple="multiple"  type="file" name="mainFile" >
							<!-- <img src="#"  alt="" id="Itemimg"  width="110" height="110"> -->
						</td>
				</tr>
				<tr>
					<th>상세 이미지</th>
					<td>
						<input type="file" name="detailFile" id="detailFile" value="${i_detailimg}">
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<select name="i_category"> 
							<option value="living">living
							<option value="kitchen">kitchen
							<option value="bathroom">bathroom
							<option value="office">office
							<option value="market">market
							<option value="travel">travel
					</select>
					</td>
				</tr>
		</table>
		<div id="Insert_Btn">
		<input type="button"  value="상품등록" onclick="ItemInsert()">
		</div>
	</form>
</div>
</section>
</body>
<script type="text/javascript">

	// 상품등록
	function ItemInsert() {
		var formData = new FormData($('form')[0]);
		if($("#i_name").val() == ""){
			alert("상품 이름을 기입하세요");
		}else if($("#i_price").val() == ""){
			alert("상품 가격을 기입하세요");
		}else if($("#itemMainImg").val() == ""){
			alert("상품 이미지를 등록하세요");
		}else if($("#detailFile").val() == ""){
			alert("상세 이미지를 등록하세요");
		}else{
			$.ajax({
				url : "ItemInsert",
				type : "post",
				datatype : "html",
				data : formData,
				enctype : "multipart/form-data",
				processData : false,
				contentType : false,
				cache : false,
				success : function(data) {
						alert("상품 등록 완료");
						$(".menu_info").html(data);
				},
				error:function(request,status,error){	   
					alert("등록 실패");
					/* alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); */
				 }
			});
			/*$("#itemMainImg").change(function(event){
				$("#Itemimg").attr("src", URL.createObjectURL(event.target.files[0]));
				var spl = $("#itemMainImg").val().split("\\");
			});*/
		}
	
	}
</script>
</html>