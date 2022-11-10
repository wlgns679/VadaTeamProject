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
            <label for="searchid" class="slide login">Find Password</label>
         </div>
         
         <div class="form-inner">
         
         	<!-- PW찾기를 위한 데이터 저장 폼 -->
            <form method="post" action="${webapproot}/searchpwproc.do" class="searchid">
               <div class="field">
                  <input type="text" name="userid" placeholder="ID" required>
               </div>
               <div class="field">
                  <input type="text" name="email" placeholder="email"
                     required>
               </div>
               <div class="pass-link">
                  <a href="${webapproot}/jsp/user/loginForm.jsp">로그인 화면으로 돌아가기</a>
               </div>
               <div class="field btn">
                  <div class="btn-layer"></div>
                  <input type="submit" value="비밀번호 찾기">
               </div>
                
            </form>

         </div>
      </div>
   </div>
	
	</body>


</html>


