<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/table.css" rel="stylesheet" />

<main style="padding: 0px 10% 0px 10%;">
	<div>
		<div>

			<h2><< ${sessionScope.userid} 님의 쪽지함 >></h2>

		</div>


		<!-- 내가 보낸 쪽지DTO의 데이터를 담은 리스트 시작 -->
		<hr>
		<p>내가 보낸 쪽지</p>
		<hr>
		<table>
			<colgroup>
				<col width="200px" />
				<col width="200px" />
				<col width="1000px" />
				<col width="400px" />
			</colgroup>
			<tr>
				<th>게시글</th>
				<th>받는 사람</th>
				<th>내용</th>
				<th>시간</th>
			</tr>

			<c:forEach var="item" items="${listmessage}" varStatus="status">
				<c:if test="${sessionScope.userid eq item.notefromuserid }">
					<tr>
						<td><a href="/Vada/boarddetailform.do?productnum=${item.noteproductnum}">
					${item.noteproductnum}</a></td>
						<td>${item.notetouserid}</td>
						<td>${item.message}</td>
						<td>${item.m_date}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<hr>

		<br /> <br />

		<!-- 내가 받은 쪽지DTO의 데이터를 담은 리스트 -->
		<hr>
		<p>내가 받은 쪽지</p>
		<hr>
		<table>
			<colgroup>
				<col width="200px" />
				<col width="200px" />
				<col width="700px" />
				<col width="400px" />
				<col width="300px" />
			</colgroup>
			<tr>
				<th>게시글</th>
				<th>보내는 사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>답장</th>
			</tr>

			<c:forEach var="item" items="${listmessage}" varStatus="status">
				<c:if test="${sessionScope.userid eq item.notetouserid }">
					<tr>
						<td><a href="/Vada/boarddetailform.do?productnum=${item.noteproductnum}">
					${item.noteproductnum}</a></td>
						<td>${item.notefromuserid}</td>
						<td>${item.message }</td>
						<td>
							<c:set var="sysYear">
							<fmt:formatDate value="${item.m_date}" pattern="yyyy-MM-dd hh:mm" />
							</c:set>
							<c:out value="${sysYear}" />
						</td>
						<td><a href="${webapproot}/jsp/board/func/noteMessageAnswerForm.jsp?productnum=${item.noteproductnum}&notefromuserid=${item.notefromuserid}">답장</a></td>
					</tr>
				</c:if>
			</c:forEach>

		</table>
		<hr>

		<br /> <br />
		<button type="button" class="btn btn-primary pull-right"
			onclick="location.href='${webapproot}/mainform.do'">메인으로
			돌아가기</button>

	</div>


</main>
<jsp:include page="/jsp/bottom.jsp" />