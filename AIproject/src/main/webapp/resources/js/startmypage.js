const changename = document.querySelector("#changename");
const slidercont = document.querySelector(".slidercont");

let nickname;

const makeImgBox = (srclink) => {
	const hvr_pulse_shrink = document.createElement("div");
	hvr_pulse_shrink.className = "hvr-pulse-shrink";
	
	const mygallery = document.createElement("a");
	mygallery.href="mygallery.do";

	const imgBox = document.createElement("img");
	imgBox.src = "resources/uploadImg/" + srclink;
	imgBox.width = 130;
	imgBox.height = 130;

	mygallery.appendChild(imgBox);
	hvr_pulse_shrink.appendChild(mygallery);
	slidercont.appendChild(hvr_pulse_shrink);
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

				for(let i=0; i < data["chatData"].length; i++){
					const srclink = data["chatData"][i]["chatData"];

					makeImgBox(srclink);
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

