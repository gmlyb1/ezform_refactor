<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/images/favicon.ico" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/libs.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/hope-ui.css?v=1.0.2">  
<script src="${pageContext.request.contextPath }/resources/js/jQuery-2.1.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    const chartLabels = [
        <c:forEach items="${HistoryListCnt}" var="item" varStatus="status">
            "${item.work_status}"<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    const chartData = [
        <c:forEach items="${HistoryListCnt}" var="item" varStatus="status">
            ${item.work_status_cnt}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    // 각 막대 색깔 배열
    const colors = [
        'rgba(54, 162, 235, 0.7)',
        'rgba(255, 99, 132, 0.7)',
        'rgba(255, 206, 86, 0.7)',
        'rgba(75, 192, 192, 0.7)',
        'rgba(153, 102, 255, 0.7)',
        'rgba(255, 159, 64, 0.7)'
    ];

    const borderColors = [
        'rgba(54, 162, 235, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
    ];

    const ctx = document.getElementById('historyChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: chartLabels,
            datasets: [{
                label: '업무 기록 현황',
                data: chartData,
                backgroundColor: chartData.map((_, i) => colors[i % colors.length]), // 데이터별 색
                borderColor: chartData.map((_, i) => borderColors[i % borderColors.length]),
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: false } },
            scales: { y: { beginAtZero: true, ticks: { stepSize: 1 } } }
        }
    });
});
</script>


<div class="conatiner-fluid content-inner mt-n5 py-0">
	<div>
	   <div class="row">
	      <div class="col-sm-12">
	         <div class="card">
	            <div class="card-header d-flex justify-content-between">
	               <div class="header-title">
	                  <h4 class="card-title"></h4>
	               </div>
	            </div>
	                      
	            <div class="card-body px-0">
	            
	            	<div class="mb-4">
				        <canvas id="historyChart" height="100"></canvas>
				    </div>
	            
				    <!-- 통계 중앙 배치 -->
				    <div class="text-center mb-4">
				        <c:forEach items="${HistoryListCnt}" var="list">
				            <span class="badge bg-primary mx-2 py-2 px-3 fs-5">
				                ${list.work_status}: ${list.work_status_cnt}
				            </span>
				        </c:forEach>
				    </div>
	            
	               <div class="table-responsive">
	                  <table id="user-list-table" class="table table-striped" role="grid" data-toggle="data-table">
	                     <thead>
	                       <tr>
							 <th class="text-center">아이디</th>
							 <th class="text-center">기록</th>
							 <th class="text-center">일시</th>
						   </tr>
	                     </thead>
	                     <tbody>
	                     	<c:forEach items="${HistoryList}" var="list">
								<tr>
								   <c:choose>
									<c:when test="${list.user_email != null || not empty list.user_email }">
										<td class="text-center">${list.user_email}</td>
									</c:when>
									<c:otherwise>
										<td class="text-center">-</td>
									</c:otherwise>								   
								   </c:choose>
									<td class="text-center">${list.work_status}</td>
									<td class="text-center">
									    <fmt:formatDate value="${list.work_regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
								</tr>
							</c:forEach>
	                     </tbody>
	                  </table>
	               </div>
	            </div>
	         </div>
	      </div>
	   </div>
	</div>
</div>
	

<%@ include file="../include/footer.jsp" %>