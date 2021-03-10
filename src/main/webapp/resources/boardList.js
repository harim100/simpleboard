const cbSelectAll = document.querySelector(".selectAll");
const cbList = document.querySelectorAll(".cb");
const tableBtnGroup = document.querySelectorAll(".tableBtnGroup");

function deleteBoard(btn){
	event.preventDefault();
	const form = new FormData();
	form.append('idx', btn.value);
	fetch('/board/delete', {
		  method: 'POST',
		  body: form
		}).then(function(response){
	       return response.text();
	    }).then(function(text){
			console.log("결과:: " + text);
			text = 1 ? alert("삭제 성공") : alert("삭제 실패");
			if(text = 1) btn.closest("tr").style.display = 'none';
		});
}

function deleteGroup(){
	event.preventDefault();
	const cbArr = Array.from(cbList);
	const checkedCbs = cbArr.filter(isChecked);	
	checkedCbs.length > 0  ? confirmDelete(checkedCbs) : alert("삭제할 글을 선택해 주세요");
}

function isChecked(cb){
	return cb.checked === true;
}

function confirmDelete(checkedCbs){
	 if(confirm("정말 삭제하시겠습니까?") == true){
	     alert("삭제되었습니다");
		 deleteChecked(checkedCbs);
	 }
	 else{
	     return ;
	 }
}

function deleteChecked(checkedCbs){
	event.preventDefault();
	const idxArr = [];
	
	for(let cbs of checkedCbs){
		idxArr.push(cbs.nextElementSibling.value);
	}
	console.log("idxArr:: " + idxArr);
	
	const form = document.createElement("form");
	const input = document.createElement("input");
	input.name = "idxArr";
	input.type = "hidden";
	input.value = idxArr;
	
	form.appendChild(input);
	
	console.log(form);
	
	form.charset = "UTF-8"
	form.method = "post"
	form.action = "/board/delete/group"
	
	document.body.appendChild(form); 
	
	form.submit(); 
}
 
function modify(obj){
	event.preventDefault();
	location.href = '/board/view?idx='+obj.value;
}

function insert(){
	location.href = '/board/write';
}

function selectAll(){
	if (this.checked) {
		for(let cbs of cbList){
			cbs.checked = true;
			cbValidation(cbs);
		}
  } else {
		for(let cbs of cbList){
			cbs.checked = false;
		}
  }
}

cbSelectAll.addEventListener("change", selectAll);
