<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/jsp/top.jsp" />

<link href="${webapproot}/css/list.css" rel="stylesheet" />
<script src="${webapproot}/js/common.js"></script>

<main>


	<div class="list con">
		<div class="container">
			<table class="table table-stripped"
				style="text-align: center; boarder: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2"
							style="background-color: #eeeeee; text-align: center;">찜 목록</th>
					</tr>
				</thead>
			</table>
		</div>
		<ul class="row" id="test">


			<c:set var="listSize" value="${likeList.size()}" />

			<!-- User의 찜목록이 존재한다면 -->
			<c:if test="${fn:length(likeList) != 0}">
				<c:forEach var="likeDTO" items="${likeList}" varStatus="stat">

					<!-- 해당 찜게시글에 해당하는 폼으로 이동 -->
					<li id="listid" style="list-style: none;" class="cell">

						<div
							onclick="location.href='${webapproot}/boarddetailform.do?productnum=${likeDTO.productnum}'">
							<!-- 첫번째 이미지파일을 출력 -->
							<div class="img-box">
								<img class=imgfile src="${webapproot}/img/${likeDTO.imgcname}"
									alt="">
							</div>

							<p class="product-price">
								가격 :
								<fmt:formatNumber value="${likeDTO.productprice}"
									pattern="#,###" />
								원
							</p>
							<p class="product-name">${likeDTO.title}</p>

							<c:set var="sysYear">
								<fmt:formatDate value="${likeDTO.likedate}"
									pattern="yyyy-MM-dd hh:mm" />
							</c:set>

							<div class="product-date">
								<c:out value="${sysYear}" />

							</div>
						</div>
						<div>
							<a class="btn btn-secondary" style="float: right;"
								href="javascript:confirmCommand('${webapproot}/likelistdeleteproc.do?productnum=${likeDTO.productnum}','찜삭제');">찜
								삭제</a>
						</div>

					</li>

				</c:forEach>

			</c:if>
			<!-- 찜목록이 존재하지 않는다면 -->
			<c:if test="${fn:length(likeList) == 0}">
				<h3 style="text-align: center;">찜 목록이 없어요~</h3>
				<br />
				<br />
				<br />
				<br />
			</c:if>

			<button class="btn btn-primary pull-right"
				onclick="location.href='${webapproot}/mainform.do'">메인으로
				돌아가기</button>

		</ul>

	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />