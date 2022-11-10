<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/list.css" rel="stylesheet" />
<!-- 메인 폼 (게시글 리스트 폼) -->
<main>

	<div class="list con">
		<div class="container">
			<table class="table table-stripped"
				style="text-align: center; boarder: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">중고거래
							게시판</th>
					</tr>
				</thead>
			</table>
		</div>

		<ul class="row" id="test">

			<c:set var="listSize" value="${boardList.size()}" />

			<!-- pageNum*pageSize-(pageSize-1) ~ pageNum*pageSize -->
			<!-- 게시판 정보를 담은 map타입 데이터 -->

			<c:forEach var="item" items="${boardList}" varStatus="stat">
				<!-- stat.count -->
				<c:if test="${(pageNum*pageSize)-pageSize-1<stat.index and pageNum*pageSize>stat.index}">
<%-- 				<p>${(pageNum*pageSize)-pageSize}</p> --%>
<%-- 				<p>${stat.index}</p> --%>
<%-- 				<p>${pageNum*pageSize+1}</p> --%>
					<!-- 게시글 세부정보 확인을 위해 게시글 제품번호 데이터 전송 및 폼이동 -->
					<li id="listid_ul" style="list-style: none;" class="cell" onclick="location.href='${webapproot}/boarddetailform.do?productnum=${item.productnum}'">
					
					<div class="img-box">
					
						<!-- 이미지 저장시 가장 첫번째로 등록된 이미지를 리스트에 출력 -->
						<img class=imgfile src="${webapproot}/img/${item.imgcname}" alt="">
					</div>
					
					<p class="product-price">
						가격 : <fmt:formatNumber value="${item.productprice}" pattern="#,###" />원
					</p>
					
					<p class="product-name">
						${item.title}
					</p> 
					
					<c:set var="sysYear">
						<fmt:formatDate value="${item.wdate}" pattern="yyyy-MM-dd hh:mm" />
					</c:set>
					
					<p class="product-date">
						<c:out value="${sysYear}" />
					</p>
				</li>

				</c:if>
			</c:forEach>

		</ul>
	</div>
	<div>
		<a id="write" href="${webapproot}/boardwriteform.do">글 등록</a>
	</div>

	<div align="center" style="board: 1px solid gray">
		<c:if test="${startPage>pageBlock}">
			<a href="${webapproot}/mainform.do?pageNum=${startPage-pageBlock}">Prev</a>
		</c:if>

		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="${webapproot}/mainform.do?pageNum=${i}" style="text-size: 15px; color: black;">${i}</a>
		</c:forEach>

		<c:if test="${endPage<pageCount}">
			<a href="${webapproot}/mainform.do?pageNum=${startPage+pageBlock}">Next</a>
		</c:if>
	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />