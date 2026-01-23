<%@page import="com.ezform.domain.EZ_boardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container-fluid content-inner mt-n5 py-0">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow-sm">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h4 class="card-title mb-0">휴가 신청 상세</h4>
                    <a href="holidayList" class="btn btn-secondary btn-sm">목록</a>
                </div>
                <div class="card-body">
                    <!-- 신청자 정보 -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">신청자</label>
                            <p class="form-control-plaintext">${detail.user_id}</p>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">결재자</label>
                            <p class="form-control-plaintext">${detail.approver_id}</p>
                        </div>
                    </div>

                    <!-- 휴가 종류 / 상태 -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">휴가 종류</label>
                            <p class="form-control-plaintext">${detail.ap_type}</p>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">결재 상태</label>
                            <p class="form-control-plaintext">
                                <span class="badge bg-${detail.ap_status == '승인' ? 'success' : detail.ap_status == '반려' ? 'danger' : 'warning'}">
                                    ${detail.ap_status}
                                </span>
                            </p>
                        </div>
                    </div>

                    <!-- 기간 -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">시작일</label>
                            <p class="form-control-plaintext">
                                <fmt:formatDate value="${detail.ap_start_date}" pattern="yyyy-MM-dd"/>
                            </p>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">종료일</label>
                            <p class="form-control-plaintext">
                                <fmt:formatDate value="${detail.ap_end_date}" pattern="yyyy-MM-dd"/>
                            </p>
                        </div>
                    </div>

					<div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">참조인</label>
                            <p class="form-control-plaintext">
							    ${fn:replace(detail.refIds, ",", ", ")}
							</p>
                        </div>
                    </div>

                    <!-- 사유 -->
                    <div class="mb-3">
                        <label class="form-label fw-bold">사유</label>
                        <textarea class="form-control" rows="5" style="resize:none;" readonly>${detail.ap_reason}</textarea>
                    </div>

                    <!-- 생성 / 수정일 -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">생성일</label>
                            <p class="form-control-plaintext">
                                <fmt:formatDate value="${detail.ap_create_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </p>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">수정일</label>
                            <p class="form-control-plaintext">
                                <fmt:formatDate value="${detail.ap_update_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </p>
                        </div>
                    </div>
                    
                    <div class="text-end mt-3">
					    <!-- 승인 -->
					    <c:if test="${sessionData.em_email eq detail.approver_id}">
	                   		 <form action="/ezform/ez_aPayment/updateStatus" method="post" style="display:inline;">
						        <input type="hidden" name="ap_id" value="${detail.ap_id}">
						        <input type="hidden" name="ap_status" value="승인">
						        <button type="submit" class="btn btn-success" onclick="return confirm('승인하시겠습니까?');">승인</button>
						    </form>
						    <!-- 반려 -->
						    <form action="/ezform/ez_aPayment/updateStatus" method="post" style="display:inline;">
						        <input type="hidden" name="ap_id" value="${detail.ap_id}">
						        <input type="hidden" name="ap_status" value="반려">
						        <button type="submit" class="btn btn-danger" onclick="return confirm('반려하시겠습니까?');">반려</button>
						    </form>
                    	</c:if>
					
					    <!-- 삭제 버튼 -->
						<form action="/ezform/ez_aPayment/deleteHoliday" method="post" style="display:inline;">
						    <input type="hidden" name="ap_id" value="${detail.ap_id}">
						    <button type="submit" class="btn btn-warning" onclick="return confirm('삭제하시겠습니까?');">삭제</button>
						</form>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="../include/footer.jsp" %>