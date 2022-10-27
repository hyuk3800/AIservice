
	// alert
	 function nomypage(seq) {
	   Swal.fire({
	     title: '마이페이지는 로그인 후 이용 가능합니다.',
	     text: "로그인 후 다양한 서비스를 이용해보세요!",
	     icon : 'warning',
	     showCancelButton: true,
	     confirmButtonColor: '#3caaffc4',
	     cancelButtonColor: '#ffb6c1',
	     confirmButtonText: '로그인하기',
	     cancelButtonText: '취소'
	   }).then((result) => {
	     if (result.value) {
	       location.href = "login.do";
	     }
	   })
	 };
	 
	 
