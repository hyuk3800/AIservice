
let hxr;




const testFunction = () => {

    if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
        hxr = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE 6 이하
        hxr = new ActiveXObject("Microsoft.XMLHTTP");
    }

    hxr.onreadystatechange = getxhr;
    hxr.open('GET', 'login.do');
    hxr.send();
}

function getxhr(){
    if (hxr.readyState === XMLHttpRequest.DONE){
        if (hxr.status === 200){
            let resp = JSON.parse(hxr.responseText);
            alert(resp);
        }
    }
}



testFunction();