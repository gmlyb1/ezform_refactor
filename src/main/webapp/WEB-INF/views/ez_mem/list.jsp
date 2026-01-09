<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
#datatable th, #datatable td {
    text-align: center;
    vertical-align: middle;
}

.emp-photo {
    width: 45px;
    height: 45px;
    border-radius: 50%;
    object-fit: cover;
    border: 1px solid #ddd;
}

.badge-status {
    padding: 6px 10px;
    font-size: 0.85rem;
}
</style>

<div class="container-fluid content-inner mt-n5 py-0">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
            
                <div class="card-header d-flex justify-content-between">
                    <div class="header-title">
                        <h4 class="card-title">직원 현황</h4>
                        <p class="text-muted mb-0">사내 직원 정보 목록</p>
                    </div>
                </div>

                <div class="card-body">
                    <div class="table-responsive">
                        <table id="datatable" class="table table-striped" style="width:100%">
                            <thead>
                                <tr class="table-primary">
                                    <th width="5%">No</th>
                                    <th width="10%">사진</th>
                                    <th width="15%">아이디</th>
                                    <th width="15%">부서</th>
                                    <th width="15%">직급</th>
                                    <th width="20%">이메일</th>
                                    <th width="20%">입사일</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="emp" items="${memList}">
                                    <tr>
                                        <td>${emp.em_id}</td>

                                        <!-- 직원 사진 -->
                                        <td>
                                            <img src="${pageContext.request.contextPath }/resources/upload/mem_Image/${emp.em_image}" alt="User-Profile" class="img-fluid avatar avatar-50 avatar-rounded" onerror="this.src='${pageContext.request.contextPath }/resources/images/silhouette.png'">
                                        </td>

                                        <td>${emp.em_name}</td>
                                        <td>${emp.em_dept}</td>
                                        <td>${emp.em_posi}</td>
                                        <td>${emp.em_email}</td>
										<td>
										    <fmt:formatDate value="${emp.em_regdate}" pattern="yyyy-MM-dd"/>
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

<%@ include file="../include/footer.jsp" %>
