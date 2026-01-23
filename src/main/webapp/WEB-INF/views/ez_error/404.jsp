<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/header.jsp" %>

<div class="container-fluid content-inner py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card text-center shadow">
                <div class="card-body p-5">
                    <i class="fa fa-exclamation-triangle text-warning" style="font-size:60px;"></i>
                    <h2 class="mt-4">404</h2>
                    <h5 class="mb-3">페이지를 찾을 수 없습니다</h5>
                    <p class="text-muted">
                        요청하신 페이지가 존재하지 않거나<br>
                        주소가 잘못되었습니다.
                    </p>
                    <a href="/ezform/main" class="btn btn-primary rounded-pill mt-3">
                        메인으로 이동
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
