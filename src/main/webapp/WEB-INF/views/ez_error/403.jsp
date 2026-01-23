<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/header.jsp" %>

<div class="container-fluid content-inner py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card text-center shadow">
                <div class="card-body p-5">
                    <i class="fa fa-lock text-danger" style="font-size:60px;"></i>
                    <h2 class="mt-4">403</h2>
                    <h5 class="mb-3">접근 권한이 없습니다</h5>
                    <p class="text-muted">
                        해당 페이지에 접근할 권한이 없습니다.<br>
                        관리자에게 문의해 주세요.
                    </p>
                    <a href="/ezform/main" class="btn btn-outline-danger rounded-pill mt-3">
                        메인으로 이동
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
