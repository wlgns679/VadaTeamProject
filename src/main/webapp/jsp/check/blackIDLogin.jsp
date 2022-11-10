<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 블랙리스트 회원 alert처리를 위한 jsp -->
<script>
 	alert("블랙 리스트 회원이여서 로그인이 불가능 합니다.");
 	location.href = "${webapproot}/jsp/user/loginForm.jsp";
</script>