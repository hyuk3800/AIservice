const chatBtn = document.querySelector('#chatBtn');
const chatinput = document.querySelector('#chatting');
const chbox = document.querySelector(".context");
const postChat = document.querySelector("#postChat");
const testBtn = document.querySelector(".plus");
const testBtn2 = document.querySelector("#hand");

const testPop = document.querySelector("#testPop");
const uploadfilter = document.querySelector("#filterBox");
const uploadfilter2 = document.querySelector("#filterBox2");

const testPop2 = document.querySelector("#testPop2");

const fileInput = document.querySelector("#fileInput");
const fileInput2 = document.querySelector("#fileInput2");

const fileBTN = document.querySelector("#fileBTN");
const fileBTN2 = document.querySelector("#fileBTN2");

const fileLabel = document.querySelector("#fileLabel");
const fileLabel2 = document.querySelector("#fileLabel2");

const headcont = document.querySelector(".headcont");

const loginout = document.querySelector("#loginout");
const mypage = document.querySelectorAll(".mypage");

const pickhairstyle = document.querySelector("#pickhairstyle");
const pickhaircolor = document.querySelector("#pickhaircolor");


const nyanya = [
	"냥냥~",
	"냥냥냐",
	"냐냐~냥",
	"냥냥냐~냥",
	"냐~",
	"냥",
	"냐냐",
	"야옹",
	"냐냐냥",
	"야옹야옹",
	"냐앙"
]

const hairlist = [
	"none",
	"afro hairstyle",
	"bob cut hairstyle",
	"bowl cut hairstyle",
	"braid hairstyle",
	"caesar cut hairstyle",
	"chignon hairstyle",
	"cornrows hairstyle",
	"crew cut hairstyle",
	"crown braid hairstyle",
	"curtained hair hairstyle",
	"dido flip hairstyle",
	"dreadlocks hairstyle",
	"extensions hairstyle",
	"fade hairstyle",
	"fauxhawk hairstyle",
	"finger waves hairstyle",
	"french braid hairstyle",
	"frosted tips hairstyle",
	"full crown hairstyle",
	"harvard clip hairstyle",
	"high and tight hairstyle",
	"hime cut hairstyle",
	"hi-top fade hairstyle",
	"jewfro hairstyle",
	"jheri curl hairstyle",
	"liberty spikes hairstyle",
	"marcel waves hairstyle",
	"mohawk hairstyle",
	"pageboy hairstyle",
	"perm hairstyle",
	"pixie cut hairstyle",
	"psychobilly wedge hairstyle",
	"quiff hairstyle",
	"regular taper cut hairstyle",
	"ringlets hairstyle",
	"shingle bob hairstyle",
	"short hair hairstyle",
	"slicked-back hairstyle",
	"spiky hair hairstyle",
	"surfer hair hairstyle",
	"taper cut hairstyle",
	"the rachel hairstyle",
	"undercut hairstyle",
	"updo hairstyle"
]


const scrollHeight = () => {
	chbox.scrollTo(0,chbox.scrollHeight);
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


const testFile = (e) => {
	console.log(e.target);
	let fileInfo;
	let reader = new FileReader();
	let uploadImg;
	
	let fileLabel1;
	
	if(e.target == fileInput){
		fileInfo = fileInput.files[0];
		uploadImg = document.getElementById("uploadImg");
		fileLabel1 = fileLabel;
	}
	else if(e.target == fileInput2){
		fileInfo = fileInput2.files[0];
		uploadImg = document.getElementById("uploadImg2");
		fileLabel1 = fileLabel2;
	}
	
	console.log(fileInfo,uploadImg, fileLabel1)
	
	reader.onload = () => {
//		console.log(reader.result);
		
		uploadImg.src = reader.result;
		fileLabel1.classList.remove("on");
	}
	
	if(fileInfo){
		reader.readAsDataURL(fileInfo);
	}
	
}



const postBtnOff = () => {
	chatBtn.style.display = "none";
	chatinput.style.width = "100%";
};

const postBtnOn = () => {
	chatBtn.style.display = "block";
	chatinput.style.width = "87%";
};

const testuploadButton = () => {
	console.log("확인");
	testPop.classList.toggle("on");
	document.getElementById("uploadImg").src = "";
	fileInput.value = "";
	if(fileLabel.classList != "on"){
		fileLabel.classList.add("on");
	}
	pickhairstyle.value = "";
	pickhaircolor.value = "none";
	
};

const testuploadButton2 = () => {
	console.log("고양이도장!");
	testPop2.classList.toggle("on");
	document.getElementById("uploadImg2").src = "";
	fileInput2.value = "";
	if(fileLabel2.classList != "on"){
		fileLabel2.classList.add("on");
	}
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

const makeingUserFile = (fileName) => {
	const userDiv = document.createElement("div");
	const user_icon = document.createElement("div");
	const userIcon = document.createElement("div");
	const usertextbox = document.createElement("div");
	const userImg = document.createElement("img")
	userDiv.className = "chat ch2";
	user_icon.className = "icon";
	userIcon.className = "user icon";
	usertextbox.className = "textbox";
	userImg.src = "resources/uploadImg/" + fileName;
	usertextbox.appendChild(userImg);
	
	user_icon.appendChild(userIcon);
	userDiv.appendChild(user_icon);
	userDiv.appendChild(usertextbox);
	
	chbox.appendChild(userDiv);
	scrollHeight()
};


const makingAi = (chat) => {
	const random = Math.round(Math.random()*10);
	const AiDiv = document.createElement("div");
	const icon = document.createElement("div");
	const aiIcon = document.createElement("div");
	const textbox = document.createElement("div");
	AiDiv.className = "chat ch1";
	icon.className = "icon";
	aiIcon.className = "ai icon";
	textbox.className = "textbox";
	
	textbox.innerText = nyanya[random] + "(" + chat + ")";
	
	icon.appendChild(aiIcon);
	AiDiv.appendChild(icon);
	AiDiv.appendChild(textbox);
	chbox.appendChild(AiDiv);
	scrollHeight()
};


const makeingAiFile = (fileName) => {
	const AiDiv = document.createElement("div");
	const icon = document.createElement("div");
	const aiIcon = document.createElement("div");
	const textbox = document.createElement("div");
	const AiImg = document.createElement("img")
	AiDiv.className = "chat ch1";
	icon.className = "icon";
	aiIcon.className = "ai icon";
	textbox.className = "textbox";
	AiImg.src = "resources/AiUploadImg/" + fileName;
	textbox.appendChild(AiImg);
	
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
				fileLabel.classList.add("on");
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				
				if(data['user'] != null){
					headcont.dataset.chatroom = data['chatRoom'];
					loginout.href="logout.do";
					mypage[0].classList.toggle("on");
					mypage[1].classList.toggle("on");
					loginout.querySelector("#moveback").src = "resources/images/gologout.png";
					if(data['chatData'] != null){
						for (let i=0; i < data['chatData'].length; i++){
							let chatRow = data['chatData'][i];
							if(chatRow['chatter'] == 'AI'){
								console.log("이건 AI", chatRow)
								if(chatRow['type']==0){
									makingAi(chatRow['chatData']);								
								}else{
									makeingAiFile(chatRow['chatData']);
								}
							}
							else{
								console.log("이건 User", chatRow)
								if(chatRow['type'] == 0){
									makeingUser(chatRow['chatData']);								
								}
								else if (chatRow['type'] == 1){
									makeingUserFile(chatRow['chatData']);
								}
							}
							scrollHeight();
						}					
					}

				}
				else{
					headcont.dataset.chatroom = null;
					loginout.querySelector("#moveback").src = "resources/images/gologin.png";
				}
				scrollHeight();
				scrollHeight();
			}
		}
	}
	xhr.open("GET", "/biz/chat/json.do", true);
	xhr.setRequestHeader("Content-type", "application/json");


	xhr.send();	
	scrollHeight();
	scrollHeight();
}

