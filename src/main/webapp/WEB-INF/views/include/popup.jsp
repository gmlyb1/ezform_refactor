<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ===== 팝업 전용 STYLE ===== -->
<style type="text/css">
/* 팝업 카드 스타일 */
.renewal-card {
  position: fixed;
  top: 20px;
  right: 20px;
  width: 400px;
  background: linear-gradient(145deg, #fefefe, #f0f4f8);
  border-radius: 16px;
  box-shadow: 0 12px 28px rgba(0,0,0,0.15);
  padding: 20px 24px;
  font-family: 'Noto Sans KR', sans-serif;
  cursor: move;
  z-index: 9999;
  display: none; /* 초기에는 숨김 */
}

.renewal-card h5 {
  margin:0;
  font-size: 18px;
  font-weight:700;
  color:#111827;
}

.renewal-card p {
  margin:12px 0 0;
  font-size:14px;
  color:#4b5563;
  line-height:1.6;
}

.renewal-card button.close-btn {
  border:none;
  background:none;
  font-size:16px;
  cursor:pointer;
}

.renewal-card .btn-confirm {
  float:right;
  background:#3b82f6;
  color:#fff;
  border:none;
  border-radius:9999px;
  padding:5px 16px;
  font-weight:600;
  cursor:pointer;
}

.renewal-card label {
  font-size:13px;
  color:#6b7280;
}
</style>

<!-- ===== 홈페이지 리뉴얼 완료 팝업 ===== -->
<div id="renewalNotice" class="renewal-card">
  <div style="display:flex; justify-content:space-between; align-items:center;">
      <h5>홈페이지 리뉴얼 완료!</h5>
      <button class="close-btn" onclick="closeRenewalNotice()">✖</button>
  </div>
  <p>
      새로워진 홈페이지에서<br>
      더 편리하고 안정적인 서비스를 제공합니다.<br>
      다양한 기능을 경험해보세요!
  </p>
  <div style="margin-top:12px; display:flex; align-items:center; justify-content:space-between;">
      <label>
          <input type="checkbox" id="hideToday"> 하루 동안 보지 않기
      </label>
      <button class="btn-confirm" onclick="closeRenewalNotice()">확인</button>
  </div>
</div>

<!-- ===== 팝업 전용 SCRIPT ===== -->
<script>
/* ===== 쿠키 설정/조회 ===== */
function setCookie(name, value, days) {
  const date = new Date();
  date.setTime(date.getTime() + (days*24*60*60*1000));
  document.cookie = name+"="+value+"; expires="+date.toUTCString()+"; path=/";
}

function getCookie(name) {
  const value = "; "+document.cookie;
  const parts = value.split("; "+name+"=");
  if(parts.length===2) return parts.pop().split(";").shift();
}

/* ===== 팝업 닫기 ===== */
function closeRenewalNotice() {
  if(document.getElementById("hideToday").checked) {
    setCookie("hideRenewalNotice","Y",1);
  }
  document.getElementById("renewalNotice").style.display="none";
}

/* ===== DOM 로드 후 팝업 표시 및 드래그 ===== */
document.addEventListener("DOMContentLoaded", function(){
  const popup = document.getElementById("renewalNotice");

  // 쿠키 확인 후 표시
  if(getCookie("hideRenewalNotice")!=="Y") {
    popup.style.display="block";

    // 드래그 기능
    let isDragging=false, startX, startY, initialX, initialY;

    popup.addEventListener("mousedown", function(e){
        isDragging=true;
        startX=e.clientX; startY=e.clientY;
        const rect = popup.getBoundingClientRect();
        initialX=rect.left; initialY=rect.top;
    });

    document.addEventListener("mousemove", function(e){
        if(!isDragging) return;
        const dx = e.clientX-startX;
        const dy = e.clientY-startY;
        popup.style.left = initialX + dx + "px";
        popup.style.top = initialY + dy + "px";
      });

    document.addEventListener("mouseup", function(){
        isDragging=false;
    });
  }
});
</script>
