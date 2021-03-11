<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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
	 <button class="js-deleteBtn btn" onClick="selDeleteGroup()">삭제</button>
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
				<input type="hidden" class="cbIdx" value="${bList.BrdIdx}">
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
						<button class="modifyOneBtn tableBtn modify" onClick="modify(this)" value="${bList.BrdIdx}">수정</button>
						<button class="deleteOneBtn tableBtn" onClick="deleteBoard(this)" value="${bList.BrdIdx}">삭제</button>
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
<script src="../../resources/boardList.js"></script>
<script>
console.log("customerNumber = " + ${customerNumber})

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

function init(){
	hideButtons();
}

init();
</script>
</body>
</html>