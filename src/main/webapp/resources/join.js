/**
 * 
 */
const idCheckBtn = document.querySelector(".js-idCheckBtn");
const warning = document.querySelector(".js-warning"); 
const id = document.querySelector(".js-id");
const pw = document.querySelector(".js-pw");
const pw2 = document.querySelector(".js-pw2");
const name = document.querySelector(".js-name");

const idReg = /^[a-zA-Z0-9]{4,20}$/ 
const pwReg = /^[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,20}$/
// 특문, 영문, 숫자 필수
// const pwReg = new RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[$@!%*#?&])[a-z0-9$@!%*#?&]{8,20}$");

const nameReg = /^[a-zA-Z가-힣]{2,30}$/
const cellReg = /^[0-9]{1,15}$/

function checkId(event){
	console.log("중복체크 클릭");
	console.log("입력한 아이디:" + id.value);
	let requestedId = id.value;
	
	if(requestedId == null || requestedId.length == 0) alert("아이디를 입력해주세요")
	else {
	
	let idVaildation = check(idReg, id, "아이디는 최소 4자리~최대 20자리, 영문, 숫자만 허용됩니다.");
	console.log("입력한 아이디 => " + requestedId);
		if(idVaildation == true) {
			const form = new FormData();
			form.append('requestedId', requestedId);
			fetch('/check-id', {
			  method: 'POST',
			  body: form
			}).then(function(response){
		       return response.text();
		    }).then(function(text){
				console.log("결과:: " + text);
				if(text != '0') warning.innerHTML = "중복된 아이디가 있습니다."
				else			warning.innerHTML = "사용가능한 아이디 입니다."
			});
		}//end of idValidation
	}//end of else
}

function check(re, what, message) {
       if(re.test(what.value)) return true;
       else{
	       alert(message);
	       what.value = "";
	       what.focus();
	       return false;
		}
   }

function validation(){
	let idCheck = check(idReg, id, "아이디는 최소 4자리~최대 20자리, 영문, 숫자만 허용됩니다.");
	let pwCheck = check(pwReg, pw, "비밀번호는 길이 최소 8자리 ~ 최대 20자리, 영문/숫자/특문 조합만 허용됩니다.");
	if(pw.value != pw2.value){
		alert("비밀번호 확인이 일치하지 않습니다.");
		pwCheck = false;
	}
	let nameCheck = check(nameReg, name, "이름은 한글 또는 영문만 최소 두 자부터 30자 까지 허용됩니다.");
	
	if(idCheck && pwCheck && nameCheck) return true;
	else return false;
}

function handleSubmit(event){
	if (validation()){
	document.querySelector(".joinForm").submit();
	}
}