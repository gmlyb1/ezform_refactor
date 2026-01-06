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
	             	<div class="mb-3 text-center">
				       <a href="/ezform/ez_aPayment/register" class="btn btn-success">휴가 신청</a>
				   </div>
	               <div class="table-responsive">
	                  <table id="user-list-table" class="table table-striped" role="grid" data-toggle="data-table">
	                     <thead>
	                       <tr>
							 <th class="text-center">순번</th>
							 <th class="text-center">신청자</th>
							 <th class="text-center">종류</th>
							 <th class="text-center">시작일</th>
							 <th class="text-center">종료일</th>
							 <th class="text-center">사유</th>
							 <th class="text-center">상태</th>
							 <th class="text-center">생성일</th>
							 <th class="text-center">수정일</th>
							 <th class="text-center">결재자</th>
						   </tr>
	                     </thead>
	                     <tbody>
	                     	<c:forEach items="${holiList}" var="list">
								<tr>
									<td class="text-center">${list.ap_id}</td>
									<td class="text-center">${list.user_id}</td>
									<td class="text-center">${list.ap_type}</td>
									<td class="text-center">
										<fmt:formatDate value="${list.ap_start_date}" pattern="yyyy-MM-dd"/> 
									</td>
									<td class="text-center">
										<fmt:formatDate value="${list.ap_end_date}" pattern="yyyy-MM-dd"/> 
									</td>
									<td class="text-center">${list.ap_reason}</td>
									<td class="text-center">
									  <a href="/ezform/ez_aPayment/detail?ap_id=${list.ap_id}">
									    <c:choose>
									        <c:when test="${list.ap_status == '진행'}">
									            <span class="badge bg-primary fs-6 px-3 py-2">${list.ap_status}</span>
									        </c:when>
									        <c:when test="${list.ap_status == '승인'}">
									            <span class="badge bg-success fs-6 px-3 py-2">${list.ap_status}</span>
									        </c:when>
									        <c:when test="${list.ap_status == '반려'}">
									            <span class="badge bg-danger fs-6 px-3 py-2">${list.ap_status}</span>
									        </c:when>
									        <c:otherwise>
									            <span class="badge bg-secondary fs-6 px-3 py-2">${list.ap_status}</span>
									        </c:otherwise>
									    </c:choose>
									    </a>
									</td>
									<td class="text-center">
									    <fmt:formatDate value="${list.ap_create_date}" pattern="yyyy-MM-dd"/>
									</td>
									<td class="text-center">
									    <fmt:formatDate value="${list.ap_update_date}" pattern="yyyy-MM-dd"/>
									</td>
									<td class="text-center">${list.approver_id}</td>
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