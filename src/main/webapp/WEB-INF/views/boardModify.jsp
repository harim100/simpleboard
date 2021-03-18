<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>게시판 수정</title>
<link rel="stylesheet" href="../../resources/css/boardModify.css">
</head>
<body>
	<div class="container">
		<div class="space"></div>
		<form id="updateForm" action="/board/update" method="post"
			enctype="multipart/form-data">
			<table class="bTable">
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				<tr>
					<th>제목</th>
					<td><input id="title" name="title" class="input title" type="text" value="${bVO.getTitle()}"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<textarea id="textarea" name="content" class="input textarea" cols="50" rows="10">${bVO.getContent()}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<input type="file" accept=".gif, .jpg, .png" name="imagePath" id="fileUploadBtn" onChange="imageChanger(this)"/> 
						<input type="hidden" name="oriImagePath" value="${bVO.getImagePath()}"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="filename" class="filename"></div> 
						<img id="imagePreview" src="${bVO.getImagePath()}" width="50" height="50">
					</td>
				</tr>
			</table>
			<input type="hidden" name="idx" value="${bVO.getBrdIdx()}"/>
		</form>
			<div class="btnGroup">
				<button class="btn" onClick="cancel()">취소</button>
				<button class="btn" onClick="deleteBoard()">삭제</button>
				<button class="btn" onClick="update()">등록</button>
			</div>
	</div>
	<div class="space"></div>
<script type="text/javascript">
	function cancel() 
	{
		location.href = `${pageContext.request.contextPath}/board/list`;
	}
	
	function deleteBoard()
	{
		if (confirm("정말 삭제하시겠습니까?") == true) 
		{	
			$.get(`/board/delete?idx=${bVO.getBrdIdx()}`, function(result)
			{
				result = 1 ? alert("삭제 성공") : alert("삭제 실패");
				location.href = `${pageContext.request.contextPath}/board/list`;
			});
		}
		else 
		{
			return;
		}
	}
	
	function update() 
	{
		const updateForm = $("#updateForm");
		const textArea = $("#textarea");
		const title = $("#title");
		
		if (writeValidation(title, 30) && writeValidation(textArea, 100)) 
		{
			updateForm.submit();
		}
	}
	
	function writeValidation(target, limit)
	{
		if(target.val().length == 0)
		{
			alert('내용을 입력해주세요.');
			target.focus();
			return false;
		}
		else if (target.val().length <= limit)
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
	
	function imageChanger(file)
	{
		const imagePreview = $("#imagePreview");
	
		let reader = new FileReader();
	
		reader.readAsDataURL(event.target.files[0]);
		reader.onload = (function(e)
		{
			imagePreview.attr("src", e.target.result);
		})
	}
	
	function getFileName()
	{
		const file = `${bVO.getImagePath()}`.split('/');
	
		if (file[file.length - 1] != 'default.png') 
		{
			$("#filename").text(file[file.length - 1]);
		}
	}
	
	function init()
	{
		getFileName();
	}
	
	init();
</script>
</body>
</html>