const container = document.querySelector("#container");

const userid = document.querySelector("#loginID");
const userPassword = document.querySelector("#loginPassword");
const loginBTN = document.querySelector("#loginBTN");

const message = document.querySelector("#message")

const singUpID = document.querySelector("#singUpID");
const singUpName = document.querySelector("#singUpName");
const singUpPwd = document.querySelector("#singUpPwd");
const singUpCPwd = document.querySelector("#singUpCPwd");
const singUpBtn = document.querySelector("#SignUpBtn");


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
			// console.log("이거");
			if(xhr.status == 200){
				//console.log(xhr.response);
				if(xhr.response == "True"){
					location.href = "home.do";					
				}
				else{
					message.innerText="로그인 정보가 일치하지 않습니다";				
				}
				
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


const postreqAdd = () => {
	console.log("hi1");
	if (singUpPwd.value != singUpCPwd.value){
		alert("비밀번호가 일치하지 않습니다");
		Event.preventDefault();
	}
	
	console.log("hi2");
	
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
			// console.log("이거");
			if(xhr.status == 200){
				console.log(xhr.response);
				if(xhr.response == "false"){
					alert("회원가입이 정상적으로 처리되지 않았습니다");
					Event.preventDefault();
				}
				else{
					alert("회원가입이 정상적으로 처리되었습니다");
					location.reload();	
				}
			}
		}
	}
	const id = singUpID.value;
	const username = singUpName.value;
	const pwd = singUpCPwd.value;
	xhr.open("POST", "/biz/addUser.do", true);
	xhr.setRequestHeader("Content-type", "application/json");
	let user = {
		userid : id,
		username : username,
		pwd : pwd
	};

	// console.log(user);
	//
	xhr.send(JSON.stringify(user));
};


loginBTN.addEventListener('click', postreq);

singUpBtn.addEventListener('click', postreqAdd);
