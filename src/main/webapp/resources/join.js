/**
 * 
 */
const idCheckBtn = document.querySelector(".js-idcheck");
const warning = document.querySelector(".js-warning");
const idForm = document.querySelector(".js-id");

function checkId(event){
	console.log("중복체크 클릭");
	let requestedId = idForm.value;
	
	console.log("입력한 아이디 => " + requestedId);
	
	const form = new FormData();
	form.append('requestedId', requestedId);
	fetch('/check-id', {
	  method: 'POST',
	  body: form
	}).then(function(response){
       return response.text();
    }).then(function(text){
		console.log("결과:: " + text);
		if(text != '0'){
			warning.innerHTML = "중복된 아이디가 있습니다."
		} else {
			warning.innerHTML = "사용가능한 아이디 입니다."
		}
	});
}

function init(){
	 idCheckBtn.addEventListener("click", checkId);
}

init();