const postreq = () => {
	console.log("클릭");
	postBtnOff();
	const roomnum = headcont.dataset.chatroom;
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
					
					
					console.log(xhr);
					makingAi(xhr.response);
					scrollHeight();
					scrollHeight();
				}
			}
		}
		const chat = chatinput.value;
		
		makeingUser(chat);
		
		xhr.open("POST", "/biz/chat/chatting.do", true);
		xhr.setRequestHeader("Content-type", "application/json");
		let chatting = {
			chat : chat,
			chatroom : roomnum
		};
		
		xhr.send(JSON.stringify(chatting));	
		chatinput.value = "";
		
	}
	chatinput.focus();
};



const postImgHair = (hairStyle, hairColor, Iname) => {
	let hair = {
			haristyle : hairStyle,
			hairColor : hairColor,
			img : Iname
		};
	
	console.log(chatting)
	
	
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
				
				
				console.log(xhr);
				makeingAiFile(xhr.response);
				scrollHeight();
				scrollHeight();
				scrollHeight();
				scrollHeight();
				scrollHeight();
				scrollHeight();
				scrollHeight();
			}
		}
	}
	
	xhr.open("POST", "/biz/chat/testAi.do", true);
	xhr.setRequestHeader("Content-type", "application/json");

	
	xhr.send(JSON.stringify(hair));	
};

const postImgTest = () => {
// 	console.log("클릭");
	let hNum = Number(pickhairstyle.value);
	let Hairstyle = hairlist[hNum];
	let color = pickhaircolor.value;
	console.log(Hairstyle, color);
	
	let fileInfo = fileInput.files[0];
// 	let reader = new FileReader();
// 	reader.readAsDataURL(fileInfo);
 	if(!fileInfo){
// 		console.log("없음");
 	}
 	else{
 //		reader.readAsBinaryString(fileInfo);
// 		console.log("있는거 확인 됨");
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
 		 			makeingUserFile(xhr.responseText);
 		 			makingAi("잠시만 기다려주세요");
 		 			postImgHair(Hairstyle, color, xhr.responseText);
 		 			
 		 			
 		 			scrollHeight();
 		 			scrollHeight();
 		 		}
 		 	}
 		 }

		
		
 		 xhr.open("POST", "/biz/chat/uploadFile.do", true);
// 		 xhr.setRequestHeader("Content-type", "multipart\/form-data;");
		 
		 
 		 xhr.send(formData);	
 		 
 	 	 testuploadButton();
 	}

 };

const postImgTest2 = () => {
	console.log("더미 전송")
};

getreq();
scrollHeight();

chatinput.addEventListener('focus', scrollHeight);
chatinput.addEventListener('keydown', postBtnOn);
testBtn.addEventListener('click', testuploadButton);
uploadfilter.addEventListener('click', testuploadButton);
chatinput.addEventListener('keyup', entbtnkeyup);


testBtn2.addEventListener('click', testuploadButton2);
uploadfilter2.addEventListener('click', testuploadButton2);

postChat.addEventListener('submit', handleToDoSubmit);
chatBtn.addEventListener('click', postreq);

fileInput.addEventListener('change', testFile);
fileInput2.addEventListener('change', testFile);

fileBTN.addEventListener("click", postImgTest);
fileBTN2.addEventListener("click", postImgTest2);
