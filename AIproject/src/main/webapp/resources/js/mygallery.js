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