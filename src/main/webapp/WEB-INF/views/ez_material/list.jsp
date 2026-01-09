<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

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
                  <h4 class="card-title">공지사항</h4>
               </div>    
            </div>
            <div class="card-body">
               	<div style="margin-bottom:5px;">
					<input type="button" class="btn btn-outline-primary" value="등록" onclick="location.href='register';"/>		 	
				</div>  	
                <div class="table-responsive">
                <!-- data-toggle="data-table" -->
                  <table id="datatable" class="table table-striped" data-toggle="data-table" style="width:1520px;">
                     <thead>
                        <tr class="table-primary">
                           <th style="display:none"></th>
                           <th width="5%">번호</th>
                           <th width="55%">제목</th>
                           <th width="15%">첨부파일</th>
                           <th width="20%">작성일</th>
                        </tr>
                     </thead>
                     <tbody>
                     	<c:forEach var="bList" items="${bList}">
                     	   <tr>
                     	     <td style="display:none"></td>
				           	 <td>${bList.ma_seq }</td>
							 <td>
								<a href="/ezform/ez_material/read?ma_seq=${bList.ma_seq }" class="num">${bList.ma_title }
									<c:if test="${(now.time - bList.ma_regdate.time) <= (3 * 24 * 60 * 60 * 1000)}">
								        <span class="badge bg-danger ms-1">NEW</span>
								    </c:if>
								</a>
							 </td>
							 
							 <td>
							 	<c:choose>
							 		<c:when test="${bList.ma_file != null }">
							 			<img class="file_img" src="${pageContext.request.contextPath }/resources/images/isFile.png"/>
							 		</c:when>
							 		<c:otherwise>
							 			&nbsp;
							 		</c:otherwise>
							 	</c:choose>
							 </td>
							 <td><fmt:formatDate value="${bList.ma_regdate }" pattern="yyyy-MM-dd hh:mm"/></td>
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