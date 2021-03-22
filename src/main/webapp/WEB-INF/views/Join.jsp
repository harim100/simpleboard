<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css">
</head>
<body>
	<div class="container">
		<h2>회원가입</h2>
		<form:form modelAttribute="mDto" id="joinForm" cssClass="joinForm" action="/insert/member" method="post">
			<table>
				<colgroup>
					<col width="30%" />
					<col width="auto" />
				</colgroup>
				<tbody>
					<tr>
						<th><span class="required"> 아이디</span></th>
						<td>
							<form:input path="id" id="cuId" type="text" maxlength="20"/>
							<form:errors path="id" cssClass="error"/>
							<div id="warningTxt" ></div>
						</td>
						<td><a id="idCheckBtn" href="javascript:checkId()">중복확인</a></td>
					</tr>
					<tr>
						<th><span class="required"> 비밀번호</span></th>
						<td>
							<form:input path="pw" id="cuPw" type="password" maxlength="20"/>
							<form:errors path="pw" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<th><span class="required"> 비밀번호 확인</span></th>
						<td><input id="cuPw2" type="password"/></td>
					</tr>
					<tr>
						<th><span class="required"> 이름</span></th>
						<td>
							<form:input path="customer_name" id="cuName" type="text" maxlength="60"/>
							<form:errors path="customer_name" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<th><span>연락처</span></th>
						<td>
							<form:input path="cell_num" id="cuCellNum" type="text" maxlength="15"/>
							<form:errors path="cell_num" cssClass="error"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<div class="btn_wrap">
			<a class="cancel" id="cancelJoinBtn" href="/login">취소</a> 
			<a class="join" id="doJoinBtn" href="javascript:handleSubmit()">가입</a>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/join.js"></script>
</body>
</html>