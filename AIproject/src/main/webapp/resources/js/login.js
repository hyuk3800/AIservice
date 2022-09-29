const userId = document.querySelector("#ID");
const password = document.querySelector("#Password")
const loginBTN = document.querySelector("#loginBTN")


const postreq = () => {
	console.log("클릭")
//	span.innerText = "";
	let xhr;
	if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE 6 이하
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState);
		if(xhr.readyState == 4){
			console.log(xhr.status);
			if(xhr.status == 200){

			}
		}
	}
	const id = userId.value;
	const pwd = password.value;
	xhr.open("POST", "/biz/login.do", true);
	xhr.setRequestHeader("Content-type", "application/json");
	let user = {
		userid : id,
		pwd : pwd
	};

	// console.log(user);
	xhr.send(JSON.stringify(user));
	
};

loginBTN.addEventListener('click', postreq);