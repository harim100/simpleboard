const joinBtn = document.querySelector(".joinBtn");
const cbId = document.querySelector(".cbId");
const loginForm = document.querySelector(".loginForm");
const memberId = document.querySelector(".js-id");

loginForm.addEventListener("submit", handleSubmit);
joinBtn.addEventListener("click", handleJoin);


window.onload = function() {
	cookieId = getCookieValue("memberId");
	console.log("cookieId ===> " + cookieId);
	if(cookieId != null){
		memberId.value = cookieId;
		cbId.checked = true;		
	}
}

function handleJoin(){
	location.href = "/join";
}

function handleSubmit(){
	if(cbId.checked){
		console.log("checked ==>" + cbId.checked);
		setCookie("memberId", memberId.value, periode)
	}
	loginForm.submit();
}

function setCookie(name, value, date){
	var date = new Date();
		date.setTime(date.getTime() + periode*24*60*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

const getCookieValue = (key) => {
  let cookieKey = key + "="; 
  let result = "";
  const cookieArr = document.cookie.split(";");
  
  for(let i = 0; i < cookieArr.length; i++) {
    if(cookieArr[i][0] === " ") {
      cookieArr[i] = cookieArr[i].substring(1);
    }
    
    if(cookieArr[i].indexOf(cookieKey) === 0) {
      result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
      return result;
    }
  }
  return result;
}