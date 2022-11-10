<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/table.css" rel="stylesheet" />

<main>

	<div class="container-fluid px-4">
		<h1 class="mt-4">내가 쓴 글</h1>
		<ol class="breadcrumb mb-4">
		</ol>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i> My Board List
			</div>
			<div class="card-body">
				<table>
					<colgroup>
						<col width="50%" />
						<col width="50%" />
					</colgroup>
					<thead>
						<tr>
							<th>게시물 제목</th>
							<th>작성일자</th>
						</tr>
					</thead>
					<tbody>

						<!-- 현재 사용자(세션ID)에 해당하는 구매목록 리스트 / buyerid가 사용자 ID와 같을 게시글 정보만 출력 -->
						<c:forEach var="boardDTO" items="${list}" varStatus="stat">
							<tr>
								<td><a href='${webapproot}/boarddetailform.do?productnum=${boardDTO.productnum}'>${boardDTO.title}</a></td>
								<td>${boardDTO.wdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<a href="${webapproot}/mainform.do" class="btn btn-secondary"
			style="float: right;">메인화면으로 돌아가기</a>
	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />