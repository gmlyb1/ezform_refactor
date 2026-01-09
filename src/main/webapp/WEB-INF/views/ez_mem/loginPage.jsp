<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>EZFORM | Business Platform Project</title>

<!-- Favicon -->
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/images/favicon.ico" />

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/libs.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/hope-ui.css?v=1.0.2"/>

<!-- JS -->
<script src="${pageContext.request.contextPath }/resources/js/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/libs.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/charts/widgetcharts.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/charts/vectore-chart.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/charts/dashboard.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/fslightbox.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/setting.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/form-wizard.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/app.js"></script>

<script>
	/* $(document).ready(function() {
		$("#ezLoginBtn").click(function() {
			var emp_email = $("#emp_email").val();
			var emp_pw = $("#emp_pw").val();
			
			if(emp_email == "") {
				alert("이메일을 입력해주세요");
				return false;
			}
			
			if(emp_pw == "") {
				alert("패스워드을 입력해주세요");
				return false;
			}
			if(emp_email != "" && emp_pw != "") {
				return "/login";
			}
		});
	}); */
	$(document).ready(function() {

	    // 페이지 로드 시 저장된 아이디 가져오기
	    if(localStorage.getItem("savedEmail")) {
	        $("#emp_email").val(localStorage.getItem("savedEmail"));
	        $("#saveIdCheck").prop("checked", true);
	    }

	    $("#ezLoginBtn").click(function(e) {
	        var emp_email = $("#emp_email").val();
	        var emp_pw = $("#emp_pw").val();

	        if(emp_email == "") {
	            alert("이메일을 입력해주세요");
	            return false;
	        }

	        if(emp_pw == "") {
	            alert("패스워드를 입력해주세요");
	            return false;
	        }

	        // 아이디 저장 체크박스 확인
	        if($("#saveIdCheck").is(":checked")) {
	            localStorage.setItem("savedEmail", emp_email);
	        } else {
	            localStorage.removeItem("savedEmail");
	        }

	        // 실제 로그인 폼 제출
	        $("form").submit();
	    });
	});

</script>

<style>
	body {
		background: linear-gradient(135deg, #f5f7fa 0%, #e4f0f6 100%);
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}

	.auth-card {
		background: rgba(255, 255, 255, 0.98);
		border-radius: 15px;
		padding: 50px 30px;
		box-shadow: 0 10px 30px rgba(0,0,0,0.08);
		transition: all 0.3s ease-in-out;
	}

	.auth-card:hover {
		transform: translateY(-5px);
		box-shadow: 0 15px 40px rgba(0,0,0,0.12);
	}

	.navbar-brand img {
		width: 50px;
		height: 50px;
	}

	.logo-title {
		font-weight: 700;
		font-size: 1.8rem;
		color: #4CAF50; /* 소프트 그린 계열 */
	}

	#ezLoginBtn {
		width: 100%;
		padding: 12px;
		font-weight: 600;
		border-radius: 50px;
		background-color: #4CAF50;
		border-color: #4CAF50;
		transition: 0.3s;
	}

	#ezLoginBtn:hover {
		background-color: #45A049;
		border-color: #45A049;
	}

	.sign-bg svg {
		position: absolute;
		top: 20px;
		left: -50px;
		transform: rotate(10deg);
		opacity: 0.05;
	}

	.col-md-6.bg-primary {
		background: #f0f4f8; /* 부드러운 라이트 그레이 */
	}

	.col-md-6.bg-primary img {
		object-fit: cover;
		filter: brightness(0.95);
		transform: scale(1.03);
		transition: transform 0.5s ease;
	}

	.col-md-6.bg-primary img:hover {
		transform: scale(1.06);
	}

	h2, p {
		color: #333;
	}
</style>

</head>
<body>
    <div id="loading">
      <div class="loader simple-loader">
          <div class="loader-body"></div>
      </div>
    </div>

    <div class="wrapper">
      <section class="login-content">
         <div class="row m-0 align-items-center vh-100">

            <div class="col-md-6 d-flex align-items-center justify-content-center">
               <div class="col-md-10">
                  <div class="card card-transparent shadow-none d-flex justify-content-center mb-0 auth-card">
                     <div class="card-body">
                        <a href="./login" class="navbar-brand d-flex align-items-center mb-3">
                           <img src="${pageContext.request.contextPath }/resources/images/logo_ezform.svg"/>
                           <h4 class="logo-title ms-3">EZFORM</h4>
                        </a>
                        <h2 class="mb-2 text-center">LOGIN</h2>
                        <!-- <p class="text-center">Login to stay connected.</p> -->

                        <form action="./login" method="post">
                           <div class="row">
                              <div class="col-lg-12 mb-3">
                                 <label class="form-label">Email</label>
                                 <input type="email" class="form-control" id="emp_email" name="em_email" placeholder="아이디를 입력해주세요.">
                              </div>
                              <div class="col-lg-12 mb-3">
                                 <label class="form-label">Password</label>
                                 <input type="password" class="form-control" id="emp_pw" name="em_pw" placeholder="비밀번호를 입력해 주세요.">
                              </div>
                           </div>
                           <div class="col-lg-12 mb-3">
							    <div class="form-check mt-2">
							        <input type="checkbox" class="form-check-input" id="saveIdCheck">
							        <label class="form-check-label" for="saveIdCheck">아이디 저장</label>
							    </div>
							</div>
                           <div class="d-flex justify-content-center">
                              <button type="submit" class="btn btn-primary" id="ezLoginBtn">로그인</button>
                           </div>
                           <p class="mt-3 text-center">Nice to meet you ezform. Please log in here.</p>
                        </form>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-md-6 d-none d-md-block bg-primary p-0 vh-100 overflow-hidden">
               <img src="${pageContext.request.contextPath }/resources/images/auth/01.png" class="img-fluid" alt="login-image">
            </div>
         </div>
      </section>
    </div>
</body>
</html>
