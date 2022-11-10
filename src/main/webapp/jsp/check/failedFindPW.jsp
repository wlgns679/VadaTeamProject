<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- PW 찾기 실패시 alert 처리를 위한-->
 <script>
 	alert("ID/이메일을 확인해주세요");
	location.href = "${webapproot}/jsp/user/searchPWForm.jsp"
 </script>