 // Animation
 $('#animatedElement').click(function () {
    $(this).addClass("slideUp");
  });

  $('#animatedElement').click(function () {
    $(this).addClass("pulse");
  });

  ///hairgallery
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


  // alert
  function logout(seq) {
    Swal.fire({
      title: '정말로 로그아웃 하시겠습니까?',
      showCancelButton: true,
      confirmButtonColor: '#3caaffc4',
      cancelButtonColor: '#ffb6c1',
      confirmButtonText: '로그아웃 하기',
      cancelButtonText: '취소'
    }).then((result) => {
      if (result.value) {
        location.href = "login.do";
      }
    })
  };

  // add to gallery

  $(function () {

    $(".recommend").click(function () {
      $(".modal").fadeIn();
    });

    $("#close").click(function () {
      $(".modal").fadeOut();
    });

  });

  // // image downloads

  // document.getElementById('exportBtn').onclick = function(){
  // domtoimage.toBlob(document.getElementById('image__'))
  // .then(function (blob) {
  //     window.saveAs(blob, 'image__.png');
  // });
  // }


  // $('.fancybox-image').each(function (){
  //     var href = $(this).attr('src');
  //     $(this).next("a").attr("href", href);
  // });

  // $('[data-fancybox="gallery"]').fancybox({
  //   buttons : [
  //     'zoom',
  //     'counter',
  //     'slideshow',
  //     'fullscreen',
  //     'thumbs',
  //     'download',
  //     'close',
  //   ],
  //   beforeShow : function(instance, slide) { 
  //     instance.$refs.container.find('.fancybox-button--download').attr('href', slide.src);
  //   }
  // });



  // fancy box
  Fancybox.bind('[data-fancybox="gallery"]', {
    Toolbar: {
      display: [
        {
          id: "counter",
          position: "center",
        },
        "zoom",
        "counter",
        "slideshow",
        "thumbs",
        "download",
        "close",
      ],
    },
  });


  // autoslide

  $(function () {
    $('#sc').slick({
      slide: 'div',
      infinite: true,
      slidesToShow: 1,
      slidesToScroll: 1,
      speed: 100,
      arrows: true,
      dots: false,
      autoplay: false,
      autoplaySpeed: 2000,
      pauseOnHover: true,
      vertical: false,
      prevArrow: "<button type='button' class='slick-prev'>Previous</button>",
      nextArrow: "<button type='button' class='slick-next'>Next</button>",
      dotsClass: "slick-dots",
      draggable: true,
    });
  })