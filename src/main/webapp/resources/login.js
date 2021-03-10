const joinBtn = document.querySelector(".joinBtn");
const cbId = document.querySelector(".cbId");
const loginForm = document.querySelector(".loginForm");
const memberId = document.querySelector(".js-id");

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

function handleSubmit(event){
    event.preventDefault();
	if(cbId.checked){
		console.log("checked ==>" + cbId.checked);
		setCookie("memberId", memberId.value, 7)
	}
	loginForm.submit();
}

function setCookie(name, value, period){
	var date = new Date();
		date.setTime(date.getTime() + period*24*60*60*1000);
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

loginForm.addEventListener("submit", handleSubmit, true);
joinBtn.addEventListener("click", handleJoin);
