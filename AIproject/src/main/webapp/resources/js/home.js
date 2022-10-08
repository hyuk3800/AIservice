const chatBtn = document.querySelector('#chatBtn');
const chatinput = document.querySelector('#chatting');
const chbox = document.querySelector(".chbox > ul");


const getreq = () => {
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
				chatinput.value = "";
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				//console.log(xhr);
			}
		}
	}
	xhr.open("GET", "/biz/chat/json.do", true);
	xhr.setRequestHeader("Content-type", "application/json");


	xhr.send();	
}


const postreq = () => {
	console.log("클릭");
	
	
	
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
				chatinput.value = "";
				console.log(xhr);
				const AiLi = document.createElement("li");
				AiLi.className = "ai";
				AiLi.innerText = xhr.response;
				chbox.appendChild(AiLi);
			}
		}
	}
	const chat = chatinput.value;
	
	const userLi = document.createElement("li");
	userLi.className = "user";
	userLi.innerText = chat;
	chbox.appendChild(userLi);
	
	xhr.open("POST", "/biz/chat/chatting.do", true);
	xhr.setRequestHeader("Content-type", "application/json");
	let chatting = {
		chat : chat
	};

	xhr.send(JSON.stringify(chatting));	
};

const entbtnkeyup = (e) => {
	 if(e.keyCode === 13){
	 	e.preventDefault();
	 	chatBtn.click();
	 }
};

getreq();

chatinput.addEventListener('keyup', entbtnkeyup);
chatBtn.addEventListener('click', postreq);

