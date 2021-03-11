const cbSelectAll = document.querySelector(".selectAll");
const cbList = document.querySelectorAll(".cb");
const tableBtnGroup = document.querySelectorAll(".tableBtnGroup");

function deleteBoard(btn){
	event.preventDefault();
	
	 if(confirm("정말 삭제하시겠습니까?") == true){
		makeForm("idx", "hidden", btn.value, "post", "/board/delete").submit();
	 }
	 else{
	     return ;
	 }
	
}

function selDeleteGroup(){
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
		deleteChecked(checkedCbs);
	    alert("삭제되었습니다");
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
	
	makeForm("idxArr", "hidden", idxArr, "post", "/board/delete/group").submit();
}

function makeForm(name, type, value, method, action){
	const form = document.createElement("form");
	const input = document.createElement("input");
	input.name = name;
	input.type = type;
	input.value = value;
	
	form.appendChild(input);
	
	console.log(form);
	
	form.charset = "UTF-8";
	form.method = method;
	form.action = action;
	
	document.body.appendChild(form); 
	
	return form;
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
