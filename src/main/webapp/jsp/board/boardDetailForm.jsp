<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<jsp:include page="/jsp/top.jsp" />

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link href="${webapproot}/css/list.css" rel="stylesheet" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<!-- 게시글 수정/삭제 시 confirm하는 스크립트 -->
<script src="${webapproot}/js/common.js"></script>

<main>

	<div style="margin-left: 25%">

		<form action="${webapproot}/boardupdateform.do" method="post">
			<input type="hidden" name="productnum" value="${boardDTO.productnum}" />

			<!-- 	이미지 슬라이드 -->
			<div class="swiper-container" onclick="swiper()">
				<div class="swiper-wrapper">
					<c:forEach var="imgDTO" items="${imgDTOList}" varStatus="status">
						<!-- 이미지 데이터 저장 list -->
						<div class="swiper-slide">
							<!-- 서버에 저장된 이미지 출력 -->
							<img style="width: 100%; height: 100%;"
								src="${webapproot}/img/${imgDTO.imgcname}" name="img">
							<!-- 게시글 수정시 사용할 파일명 -->
							<input type="hidden" name="imgcname${status.index}"
								value="${imgDTO.imgcname}" />
							<!-- 게시글 수정시 사용할 파일크기 -->
							<input type="hidden" name="imgsize${status.index}"
								value="${imgDTO.imgsize}" />
						</div>
					</c:forEach>

				</div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>

			<script>
				new Swiper('.swiper-container', {
					loop : true,
					spaceBetween : 30,
					pagination : {
						el : ".swiper-pagination",
						clickable : true,
						type : 'fraction'
					},
					navigation : {
						nextEl : ".swiper-button-next",
						prevEl : ".swiper-button-prev",
					},
				});
			</script>

			<div class="row">
				<div class="col-md-6">

					<h3>
						<!-- 게시판 제목 출력 / hidden 타입 Input는 수정 시 폼데이터로 title값 전송 -->
						제목 : ${boardDTO.title}<input type="hidden" name="title"
							value="${boardDTO.title}" />
					</h3>

					<h3>내용</h3>
					<textarea readonly style="width: 100%" rows="15" name="content">${boardDTO.content}</textarea>
					<br />
					<h3>카테고리 : ${categoryDTO.categoryname}</h3>
					<input type="hidden" name="bcategorynum"
						value="${categoryDTO.categoryname}" />
					<h3>
						가격 :
						<fmt:formatNumber value="${productpriceDTO.productprice}"
							pattern="#,###" />
						원
					</h3>
					<input type="hidden" name="productprice"
						value="${productpriceDTO.productprice}" /> <br />


					<!-- 판매자가 로그인한 사용자일 떄 버튼 (수정/삭제/예약취소/판매완료)-->
					<c:if test="${sessionScope.userid eq boardDTO.sellerid}">

						<!-- 해당 게시글이 예약되지 않은 상태일 때만 게시글 수정 가능 -->
						<c:if test="${boardDTO.reservation eq 'no'}">
							<input type="button" class="btn btn-secondary"
								onclick="this.form.submit()" value="글 수정">
						</c:if>

						<a class="btn btn-secondary"
							href="javascript:confirmCommand('${webapproot}/boarddeleteproc.do?productnum=${boardDTO.productnum}','게시글 삭제');">
							글 삭제
						</a>
						
						<!-- 해당 게시글이 예약중이 상태 일 때 -->
						<c:if test="${boardDTO.reservation eq 'yes'}">

							<!-- 아직 판매 완료되지 않았을 때 -->
							<c:if test="${boardDTO.buyerid eq 'default'}">
								<a class="btn btn-secondary" style="float: right"
									href="javascript:confirmCommand('${webapproot}/reserveproc.do?productnum=${boardDTO.productnum}&command=cancel','예약 취소');">
									예약 취소하기
								</a>
							</c:if>

							<c:if test="${empty boardDTO.soldoutdate}">
								<a class="btn btn-secondary" style="float: right"
									href="javascript:confirmCommand('${webapproot}/soldoutproc.do?productnum=${boardDTO.productnum}&reserveid=${boardDTO.reserveid}','판매 완료 처리');">판매
									완료 처리하기</a>
								<br />
							</c:if>

						</c:if>

					</c:if>

					<!-- 판매자가 로그인한 사용자가 아닐 때 버튼(신고/채팅/쪽지/찜/예약)-->
					<c:if test="${sessionScope.userid ne boardDTO.sellerid}">

						<a class="btn btn-secondary" style="float: right"
							href="javascript:confirmCommand('${webapproot}/jsp/board/func/notifyWriteForm.jsp?productnum=${boardDTO.productnum}&title=${boardDTO.title}','게시글 신고');">
							게시글 신고
						</a>
						<br /><br /><br />

						<!-- 						<a class="btn btn-info" style="float: right color: red" -->
						<%-- 							 href="${webapproot}/jsp/board/func/chatList.jsp?productnum=${boardDTO.productnum}&userid=${sessionScope.userid}">채팅하기&raquo;</a> --%>
						
						<!-- 판매중 또는 예약중인 게시글이면 -->
						<c:if test="${boardDTO.buyerid eq 'default'}">
							<a class="btn btn-info" style="float: right color: red"
								href="${webapproot}/jsp/board/func/noteMessageWriteForm.jsp?productnum=${boardDTO.productnum}&sellerid=${boardDTO.sellerid}">
								쪽지 보내기&raquo;</a>
							
							<a class="btn btn-secondary"
								style="float: right; margin-right: 5px;"
								href="javascript:confirmCommand('${webapproot}/addlikeproc.do?productnum=${boardDTO.productnum}','찜');">찜하기</a>
						</c:if>
						
						<!-- 예약되지 않은 게시글이면 -->
						<c:if test="${boardDTO.reservation eq 'no'}">
							<a class="btn btn-secondary" style="float: right"
								href="javascript:confirmCommand('${webapproot}/reserveproc.do?productnum=${boardDTO.productnum}&command=reserve','구매 예약 신청');">
								구매 예약
							</a>
						</c:if>

					</c:if>
					
					<br /> <br />


					<!-- 판매중/예약중/판매완료 상태 텍스트 출력 -->
					<h3>(${reserveText})</h3>

					<!-- 후기글이 있으면 후기글 화면에 출력 -->
					<c:if test="${!empty boardDTO.review}">
						<h3>리뷰 : ${boardDTO.review}</h3>
						<h3>
							별점 :

							<c:forEach var="i" begin="1" end="${boardDTO.reviewscore}">   
						 		<span style="color: FFF064; font-size: xx-large;">★</span>
							</c:forEach>

						</h3>
					</c:if>
					<br /> <br />
				</div>
			</div>
		</form>

		<div>
			<a href="${webapproot}/mainform.do" class="btn btn-secondary">메인
				화면으로 돌아가기</a>
		</div>

	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />