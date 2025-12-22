<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ===== 팝업 전용 SCRIPT ===== -->
<script type="text/javascript">
function setCookie(name, value, days) {
  const date = new Date();
  date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
  document.cookie = name + "=" + value +
    "; expires=" + date.toUTCString() +
    "; path=/";
}

function getCookie(name) {
  const value = "; " + document.cookie;
  const parts = value.split("; " + name + "=");
  if (parts.length === 2) return parts.pop().split(";").shift();
}

function closeRenewalNotice() {
  if (document.getElementById("hideToday").checked) {
    setCookie("hideRenewalNotice", "Y", 1);
  }
  bootstrap.Modal.getInstance(
    document.getElementById("renewalNotice")
  ).hide();
}

document.addEventListener("DOMContentLoaded", function () {
  if (getCookie("hideRenewalNotice") !== "Y") {
    const modal = new bootstrap.Modal(
      document.getElementById("renewalNotice"),
      {
        backdrop: false,
        keyboard: true
      }
    );
    modal.show();
  }
});
</script>

<!-- ===== 팝업 전용 STYLE ===== -->
<style type="text/css">
.renewal-card {
  background-color: #ffffff;
  border: 1px solid #e6e8ec;
  border-radius: 12px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  width: 420px;
  min-height: 260px;
}

.renewal-card .modal-header {
  padding: 12px 20px 6px;
  border: none;
}

.renewal-title {
  font-size: 11px;
  letter-spacing: 0.18em;
  color: #9aa0a6;
  font-weight: 600;
}

.renewal-card .modal-body {
  padding: 16px 24px 20px;
  text-align: center;
}

.renewal-card h5 {
  font-size: 17px;
  font-weight: 600;
  color: #1f2937;
}

.renewal-card p {
  font-size: 14px;
  line-height: 1.6;
  color: #6b7280;
}

.renewal-card .modal-footer {
  padding: 12px 24px 18px;
  border: none;
  flex-direction: column;
}

.renewal-card .form-check-label {
  font-size: 13px;
  color: #6b7280;
}

.renewal-card .btn-outline-secondary {
  border-color: #d1d5db;
  color: #374151;
}

.renewal-card .btn-outline-secondary:hover {
  background-color: #f3f4f6;
  color: #111827;
}

.modal-backdrop {
  display: none;
}
</style>

<!-- ===== 홈페이지 리뉴얼 공지 팝업 ===== -->
<div class="modal fade" id="renewalNotice" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content renewal-card">

      <div class="modal-header">
        <h6 class="modal-title renewal-title">NOTICE</h6>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <div class="modal-body">
        <h5>홈페이지 리뉴얼 안내</h5>
        <p>
          보다 안정적인 서비스 제공을 위해<br>
          홈페이지 리뉴얼 작업을 진행 중입니다.<br>
          <strong>일부 기능 이용에 제한</strong>이 있을 수 있습니다.
        </p>
      </div>

      <div class="modal-footer">
        <div class="form-check mb-2">
          <input class="form-check-input" type="checkbox" id="hideToday">
          <label class="form-check-label" for="hideToday">
            하루 동안 보지 않기
          </label>
        </div>
        <button type="button"
                class="btn btn-outline-secondary btn-sm rounded-pill px-4"
                onclick="closeRenewalNotice()">
          확인
        </button>
      </div>

    </div>
  </div>
</div>
