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
				<input type="hidden" value="${sessionScope.customerNumber}" name="customerNum"/>
				<input name="title" class="input title" type="text"/>
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
				<input type="file" accept=".gif, .jpg, .png" name="imageFile" 
					id="fileUploadBtn" class="fileUploadBtn" 
					onChange="imageChanger(this)"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<img id="imagePreview" class="imagePreview" width="50" height="50">
			</td>
		</tr>
	</table>
	<div class="btnGroup">
		 <button class="btn" onclick="cancel()">취소</button>
		 <button id="insertBtn" class="js-insertBtn btn" onclick="insert()">등록</button>
	</div>
</form>
</div>
<div class="space"></div>
<script>
console.log(`customerNumber = + ${customerNumber}`);

const insertForm = document.querySelector(".insertForm");
const textArea = document.querySelector(".textarea");
const title = document.querySelector(".title");

function cancel(){
	event.preventDefault();
    location.href = `${pageContext.request.contextPath}/board/list`;
}

function insert(){
	event.preventDefault();
	
	if(writeValidation(title, 30) && writeValidation(textArea, 100)){
		insertForm.submit();
	}
}

function writeValidation(what, limit) {
    if(what.value.length < limit) return true;
    else{
	       alert('최대 ' + limit + '자 까지만 입력가능합니다');
	       what.value = "";
	       what.focus();
	       return false;
		}
}

function imageChanger(file){
	const imagePreview = document.querySelector(".imagePreview");
	
	let reader = new FileReader(); 
	
	reader.readAsDataURL(event.target.files[0]); 
	reader.onload = (function (e) {
            imagePreview.src = e.target.result;
    })
	console.log(event.target.files[0]);
}

</script>
</body>
</html>