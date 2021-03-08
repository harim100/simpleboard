/**
 * 
 */
const cbSelectAll = document.querySelector(".selectAll");
const cbList = document.querySelectorAll(".cb");


cbSelectAll.addEventListener("change", selectAll);

function selectAll(){
	if (this.checked) {
		for(let cbs of cbList){
			cbs.checked = true;
		}
  } else {
		for(let cbs of cbList){
			cbs.checked = false;
		}
  }
}