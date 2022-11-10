<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 회원PW 찾았을때 PW alert 출력을 위한 jsp -->
<script>
	alert("회원님의 패스워드는 ${param.searchUserpw} 입니다.");
	location.href="${webapproot}/jsp/user/loginForm.jsp"
</script>