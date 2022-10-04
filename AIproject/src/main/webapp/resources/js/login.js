const container = document.querySelector("#container");

const userid = document.querySelector("#loginID");
const userPassword = document.querySelector("#loginPassword");
const loginBTN = document.querySelector("#loginBTN");

// let container = document.getElementById('container')
 toggle = () => {
   container.classList.toggle('sign-in')
   container.classList.toggle('sign-up')
 }
 setTimeout(() => {
   container.classList.add('sign-in')
 }, 200)


function gohome() {
	location.href="home.html";
}

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
			console.log("이거");
			if(xhr.status == 200){
				console.log(xhr.response);
				location.href = xhr.response;
			}
		}
	}
	const id = userid.value;
	const pwd = userPassword.value;
	xhr.open("POST", "/biz/login.do", true);
	xhr.setRequestHeader("Content-type", "application/json");
	let user = {
		userid : id,
		pwd : pwd
	};

	// console.log(user);
	//
	xhr.send(JSON.stringify(user));
	
};

loginBTN.addEventListener('click', postreq);
