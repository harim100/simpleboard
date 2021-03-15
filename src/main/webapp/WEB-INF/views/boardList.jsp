<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="../../resources/boardList.css">
</head>
<body>

<div class="container">
	<div class="navBar">
		<span> ${customerName} | </span>
		<a href="/logout"><span class="logoutTxt">로그아웃 </span></a>
	</div>
	<div class="space"></div>
	<div class="btnGroup">
	 <button class="js-deleteBtn btn" id="deleteBtn" onClick="selDeleteGroup()">삭제</button>
	 <button class="js-insertBtn btn" id="insertBtn" onClick="insert()">등록</button>
	</div>
<form>
	<table id="bListTable" class="bListTable">
		 <colgroup>
		      <col width="5%"/>
		      <col width="10%"/>
		      <col width="40%"/>
		      <col width="25%"/>
		      <col width="20%"/>
	     </colgroup>
		<tr>
		    <th>
		    	<input type="checkbox" id="selectAll" class="selectAll"/>
		    </th>
		    <th>이미지</th>
		    <th>제목</th>
		    <th>등록일</th>
		    <th>수정/삭제</th>
		</tr>
		<c:forEach var = "bList" items="${bList}">
			<tr>
			<td>
				<input type="checkbox" name="cb" class="cb" value="${bList.CustomerNum}" onChange="cbValidation(this)"/>
				<input type="hidden" class="cbIdx" value="${bList.BrdIdx}">
			</td>
			<!-- 이미지 -->
			<td><img src="${bList.ImagePath}" width="50" height="50"></td>
			<!-- 제목 -->
				<td class="tdTitle">
				<c:choose>
		       	 	<c:when test="${fn:length(bList.Title) gt 11}">
		        		<c:out value="${fn:substring(bList.Title, 0, 10)}"/>...
		        	</c:when>
		        	<c:otherwise>
		        		<c:out value="${bList.Title}"/>
		        	</c:otherwise>
				</c:choose>
				</td>
			<!-- 등록일 -->
			<td>${bList.InsDate}</td>
			<!-- 버튼 -->
			<td>
				<div class="tableBtnGroup">
					<div class="btns" name="tableBtns">
						<button class="modifyOneBtn tableBtn modify" onclick="modify(this, event)" value="${bList.BrdIdx}">수정</button>
						<button class="deleteOneBtn tableBtn" onclick="deleteBoard(this, event)" value="${bList.BrdIdx}">삭제</button>
					</div>
					<input type="hidden" value="${bList.CustomerNum}" name="customerNum">
				</div>
			</td>
			</tr>
		</c:forEach>
	</table>
</form>
	<ul> 
	</ul>
	<table class="pagination">
		<tr>
			<c:if test="${pagination.isBefore}">
				<td><a href="${pageContext.request.contextPath}/board/list?page=${pagination.startPage}">이전</a></td>
			</c:if>
			<c:forEach var = "curPage" items="${pagination.pages}">
				<td><a href="${pageContext.request.contextPath}/board/list?page=${curPage+1}">${curPage+1}</a></td>
			</c:forEach> 
			<c:if test="${pagination.isNext}">
				<td><a href="${pageContext.request.contextPath}/board/list?page=${pagination.endPage+1}">다음</a></td>
			</c:if>
		</tr>
	</table>
</div>
<div class="space"></div>
<script>

const cbSelectAll = $("#selectAll");
const cbList = $("[name=cb]");
const tableBtns = $("[name=tableBtns]");
const cbArr = [];

function init(){
	let customerNum = "${customerNumber}";
	customerNum.length <= 0 ? (
		alert("로그인이 필요합니다"),
		location.href ="/login"
	) : hideButtons();
}

function hideButtons(obj){
	console.log(obj);
	var authorNum = obj.nextElementSibling.value;
	if("${customerNumber}"!=authorNum) {
		obj.style.display = "none";
	}
} 

function cbValidation(cb){
	let author = cb.value;
		if(author != "${customerNumber}"){
			cb.checked = false;
		} else {
			cbArr.push(cb);
		}
	}

function selDeleteGroup() {
	cbArr.length > 0 ? confirmDelete(cbArr) : alert("삭제할 글을 선택해 주세요");
}

function deleteBoard(btn) {
	event.preventDefault();

	if (confirm("정말 삭제하시겠습니까?") == true) {
		makeForm("idx", "hidden", btn.value, "post", "/board/delete").submit();
	} else {
		return;
	}
}

function isChecked(cb) {
	return cb.checked === true;
}

function confirmDelete(cbArr) {
	if (confirm("정말 삭제하시겠습니까?") == true) {
		var idxArr = [];

		for (var i=0 ; i < cbArr.length ; i++){
			idxArr.push(cbArr[i].nextElementSibling.value);
		}
		
		var obj = {idxArr : idxArr};
		
		$.ajax({
			url: '/board/delete/group'
			, data: obj
			, method: 'get'
			, success: function(data) {
				data = 1 ? alert("삭제 성공") : alert("삭제 실패");
				location.href = "javascript:location.reload()";
			}, error: function(e) {
				alert("error" + e);
			}
		});//end of ajax
	} else {
		return;
	}
}

function makeForm(name, type, value, method, action) {
	const form = document.createElement("form");
	const input = document.createElement("input");
	input.name = name;
	input.type = type;
	input.value = value;

	form.appendChild(input);

	form.charset = "UTF-8";
	form.method = method;
	form.action = action;

	document.body.appendChild(form);

	return form;
}

function modify(obj, event) {
	event.preventDefault();
	location.href = '/board/view?idx=' + obj.value;
}

function insert() {
	location.href = '/board/write';
}

cbSelectAll.change(function() {
	
	if(this.checked) {
		for (let cbs of cbList) {
			cbs.checked = true;
			cbValidation(cbs);
		} 
	} else {
		for (let cbs of cbList) {
			cbs.checked = false;
		}
	}
});


init();

</script>
</body>
</html>