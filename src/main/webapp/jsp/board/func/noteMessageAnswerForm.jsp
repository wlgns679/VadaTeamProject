<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/jsp/top.jsp" />

<main>

		<h2 align="center" >쪽지 답장하기</h2>
		<hr />
	  <div>
	  
	  <!-- 메시지 작성을 위한 데이터 저장 폼 -->
      <form align="center" action="${webapproot}/MessageServlet" method="post" style="width: 100%; margin-top: 3%;">
         <input type="hidden" name="productnum" value="${param.productnum}" />
         
         <div>
            <lable for="notefromuserid">받는 사람</lable>
            <input readonly="readonly" type="text" id="toid" name="notetouserid" value="${param.notefromuserid}"/> <!--  받는 사람 ID (판매자 ID) -->
         </div>
         <br />
         
         <div> 
            <input type="hidden" id="fromid" name="notefromid"/>	<!-- 보내는 사람 ID (유저 ID) -->
         </div>
         
         <div>
            <lable for="message">Message</lable><br />
            <textarea id="message" rows="6" name="message" style="width: 700px;"></textarea>
         </div>
         
         <br />
         <div class="actions" >
            <input type="reset" value="내용지우기" />&nbsp;&nbsp;
            <input type="submit" value="답장하기" />
         </div>
      </form>
   
   </div>

</main>
 
 <jsp:include page="/jsp/bottom.jsp" />
 