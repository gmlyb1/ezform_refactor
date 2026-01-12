<%@page import="java.util.Date"%>
<%@page import="com.ezform.domain.EZ_empVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="include/popup.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />

<c:if test="${not empty msg}">
<script>
    alert("${msg}");
</script>
</c:if>

<style>
.dashboard-cell {
    padding: 12px 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 80px; /* 최소 높이 지정 */
    transition: all 0.3s ease;
}

.dashboard-cell .value {
    font-size: 2rem; /* 기본 크기 */
    font-weight: bold;
}

@media (max-width: 1200px) {
    .dashboard-cell .value {
        font-size: 1.8rem;
    }
}

@media (max-width: 992px) {
    .dashboard-cell .value {
        font-size: 1.6rem;
    }
}

@media (max-width: 768px) {
    .dashboard-cell {
        min-height: 70px;
    }
    .dashboard-cell .value {
        font-size: 1.4rem;
    }
}

@media (max-width: 576px) {
    .dashboard-cell {
        min-height: 60px;
    }
    .dashboard-cell .value {
        font-size: 1.2rem;
    }
}

</style>

<div class="conatiner-fluid content-inner mt-n5 py-0">
	<div class="row">
		<div class="col-md-12 col-lg-9">
			<div class="row">
				<!--------------------------------- 캘린더 영역 ------------------------------>
				<div class="col-md-12">
					<div class="row">
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-12">
									<div class="card  ">
										<div class="card-body">
											<div id="wrapper">
												<!-- <div id="calendar1" class="calendar-s" style="height:200px;"></div> -->
												<!-- <div id="loading"></div> -->
												<div id="calendar" class="calendar-s"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--------------------------------- 캘린더 영역 ------------------------------>

				<!--------------------------------- 커뮤니티 영역 ------------------------------>
				<div class="col-md-12 col-xl-6">
					<div class="card">
						<div class="card-header d-flex justify-content-between flex-wrap">
							<div class="header-title">
								<h4 class="card-title">커뮤니티</h4>
							</div>
							<div class="dropdown">

								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="dropdownMenuButton1">
									<li><a class="dropdown-item" href="#">This Week</a></li>
									<li><a class="dropdown-item" href="#">This Month</a></li>
									<li><a class="dropdown-item" href="#">This Year</a></li>
								</ul>
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table id="datatable" class="table table-striped">
									<thead>
										<tr>
											<th>제목</th>
											<th style="text-align:right; font-size:12px;">
												<a href="/ezform/board/listPage" style="color:rgb(130,130,130)">더보기</a>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${boardList == null or fn:length(boardList) == 0}">
												<tr>
													<td><p>등록된 커뮤니티가 없습니다.</p></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach begin="0" end="2" items="${boardList }" var="bd">
													<tr>
														<td width="80%;">
															<a href="/ezform/board/read?cm_bnum=${bd.cm_bnum}">
																${bd.cm_title }
																<c:if test="${(now.time - bd.cm_regdate.time) <= (3 * 24 * 60 * 60 * 1000)}">
															        <span class="badge bg-danger ms-1">NEW</span>
															    </c:if>
															</a>
														</td>
														<td>
															<div>
																<fmt:formatDate pattern="yyyy-MM-dd" value="${bd.cm_regdate}"/>
															</div>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>	
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--------------------------------- 커뮤니티 영역 ------------------------------>

				<!--------------------------------- 공지사항 영역 ------------------------------>
				<div class="col-md-12 col-xl-6">
					<div class="card">
						<div class="card-header d-flex justify-content-between flex-wrap">
							<div class="header-title">
								<h4 class="card-title">공지사항</h4>
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table id="datatable" class="table table-striped">
									<thead>
										<tr>
											<th>제목</th>
											<th style="text-align:right; font-size:12px;">
												<a href="/ezform/ez_notice/listAll" style="color:rgb(130,130,130)">더보기</a>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${notiList == null or fn:length(notiList) == 0}">
												<tr>
													<td><p>등록된 공지사항이 없습니다.</p></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach begin="0" end="2" items="${notiList }" var="noti">
													<tr>
														<td width="80%;">
															<a href="/ezform/ez_notice/read?not_num=${noti.not_num }" class="num"> ${noti.not_title }
																<c:if test="${(now.time - noti.not_regdate.time) <= (3 * 24 * 60 * 60 * 1000)}">
															        <span class="badge bg-danger ms-1">NEW</span>
															    </c:if>
															</a>
														</td>
														<td>
															<div>
																<fmt:formatDate pattern="yyyy-MM-dd" value="${noti.not_regdate}"/>
															</div>	
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>	
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--------------------------------- 공지사항 영역 ------------------------------>
			</div>
		</div>
		<div class="col-md-12 col-lg-3">
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="card">
					  <div class="card-header">
					    <div class="header-title">
					      <h4 class="card-title">모니터링</h4>
					    </div>
					  </div>
					  <%-- <div class="card-body text-center">
					  	<p style="font-size:14px; color:#777;">현재 접속자 수</p>
					    <h2 style="font-weight:bold;">${currentUserCount}</h2>
					  	
					  	<hr>
					  	
					    <p style="font-size:14px; color:#777;">오늘 방문자</p>
					    <h2 style="font-weight:bold;">${today}</h2>
					
					    <hr>
					
					    <p style="font-size:14px; color:#777;">누적 방문자</p>
					    <h2 style="font-weight:bold;">${total}</h2>
					  </div> --%>
					 <div class="card-body">
					  <div class="row text-center g-0">
					
					   <!-- 1줄 : 접속 현황 -->
						<div class="col-4 dashboard-cell text-white" style="background-color:#28a745; border-radius:6px;">
						  <p class="label">현재 접속자</p>
						  <h4 class="value">${currentUserCount}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-start" style="background-color:#007bff; border-radius:6px;">
						  <p class="label">오늘 방문자</p>
						  <h4 class="value">${today}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-start" style="background-color:#6f42c1; border-radius:6px;">
						  <p class="label">누적 방문자</p>
						  <h4 class="value">${total}</h4>
						</div>
						
						<!-- 2줄 : 나의 할 일 -->
						<div class="col-4 dashboard-cell text-white border-top" style="background-color:#dc3545; border-radius:6px; cursor:pointer;"
						     onclick="location.href='/ezform/ez_aPayment/list'">
						  <p class="label">미결재</p>
						  <h4 class="value">${unApaymentList}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-top border-start" style="background-color:#0d6efd; border-radius:6px; cursor:pointer;"
						     onclick="location.href='/ezform/ez_mail/recMail'">
						  <p class="label">안읽은 메일</p>
						  <h4 class="value">${unReadMailList}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-top border-start" style="background-color:#ffc107; border-radius:6px; cursor:pointer;"
						     onclick="location.href='/ezform/ez_message/sendList'">
						  <p class="label">안 읽은 쪽지</p>
						  <h4 class="value">${unreadMsg}</h4>
						</div>
						
						<!-- 3줄 : 업무 상태 -->
						<div class="col-4 dashboard-cell text-white border-top" style="background-color:#198754; border-radius:6px;"
							onclick="location.href='/ezform/calendar/'">
						  <p class="label">오늘 일정</p>
						  <h4 class="value">${todayEventList}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-top border-start" style="background-color:#0dcaf0; border-radius:6px;"
						onclick="location.href='/ezform/ez_inq/list'">
						  <p class="label">1:1문의</p>
						  <h4 class="value">${unCheckInquiryList}</h4>
						</div>
						
						<div class="col-4 dashboard-cell text-white border-top border-start" style="background-color:#6c757d; border-radius:6px;"
						onclick="location.href='/ezform/ez_notification/list'">
						  <p class="label">알림</p>
						  <h4 class="value">${unreadNotificationList}</h4>
						</div>
					  </div>
					</div>

					</div>
					<div class="card">
						<div class="card-body d-flex justify-content-around text-center" style="padding: 24px 24px 0 24px;">
							<div
								style="color: #666; font-size: 35px; text-align: center;"
								id="clock">
							</div>
						</div>
						<div class="card-body">
							<div class="text-center">
	                           <div class="user-profile">
		                           <div class="mt-3">
		                              <h3 class="d-inline-block">${resultVO.em_name}</h3><br>
		                              <p class="d-inline-block pl-3">${resultVO.em_dept}</p>
		                           </div>
		                           <img
										src="${pageContext.request.contextPath }/resources/upload/mem_Image/${resultVO.em_image}"
										alt="User-Profile"
										class="img-fluid avatar avatar-155 avatar-rounded"
										onerror="this.src='${pageContext.request.contextPath }/resources/images/silhouette.png'">
	                           </div>
	                        </div>
						</div>
						<!------------------------------vvv--- 근태 영역 ----vvv--------------------->
						<div class="card-header">
							<div class="header-title">
								<h4 class="card-title" style="text-align:center;">근태입력</h4>
							</div>
						</div>
						<div class="card-body">							
							<div class="text-center">
							   <form action="./ez_emp/wstatus" method="post">
							    
							   		<select name="work_status" class="form-select">
							   			<option value="출근">출근</option>
							   			<option value="퇴근">퇴근</option>
							   			<option value="외근">외근</option>
							   			<option value="휴가">휴가</option>
							   		</select>
									<button class="btn btn-outline-success rounded-pill d-inline-block" style="margin-top:15px;">확인</button>
									
							   </form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-12">
					<div class="card">
						<div class="card-header">
							<div class="header-title">
								<h4 class="card-title">사원 근태 현황</h4>
							</div>
						</div>
						<div class="card-body">
							<ul class="list-inline m-0 p-0">
									
							<c:forEach var="wslist" items="${wslist}" varStatus="status">
								<li class="d-flex mb-4 align-items-center">
									<div class="img-fluid bg-soft-warning rounded-pill">
										<img
											src="${pageContext.request.contextPath }/resources/upload/mem_Image/${wslist.em_image}"
											alt="story-img" class="rounded-pill avatar-40"
											onerror="this.src='${pageContext.request.contextPath }/resources/images/silhouette.png'">
									</div>
									<div class="ms-3 flex-grow-1">
										<h6>${wslist.em_name }</h6>
										<p class="mb-0">
											
											<fmt:formatDate value="${wslist.ez_workVO.work_regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
										</p>
									</div> 
								   <div class="iq-media-group iq-media-group-1">
	                                    <a href="#" class="iq-media-1">
											<div class="icon iq-icon-box-3 rounded-pill">${wslist.ez_workVO.work_status }</div>
										</a>
									</div>	
								</li>
							</c:forEach>
								<!--------- ------------------------------------근태 영역--------------- -->								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<%@ include file="include/footer.jsp"%>