<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>참조인 선택</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/libs.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hope-ui.css"/>
<style>
    body { padding:20px; font-family:'Segoe UI', sans-serif; background:#f5f7fa; }
    h4 { color:#0d6efd; margin-bottom:15px; }
    .emp-card { display:flex; justify-content:space-between; align-items:center;
        padding:10px 15px; margin-bottom:10px; background:#fff; border-radius:8px;
        box-shadow:0 2px 6px rgba(0,0,0,0.1); cursor:pointer; transition:all 0.2s ease-in-out; }
    .emp-card:hover { transform:translateY(-2px); box-shadow:0 4px 12px rgba(0,0,0,0.15); background-color:#e9f2ff; }
    .emp-info { display:flex; flex-direction:column; }
    .emp-info span { font-size:0.9rem; color:#555; }
    .emp-name { font-weight:bold; color:#0d6efd; }
    .emp-email { font-size:0.85rem; color:#333; }
    .emp-dept { font-size:0.85rem; color:#777; }
    .btn-select { background:#0d6efd; color:#fff; border:none; padding:5px 12px; border-radius:5px; font-size:0.85rem; transition:background 0.2s ease-in-out; }
    .btn-select:hover { background:#0b5ed7; }
    .container { max-width:600px; margin:0 auto; }
</style>

<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.emp-card').forEach(card => {
        card.querySelector('.btn-select').addEventListener('click', () => {
            const email = card.dataset.email;
            const name = card.dataset.name;

            console.log('Popup email:', email, 'name:', name); // 팝업 디버깅

            if(window.opener && !window.opener.closed){
                window.opener.addRef(email, name); // 부모창 호출
                window.close();
            }
        });
    });
});
</script>
</head>
<body>
<div class="container">
    <h4>참조인 선택</h4>
    <p>사원을 클릭하면 참조인으로 추가됩니다.</p>

    <c:forEach items="${empList}" var="emp">
        <div class="emp-card" 
             data-email="${fn:escapeXml(emp.em_email)}" 
             data-name="${fn:escapeXml(emp.em_name)}">
            <div class="emp-info">
                <span class="emp-name">${fn:escapeXml(emp.em_name)}</span>
                <span class="emp-email">${fn:escapeXml(emp.em_email)}</span>
                <span class="emp-dept">${fn:escapeXml(emp.em_dept)}</span>
            </div>
            <button type="button" class="btn-select">선택</button>
        </div>
    </c:forEach>
</div>
</body>
</html>
