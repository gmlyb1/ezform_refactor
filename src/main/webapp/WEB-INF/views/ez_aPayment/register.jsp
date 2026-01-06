<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function validateHolidayForm() {
    const form = document.holidayForm;

    if (!form.ap_type.value) {
        alert("휴가 종류를 선택하세요");
        form.ap_type.focus();
        return false;
    }
    if (!form.ap_start_date.value) {
        alert("시작일을 선택하세요");
        form.ap_start_date.focus();
        return false;
    }
    if (!form.ap_end_date.value) {
        alert("종료일을 선택하세요");
        form.ap_end_date.focus();
        return false;
    }
    if (!form.ap_reason.value.trim()) {
        alert("사유를 입력하세요");
        form.ap_reason.focus();
        return false;
    }

    const start = new Date(form.ap_start_date.value);
    const end = new Date(form.ap_end_date.value);
    if (end < start) {
        alert("종료일은 시작일 이후여야 합니다");
        form.ap_end_date.focus();
        return false;
    }

    return true;
}
</script>

<div class="container-fluid content-inner mt-n5 py-0">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h4 class="card-title mb-0">휴가 신청</h4>
                   <!--  <a href="holidayList" class="btn btn-secondary btn-sm">목록</a> -->
                </div>

                <div class="card-body">
                    <form name="holidayForm" action="/ezform/ez_aPayment/register" method="post" onsubmit="return validateHolidayForm();">
						
						 <div class="mb-3">
                            <label for="ap_start_date" class="form-label">신청인</label>
                            <input type="text" class="form-control" value="${loginUser.em_email}" readonly>
						    <input type="hidden" name="user_id" value="${loginUser.em_email}">
                        </div>
						
                        <!-- 휴가 종류 -->
                        <div class="mb-3">
                            <label for="ap_type" class="form-label">휴가 종류</label>
                            <select class="form-select form-select-sm" id="ap_type" name="ap_type">
                                <option value="">선택</option>
                                <option value="연차">연차</option>
                                <option value="병가">병가</option>
                                <option value="반차">반차</option>
                                <option value="기타">기타</option>
                            </select>
                        </div>

                        <!-- 시작일 -->
                        <div class="mb-3">
                            <label for="ap_start_date" class="form-label">시작일</label>
                            <input type="date" class="form-control form-control-sm" id="ap_start_date" name="ap_start_date">
                        </div>

                        <!-- 종료일 -->
                        <div class="mb-3">
                            <label for="ap_end_date" class="form-label">종료일</label>
                            <input type="date" class="form-control form-control-sm" id="ap_end_date" name="ap_end_date">
                        </div>

                        <!-- 사유 -->
                        <div class="mb-3">
                            <label for="ap_reason" class="form-label">사유</label>
                            <textarea class="form-control" id="ap_reason" name="ap_reason" rows="5" placeholder="휴가 사유를 입력하세요"></textarea>
                        </div>

                        <!-- 결재자 -->
                        <div class="mb-3">
                            <label for="approver_id" class="form-label">결재자</label>
                            <input type="text" class="form-control form-control-sm" id="approver_id" name="approver_id" placeholder="결재자 입력">
                        </div>

                        <!-- 버튼 -->
                        <div class="text-end">
                            <input type="submit" class="btn btn-primary btn-sm" value="신청">
                            <input type="button" class="btn btn-secondary btn-sm" value="취소" onclick="location.href='holidayList';">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
