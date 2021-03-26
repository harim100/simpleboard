<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>게시판 등록</title>
<link rel="stylesheet" href="../../resources/css/boardModify.css">
<script type="text/javascript">
	function writeValidation(target, limit) 
	{
		if(target.val().length == 0)
		{
			alert('<spring:message code="Board.empty.error"/>');
			target.focus();
			return false;
		}
		else if(target.val().length <= limit)
	    {
	    	return true;
	    }
	    else
	    {
			alert('<spring:message code="Board.length.error"/>');
			target.val(target.val().substring(0, limit));
			target.focus();
			return false;
		}
	}
	
	function cancel()
	{
	    location.href = '/board/list';
	}
	
	function insert()
	{
		const title = $("#title");
		const insertForm = $("#insertForm");
		const textArea = $("#textarea");
		
		if(writeValidation(title, 30) && writeValidation(textArea, 100))
		{
			insertForm.submit();
		}
	}
	
	function imageChanger(file)
	{
		imageValidation(file);
		const imagePreview = $("#imagePreview");
		
		let reader = new FileReader(); 
		reader.readAsDataURL(event.target.files[0]); 
		reader.onload = (function (e) 
		{
			imagePreview.attr("src", e.target.result);
	    })
	}
	
	function imageValidation(file)
	{
		const allowedImageTypes = ["image/gif", "image/jpeg", "image/png"];
		
		if(file.files[0].size > 5242880)
		{
			alert('<spring:message code="Fileupload.sizeError"/>');
		}
		
		if(!allowedImageTypes.includes(file.files[0].type))
		{
			alert('<spring:message code="Fileupload.typeError"/>');
		}
	}
	
</script>
</head>
<body>
	<div class="container">
		<form id="insertForm" name="insertForm" action="/board/insert" method="post" enctype="multipart/form-data">
			<table class="bTable">
				<colgroup>
					<col width="20%"/>
					<col width="80%"/>
		     	</colgroup>
				<tr>
					<th>제목</th>
					<td>
						<input type="hidden" value="${customer_no}" name="customer_no"/>
						<input id="title" name="title" class="input title" type="text" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="textarea" name="content" class="input textarea" cols="50" rows="10" maxlength="100"></textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<input type="file" accept=".gif, .jpg, .png" name="imageFile" id="fileUploadBtn" onChange="imageChanger(this)"/>
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
</body>
</html>