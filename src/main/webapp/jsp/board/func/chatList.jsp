<%@page import="vada.service.board.func.ChatService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="vada.dao.impl.board.func.ChatDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vada.dto.KtuserchatroomDTO"%>
<%@page import="java.util.List"%>
<%@page import="vada.dto.ChatmsgDTO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/jsp/top.jsp" />
<jsp:useBean id="ktuserchatroomDTO" class="vada.dto.KtuserchatroomDTO" />
<jsp:setProperty name="ktuserchatroomDTO" property="*" />

<%
	ChatService chatService = new ChatDAOImpl();
	String ktuserid = request.getParameter("userid");
	
	List<KtuserchatroomDTO> list = new ArrayList<KtuserchatroomDTO>();
	
	list = chatService.ktchatroomList(ktuserid);
	
	pageContext.setAttribute("list", list);
%>


<main>

	<div class="container-fluid px-4">
		<h1 class="mt-4">채팅</h1>
		<ol class="breadcrumb mb-4">
		</ol>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i> 채팅목록
			</div>
			<div class="card-body">

				<table>
					<colgroup>
						<col width="3000px" />
						<col width="600px" />
					</colgroup>
					<thead>
						<tr>
							<th>채팅방 정보</th>
							<th>시간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="status">
							<c:if test="${sessionScope.userid eq item.ktuserid }">
								<tr>
									<td>게시글명 : ${item.chatroomtitle }</td>
									<td>${item.chatroomdate}</td>
								</tr>
								<tr>
									<td>MyID : ${item.ktuserid}</td>
								</tr>
								<tr>
									<td>판매자ID : ${item.ktsellerid}</td>
								</tr>

							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />