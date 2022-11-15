const flexcont = document.querySelector("#flexcont");

const makeImgBox = (srclink) => {
	const abox = document.createElement("a");
	
	abox.dataset.fancybox = "gallery";
	abox.href = "resources/AiUploadImg/" + srclink;
	abox.className = "myatags";
	const imgBox = document.createElement("img");
	imgBox.className = "rounded";
	imgBox.src="resources/AiUploadImg/" + srclink;
	
//	imgBox.width = 100;
//	imgBox.height = 100;

	abox.appendChild(imgBox);
	flexcont.append(abox);
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
				if(data["chatData"].length != 0){
					for(let i=0; i < data["chatData"].length; i++){
						const srclink = data["chatData"][i]["chatData"];
						
						makeImgBox(srclink);
					}					
				}
				else{
					console.log("null");
					nomygallery.className = "on";
				}

			}
		}
	}
	xhr.open("GET", "/biz/mygallery/json.do", false);
	xhr.setRequestHeader("Content-type", "application/json");


	xhr.send();		
};

getJson();
