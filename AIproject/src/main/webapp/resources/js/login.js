const container = document.querySelector("#container");

const FormDo = document.querySelectorAll(".formDo")

const userid = document.querySelector("#loginID");
const userPassword = document.querySelector("#loginPassword");
//페이지에서 찾아서 btn으로 지정할 것이다
const loginBTN = document.querySelector("#loginBTN");

// 로그인 정보 일치 않을시,
const loginMSG = document.querySelector("#loginMSG");
const singUpMSGI = document.querySelector("#singUpMSGI");
const singUpMSGP = document.querySelector("#singUpMSGP");

// 회원가입
const singUpID = document.querySelector("#singUpID");
const singUpName = document.querySelector("#singUpName");
const singUpPwd = document.querySelector("#singUpPwd");
const singUpCPwd = document.querySelector("#singUpCPwd");
const singUpBtn = document.querySelector("#SignUpBtn");



// let container = document.getElementById('container')
 toggle = () => {
   container.classList.toggle('sign-in')
   container.classList.toggle('sign-up')

   userid.value = "";
   userPassword.value = "";
		   
   singUpID.value = "";
   singUpName.value = "";
   singUpPwd.value = "";
   singUpCPwd.value = "";
   
   singUpMSGI.innerText = "";
   singUpMSGP.innerText = "";
 }
 setTimeout(() => {
   container.classList.add('sign-in')
 }, 200)

 
function shortcut(seq){
		Swal.fire({
		  title: '정말 로그인하지 않겠습니까?😭',
		  text: "다양한 혜택을 위해 로그인을 권장드립니다.",
      icon : 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3caaffc4',
		  cancelButtonColor: '#ffb6c1',
		  confirmButtonText: '바로이용',
		  cancelButtonText: '로그인'
		}).then((result) => {
		  if (result.value) {
        alert("AI 헤어디자이너 헤어캣을 바로 만나보세요!");
        location.href="home.do";
		  }
		})
};
 

const postreq = () => {
	console.log("클릭")
	loginMSG.innerText="";
	if (userPassword.value == "" || userid.value == ""){
		console.log("입력안함")
	}
	else{
//	span.innerText = "";
		let xhr;
		if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
	        xhr = new XMLHttpRequest();
	    } else if (window.ActiveXObject) { // IE 6 이하
	        xhr = new ActiveXObject("Microsoft.XMLHTTP");
	    }
		xhr.onreadystatechange = function(){
			console.log(xhr.readyState);
			// 통신완료 : 4단계
			if(xhr.readyState == 4){
				console.log(xhr.status);
				// console.log("이거");
				if(xhr.status == 200){
					// console.log(xhr.response);
					if(xhr.response == "true"){
						// alert("로그인 성공");
					    location.href = "home.do";					
					}
					else{
						loginMSG.innerText="※ 로그인 정보가 일치하지 않습니다";
					}
					
				}
			}
		}
		const id = userid.value;
		const pwd = userPassword.value;
		xhr.open("POST", "/biz/login.do", true);
		// json 타입으로 데이터 전송
		xhr.setRequestHeader("Content-type", "application/json");
		let user = {
			userid : id,
			pwd : pwd
		};
	
		// console.log(user);
		//
		xhr.send(JSON.stringify(user));
	}
};


const postSearch = () => {
	console.log("Blur");
	// id 공백이 아닐때
	if(singUpID.value != ""){
		console.log("Blur2");
		singUpMSGI.innerText = "";
		
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
					if(xhr.response == "overlap"){
						singUpMSGI.innerText = "※ 아이디가 중복되었습니다";
					}
				}
			}
		}
		const id = singUpID.value;
		xhr.open("POST", "/biz/searchID.do", true);
		xhr.setRequestHeader("Content-type", "application/json");
		let user = {
			userid : id
		};

		xhr.send(JSON.stringify(user));
	}
};

const testPWDSelect = () => {
	if (singUpPwd.value != singUpCPwd.value){
		singUpMSGP.innerText= "※ 비밀번호가 일치하지 않습니다";
	}
}

const postreqAdd = () => {
	singUpMSGP.innerText = "";
	testPWDSelect();
	console.log(singUpMSGP.innerText);
	if (singUpMSGP.innerText == "※ 비밀번호가 일치하지 않습니다" || singUpMSGI.innerText == "※ 아이디가 중복되었습니다"){
		
	}
	else{
		
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
	}
};

const handleToDoSubmit = (event) => {
	event.preventDefault()
};

for (let i=0 ; i < FormDo.length; i++){
	FormDo[i].addEventListener('submit', handleToDoSubmit)
}

// 커서가 떼졌을때 실행
singUpID.addEventListener('blur', postSearch);
singUpCPwd.addEventListener('blur', testPWDSelect);
loginBTN.addEventListener('click', postreq);

singUpBtn.addEventListener('click', postreqAdd);
