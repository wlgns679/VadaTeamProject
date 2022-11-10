<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/jsp/top.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${webapproot}/css/login.css" rel="stylesheet" />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

	// 우편번호 API
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

	//이메일 옵션 선택후 주소 자동 완성
	function change_email() {
		var email_add = document.getElementById("email_add");
		var email_sel = document.getElementById("email_sel");

		//지금 골라진 옵션의 순서와 값 구하기
		var idx = email_sel.options.selectedIndex;
		var val = email_sel.options[idx].value;

		email_add.value = val;
	}

</script>

<main>
	<div class="wrapper" style="margin: 0;">
      <div class="form-container">
         <div class="slide-controls">
            <label for="login" style="font-weight: bold; font-size: 25px;">회원 정보 수정</label>
         </div>
         
         <div class="form-inner">
	<form name="join_form" action="${webapproot}/userinfoupdateproc.do" method="post">
	
		<div>
			아이디 : ${userDTO.userid}
		</div>
		<div class="field">
			비밀번호 : <input type="password" name="userpw" id="pwd" placeholder="영문자+숫자+특수문자 조합" value="${userDTO.userpw}"/>
		</div><br />
		<div class="field">
			<p>비밀번호 재확인 : </p><input type="password" name="pwd" id="repwd" value="${userDTO.userpw}">
		</div><br />
		<div class="field">
			<p>이름 : </p><input type="text" name="name" id="uname" value="${userDTO.name}">
		</div><br /><br />
			<p>전화번호 : </p>
		<div class="field">
			<input type="tel" name="tel" id="mobile" value="${userDTO.tel}">		  
		</div>
		<div class="field">
			<p>이메일 : </p><input type="text" name="email" id="email_id" value="${userDTO.email}">
		</div><br />
		<div class="field">
			<p>기본주소 : <button type="button" id="address_kakao">우편번호 찾기</button></p>
			<input type="text" id="address" name="address"  value="${userDTO.address}" readonly>
		</div><br />
		<div class="field">
			<p>상세주소 : </p><input type="text" name="detailaddress" id="addr2" value="${userDTO.detailaddress}">
		</div><br />
		<div class="field">
			<p>닉네임 : </p><input type="text" name="nickname" id="nickname" value="${userDTO.nickname}">
		</div><br /><br />
		<div>
			<p>관심 카테고리 : </p>
			<select name="ca1" id="cate1" >
                <option value="1000" >전체</option>
                <c:forEach var="categoryDTO" items="${categoryDTOList}">
	           		<c:if test="${fn:contains(categoryDTO.categorynum, '00')}" >
						<option value="${categoryDTO.categorynum}">${categoryDTO.categoryname}</option>
					</c:if>
				</c:forEach>
			</select> &nbsp;&nbsp;
							
				<select name="interestcategorynum" id="interestcategorynum" >
                	<option value="1000" >전체</option>
				</select> &nbsp;&nbsp;
							
				<script src="http://code.jquery.com/jquery-latest.js"></script>
				<script>
					$(document).ready(function() {
						$("#cate1").change(function(){
										
							$('#interestcategorynum').children('option:not(:first)').remove();
									
							var categoryappend = $(this).val().substring(0, 2);
										
							<c:forEach var="item" items="${categoryDTOList}">
											
							var categorynum = "${item.categorynum}";
												
								if(categorynum.match("^"+categoryappend) && categorynum!=$(this).val()) {
									$('#interestcategorynum').append($('<option>', {
								        value: ${item.categorynum},
								        text : '${item.categoryname}'
								    }));
								}
	
							</c:forEach>
						});
					});
				</script>
			</lable>
		</div>
		<div class="signup-link">
			<button type="button" onclick="history.back();">이전페이지로</button>
			<button type="button" onclick="this.form.submit();">변경하기</button>
		</div>
		
		
        </form>
		</div>
	</div>
   </div>
	
</main>


<jsp:include page="/jsp/bottom.jsp" />
