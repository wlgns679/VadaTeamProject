<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/jsp/top.jsp" />

<main>

	<div class="container">
		<div class="row">
			
			<!-- 이미지 파일 서버에 저장하기 위해 서블릿으로 데이터 전송 / 게시글 데이터 저장 폼 -->
			<form method="post" action="${webapproot}/fileupload" enctype="multipart/form-data" accept-charset="UTF-8">
         
         	<!-- 글 작성 or 글 수정 인지 확인을 위한 데이터 -->
         	<input type="hidden" name="command" value="write" />
				
				<table class="table table-stripped" style="text-align: center; boarder: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">글 작성하기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="title" ></td>
						</tr>
						<tr>
							
							<!-- level1 카테고리를 클릭하면 level1에 해당하는 level2 카테고리를 출력함 -->
							<td style="float: left">카테고리 : 
							<select name="bcategorynum" id="bcategorynum" >
                				<option value="1000" >전체</option>
                				<c:forEach var="categoryDTO" items="${categoryDTOList}">
	           						<c:if test="${fn:contains(categoryDTO.categorynum, '00')}" >
										<option value="${categoryDTO.categorynum}">${categoryDTO.categoryname}</option>
									</c:if>
								</c:forEach>
							</select> &nbsp;&nbsp;
							
							<select name="bcategorynum2" id="bcategorynum2" >
                				<option value="1000" >전체</option>
							</select> &nbsp;&nbsp;
							
							<script src="http://code.jquery.com/jquery-latest.js"></script>
							<script>
								$(document).ready(function() {
									$("#bcategorynum").change(function(){
										
										$('#bcategorynum2').children('option:not(:first)').remove();
										
										var categoryappend = $(this).val().substring(0, 2);
										
										<c:forEach var="item" items="${categoryDTOList}">
											
										var categorynum = "${item.categorynum}";
												
											if(categorynum.match("^"+categoryappend) && categorynum!=$(this).val()) {
												$('#bcategorynum2').append($('<option>', {
											        value: ${item.categorynum},
											        text : '${item.categoryname}'
											    }));
											}
	
										</c:forEach>
									});
								});
							</script>
							
						</tr>
						
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 350px"></textarea></td>
						</tr>
						<tr>
							<td><input type="number" class="form-control" placeholder="가격 (원)" name="productprice" maxlength="50"></td>
						</tr>
						<tr>
							<td style="float: left">첨부파일 : <input type="file" name="file1" /><br />
						</tr>
						<tr>
							<td style="float: left">첨부파일 : <input type="file" name="file2" /><br />
						</tr>
						<tr>
							<td style="float: left">첨부파일 : <input type="file" name="file3" /><br />
						</tr>
					</tbody>
					
				</table>
				
				<input type="submit" class="btn btn-primary pull-right" value="등록">
				<button type="button" class="btn btn-primary pull-right" onclick="history.back();">취소</button>
				
			</form>
			
		</div>
	</div>
	
</main>

<jsp:include page="/jsp/bottom.jsp" />