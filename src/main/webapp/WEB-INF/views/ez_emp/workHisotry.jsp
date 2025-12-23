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
	               <div class="table-responsive">
	                  <table id="user-list-table" class="table table-striped" role="grid" data-toggle="data-table">
	                     <thead>
	                       <tr>
							 <th class="text-center">기록</th>
							 <th class="text-center">일시</th>
						   </tr>
	                     </thead>
	                     <tbody>
	                     	<c:forEach items="${HistoryList}" var="list">
								<tr>
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