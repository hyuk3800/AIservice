const chatBtn = document.querySelector('#chatBtn');
const chatinput = document.querySelector('#chatting');
const chbox = document.querySelector(".context");
const postChat = document.querySelector("#postChat");
const testBtn = document.querySelector("#testBtn");

const testPop = document.querySelector("#testPop");
const uploadfilter = document.querySelector(".filter");

const fileInput = document.querySelector("#fileInput");
const fileBTN = document.querySelector("#fileBTN");

const entbtnkeyup = (e) => {
	if(e.keyCode === 13){
		e.preventDefault();
		chatBtn.click();
	}
};

const handleToDoSubmit = (event) => {
	event.preventDefault()
};


const testFile = () => {
	// console.log(fileInput.value);
	let fileInfo = fileInput.files[0];
	let reader = new FileReader();
	
	reader.onload = () => {
		document.getElementById("uploadImg").src = reader.result;
//		console.log(reader.result);
	}
	
	if(fileInfo){
		reader.readAsDataURL(fileInfo);
	}
	
}

const scrollHeight = () => {
	chbox.scrollTo(0,chbox.scrollHeight);
};

const postBtnOff = () => {
	chatBtn.style.display = "none";
	chatinput.style.width = "100%";
};

const postBtnOn = () => {
	chatBtn.style.display = "block";
	chatinput.style.width = "80%";
};

const testuploadButton = () => {
	console.log("확인");
	testPop.classList.toggle("on");
};

const makeingUser = (chat) => {
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
	scrollHeight();
};

const makingAi = (chat) => {
	const AiDiv = document.createElement("div");
	const icon = document.createElement("div");
	const aiIcon = document.createElement("div");
	const textbox = document.createElement("div");
	AiDiv.className = "chat ch1";
	icon.className = "icon";
	aiIcon.className = "ai icon";
	textbox.className = "textbox";
	
	textbox.innerText = chat;
	
	icon.appendChild(aiIcon);
	AiDiv.appendChild(icon);
	AiDiv.appendChild(textbox);
	chbox.appendChild(AiDiv);
	scrollHeight()
};

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
					makingAi(xhr.response);
				}
			}
		}
		const chat = chatinput.value;
		
		makeingUser(chat);
		
		xhr.open("POST", "/biz/chat/chatting.do", true);
		xhr.setRequestHeader("Content-type", "application/json");
		let chatting = {
			chat : chat
		};
	
		xhr.send(JSON.stringify(chatting));	
	}
};

 const postImgTest = () => {
 	console.log("클릭");
 	let fileInfo = fileInput.files[0];
 	let reader = new FileReader();
 	reader.readAsDataURL(fileInfo);
 	if(!fileInfo){
 		console.log("없음");
 	}
 	else{
 //		reader.readAsBinaryString(fileInfo);
 		console.log("있는거 확인 됨");
 		let formData = new FormData();
 		formData.append('files', fileInfo);
 //		console.log(JSON.parse(formData));
 //		console.log(formData);


 		 let xhr;
 		 if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
 	         xhr = new XMLHttpRequest();
 	     } else if (window.ActiveXObject) { // IE 6 이하
 	         xhr = new ActiveXObject("Microsoft.XMLHTTP");
 	     }
 		 xhr.onreadystatechange = () => {
 		 	console.log(xhr.readyState);
 		 	if(xhr.readyState == 4){
 		 		console.log(xhr.status);
 		 		// console.log("이거");
 		 		if(xhr.status == 200){
 		 			console.log(xhr);
 		 		}
 		 	}
 		 }

		
		
 		 xhr.open("POST", "/biz/chat/uploadFile.do", true);
// 		 xhr.setRequestHeader("Content-type", "multipart\/form-data;");
		 
		 
 		 xhr.send(formData);	
 	}
 };



const testform = () => {
	const testForm = document.querySelector("#testForm");
	let fileInfo = fileInput
	console.log(fileInfo.files[0]);
	console.log(testForm[0].files[0]);
	let formData1 = new FormData();
	let formData2 = new FormData();
	formData1.append('file', fileInfo.files[0]);
	formData2.append('file', testForm[0].files[0]);
	console.log("formData : ", formData1.get('file'));
	console.log("testForm : ", formData2.get('file'));
	console.log(JSON.stringify(formData1.get('file')));
	console.log(JSON.stringify(formData2.get('file')));
};


getreq();
scrollHeight();

postChat.addEventListener('submit', handleToDoSubmit);
chatinput.addEventListener('keydown', postBtnOn);
testBtn.addEventListener('click', testuploadButton);
uploadfilter.addEventListener('click', testuploadButton);
chatinput.addEventListener('keyup', entbtnkeyup);
chatBtn.addEventListener('click', postreq);

fileInput.addEventListener('change', testFile);
fileBTN.addEventListener("click", postImgTest);
//fileBTN.addEventListener("click", testform);
