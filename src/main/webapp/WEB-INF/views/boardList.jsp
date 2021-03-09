<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
body {height:100%; font-family: 'Noto Sans KR';}

.container{
	width: 60%;
	margin: 0 auto 0 auto;
} 
.bListTable{
	width: 100%;
	text-align: center;
}
.tdTitle{
	text-align: left;
	padding-left: 2em;
}
.space{
	margin-top: 10em;
}
.bListTable, .bListTable th {
	border: 1px solid #ececec;
	border-collapse: collapse;
}

.navBar{
	text-align: right;
}

.btnGroup{
	float: right;
	margin-bottom: 0.5em;
}

.btn{
	border : 1px solid #ececec;
	padding: 1em 3em 1em 3em;
	font-weight: bold;
}

.tableBtn{
	border : 1px solid #ececec;
	padding: 1em 4em 1em 4em;
	font-weight: bold;
	margin: 0.2em;
}
.modifyOneBtn{
	background: mediumpurple;
	margin-bottom: 0.2em;
}
.js-deleteBtn, .deleteOneBtn{
	background: mistyrose;
}
.js-insertBtn{
	background: powderblue;
}
</style>
</head>
<body>

<div class="container">
	<div class="navBar">
		<span> ${customerName} | </span>
		<a href="/logout"><span class="logoutTxt">로그아웃 </span></a>
	</div>
	<div class="space"></div>
	<div class="btnGroup">
	 <button class="js-deleteBtn btn" onClick="deleteGroup()">삭제</button>
	 <button class="js-insertBtn btn" onClick="insert()">등록</button>
	</div>
<form>
	<table class="bListTable">
		 <colgroup>
		      <col width="5%"/>
		      <col width="10%"/>
		      <col width="40%"/>
		      <col width="25%"/>
		      <col width="20%"/>
	     </colgroup>
		<tr>
		    <th>
		    	<input type="checkbox" class="selectAll"/>
		    </th>
		    <th>이미지</th>
		    <th>제목</th>
		    <th>등록일</th>
		    <th>수정/삭제</th>
		</tr>
		<c:forEach var = "bList" items="${bList}">
			<tr>
			<td>		    	
				<input type="checkbox" class="cb" value="${bList.CustomerNum}" onChange="cbValidation(this)"/>
			</td>
			<!-- 이미지 -->
			<td><img src="${bList.ImagePath}" width="50" height="50"></td>
			<!-- 제목 -->
				<td class="tdTitle">
				<c:choose>
		       	 	<c:when test="${fn:length(bList.Title) gt 11}">
		        		<a href='/board/view?idx=${bList.BrdIdx}'> 
		        		<c:out value="${fn:substring(bList.Title, 0, 10)}"/>...
		        		</a>
		        	</c:when>
		        	<c:otherwise>
		        		<a href='/board/view?idx=${bList.BrdIdx}'> <c:out value="${bList.Title}"/></a>
		        	</c:otherwise>
				</c:choose>
				</td>
			<!-- 등록일 -->
			<td>${bList.InsDate}</td>
			<!-- 버튼 -->
			<td>
				<div class="tableBtnGroup">
					<div class="btns" name="tableBtns">
						<button class="modifyOneBtn tableBtn modify" onClick="modify(this, event)" value="${bList.BrdIdx}">수정</button>
						<button class="deleteOneBtn tableBtn" onClick="deleteBoard(this, event)" value="${bList.BrdIdx}">삭제</button>
					</div>
					<input type="hidden" value="${bList.CustomerNum}" name="customerNum">
				</div>
			</td>
			</tr>
		</c:forEach>
	</table>
</form>
</div>
<div class="space"></div>
<script>
console.log(`customerNumber==> ${customerNumber}`);

const cbSelectAll = document.querySelector(".selectAll");
const cbArr = document.querySelectorAll(".cb");
const tableBtnGroup = document.querySelectorAll(".tableBtnGroup");

cbSelectAll.addEventListener("change", selectAll);

function deleteBoard(btn, event){
	event.preventDefault();
	const form = new FormData();
	form.append('idx', btn.value);
	fetch('/board/delete', {
		  method: 'POST',
		  body: form
		}).then(function(response){
	       return response.text();
	    }).then(function(text){
			console.log("결과:: " + text);
			text = 1 ? alert("삭제 성공") : alert("삭제 실패");
			if(text = 1) btn.closest("tr").style.display = 'none';
		});
}

function deleteGroup(){
	console.log(cbArr); //<-- nodeList
	const checked = cbArr.find(isChecked);	
	cbSelectAll.checked = true || checked  ? deleteChecked() : alert("삭제할 글을 선택해 주세요");
}

function isChecked(cb){
	return cb.checked === true;
}

function modify(obj, event){
	event.preventDefault();
	location.href = '/board/view?idx='+obj.value;
}

function insert(){
	location.href = '/board/write';
}

function hideButtons(){
	tableBtnGroup.forEach(function(element, index, array){
		let buttons = element.children.tableBtns;
		let authorNum = element.children.customerNum.value;
		if(${customerNumber}!=authorNum) {
			buttons.style.display = 'none';
		}
	});
}

function cbValidation(cb){
	let author = cb.value;
		if(author != ${customerNumber}){
			cb.checked = false;
		}
}

function selectAll(){
	if (this.checked) {
		for(let cbs of cbArr){
			cbs.checked = true;
			cbValidation(cbs);
		}
  } else {
		for(let cbs of cbArr){
			cbs.checked = false;
		}
  }
}

function init(){
	hideButtons();
}

init();
</script>
</body>
</html>