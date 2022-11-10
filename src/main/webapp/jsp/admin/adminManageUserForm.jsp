<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/table.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<main>

	<div class="container-fluid px-4">
		<ol class="breadcrumb mb-4">
		</ol>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i> 회원 목록
			</div>
			<div class="card-body">
			
				<form action="${webapproot}/blacklistproc.do" method="Post">	<!-- 블랙리스트 데이터 저장 폼  -->
				
					<table style="border: 1px solid #000000; text-align: center;">
						<colgroup>
							<col width="500px" />
							<col width="1500px" />
							<col width="500px" />
						</colgroup>
						
						<thead>
							<tr>
								<th>회원ID</th>
								<th>가입일자</th>
								<th>블랙리스트</th>
							</tr>
						</thead>

						<tbody>
							<input type="hidden" name="listsize" value="${list.size()}" />
							<c:forEach var="userinfo" items="${list}" varStatus="status">		<!-- 회원가입유저 목록(userDTO)을 가지는 list를 userinfo 변수로 하나씩 사용 -->
								<tr>
									<td><input type="hidden" name="${status.index}" value="${userinfo.userid}" />${userinfo.userid}</td>	<!-- userid 출력 -->
									<td>${userinfo.joindate}</td>		<!-- 회원가입 날짜 -->
									<td>
										<!-- 블랙리스트 여부(체크박스)  -->
										<!-- 블랙리스트 여부가 yes이면 체크 상태로 표시 no 면 체크 해제 -->
										<input type="checkbox" id="${status.index}" name="checkbox" value="${userinfo.blackyn}"
											<c:if test="${userinfo.blackyn eq 'yes'}">checked</c:if> 
										/> 	
										
										<span id="blackyn${status.index}">${userinfo.blackyn}</span></td>
								</tr>
								<input type="hidden" name="blackyn${status.index}" id="blackynparam${status.index}" value="${userinfo.blackyn}" />		<!-- 블랙리스트 여부 저장 태그-->
								
								<!-- 체크되어 있으면 블랙리스트 여부 저장 태그 값 yes 아니면 no 값 -->
								<script>		
									$(document).ready(function() {
										$("#${status.index}").change(function() {
											if ($("#${status.index}").is(":checked")) {
												$("#blackyn${status.index}").text("yes");
												$("#blackynparam${status.index}").val("yes");
											} else {
												$("#blackyn${status.index}").text("no");
												$("#blackynparam${status.index}").val("no");
											}
										});
									});
								</script>
								
							</c:forEach>
						</tbody>
					</table>
					<br />
					<input type="button" value="회원 정보 수정" onclick="this.form.submit()" />	<!-- 블랙리스트 처리 핸들러로 데이터를 전송 -->
				</form>
				
			</div>
		</div>
			<a href = "${webapproot}/adminmanagenotifyform.do">신고글 관리페이지 이동</a>		<!-- 신고글 관리페이지로 이동 -->
	</div>

</main>



<jsp:include page="/jsp/bottom.jsp" />