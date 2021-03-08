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
</head>
<body>

<div class="container">
	<div class="space"></div>
<form>
	<table class="bTable">
	 <colgroup>
	      <col width="20%"/>
	      <col width="80%"/>
     </colgroup>
		<tr>
			<th>제목</th>
			<td>
				<input class="input title" type="text" value="${bVO.getTitle()}">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea class="input textarea" cols="50" rows="10">${bVO.getContent()}</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
			<input type="file" name="mediaFile">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<div class="filename">
				${bVO.getImagePath()}
			</div>
			<img src="${bVO.getImagePath()}" width="50" height="50">
			</td>
		</tr>
	</table>
	<div class="btnGroup">
		 <button class="btn" onclick="cancel();">취소</button>
		 <button class="js-deleteBtn btn" onClick="javascript:delete()">삭제</button>
		 <button class="js-insertBtn btn" onClick="javascript:insert()">등록</button>
	</div>
</form>
</div>
<div class="space"></div>
<script>
function cancel(){
    location.href = "/boardList";
}

const splits = '${bVO.getImagePath()}'.split('/');
console.log(splits[splits.length-1]);

const filename = document.querySelector(".filename");
filename.innerText = splits[splits.length-1];
</script>
</body>
</html>