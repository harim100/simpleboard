<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>게시판 수정</title>
<link rel="stylesheet" href="../../resources/css/boardModify.css">
<script type="text/javascript">
	const imagePreview = $("#imagePreview");

	function cancel() 
	{
		location.href = '/board/list';
	}
	
	function deleteBoard()
	{
		if (confirm('<spring:message code="Board.delete.confirm"/>') == true) 
		{	
			$.get(`/board/delete?brdIdx=${bDto.getBrd_idx()}`, function(result)
			{
				result = 1 ? alert("삭제 성공") : alert("삭제 실패");
				location.href = '/board/list';
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
			alert('<spring:message code="Board.empty.error"/>');
			target.focus();
			return false;
		}
		else if (target.val().length <= limit)
		{
			return true;
		}
		else 
		{
			var sizeErroMsg = '<spring:message code="Board.length.error" arguments='###' />';
			sizeErroMsg = sizeErroMsg.replace('###', limit);
			alert(sizeErroMsg);
			target.val(target.val().substring(0, limit));
			target.focus();
			return false;
		}
	}
	
	function imageChanger(file)
	{
		let reader = new FileReader();
	
		reader.readAsDataURL(event.target.files[0]);
		reader.onload = (function(e)
		{
			imagePreview.attr("src", e.target.result);
		})
	}
	
	function getFileName()
	{
		const file = `${bDto.getImage_path()}`.split('/');
		const filename = $("#filename");
		const deleteImageBtn = $('#deleteImageBtn');
		const oriImagePath = $('#oriImagePath');
	
		if (file[file.length - 1] != 'default.png') 
		{
			filename.text(file[file.length - 1]);
			deleteImageBtn.css("display", "inline-block");
			deleteImageBtn.text('X');
			deleteImageBtn.click(function ()
			{
				filename.text("");
				imagePreview.attr("src", "/simpleboard/upload/default.png");
				deleteImageBtn.css("display", "none");
				oriImagePath.val("/simpleboard/upload/default.png");
			});
		}
	}
	
	function checkAuth()
	{
		if(${bDto.customer_no} != ${sessionScope.customer_no})
		{
			alert('<spring:message code="Board.auth.error"/>');
			location.href="/board/list";
		}
	}
	
	function init()
	{
		checkAuth();
		getFileName();
	}
	
	init();
</script>
</head>
<body>
	<div class="container">
		<form id="updateForm" action="/board/update" method="post" enctype="multipart/form-data">
			<table class="bTable">
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				<tr>
					<th>제목</th>
					<td><input id="title" name="title" class="input title" type="text" value="${bDto.getTitle()}" maxlength="30"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<textarea id="textarea" name="content" class="input textarea" cols="50" rows="10" maxlength="100">${bDto.getContent()}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<input type="file" accept=".gif, .jpg, .png" name="imageFile" id="fileUploadBtn" onChange="imageChanger(this)"/> 
						<input type="hidden" id="oriImagePath" name="oriImagePath" value="${bDto.getImage_path()}"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div>
							<span id="filename" class="filename"></span>
							<span id="deleteImageBtn"></span>
						</div>
						<img id="imagePreview" src="${bDto.getImage_path()}" width="50" height="50"/>
					</td>
				</tr>
			</table>
			<input type="hidden" name="brd_idx" value="${bDto.getBrd_idx()}"/>
		</form>
		<div class="btnGroup">
			<button class="btn" onClick="cancel()">취소</button>
			<button class="btn" onClick="deleteBoard()">삭제</button>
			<button class="btn" onClick="update()">등록</button>
		</div>
	</div>
</body>
</html>