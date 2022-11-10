<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<html>
	<head>
	<link href="${webapproot}/css/login.css" rel="stylesheet" />
	</head>
	<body>
	<h1>VADA</h1>
	<div class="wrapper">
      <div class="form-container">
         <div class="slide-controls">
            <label for="searchid" class="slide login">Find ID</label>
         </div>
         
         <div class="form-inner">
         
         	<!-- ID찾기를 위한 데이터 저장 폼 -->
            <form method="post" action="${webapproot}/searchidproc.do" class="searchid">
               <div class="field">
                  <input type="text" name="email" placeholder="email" required>
               </div>
               <div class="field">
                  <input type="text" name="username" placeholder="이름"
                     required>
               </div>
               <div class="pass-link">
                  <a href="${webapproot}/jsp/user/loginForm.jsp">로그인 화면으로 돌아가기</a>
               </div>
               <div class="field btn">
                  <div class="btn-layer"></div>
                  <input type="submit" value="아이디 찾기">
               </div>
                
            </form>

         </div>
      </div>
   </div>
	
	</body>


</html>


