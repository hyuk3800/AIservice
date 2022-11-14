const changename = document.querySelector("#changename");
const slidercont = document.querySelector("#sc");
const slidercont2 = document.querySelector("#sc2");
const nomygallery = document.querySelector("#nomygallery");
const catcatBox = document.querySelector(".catcatBox");
const hvr_pulse_shrink = document.querySelector(".hvr-pulse-shrink");

let nickname;

const makeImgBox = (srclink) => {
	const hvr_pulse_shrink = document.createElement("div");
	hvr_pulse_shrink.className = "hvr-pulse-shrink";
	
	const mygallery = document.createElement("a");
	mygallery.href="mygallery.do";
	
	const imgBox = document.createElement("img");
	imgBox.src = "resources/AiUploadImg/" + srclink;
	imgBox.width = 130;
	imgBox.height = 130;

	mygallery.appendChild(imgBox);
	hvr_pulse_shrink.appendChild(mygallery);
	slidercont.appendChild(hvr_pulse_shrink);
};
const makeImgBox2 = (srclink) => {
	const hvr_pulse_shrink = document.createElement("div");
	hvr_pulse_shrink.className = "hvr-pulse-shrink";
	
	const mygallery = document.createElement("a");
	mygallery.href="aigallery.do";
	
	const imgBox = document.createElement("img");
	imgBox.src = "resources/AiUploadImg/" + srclink;
	imgBox.width = 130;
	imgBox.height = 130;

	mygallery.appendChild(imgBox);
	hvr_pulse_shrink.appendChild(mygallery);
	slidercont2.appendChild(hvr_pulse_shrink);
};


const getJson = () => {
	console.log("시작")
	
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
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				let user = data['user'];
				const userID = document.querySelector("#userID");
				nickname = user['nickname'];
				userID.value = user['nickname'];
				if(data["dummyList"].length != 0){
					for(let i=0; i < data["dummyList"].length; i++){
						const srclink = data["dummyList"][i]["chatData"];
						makeImgBox(srclink);							
						
					}					
				}else if(data["dummyList"].length == 0){
					console.log("null");
					nomygallery.classList = "on";
				}
				
				if(data["hairCatList"].length != 0){
					for(let i=0; i < data["hairCatList"].length; i++){
						const srclink = data["hairCatList"][i]["chatData"];
						makeImgBox2(srclink);							
						
					}					
				}
				else if(data["hairCatList"].length == 0){
					console.log("null");
					catcatBox.classList = "on";
				}
			}
		}
	}
	xhr.open("GET", "/biz/mypage/json.do", false);
	xhr.setRequestHeader("Content-type", "application/json");


	xhr.send();		
};



getJson();



const changeNEvent = () => {
	console.log("클릭");
	let userCnick = userID.value;
	
	if(nickname != userCnick){
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
					if(xhr.response == "1"){
						nickname = userCnick;
					}
				}
			}
		}
		
		
		
		
		xhr.open("POST", "/biz/mypage/changename.do", true);
		xhr.setRequestHeader("Content-type", "application/json");
		let newName = {
				newname : userCnick
		}
			
		xhr.send(JSON.stringify(newName));	
	}
};



changename.addEventListener("click", changeNEvent);

