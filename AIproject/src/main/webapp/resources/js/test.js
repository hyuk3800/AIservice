const reqOne = () => {
	let xhr;
	let data;
    if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE 6 이하
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
	xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        if (xhr.readyState == 4) {
            console.log(xhr.status);
            if (xhr.status == 200) {
            	data = JSON.parse(xhr.responseText);
            	if(!data){
            		console.log("data없음")
            	}else{
                    console.log("데이터는");
                    console.log(data);
            	}
//                
//				data = JSON.parse(xhr.responseText);
//
//				if(data["user"]!= null){
//					console.log(data["user"]);
//				}else{
//					console.log("없음");
//					}
            }
		}
    }
	xhr.open("GET", "/biz/testjackson", true);
    xhr.send();
	

};

reqOne();