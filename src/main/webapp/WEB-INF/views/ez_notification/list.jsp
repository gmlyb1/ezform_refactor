<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script>
function markAllRead() {
    if (!confirm('모든 알림을 읽음 처리하시겠습니까?')) return;

    $.ajax({
        url: '/ezform/ez_notification/readAll',
        type: 'POST',
        success: function (res) {
            alert('모든 알림이 읽음 처리되었습니다.');
            location.reload(); // 가장 간단
        },
        error: function () {
            alert('처리 중 오류가 발생했습니다.');
        }
    });
}
</script>

<style type="text/css">
	span {
		margin-left:25px;
		margin-right:25px;	
	}
	#datatable>thead>tr {
		text-align : center;
	}
	#datatable>tbody>tr {
		text-align : center;
	}
	.file_img {
		width:25px;
		height:25px;
	}
	
</style>

<jsp:useBean id="now" class="java.util.Date" />

<div class="conatiner-fluid content-inner mt-n5 py-0">
   <div class="row">
      <div class="col-sm-12">
         <div class="card">
            <div class="card-header d-flex justify-content-between">
               <div class="header-title">
                  <h4 class="card-title">알림</h4>
               </div>    
            </div>
            <button type="button" class="btn btn-sm btn-outline-primary" onclick="markAllRead()">
		        전체 읽음
		    </button>
            <div class="card-body">
                <div class="table-responsive">
                <!-- data-toggle="data-table" -->
                  <table id="datatable" class="table table-striped" data-toggle="data-table" style="width:1520px;">
                     <thead>
                        <tr class="table-primary">
                           <th style="display:none"></th>
                           <th width="5%">번호</th>
                           <th width="55%">아이디</th>
                           <th width="15%">타입</th>
                           <th width="20%">메시지</th>
                           <th width="10%">링크</th>
                           <th width="20%">날짜</th>
                        </tr>
                     </thead>
                     <tbody>
                     	<c:forEach var="notiList" items="${notificationList}">
                     	   <tr>
                     	     <td style="display:none"></td>
				           	 <td class="text-center">${notiList.noti_id }</td>
							 <td class="text-center">${notiList.receiver_name}</td>
							 <td class="text-center">${notiList.noti_type }</td>
							 <td class="text-center">${notiList.noti_message }</td>
							 <td class="text-center">${notiList.noti_link }</td>
							 <td class="text-center"><fmt:formatDate value="${notiList.create_date }" pattern="yyyy-MM-dd hh:mm"/></td>
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