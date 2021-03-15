<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>로그인</title>
	<link rel="stylesheet" href="../../resources/login.css">
	<script>
	
	console.log("${msg}")
	</script>
</head>
<body>
	<div class="container">
	<h1>
		로그인
	</h1>
	<form action="/do/login" method="post" id="loginForm" class="loginForm">
	<table>
		<tr>
		<td>
			<input class="js-id form-control" type="text" id="Id" name="Id" placeholder="아이디"/>
		</td>
		</tr>	
		<tr>
		<td>
			<input class="form-control" type="password" id="Pw" name="Pw" placeholder="패스워드" />
		</td>
		</tr>
		<tr>
			<td>
			<label><input type="checkbox" id="cbId" name="cbId" class="cbId">아이디 저장하기</label>
			</td>
		</tr>
		<tr>
			<td>
			<input id="loginBtn" class="loginBtn btn" type="submit" value="로그인" />
			</td>
		</tr>
	</table>
	<c:if test= "${msg == false}">
		<div style="color : red;">
		아이디, 비밀번호가 틀렸습니다.	
		</div>
	</c:if>
	</form>
	<div id="loginWarning" class="js-warning"></div>
	<button id="joinBtn" class="joinBtn btn"> 회원가입 </button>
	</div>

<script src="./resources/login.js"></script>
</body>
</html>
