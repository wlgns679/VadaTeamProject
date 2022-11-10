<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<html>
	<head>
	<link href="${webapproot}/css/login.css" rel="stylesheet" />
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	$(function(){
	
		// 입력상자 클릭시 내용 비우기
		$("input[type='text'], input[type='password']").click(function() {
			this.value = '';
		});
	
		 // 유효성검사 메세지 (한국어)
		 $.extend($.validator.messages, {
			required: "필수 입력 항목입니다!",
			email: "유효하지 않은 이메일주소 입니다!",
			url: "유효하지 않은 URL 입니다!",
			date: "유효하지 않은 날짜형식 입니다!",
			dateISO: "유효하지 않은 날짜형식(ISO) 입니다!",
			number: "숫자(기호포함)만 입력해 주세요!",
			digits: "숫자만 입력해 주세요!",
			creditcard: "유효하지 않은 신용카드번호 입니다!",
			equalTo: "비밀번호와 비밀번호 확인이 일치하지 않습니다!",
			maxlength: jQuery.validator.format("최대 {0}자로 입력해 주세요!"),
			minlength: jQuery.validator.format("최소 {0}자를 입력해 주세요!"),
			rangelength: jQuery.validator.format("{0}글자 이상 {1}글자 이하로 입력해 주세요!"),
			max: jQuery.validator.format("{0}이상 입력해 주세요!"),
			min: jQuery.validator.format("{0}이하로 입력해 주세요!"),
			range: jQuery.validator.format("{0}이상 {1}이하 숫자를 입력해 주세요!")
		});
	
		// 정규표현식 유효성검사를 위한 메소드 추가
		$.validator.addMethod("regex", 
			function(value, element, regexpr) {
				return regexpr.test(value); 
			}
		);
	
		// 유효성검사 (form엘리먼트의 name속성 값을 사용함)
		$("form[name='joinForm']").validate({
	
			debug: false, // true인 경우 서밋을 수행하지 않음 (디버그 모드),
	
			errorPlacement: function(error, element) { // 유효성검사 실패시 에러 표시
				var errorSelector = '.validation_error[for="' + element.attr('id') + '"]';
				var $element = $(errorSelector);
				$(errorSelector).html(error.html());
			},
	
			success: function(element){ // 유효성검사 성공시 에러 제거
				element.html();                        
			},
	
			rules: { // 유효성 검사 규칙
				userid: { // 필수, 4~12글자
					required: true,
					rangelength: [4, 12]
				},
				pwd: { // 필수, 8~15글자 (문자,숫자,특수문자 포함)
					required: true,
					regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/,
					rangelength: [8, 15]
				},
				userpw: { // 필수, 8~15글자 (문자,숫자,특수문자 포함)
					required: true,
					regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/,
					rangelength: [8, 15],
					equalTo: "input[name='pwd']" // 비밀번호와 같으지 확인
				},
				name: { // 필수, 최소 2글자
					required: true,
					minlength: 2
				},
				tel: { // 필수, 000-0000-0000
					required: true,
					regex: /^\d{3}-\d{3,4}-\d{4}$/
				},
				email: { // 필수, 이메일 형식
					required: true,
					regex: /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
				}
			},
	
			messages: { // 유효성검사가 실패했을 때 메세지
				userid: {
					required: "아이디를 입력해 주세요!"
				},
				pwd: {
					required: "비밀번호를 입력해 주세요!",
					regex: "비밀번호확인 형식에 맞지 않습니다!<br />(형식 : 문자,숫자,특수문자 포함 8~15글자)"
				},
				userpw: {
					required: "비밀번호확인을 입력해 주세요!",
					regex: "비밀번호 확인 형식에 맞지 않습니다!<br />(형식 : 문자,숫자,특수문자 포함 8~15글자)"
				},
				name: {
					required: "이름을 입력해 주세요!"
				},
				tel: {
					required: "휴대폰번호를 입력해 주세요!",
					regex: "휴대폰번호 형식에 맞지 않습니다!  (형식 : 000-0000-0000)"
				},
				email: {
					required: "이메일주소를 입력해 주세요!",
					regex: "이메일주소 형식에 맞지 않습니다!"
				}
			},
	
	// 		submitHandler: function() { // 폼을 서밋하지 않고 AJAX로 처리할 때 사용, 실제 폼 서밋은 일어나지 않음
	// 			alert("유효성 검사 완료!");
	// 		}
	
		});
		
	});
	
	//우편번호 API
	window.onload = function() {
		document.getElementById("address_kakao").addEventListener("click", function() { // 주소입력칸을 클릭하면
			new daum.Postcode({
				oncomplete : function(data) { // 선택 시 입력값 세팅
					document.getElementById("address").value = data.address; // 주소 넣기
					document.querySelector("input[name=detailaddress]").focus(); // 상세입력 포커싱
				}
			}).open();
		});
	}
	
	</script>
	
	</head>
	<body>
	<h1>회원가입</h1>
	<div class="wrapper">
		<div class="form-container">
         
		<div class="form-inner">
		
			<form name="joinForm" action="${webapproot}/joinproc.do" method="post">
				* 아이디 <input type="text" name="userid" id="uid" placeholder="4~12글자 " /><br /><label for="uid" class="validation_error"></label><br />
				* 비밀번호 <input type="password" name="pwd" id="upass" placeholder="8~15글자 (문자, 숫자, 특수문자 포함)"/><br /><label for="upass" class="validation_error"></label><br />
				* 비밀번호확인 <input type="password" name="userpw" id="upassre" placeholder="8~15글자 (문자, 숫자, 특수문자 포함)"/><br /><label for="upassre" class="validation_error"></label><br />
				* 이름 <input type="text" name="name" id="uname" placeholder="2글자 이상"/><br /><label for="uname" class="validation_error"></label><br />
				* 전화번호 <input type="text" name="tel" id="usp" placeholder="000-0000-0000"/><br /><label for="usp" class="validation_error"></label><br />
				* 이메일주소 <input type="text" name="email" id="uemail" placeholder="a@a.com"/><br /><label for="uemail" class="validation_error"></label><br />
				* 기본주소<input type="text" id="address" name="address" readonly>
					<button type="button" id="address_kakao">우편번호 찾기</button><br />
				* 상세주소<input type="text" name="detailaddress" id="addr2" size="30"><br /><label for="uaddr" class="validation_error"></label><br />
				* 닉네임<input type="text" name="nickname" id="nickname"><br /><label for="unickname" class="validation_error"></label><br />
				<div class="field btn">
                  <div class="btn-layer"></div>
                  <input type="submit" value="회원가입">
               </div>
               
               <div class="pass-link">
                  <a href="${webapproot}/jsp/user/loginForm.jsp">로그인 화면으로 이동</a>
               </div>
			</form>
		
		</div>
		</div>
	   </div>
	
	</body>


</html>
