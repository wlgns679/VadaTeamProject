<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ID 찾기 실패시 alert 처리를 위한 jsp -->
 <script>
	alert("이름/이메일을 확인해주세요");
	location.href = "${webapproot}/jsp/user/searchIDForm.jsp"
 </script>