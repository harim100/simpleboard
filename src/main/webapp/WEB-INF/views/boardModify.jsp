<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../../resources/boardModify.css">
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
					<textarea id="textarea" name="content" 
					class="input textarea" cols="50" rows="10">${bVO.getContent()}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<input type="file" accept=".gif, .jpg, .png" name="imagePath" id="fileUploadBtn" class="fileUploadBtn" onChange="imageChanger(this)"/> 
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
			<div class="btnGroup">
				<button class="btn" onclick="cancel(event)">취소</button>
				<button class="deleteBtn btn" onClick="deleteBoard(event)">삭제</button>
				<button class="insertBtn btn" onClick="update(event)">등록</button>
			</div>
		</form>
	</div>
	<div class="space"></div>
	<script>
		const updateForm = $("#updateForm");
		const textArea = $("#textarea");
		const title = $("#title");

		function cancel(event) {
			event.preventDefault();
			location.href = `${pageContext.request.contextPath}/board/list`;
		}

		function deleteBoard(event) {
			event.preventDefault();
			if (confirm("정말 삭제하시겠습니까?") == true) {
				$
						.ajax({
							url : `/board/delete?idx=${bVO.getBrdIdx()}`,
							data : obj,
							method : 'GET',
							success : function(data) {
								text = 1 ? alert("삭제 성공") : alert("삭제 실패");
								location.href = `${pageContext.request.contextPath}/board/list`;
							},
							error : function(e) {
								alert("error" + e);
							}
						});//end of ajax
			} else {
				return;
			}
		}

		function update(event) {
			event.preventDefault();
			if (writeValidation(title, 30) && writeValidation(textArea, 100)) {
				updateForm.submit();
			}
		}

		function writeValidation(what, limit) {
			if (what.val().length <= limit)
				return true;
			else {
				alert('최대 ' + limit + '자 까지만 입력가능합니다');
				what.val(what.val().substring(0, limit));
				what.focus();
				return false;
			}
		}

		function imageChanger(file) {
			const imagePreview = $("#imagePreview");

			let reader = new FileReader();

			reader.readAsDataURL(event.target.files[0]);
			reader.onload = (function(e) {
				imagePreview.attr("src", e.target.result);
			})
		}

		function getFileName() {
			const file = `${bVO.getImagePath()}`.split('/');

			if (file[file.length - 1] != 'default.png') {
				$("#filename").text(file[file.length - 1]);
			}
		}

		function init() {
			getFileName();
		}
		init();
	</script>
</body>
</html>