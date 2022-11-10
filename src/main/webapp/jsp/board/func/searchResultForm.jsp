<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 게시판검색 결과창(리스트)을 띄우기 위한 폼 -->

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/list.css" rel="stylesheet" />

<main>

	<div class="container">
		<table class="table table-stripped"
			style="text-align: center; boarder: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2"
						style="background-color: #eeeeee; text-align: center;">'${param.searchText}'
						검색결과</th>
				</tr>
			</thead>
		</table>
	</div>

	<div class="list con">
		<ul class="row" id="test">

			<c:set var="listSize" value="${list.size()}" />

			<!-- 검색한 결과가 존재할때 -->
			<c:if test="${fn:length(list) != 0}">

				<!-- 검색한 게시판의 카테고리/제목과 포함하는 데이터를 저장한 리스트 -->
				<c:forEach var="item" items="${list}" varStatus="stat">
					<c:if
						test="${(pageNum*pageSize)-pageSize-1<stat.index and pageNum*pageSize>stat.index}">
						<li id="listid" class="cell" style="list-style: none;"
							onclick="location.href='${webapproot}/boarddetailform.do?productnum=${item.productnum}'">
							<div class="img-box">
								<img class=imgfile src="${webapproot}/img/${item.imgcname}">

							</div>
							<p class="product-price">
								가격 :
								<fmt:formatNumber value="${item.productprice}" pattern="#,###" />
								원
							</p>
							<p class="product-name">${item.title}</p> <c:set var="sysYear">
								<fmt:formatDate value="${item.wdate}"
									pattern="yyyy-MM-dd hh:mm:ss" />
							</c:set>
							<p class="product-date">
								<c:out value="${sysYear}" />
							</p>
					</c:if>
				</c:forEach>


			</c:if>

			<!-- 검색한 결과가 존재하지 않을때 -->
			<c:if test="${fn:length(list) == 0}">
				<h3 style="text-align: center;">'${param.searchText}' 검색결과 없음</h3>
			</c:if>

		</ul>
	</div>
	<div>
		<a href="${webapproot}/mainform.do" class="btn btn-secondary"
			style="float: right;">메인화면으로 돌아가기</a>
	</div>

	<div align="center" style="board: 1px solid gray">
		<c:if test="${startPage>pageBlock}">
			<a href="${webapproot}/searchresultform.do?pageNum=${startPage-pageBlock}&cate1=${cate1}&cate2=${cate2}&searchText=${searchText}">Prev</a>
		</c:if>

		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="${webapproot}/searchresultform.do?pageNum=${i}&cate1=${cate1}&cate2=${cate2}&searchText=${searchText}"
				style="text-size: 15px; color: black;">${i}</a>
		</c:forEach>

		<c:if test="${endPage<pageCount}">
			<a href="${webapproot}/searchresultform.do?pageNum=${startPage+pageBlock}&cate1=${cate1}&cate2=${cate2}&searchText=${searchText}">Next</a>
		</c:if>
	</div>
</main>

<jsp:include page="/jsp/bottom.jsp" />