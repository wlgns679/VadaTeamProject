<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="vada.dao.impl.board.func.CategoryListDAOImpl"%>
<%@page import="vada.service.board.func.CategoryService"%>
<%@page import="vada.dto.CategoryDTO"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 모든 jsp의 기본 베이스가 되며 베이스의 윗부분 코드 -->
<%
CategoryService categoryService = new CategoryListDAOImpl();
List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();
pageContext.setAttribute("categoryDTOList", categoryDTOList);
%>

<!DOCTYPE html>
<style>
select {
	padding: 3px;
	margin: 0;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
	-moz-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
	box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
	background: #f8f8f8;
	color: #888;
	border: none;
	outline: none;
	display: inline-block;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	cursor: pointer;
}

label:after {
	content: '<>';
	font: 11px "Consolas", monospace;
	color: #aaa;
	-webkit-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	transform: rotate(90deg);
	right: 8px;
	top: 2px;
	padding: 0 0 2px;
	border-bottom: 1px solid #ddd;
	position: absolute;
	pointer-events: none;
}

label:before {
	content: '';
	right: 6px;
	top: 0px;
	width: 20px;
	height: 20px;
	background: #f8f8f8;
	position: absolute;
	pointer-events: none;
	display: block;
}
</style>
<html>
<head>
<meta charset="UTF-8">
<title>Vada 중고거래</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Vada-중고거래</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${webapproot}/css/style.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" style="font-size: 30px"
			href="${webapproot}/mainform.do">VADA</a>
		<!-- Sidebar Toggle-->

		<i class="fas fa-bars"></i>
		</button>


		<!-- 검색 창 -->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"
			action="${webapproot}/searchresultform.do" method="post">

			<div class="input-group" style="line-height: 20px">
				<p style="color: white; margin-top: 15px;">카테고리 :</p>
				&nbsp;&nbsp;
				<lable style="margin-top: 12px"> <!-- 검색 창 카테고리 목록 출력 시작 -->
				<select name="categories1" id="categories1">
					<option value="1000">전체</option>
					<c:forEach var="categoryDTO" items="${categoryDTOList}">
						<c:if test="${fn:contains(categoryDTO.categorynum, '00')}">
							<option value="${categoryDTO.categorynum}">${categoryDTO.categoryname}</option>
						</c:if>
					</c:forEach>
				</select> &nbsp;&nbsp; <select name="categories2" id="categories2">
					<option value="1000">전체</option>
				</select> &nbsp;&nbsp; <script src="http://code.jquery.com/jquery-latest.js"></script>
				<script>
									$(document).ready(function() {
										$("#categories1").change(function(){
											
											$('#categories2').children('option:not(:first)').remove();
											
											var categoryappend = $(this).val().substring(0, 2);
											
											<c:forEach var="item" items="${categoryDTOList}">
												
											var categorynum = "${item.categorynum}";
													
												if(categorynum.match("^"+categoryappend) && categorynum!=$(this).val()) {
													$('#categories2').append($('<option>', {
												        value: ${item.categorynum},
												        text : '${item.categoryname}'
												    }));
												}
		
											</c:forEach>
										});
									});
					</script> </lable>
				<!-- 검색 창 카테고리 목록 출력 끝 -->

				<!-- 검색어 입력 텍스트 박스 -->
				<input class="form-control" name="searchText" value="${selected}"
					type="text" placeholder="Search for..." aria-label="Search for..."
					aria-describedby="btnNavbarSearch" />

				<!-- 검색 버튼(돋보기) -->
				<button class="btn btn-primary" id="btnNavbarSearch" type="button"
					onclick="this.form.submit()">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>

		<!-- 사용자/관리자 사용가능 목록 창 -->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">

					<li><p class="dropdown-item">${sessionScope.userid}님</p></li>

					<!-- 관리자가 아닐때 찜/구매목록/쪽지함/회원정보 변경 목록 -->
					<c:if test="${sessionScope.adminyn eq 'no'}">
						<li><a class="dropdown-item"
							href="${webapproot}/myboardlist.do">내가 쓴 글</a></li>
						<li><a class="dropdown-item"
							href="${webapproot}/likelistform.do">찜 목록</a></li>
						<li><a class="dropdown-item"
							href="${webapproot}/userbuylistform.do">구매 목록</a></li>
						<li><a class="dropdown-item"
							href="${webapproot}/notemessagelistform.do">쪽지함</a></li>
						<li><a class="dropdown-item"
							href="${webapproot}/userinfoupdateform.do">회원정보 번경</a></li>
					</c:if>


					<!-- 관리자가 아닐때 회원관리/신고글 목록 -->
					<c:if test="${sessionScope.adminyn eq 'yes'}">
						<li><a class="dropdown-item"
							href="${webapproot}/adminmanageuserform.do">회원관리</a></li>
						<li><a class="dropdown-item"
							href="${webapproot}/adminmanagenotifyform.do">신고글 목록</a></li>
					</c:if>

					<li><hr class="dropdown-divider" /></li>

					<!-- 로그아웃목록은 관리자/사용자 모두 사용 -->
					<li><a class="dropdown-item" href="${webapproot}/logout.do">로그아웃</a></li>

				</ul></li>
		</ul>

	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<a class="nav-link" href="${webapproot}/mainform.do">
							<div class="sb-nav-link-icon"></div> 중고거래
						</a> 
						<a style="font-size: 14px;" class="nav-link" href="${webapproot}/categoryboardform.do?category=1">
							<div class="sb-nav-link-icon"></div> &nbsp패션의류
						</a> 
						<a style="font-size: 14px;" class="nav-link" href="${webapproot}/categoryboardform.do?category=2">
							<div class="sb-nav-link-icon"></div> &nbsp가전/디지털
						</a> 
						<a style="font-size: 14px;" class="nav-link" href="${webapproot}/categoryboardform.do?category=3">
							<div class="sb-nav-link-icon"></div> &nbsp반려동물용품
						</a> 
						<a style="font-size: 14px;" class="nav-link" href="${webapproot}/categoryboardform.do?category=4">
							<div class="sb-nav-link-icon"></div> &nbsp취미/도서/문구
						</a> 
						<a style="font-size: 14px;" class="nav-link" href="${webapproot}/categoryboardform.do?category=5">
							<div class="sb-nav-link-icon"></div> &nbsp기타
						</a> 
						</br>
						<a class="nav-link" href="${webapproot}/notemessagelistform.do">
							<div class="sb-nav-link-icon"></div>쪽지함
						</a>
						<a class="nav-link" href="${webapproot}/jsp/board/func/chat/chatClient.jsp">
							<div class="sb-nav-link-icon"></div>채팅하기
						</a>

					</div>
				</div>

			</nav>
		</div>
		<div id="layoutSidenav_content">