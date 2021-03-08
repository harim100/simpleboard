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
	 <button class="js-deleteBtn btn" onClick="javascript:delete()">삭제</button>
	 <button class="js-insertBtn btn" onClick="javascript:insert()">등록</button>
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
				<input type="checkbox" class="cb"/>
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
				<button class="modifyOneBtn tableBtn modify">수정</button>
				<button class="deleteOneBtn tableBtn">삭제</button>
			</td>
			</tr>
		</c:forEach>
	</table>
</form>
</div>
<div class="space"></div>
<script src="../resources/boardList.js"></script>
</body>
</html>