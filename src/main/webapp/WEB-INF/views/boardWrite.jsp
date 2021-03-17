<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/jquery-3.6.0.min.js"></script>
<title>게시판 등록</title>
<link rel="stylesheet" href="../../resources/boardModify.css">
</head>
<body>

<div class="container">
	<div class="space"></div>
<form id="insertForm" action="/board/insert" method="post" enctype="multipart/form-data">
<table class="bTable">
	 <colgroup>
	      <col width="20%"/>
	      <col width="80%"/>
     </colgroup>
		<tr>
			<th>제목</th>
			<td>
				<input type="hidden" value="${sessionScope.customerNumber}" name="customerNum"/>
				<input id="title" name="title" class="input title" type="text"/>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea id="textarea" name="content" class="input textarea" cols="50" rows="10"></textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file" accept=".gif, .jpg, .png" name="imagePath" id="fileUploadBtn" onChange="imageChanger(this)"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<img id="imagePreview" width="50" height="50">
			</td>
		</tr>
	</table>
</form>
	<div class="btnGroup">
		 <button class="btn" onclick="cancel()">취소</button>
		 <button id="insertBtn" class="btn" onclick="insert()">등록</button>
	</div>
</div>
<div class="space"></div>
<script>
const insertForm = $("#insertForm");
const textArea = $("#textarea");
const title = $("#title");

function cancel(){
    location.href = `${pageContext.request.contextPath}/board/list`;
}

function insert(){
	if(writeValidation(title, 30) && writeValidation(textArea, 100))
	{
		insertForm.submit();
	}
}

function writeValidation(target, limit) {
	if(target.val().length == 0){
		alert('내용을 입력해주세요.');
		target.focus();
		return false;
	}
	else if(target.val().length <= limit)
    {
    	return true;
    }
    else
    {
		alert('최대 ' + limit + '자 까지만 입력가능합니다');
		target.val(target.val().substring(0, limit));
		target.focus();
		return false;
	}
}

function imageChanger(file){
	const imagePreview = $("#imagePreview");
	
	let reader = new FileReader(); 
	
	reader.readAsDataURL(event.target.files[0]); 
	reader.onload = (function (e) {
		imagePreview.attr("src", e.target.result);
    })
}

</script>
</body>
</html>