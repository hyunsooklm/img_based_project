<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>AbanDog</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/assets/img/favicon.ico" rel="icon">
  <link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Montserrat:300,400,500,600,700" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body>


  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center header-transparent">
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto"><a href="${pageContext.request.contextPath}/main">AbanDog</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto active" href="${pageContext.request.contextPath}/main">Home</a></li>
          <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/aboutus">About Us</a></li>
          <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/aboutdogs">About Abandoned dogs</a></li>
          <li class="dropdown"><a href="#"><span>Search Dogs</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="${pageContext.request.contextPath}/searchbyimage">Search Dogs by image</a></li>
              <li><a href="${pageContext.request.contextPath}/searchbycategory">Search Dogs by category</a></li>
            </ul>
          </li>
          <c:choose>
          	<c:when test="${loginVO == null}">
	          <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/logIn">Log In</a></li>  	
          	</c:when>
          	<c:otherwise>
          	  <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/logout">Log Out</a></li>
          	</c:otherwise>
          </c:choose>
          <li><a class="nav-link scrollto" href="#footer">Contact</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

      <div class="social-links">
        <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
        <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
        <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
        <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
      </div>

    </div>
  </header><!-- End Header -->


  <!-- ======= Hero Section ======= -->
  <section id="hero" class="clearfix" style="height:50vh">
      <div class="row justify-content-center align-self-center" data-aos="fade-up">
        <div class="col-lg-6 intro-info order-lg-first order-last" data-aos="zoom-in" data-aos-delay="100">
          <h2>회원 <span>가입</span></h2>
          <h4>회원가입 후 마음에 드는 강아지를 저장해보세요!</h4>
        </div>
      </div>

  </section><!-- End Hero -->
  
  <main id="main">
       
    <!-- ======= Team Section ======= -->
    <section id="team" class="team section-bg">
      <div class="container" data-aos="fade-up">
        <div class="row">
        
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
	        <div class="form">
	
	           <form name="joinfrm" action="insertMember" method="post" role="form" class="php-email-form">
	             <div class="form-group">
	               <input type="text" name="name" class="form-control" id="name" placeholder="이름" required="">
	             </div>
	             <div class="form-group mt-3">
		             <div class="row">
			             <div class="col-sm-9">
			               <input type="email" class="form-control" name="email" id="email" placeholder="이메일" required="">
			             </div>
			             <div class="col-sm-3">
			               <input type="button" id="btn" onclick="checkEmail()" value="중복확인">
		               </div>
	               	</div>
	               <input type="hidden" id="checkEmail_" value="0">
	             </div>
	             <div class="form-group mt-3">
		             <div class="row">
			             <div class="col-sm-8">
			               <input type="text" class="form-control" name="emailNum" id="emailNum" placeholder="이메일 인증번호" required="">
			             </div>
			             <div class="col-sm-4">
			               <input type="button" id="emailbtn" onclick="checkEmailNum()" value="인증번호 확인">
		               </div>
	               	</div>
	               <input type="hidden" id="checkEmailNum_" value="0">
	               <input type="hidden" id="emailNum_" value="0">
	             </div>
	             <div class="form-group mt-3">
	             	<div class="row">
		             	<div class="col-sm-9">
		               		<input type="text" class="form-control" name="id" id="id" placeholder="아이디" required="">
		               	</div>
		               	<div class="col-sm-3">
		               		<input type="button" id="btn" onclick="checkId()" value="중복확인">
		               	</div>
	               	</div>
	               <input type="hidden" id="checkId_" value="0">
	             </div>
	             <div class="form-group mt-3">
	               <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호" required="">
	             </div>
	             <div class="form-group mt-3">
	               <input type="password" class="form-control" name="pw2" id="pw2" placeholder="비밀번호확인" required="">
	             </div>
	
	             <div class="my-3">

	             </div>
	
	             <div class="text-center"><button type="button" onclick="checkAll()">Join</button></div>
	           </form>
	
	         </div>
         </div>
         
         <div class="col-sm-4"></div>

        </div>

      </div>
    </section><!-- End Team Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="section-bg">
    <div class="footer-top">
      <div class="container">

        <div class="row">

            <div class="row">

			  <div class="col-sm-4">
			    <img src="assets/img/logo.png">
			  </div>
			  
              <div class="col-sm-4">
                <div class="footer-info">
                  <h3>AbanDog</h3>
                  <p>이제 사지 말고 입양하세요. <br> 유기견들이 당신을 기다리고 있어요!</p>
                </div>
              </div>

              <div class="col-sm-4">
                <div class="footer-links">
                  <h4>Contact Us</h4>
                  <p>
                                   중앙대학교 <br>
                                   서울특별시 동작구 흑석동 흑석로 84<br>
                                   대한민국 <br>
                    <strong>Phone:</strong> 010-5914-9393<br>
                    <strong>Email:</strong> b2nyb2st77@gmail.com<br>
                  </p>
                </div>
                
                <div class="social-links">
                  <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                  <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                  <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                  <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                </div>
                
               </div>

			   

              </div>

            </div>

          </div>

        </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong>AbanDog</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
        All the links in the footer should remain intact.
        You can delete the links only if you purchased the pro version.
        Licensing information: https://bootstrapmade.com/license/
        Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Rapid
      -->
        Designed by Soobeen</a>
      </div>
    </div>
  </footer><!-- End  Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/purecounter/purecounter.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  
  <script>
	  function checkId(){
		  
		  var id = $("input#id").val();
		  var data = {id:id}
		  
		  $.ajax({
		       url : "${pageContext.request.contextPath}/checkId",
		       type : "post",
		       data : data,
		       success : function(result) {
		           if(result == 'fail') {
		               alert("이미 존재하는 아이디입니다.");
		               document.getElementById("id").focus();
		               $("input#checkId_").val(0);
		           } else {
		        	   alert("사용가능한 아이디입니다.");
		        	   $("input#checkId_").val(1);
		           }
		       },
		       error: function(data, status, er) {
		           alert("[error] data : " + data + ", status : " + status);
		       }
		       
		    });
		  
	  }
	  
	  function checkEmail(){
		  
		  var email = $("input#email").val();
		  var data = {email:email}
		  
		  $.ajax({
		       url : "${pageContext.request.contextPath}/checkEmail",
		       type : "post",
		       data : data,
		       success : function(result) {
		           if(result == 'fail') {
		               alert("이미 존재하는 이메일입니다.");
		               document.getElementById("email").focus();
		               $("input#checkEmail_").val(0);
		           } else {
		        	   alert("사용가능한 이메일입니다.");
		        	   alert("이메일 인증번호를 발송 중입니다. 잠시만 기다려주세요.");
		        	   $("input#checkEmail_").val(1);
		        	   checkEmail2();
		           }
		       },
		       error: function(data, status, er) {
		           alert("[error] data : " + data + ", status : " + status);
		       }
		       
		    });
		  
	  }
	  
	  function checkEmail2(){
		  
		  var email = $("input#email").val();
		  var data = {email:email}

		  
		  $.ajax({
		       url : "${pageContext.request.contextPath}/checkEmailNum",
		       type : "post",
		       data : data,
		       success : function(result) {
		           if(result == 'fail') {
		               alert("인증번호 발송에 실패하였습니다. 이메일을 다시 확인해주세요.");
		               document.getElementById("emailNum").focus();
		               $("input#checkEmailNum_").val(0);
		           } else {
		        	   alert("인증번호를 발송했습니다. 이메일을 확인해주세요.");
		        	   $("input#emailNum_").val(result);
		           }
		       },
		       error: function(data, status, er) {
		           alert("인증번호 발송에 실패하였습니다. 이메일을 다시 확인해주세요.");
		       }
		       
		    });
		  
	  }
	  
	  function checkEmailNum(){
		  if($("input#emailNum").val() == $("input#emailNum_").val()){
       	   alert("인증이 완료되었습니다.");
       	   $("input#checkEmailNum_").val(1);
       	   document.getElementById('emailNum').disabled = true ;
   	   } else{
   		   alert("인증에 실패하였습니다. 인증번호를 다시 확인해주세요.");
              document.getElementById("emailNum").focus();
              $("input#checkEmailNum_").val(0);
   	   }
		  
	  }
	  
	  function checkAll(){
		  	var frm = document.joinfrm;
			var name = $("input#name").val();
			var email = $("input#email").val();
			var id = $("input#id").val();
			var pw = $("input#pw").val();
			var pw2 = $("input#pw2").val();
			
			var RegExp = /^[a-zA-z0-9]{4,12}$/;
			
			if(name.length == 0 || name.length > 10){
				alert("10자 이내의 이름을 입력해주세요.");
				document.getElementById("name").focus();
			} else if (!RegExp.test(id)) {
	            alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다");
	            document.getElementById("id").focus();
	        } else if (!RegExp.test(pw)) {
	            alert("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다");
	            document.getElementById("pw").focus();
	        } else if($("input#checkEmail_").val() == 0){
				alert("이메일 중복확인은 필수입니다.");
				document.getElementById("emailNum").focus();
			} else if($("input#checkEmailNum_").val() == 0){
				alert("이메일 인증은 필수입니다.");
				document.getElementById("email").focus();
			} else if($("input#checkId_").val() == 0){
				alert("아이디 중복확인은 필수입니다.");
				document.getElementById("id").focus();
			} else if(pw != pw2){
				alert("비밀번호가 일치하지 않습니다.");
				document.getElementById("pw").focus();
			} else {
				frm.submit();
				alert("AbanDog의 회원이 되신 것을 축하합니다!");
			}		
		  
	  }
  
  </script>
  
</body>
</html>
