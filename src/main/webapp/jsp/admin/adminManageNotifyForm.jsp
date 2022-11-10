<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/table.css" rel="stylesheet" />

<main>

   <div class="container-fluid px-4">
      <ol class="breadcrumb mb-4">
      </ol>
      <div class="card mb-4">
         <div class="card-header">
            <i class="fas fa-table me-1"></i> 신고글 목록
         </div>
         <div class="card-body">
         
            <table>
               <colgroup>
                  <col width="500px" />
                  <col width="1500px" />
                  <col width="500px" />
               </colgroup>
               
               <thead>
                  <tr>
                     <th>신고자ID</th>
                     <th>신고사유</th>
                     <th>신고일시</th>
                  </tr>
               </thead>
               
               <tbody>
                  <c:forEach var="notifylistDTO" items="${list}" varStatus="stat">		
                  <!-- 신고글 목록 핸들러에서 신고글 리스트를 받아오며 notifylistDTO라는 이름으로 notifylistDTO 객체를 하나씩 담는다 -->
                     <tr>
                        <td>${notifylistDTO.notifyuserid}</td>		<!-- notifylistDTO에서 notifyuserid를 출력 -->
                        <td><a href="${webapproot}/notifydetail.do?notifyid=${notifylistDTO.notifyid}">${notifylistDTO.notifyreason}</a></td>
                        <!-- 신고사유를 클릭하면 해당 신고사유에 해당하는 신고세부내용폼으로 이동 -->
                        <td>${notifylistDTO.notifydate}</td>	<!-- 신고글 작성 날짜 데이터 -->
                     </tr>
                  </c:forEach>
               </tbody>
               
            </table>
            
         </div>
      </div>
      <a href="${webapproot}/adminmanageuserform.do">회원 목록 이동</a>		<!-- 회원관리목록으로 이동 -->
   </div>

</main>

<jsp:include page="/jsp/bottom.jsp" />