<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="../../resources/login.css">
	<script>
	const loginForm = document.querySelector("#loginForm");
	const warning = document.querySelector(".js-warning");
	
	function init(){
		loginForm.addEventListener("submit", login);
	}
	function login(){
		loginForm.submit();
		if(${msg} == "fail"){
			warning.innerText("아이디 또는 비밀번호가 틀렸습니다.");
		} else {
			alert("로그인 성공");
		}
	}	
	
	init();
	</script>
</head>
<body>
<h1>
	로그인
</h1>
	<div class="container">
	<form action="/login" method="post" id="loginForm">
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
			<label><input type="checkbox" name="cb_id" value="data">아이디 저장하기</label>
			</td>
		</tr>
		<tr>
			<td>
			<input class="loginBtn" type="submit" value="로그인" />
			</td>
		</tr>
	</table>
			<div class="js-warning"></div>
	</form>
	<div class="js-warning"></div>
	<button class="joinBtn"> 회원가입 </button>
	</div>
</body>
</html>
