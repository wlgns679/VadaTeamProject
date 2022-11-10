<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
 
<jsp:include page="/jsp/top.jsp" />
<main>
   <div class="container">
      <div class="row">
      
         <form method="post" action="${webapproot}/fileupload" enctype="multipart/form-data" accept-charset="utf-8">
         
         	<input type="hidden" name="command" value="update" />
         	<input type="hidden" name="productnum" value="${boardDTO.productnum}"/>
         	
            <table class="table table-stripped" style="text-align: center; boarder: 1px solid #dddddd">
               <thead>
                  <tr>
                     <th colspan="2" style="background-color: #eeeeee; text-align: center;">
                        글 수정하기
                     </th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td>
                     	<input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="20" value=" ${boardDTO.title}">
                     </td>
                  </tr>
                  <tr>
                     <td style="float: left">카테고리 : 
                     
                          <select name="bcategorynum" id="ca1" >
                            <option value="1000" >전체</option>
                            <c:forEach var="categoryDTO" items="${categoryDTOList}">
                                <c:if test="${fn:contains(categoryDTO.categorynum, '00')}" >
                              		<option value="${categoryDTO.categorynum}">${categoryDTO.categoryname}</option>
                           		</c:if>
                        	</c:forEach>
                    	  </select> &nbsp;&nbsp;
                     
		                     <select name="bcategorynum2" id="ca2" >
		                            <option value="1000" >전체</option>
		                     </select> &nbsp;&nbsp;
                     
		                     <script src="http://code.jquery.com/jquery-latest.js"></script>
		                     <script>
		                        $(document).ready(function() {
		                           $("#ca1").change(function(){
		                              
		                              $('#ca2').children('option:not(:first)').remove();
		                              
		                              var categoryappend = $(this).val().substring(0, 2);
		                              
		                              <c:forEach var="item" items="${categoryDTOList}">
		                                 
		                              var categorynum = "${item.categorynum}";
		                                    
		                                 if(categorynum.match("^"+categoryappend) && categorynum!=$(this).val()) {
		                                    $('#ca2').append($('<option>', {
		                                         value: ${item.categorynum},
		                                         text : '${item.categoryname}',
		                                         id : ${item.categorynum}
		                                     }));
		                                 }
		   
		                              </c:forEach>
		                           });
		                        });
		                     </script>
                     </td>
                  </tr>
                  
                  <tr>
                     <td><textarea  type = "text"class="form-control"
                           name="content" maxlength="2048" style="height: 350px"  >${boardDTO.content}</textarea></td>
                  </tr>
                  <tr>
                     <td>
                     	<input type="text" class="form-control" placeholder="가격 (원)" name="productprice" maxlength="50" value="${productpriceDTO.productprice}">
                     </td>
                  </tr>
                  
                  <c:forEach var="item" items="${imgcnamelist}" varStatus="status">
	                  <tr>
	                     <td style="float: left">첨부파일 ${status.count} : 
		                     ${item} (${imgsizelist[status.index]} bytes) 
		                     <input type="file" name="file${stat.count}" />
		                     <br />
	                     </td>
	                  </tr>
             	  </c:forEach> 
             	
               </tbody>
            </table>
            
            <input type="submit" class="btn btn-primary pull-right" value="수정">
            <button type="button" class="btn btn-primary pull-right" onclick="history.back();">취소</button>
            
         </form>
         
      </div>
   </div>
</main>

<jsp:include page="/jsp/bottom.jsp" />