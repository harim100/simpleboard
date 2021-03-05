<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/join.css">
</head>
<body>
<section id="container">
			<form action="/create/member" method="post" id="regForm">
				<label class="control-label" for="Id">아이디</label>
				<input class="js-id" type="text" id="Id" name="Id" />
				<button class="js-idcheck" type="button" id="idChk" value="N">중복확인</button>
					<div class="js-warning"></div>
				<label class="control-label" for="Pw">패스워드</label>
				<input class="form-control" type="password" id="Pw" name="Pw" />
				<label class="control-label" for="CustomerName">성명</label>
				<input class="form-control" type="text" id="CustomerName" name="CustomerName" />
				<button class="cencle btn btn-danger" type="button">취소</button>
				<input type="submit" value="회원가입"/>
			</form>
		</section>
		 <div class="wrap wd668">
      <div class="container">
        <div class="form_txtInput">
          <h2 class="sub_tit_txt">회원가입</h2>
          <p class="exTxt">회원가입시 이메일 인증을 반드시 진행하셔야 합니다.</p>
          <div class="join_form">
            <table>
              <colgroup>
                <col width="30%"/>
                <col width="auto"/>
              </colgroup>
              <tbody>
                <tr>
                  <th><span>아이디</span></th>
                  <td><input type="text" placeholder="ID 를 입력하세요."></td>
                </tr>
                <tr>
                  <th><span>이름</span></th>
                  <td><input type="text" placeholder=""></td>
                </tr>
                <tr>
                  <th><span>비밀번호</span></th>
                  <td><input type="text" placeholder="비밀번호를 입력해주세요."></td>
                </tr>
                <tr>
                  <th><span>비밀번호 확인</span></th>
                  <td><input type="text" placeholder="비밀번호를 확인하세요"></td>
                </tr>
                <tr>
                  <th><span>휴대폰 번호</span></th>
                  <td><input type="text" placeholder="ID 를 입력하세요."></td>
                </tr>
              </tbody>
            </table>
          </div><!-- join_form E  -->
          <div class="btn_wrap">
            <a href="javascript:;">다음</a>
          </div>
        </div> <!-- form_txtInput E -->
      </div><!-- content E-->
    </div> <!-- container E -->
	</body>
	
	 <script src="./resources/join.js"></script>
</html>