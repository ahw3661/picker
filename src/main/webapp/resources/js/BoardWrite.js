$('#contentEditor').summernote({
	placeholder : '내용을 입력해주세요',
	tabsize : 4,
	height : 500,
	minHeight: 500,
	maxHeight: null,
	lang : 'ko-KR',
	toolbar : [
		['fontname', ['fontname']],
		['font', ['fontsize', 'bold', 'italic', 'underline', 'strikethrough', 'color', 'clear']],
		['para', ['paragraph', 'ol', 'ul']],
		['insert', ['hr', 'link', 'picture']],
		['view', ['undo', 'redo', 'codeview', 'help']]
	],
	fontNames: ['helvetica', 'Arial', 'Arial Black', 'Georgia', 'Tahoma',
		'Times New Roman', 'Verdana', 'Noto Sans KR', '나눔고딕', '맑은 고딕', '궁서', '바탕'],
	fontNamesIgnoreCheck: ['Noto Sans KR'],	
	fontSizes: ['8','9','10','11','12','13','14','16','18','20','22','24','26','30','36','48','60','96'],
	resize: false
});

$("#resetBtn").click(function(){
	$("#contentEditor").summernote("reset");
})

function fillOutCheck(){
	if($("input[name=q_title]").val() == null || $("input[name=q_title]").val() == ""){
		window.alert("제목을 입력해주세요.");
		return false;
	} else if($("textarea[name=q_content]").val() == null || $("textarea[name=q_content]").val() == ""){
		window.alert("내용을 입력해주세요.");
		return false;
	} else {
		return true;
	}
}

function noticeFillOutCheck(){
	if($("input[name=n_title]").val() == null || $("input[name=n_title]").val() == ""){
		window.alert("제목을 입력해주세요.");
		return false;
	} else if($("textarea[name=n_content]").val() == null || $("textarea[name=n_content]").val() == ""){
		window.alert("내용을 입력해주세요.");
		return false;
	} else {
		return true;
	}
}
$('#writeBtn').click(function(){
	if(fillOutCheck()){
		$.ajax({
			url : 'qnaWriteProc',
			type : 'post',
			datatype : 'json',
			data : $('form').serialize(),
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(json){
				if(json.chk) {
					window.alert('게시글이 작성되었습니다.');
					location.href=document.referrer;
				} else if(json.logError != undefined && json.logError) {	
					window.alert('[세션종료] 세션이 종료되었습니다.');
				} else {
					window.alert('글 작성 오류');
				}
			},
			error : function(){
				window.alert('ajax 에러');
			}
		})
	}
   
})

$('#modifyBtn').click(function(){
	if(fillOutCheck()){
		$.ajax({
			url : 'qnaModifyProc',
			type : 'post',
			datatype : 'json',
			data : $('form').serialize(),
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "json");
			},
			success : function(json){
				if(json.chk) {
					window.alert('게시글이 수정되었습니다.');
					location.href=document.referrer;
				} else if(json.logError != undefined && json.logError) {	
					window.alert('[세션종료] 세션이 종료되었습니다.');
				} else {
					window.alert('글 수정 오류');
				}
			},
			error : function(){
				window.alert('ajax 에러');
			}
		})
	}
   
})


$('#noticeWriteBtn').click(function(){
	if(noticeFillOutCheck()){
		$.ajax({
			url : 'noticeWriteProc',
			type : 'post',
			datatype : 'json',
			data : $('form').serialize(),
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(json){
				if(json.chk) {
					window.alert('공지글이 작성되었습니다.');
					location.href='noticeList';
				} else if(json.logError != undefined && json.logError) {	
					window.alert('[세션종료] 세션이 종료되었습니다.');
				} else {
					window.alert('공지 작성 오류');
				}
			},
			error : function(){
				window.alert('ajax 에러');
			}
		})
	}
   
})

$('#noticeModifyBtn').click(function(){
	if(noticeFillOutCheck()){
		$.ajax({
			url : 'noticeModifyProc',
			type : 'post',
			datatype : 'json',
			data : $('form').serialize(),
			beforeSend : function(xmlHttpRequest) {
				xmlHttpRequest.setRequestHeader("ajax", "true");
			},
			success : function(json){
				if(json.chk) {
					window.alert('공지글이 수정되었습니다.');
					location.href=document.referrer;
				} else if(json.logError != undefined && json.logError) {	
					window.alert('[세션종료] 세션이 종료되었습니다.');
				} else {
					window.alert('공지 수정 오류');
				}
			},
			error : function(){
				window.alert('ajax 에러');
			}
		})
	}
   
})