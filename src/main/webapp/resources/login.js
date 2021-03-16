const joinBtn = $("#joinBtn");
const cbId = $("#cbId");
const loginForm = $("#loginForm");
const loginId = $("#loginId");

window.onload = function() {
	cookieId = getCookieValue("loginId");
	if(cookieId != null)
	{
		loginId.val(cookieId);
		cbId.prop("checked", true);		
	}
}

joinBtn.click(function (){
	location.href = "/join";
});

loginForm.submit(function(){
	if(cbId.prop("checked"))
	{
		setCookie("loginId", loginId.val(), 7);
	}
});

function setCookie(name, value, period){
	var date = new Date();
		date.setTime(date.getTime() + period*24*60*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function getCookieValue(key){
  var cookieKey = key + "="; 
  var result = "";
  const cookieArr = document.cookie.split(";");
  for(var i = 0; i < cookieArr.length; i++) {
	if(cookieArr[i][0] === " ") 
	{
	  cookieArr[i] = cookieArr[i].substring(1);
	}
	
	if(cookieArr[i].indexOf(cookieKey) === 0) 
	{
	  result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
	  return result;
	}
  }
  return result;
}
