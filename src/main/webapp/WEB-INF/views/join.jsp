<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="./resources/join.css">
</head>
<body>
	<div class="container">
		<h2>회원가입</h2>
		<form action="/create/member" method="post" id="joinForm" class="joinForm">
			<table>
				<colgroup>
					<col width="30%" />
					<col width="auto" />
				</colgroup>
				<tbody>
					<tr>
						<th><span class="required"> 아이디</span></th>
						<td>
							<input id="cuId" name="Id" type="text"/>
							<div id="warningTxt" ></div></td>
						<td><a id="idCheckBtn" href="javascript:checkId()">중복확인</a>
						</td>
					</tr>
					<tr>
						<th><span class="required"> 비밀번호</span></th>
						<td><input id="cuPw" name="Pw" type="password"/></td>
					</tr>
					<tr>
						<th><span class="required"> 비밀번호 확인</span></th>
						<td><input id="cuPw2" type="password"/></td>
					</tr>
					<tr>
						<th><span class="required"> 이름</span></th>
						<td><input id="cuName" name="CustomerName" type="text"/></td>
					</tr>
					<tr>
						<th><span>연락처</span></th>
						<td><input id="cuCellNum" name="CustomerPhone" type="text"/></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btn_wrap">
			<a id="cancelJoinBtn" href="/login">취소</a> 
			<a id="doJoinBtn" href="javascript:handleSubmit(event)">가입</a>
		</div>
	</div>
</body>
<script src="./resources/join.js"></script>
</html>