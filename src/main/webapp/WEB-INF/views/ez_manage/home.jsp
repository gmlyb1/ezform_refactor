<%@page import="java.util.Date"%>
<%@page import="com.ezform.domain.EZ_empVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />

<style>
.card-body {
    height: 260px;          /* 🔥 핵심 */
}

.card-title {
    font-size: 1rem;
    font-weight: 600;
}

canvas {
    width: 100% !important;
    height: 100% !important;
}
</style>

<div class="container mt-4">
    <div class="row g-3">

        <c:set var="labels"
            value="${fn:split('현재 접속자,오늘 방문자,누적 방문자,미결재,안읽은 메일,안 읽은 쪽지,오늘 일정,1:1 문의,알림', ',')}" />

        <c:forEach var="i" begin="0" end="8">
            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-header text-center">
                        <h5 class="card-title mb-0">${labels[i]}</h5>
                    </div>
                    <div class="card-body">
                        <canvas id="chart${i}"></canvas>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {

    const labels = [
        "현재 접속자", "오늘 방문자", "누적 방문자",
        "미결재", "안읽은 메일", "안 읽은 쪽지",
        "오늘 일정", "1:1 문의", "알림"
    ];

    const dataValues = [
        ${currentUserCount},
        ${today},
        ${total},
        ${unApaymentList},
        ${unReadMailList},
        ${unreadMsg},
        ${todayEventList},
        ${unCheckInquiryList},
        ${unreadNotificationList}
    ];

    console.log("dataValues =", dataValues);

    for (let i = 0; i < 9; i++) {
        const canvas = document.getElementById("chart" + i);
        if (!canvas) continue;

        const ctx = canvas.getContext("2d");

        new Chart(ctx, {
            type: "bar",
            data: {
                labels: [labels[i]],
                datasets: [{
                    data: [dataValues[i]],
                    backgroundColor: "#4e73df"
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { precision: 0 }
                    }
                }
            }
        });
    }
});
</script>

<%@ include file="../include/footer.jsp"%>
