<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="./resources/join.css">
</head>
<body>
      <div class="container">
        <div class="form_txtInput">
          <h2 class="sub_tit_txt">회원가입</h2>
          <div class="join_form">
          <form action="/create/member" method="post" class="joinForm">
            <table>
              <colgroup>
                <col width="30%"/>
                <col width="auto"/>
              </colgroup>
              <tbody>
                <tr>
                  <th><span class="required"> 아이디</span></th> 
                  <td><input name="Id" type="text" class="js-id" placeholder="">
                  <div class="js-warning"></div>
                  </td>
                  <td>
                  <a class="js-idCheckBtn" href="javascript:checkId()">중복확인</a>
                  </td>
                </tr>
                <tr>
                  <th><span class="required"> 비밀번호</span></th>
                  <td><input name="Pw" class="js-pw" type="password" placeholder=""></td>
                </tr>
                <tr>
                  <th><span class="required"> 비밀번호 확인</span></th>
                  <td><input class="js-pw2" type="password" placeholder=""></td>
                </tr>
                <tr>
                  <th><span class="required"> 이름</span></th>
                  <td><input name="CustomerName" class="js-name" type="text" placeholder=""></td>
                </tr>
                <tr>
                  <th><span>연락처</span></th>
                  <td><input class="js-cell" name="CustomerPhone" type="text" placeholder=""></td>
                </tr>
              </tbody>
            </table>
            </form>
          </div><!-- join_form E  -->
          <div class="btn_wrap">
            <a class="cancelJoinBtn" href="/login">취소</a>
            <a class="doJoinBtn" href="javascript:handleSubmit()">가입</a>
          </div>
        </div> <!-- form_txtInput E -->
      </div><!-- content E-->
	</body>
	
	 <script src="./resources/join.js"></script>
</html>