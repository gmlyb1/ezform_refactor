<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/images/favicon.ico" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/libs.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/hope-ui.css?v=1.0.2">  
<script src="${pageContext.request.contextPath }/resources/js/jQuery-2.1.4.min.js"></script>

<script>
$(document).on("click", ".msg-title", function () {
    var msSeq = $(this).data("id");
    var $row = $(this).closest("tr"); // 클릭한 tr 가져오기

    $.ajax({
        url: "${pageContext.request.contextPath}/ez_message/detail",
        type: "GET",
        data: { msSeq: msSeq },
        success: function (data) {
            $("#modalTitle").text(data.ms_title);
            $("#modalSender").text(data.sender_name);
            $("#modalDate").text(data.create_date);
            $("#modalContent").text(data.ms_content);

            $("#messageDetailModal").modal("show");

            // 읽음 표시
            $row.addClass("read");
        },
        error: function () {
            alert("쪽지 조회 실패");
        }
    });

    // 모달 닫기
    $(".close").off("click").on("click", function() {
        $("#messageDetailModal").modal('hide');
    });
// 제이쿼리 끝
});
	$(document).on("change", "#checkAll", function () {
	
		$(".msgCheck").prop("checked", this.checked);
	
	});

	$(document).on("change", ".msgCheck", function () {
	 if (!this.checked) {
	 
		 $("#checkAll").prop("checked", false);
	 
	 } else if ($(".msgCheck:checked").length === $(".msgCheck").length) {
	 
		 $("#checkAll").prop("checked", true);
	 }
	});

	$(document).on("click", "#deleteBtn", function () {

	    let selected = [];
	
	    $(".msgCheck:checked").each(function () {
	        selected.push($(this).val());
	    });
	
	    if (selected.length === 0) {
	        alert("삭제할 쪽지를 선택하세요.");
	        return;
	    }
	
	    if (!confirm("선택한 쪽지를 삭제하시겠습니까?")) {
	        return;
	    }
	
	    $.ajax({
	        url: "${pageContext.request.contextPath}/ez_message/deleteSelected",
	        type: "POST",
	        traditional: true,   // ⭐ List<Integer> 받을 때 필수
	        data: { msSeqs: selected },
	        success: function () {
	            // 화면에서 제거
	            selected.forEach(function (seq) {
	                $("#msg_" + seq).remove();
	            });
	
	            $("#checkAll").prop("checked", false);
	            alert("삭제되었습니다.");
	        },
	        error: function () {
	            alert("삭제 실패");
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
	               <div class="table-responsive">
	                  <table id="user-list-table" class="table table-striped" role="grid" data-toggle="data-table">
	                     <thead>
	                       <tr>
	                       	 <th><input class="form-check-input" type="checkbox" id="checkAll"></th>
	                       	 <th class="text-center">번호</th>
							 <th class="text-center">주제</th>
							 <th class="text-center">내용</th>
							 <th class="text-center">보낸사람</th>
							 <th class="text-center">작성일자</th>
						   </tr>
	                     </thead>
	                     <tbody>
	                     	<c:forEach items="${msg_result}" var="list">
								<tr id="msg_${list.ms_seq}">
								    <td> <input type="checkbox" class="form-check-input msgCheck" value="${list.ms_seq}"> </td>
									<td class="text-center">${list.rowNum}</td>
									<td class="text-center">
									    <a href="javascript:void(0)"
									       class="msg-title"
									       data-id="${list.ms_seq}">
									        ${list.ms_title}
									    </a>
									</td>
									<td class="text-center">${list.ms_content}</td>
									<td class="text-center">${list.sender_name}</td>
									<td class="text-center">${list.create_date}</td>
								</tr>
							</c:forEach>
	                     </tbody>
	                  </table>
	                  <button type="button" id="deleteBtn" class="btn btn-danger" style="margin-left:10px;">
				        삭제
				      </button>
	               </div>
	            </div>
	         </div>
	      </div>
	   </div>
	</div>
</div>

<!-- 쪽지 상세 모달 -->
<div class="modal fade" id="messageDetailModal" tabindex="-1">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title">쪽지 상세</h5>
        <button type="button" class="btn btn-danger close" data-dismiss="modal">
          &times;
        </button>
      </div>

      <div class="modal-body">
        <table class="table table-bordered">
          <tr>
            <th style="width:20%">제목</th>
            <td id="modalTitle"></td>
          </tr>
          <tr>
            <th>보낸사람</th>
            <td id="modalSender"></td>
          </tr>
          <tr>
            <th>작성일</th>
            <td id="modalDate"></td>
          </tr>
          <tr>
            <th>내용</th>
            <td id="modalContent" style="white-space:pre-line;"></td>
          </tr>
        </table>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close" data-dismiss="modal">
          닫기
        </button>
      </div>

    </div>
  </div>
</div>
	

<%@ include file="../include/footer.jsp" %>