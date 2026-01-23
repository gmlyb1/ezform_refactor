<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ include file="../include/header.jsp" %>

<div class="container-fluid content-inner py-5">
    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-6">
            <div class="card text-center shadow">
                <div class="card-body p-5">
                    <i class="fa fa-bug text-danger" style="font-size:60px;"></i>
                    <h2 class="mt-4">500</h2>
                    <h5 class="mb-3">시스템 오류가 발생했습니다</h5>
                    <p class="text-muted">
                        처리 중 오류가 발생했습니다.<br>
                        잠시 후 다시 시도해 주세요.
                    </p>

                    <!-- 개발 환경에서만 표시 추천 -->
                    <c:if test="${not empty exception}">
                        <div class="alert alert-light text-start mt-4">
                            <small>${exception}</small>
                        </div>
                    </c:if>

                    <a href="/ezform/main" class="btn btn-danger rounded-pill mt-3">
                        메인으로 이동
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
