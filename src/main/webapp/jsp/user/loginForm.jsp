<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 로그인 폼 -->
<html>
<head>
<link href="${webapproot}/css/login.css" rel="stylesheet" />
</head>
<body>
	<h1>VADA</h1>
	<div class="wrapper">
		<div class="form-container">
			<div class="slide-controls">
				<label for="login" class="slide login">Login</label>
			</div>

			<div class="form-inner">

				<form method="post" action="${webapproot}/loginproc.do"
					class="login">
					<div class="field">
						<input type="text" name="userid" placeholder="ID" required>
					</div>
					<div class="field">
						<input type="password" name="userpw" placeholder="Password"
							required>
					</div>
					<div class="pass-link">
						<a href="${webapproot}/jsp/admin/adminLoginForm.jsp">관리자용 로그인</a>
					</div>
					<div class="pass-link">
						<a href="${webapproot}/jsp/user/searchIDForm.jsp">아이디 찾기</a>
					</div>
					<div class="pass-link">
						<a href="${webapproot}/jsp/user/searchPWForm.jsp">비밀번호 찾기</a>
					</div>
					<div class="field btn">
						<div class="btn-layer"></div>
						<input type="submit" value="Login">
					</div>
					<div class="signup-link">
						<a href="${webapproot}/joinform.do">회원가입 하기</a>
					</div>
				</form>

			</div>
		</div>
	</div>

</body>


</html>

