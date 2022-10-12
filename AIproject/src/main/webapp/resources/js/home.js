const chatBtn = document.querySelector('#chatBtn');
const chatinput = document.querySelector('#chatting');
const chbox = document.querySelector(".context");
const postChat = document.querySelector("#postChat");


const postBtnOff = () => {
	chatBtn.style.display = "none";
	chatinput.style.width = "100%"
}

const postBtnOn = () => {
	chatBtn.style.display = "block";
	chatinput.style.width = "80%"
}


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
	if(chatinput.value == ""){
		
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
					chatinput.value = "";
					postBtnOff()
					console.log(xhr);
					const AiDiv = document.createElement("div");
					const icon = document.createElement("div");
					const aiIcon = document.createElement("div");
					const textbox = document.createElement("div");
					AiDiv.className = "chat ch1";
					icon.className = "icon";
					aiIcon.className = "ai icon";
					textbox.className = "textbox";
					
					textbox.innerText = xhr.response;
					
					icon.appendChild(aiIcon);
					AiDiv.appendChild(icon);
					AiDiv.appendChild(textbox);
					chbox.appendChild(AiDiv);
				}
			}
		}
		const chat = chatinput.value;
		
		const userDiv = document.createElement("div");
		const user_icon = document.createElement("div");
		const userIcon = document.createElement("div");
		const usertextbox = document.createElement("div");
		userDiv.className = "chat ch2";
		user_icon.className = "icon";
		userIcon.className = "user icon";
		usertextbox.className = "textbox";
		
		usertextbox.innerText = chat;
		
		user_icon.appendChild(userIcon);
		userDiv.appendChild(user_icon);
		userDiv.appendChild(usertextbox);
		
		chbox.appendChild(userDiv);
		
		
		xhr.open("POST", "/biz/chat/chatting.do", true);
		xhr.setRequestHeader("Content-type", "application/json");
		let chatting = {
			chat : chat
		};
	
		xhr.send(JSON.stringify(chatting));	
	}
};

const entbtnkeyup = (e) => {
	 if(e.keyCode === 13){
	 	e.preventDefault();
	 	chatBtn.click();
	 }
};

const handleToDoSubmit = (event) => {
	event.preventDefault()
};


getreq();

postChat.addEventListener('submit', handleToDoSubmit);


chatinput.addEventListener('keypress', postBtnOn);


chatinput.addEventListener('keyup', entbtnkeyup);
chatBtn.addEventListener('click', postreq);

