<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<link rel="stylesheet" href="../../resources/boardModify.css">
<script>
window.onload = function() {

}//end of onload
</script>
</head>
<body>

<div class="container">
	<div class="space"></div>
<form class="updateForm" action="/board/update" method="post" enctype="multipart/form-data">
	<table class="bTable">
	 <colgroup>
	      <col width="20%"/>
	      <col width="80%"/>
     </colgroup>
		<tr>
			<th>제목</th>
			<td>
				<input name="title" class="input title" type="text" value="${bVO.getTitle()}">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name="content" class="input textarea" cols="50" rows="10">${bVO.getContent()}</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file"  accept=".gif, .jpg, .png"  name="imagePath" 
				id="fileUploadBtn" class="fileUploadBtn" onChange="imageChanger(this)">
<!-- 				<label class="fileUploadLabel" for="input-file">
				  업로드
				</label>
				<input type="file" name="mediaFile" id="fileUploadBtn" class="fileUploadBtn"> -->
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<div class="filename">
				${bVO.getImagePath()}
			</div>
			<img class="imagePreview" src="${bVO.getImagePath()}" width="50" height="50">
			</td>
		</tr>
	</table>
	<input type="hidden" name="idx" value="${bVO.getBrdIdx()}">
	<div class="btnGroup">
		 <button class="btn" onclick="cancel(event)">취소</button>
		 <button class="js-deleteBtn btn" onClick="deleteBoard(event)">삭제</button>
		 <button class="js-insertBtn btn" onClick="update(event)">등록</button>
	</div>
</form>
</div>
<div class="space"></div>
<script>
const updateForm = document.querySelector(".updateForm");

function cancel(event){
	event.preventDefault();
    location.href = `${pageContext.request.contextPath}/board/list`;
}

function deleteBoard(event){
	event.preventDefault();
	if(${bVO.getCustomerNum()}==${sessionScope.customerNumber}){
		//location.href = `board/delete?idx=${bVO.getBrdIdx()}`;
		fetch(`/board/delete?idx=${bVO.getBrdIdx()}`, {
			  method: 'GET',
			}).then(function(response){
		       return response.text();
		    }).then(function(text){
				console.log("결과:: " + text);
				text = 1 ? alert("삭제 성공") : alert("삭제 실패");
				location.href = `${pageContext.request.contextPath}/board/list`;
			});
	}
	else{
		alert("본인이 작성한 글만 삭제할 수 있습니다.");
	}
}

function update(event){
	event.preventDefault();
	if(${bVO.getCustomerNum()}==${sessionScope.customerNumber}){
		updateForm.submit();
	}
	else{
		alert("본인이 작성한 글만 수정할 수 있습니다.");
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

function getFileName(){
	const splits = `${bVO.getImagePath()}`.split('/');
	console.log(splits[splits.length-1]);
	
	const filename = document.querySelector(".filename");
	filename.innerText = splits[splits.length-1];
}

function init(){
	getFileName();
}
init();
</script>
</body>
</html>