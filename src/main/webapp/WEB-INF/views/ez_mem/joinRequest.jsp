<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EZFORM | 가입요청</title>

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/libs.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/hope-ui.css"/>



<style>
    body {
        background: linear-gradient(135deg, #f5f7fa 0%, #e4f0f6 100%);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .auth-card {
        background: rgba(255, 255, 255, 0.98);
        border-radius: 15px;
        padding: 40px 35px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.08);
    }

    .logo-title {
        font-weight: 700;
        font-size: 1.6rem;
        color: #4CAF50;
    }

    .btn-success {
        border-radius: 50px;
        padding: 12px;
        font-weight: 600;
    }
</style>
</head>

<body>

<div class="wrapper">
<section class="login-content">
<div class="row m-0 align-items-center vh-100 justify-content-center">

    <div class="col-md-5 col-lg-4">
        <div class="auth-card">

            <!-- 로고 -->
            <div class="text-center mb-3">
                <img src="${pageContext.request.contextPath }/resources/images/logo_ezform.svg" width="60">
                <div class="logo-title mt-2">EZFORM</div>
            </div>

            <h4 class="text-center mb-2">가입요청</h4>
            <p class="text-center text-muted mb-4">
               <strong style="color:red;">
	               	관리자 승인 후 서비스 이용이 가능합니다. <br>
	                직원번호는 배정받은 직원번호를 입력해 주시기 바랍니다
                </strong> 
            </p>

            <!-- 가입요청 폼 -->
            <form action="/ezform/joinRequest" method="post">

				<div class="mb-3">
                    <label class="form-label">직원번호</label>
                    <input type="text" class="form-control" name="em_id" placeholder="안내받은 직원번호를 입력해 주세요." required>
                </div>

                <div class="mb-3">
                    <label class="form-label">이름</label>
                    <input type="text" class="form-control" name="em_name" placeholder="요청하실 이름을 입력해 주세요." required>
                </div>

                <div class="mb-3">
                    <label class="form-label">이메일</label>
                    <input type="email" class="form-control" name="em_email" placeholder="요청하실 이메일을 입력해 주세요." required>
                </div>

                <div class="mb-3">
                    <label class="form-label">부서</label>
                    <select class="form-control" name="em_dept">
                        <option value="">선택</option>
                        <option value="개발팀">개발팀</option>
                        <option value="기술지원팀">기술지원팀</option>
                        <option value="경영지원팀">경영지원팀</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">직급</label>
                    <input type="text" class="form-control" name="em_posi">
                </div>

                <div class="mb-3">
                    <label class="form-label">요청 사유</label>
                    <textarea class="form-control" rows="3" name="em_etc" placeholder="사용 목적 또는 간단한 요청 내용을 입력해주세요"></textarea>
                </div>

                <button type="submit" class="btn btn-success w-100">
                    가입요청
                </button>

                <div class="text-center mt-3">
                    <a href="./login">← 로그인 화면으로</a>
                </div>

            </form>
        </div>
    </div>

</div>
</section>
</div>

</body>
</html>
