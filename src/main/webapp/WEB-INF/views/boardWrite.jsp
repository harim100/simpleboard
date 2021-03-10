<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<link rel="stylesheet" href="../../resources/boardModify.css">
</head>
<body>

<div class="container">
	<div class="space"></div>
<form class="insertForm" action="/board/insert" method="post" enctype="multipart/form-data">
<table class="bTable">
	 <colgroup>
	      <col width="20%"/>
	      <col width="80%"/>
     </colgroup>
		<tr>
			<th>제목</th>
			<td>
				<input name="title" class="input title" type="text">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name="content" class="input textarea" cols="50" rows="10">
			
			</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file" accept=".gif, .jpg, .png" name="imagePath" id="fileUploadBtn" class="fileUploadBtn" 
				onChange="imageChanger(this, event)">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<img class="imagePreview" width="50" height="50">
			</td>
		</tr>
	</table>
	<input type="hidden" value="${sessionScope.customerNumber}" name="customerNum">
	<div class="btnGroup">
		 <button class="btn" onclick="cancel(event)">취소</button>
		 <button class="js-insertBtn btn" onClick="insert(event)">등록</button>
	</div>
</form>
</div>
<div class="space"></div>
<script>
const insertForm = document.querySelector(".insertForm");

function cancel(event){
	event.preventDefault();
    location.href = `${pageContext.request.contextPath}/board/list`;
}

function cancel(event){
	event.preventDefault();
    location.href = `${pageContext.request.contextPath}/board/insert`;
}

function imageChanger(file, event){
	const imagePreview = document.querySelector(".imagePreview");
	
	let reader = new FileReader(); 
	
	reader.readAsDataURL(event.target.files[0]); 
	reader.onload = (function (e) {
            imagePreview.src = e.target.result;
    })
	console.log(event.target.files[0]);
}

function init(){
}
init();
</script>
</body>
</html>