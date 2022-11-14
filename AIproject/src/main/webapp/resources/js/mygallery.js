console.log("mygallery");

//Animation
$('#animatedElement').click(function() {
$(this).addClass("slideUp");
});

$('#animatedElement').click(function() {
    $(this).addClass("pulse");
});

// lightbox
lightbox.option({
'resizeDuration': 300,
'wrapAround': true,
'alwaysShowNavOnTouchDevices':true,
'disableScrolling':true,
'positionFromTop':260
});

//hairgallery

// $(document).ready(function (e){
// $(".package").on("click","img",function(){
//     var path = $(this).attr('src')
//     showImage(path);
// });

// function showImage(fileCallPath){
    
//     $(".bigPictureWrapper").css("display","flex").show();
    
//     $(".bigPicture")
//     .html("<img src='"+fileCallPath+"' >")
//     .animate({width:'100%', height: '100%'}, 700);
    
//     }
    
// $(".bigPictureWrapper").on("click", function(e){
//     $(".bigPicture").animate({width:'0%', height: '0%'}, 500);
//     setTimeout(function(){
//         $('.bigPictureWrapper').hide();
//     }, 500);
//     });
// });

function logout(seq){
Swal.fire({
    title: '정말로 로그아웃 하시겠습니까?',
    showCancelButton: true,
    confirmButtonColor: '#3caaffc4',
    cancelButtonColor: '#ffb6c1',
    confirmButtonText: '로그아웃 하기',
    cancelButtonText: '취소'
}).then((result) => {
    if (result.value) {
location.href="logout.do";
    }
})
};


// fancy box
Fancybox.bind('[data-fancybox="gallery"]', {
Toolbar: {
  display: [
    {
      id: "counter",
      position: "center",
    },
    "counter",
    "zoom",
    "slideshow",
    "thumbs",
    "download",
    "close",
  ],
},
});

// Fancybox.bind('[data-fancybox="gallery"]', {
//   caption: function (fancybox, carousel, slide) {
//     return (
//       `${slide.index + 1} / ${carousel.slides.length} <br />` + slide.caption
//     );
//   },
// });

function language () {
    var nav = window.navigator,
    browserLanguagePropertyKeys = ['language', 'browserLanguage', 'systemLanguage', 'userLanguage'],
    i,
    language;

    // support for HTML 5.1 "navigator.languages"
    if (Array.isArray(nav.languages)) {
      for (i = 0; i < nav.languages.length; i++) {
        language = nav.languages[i];
        if (language && language.length) {
          return language;
        }
      }
    }

    // support for other well known properties in browsers
    for (i = 0; i < browserLanguagePropertyKeys.length; i++) {
      language = nav[browserLanguagePropertyKeys[i]];
      if (language && language.length) {
        return language;
      }
    }

    return null;
  };
function checker () {
  const whatIs = language();
  if (whatIs === "ko-KR") {
    window.location.href = 'https://example.com/ko/';
     }
  else if (whatIs === "ja-JP") {
    window.location.href = 'https://example.com/ja/';
    }


// sns 공유
function shareInstagram() {
  window.open("https://www.instagram.com/" + encodeURIComponent(location.href));
};

function shareKakaotalk() {
  window.open("https://www.facebook.com/sharer.php?u=http://221.148.138.28:9000/biz/login.do" + encodeURIComponent(location.href));
};

function shareNaverBlog() {
  window.open("https://share.naver.com/web/shareView.nhn?url=http://naver.com&title=챗봇 헤어캣 서비스" + encodeURIComponent(location.href));
};

function shareTwitter() {
  window.open("https://twitter.com/intent/tweet?url=http://221.148.138.28:9000/biz/login.do" + encodeURIComponent(location.href));
};

//function shareFacebook() {
//  window.open("https://www.facebook.com/sharer.php?u=http://221.148.138.28:9000/biz/login.do" + encodeURIComponent(location.href));
//};
}