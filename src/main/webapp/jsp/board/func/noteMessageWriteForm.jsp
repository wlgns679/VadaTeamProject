<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/jsp/top.jsp" />
<style>

#notemsgdiv{
	margin-left: 35%;
	margin-top: 3%;
}
h2{
margin-right: 16%;
}
</style>
<main>
<h2 align="center">쪽지보내기</h2>
	  <div id="notemsgdiv">
	  
	  <!-- 메시지 작성을 위한 데이터 저장 폼 -->
      <form action="${webapproot}/MessageServlet" method="post">
         <input type="hidden" name="productnum" value="${param.productnum}" />
         <input type="hidden" name="sellerid" value="${param.sellerid}" />
         
         <div>
            <lable for="notefromuserid">받는 사람</lable>
            <input readonly="readonly" type="text" id="toid" name="notetouserid" value="${param.sellerid}"/> <!--  받는 사람 ID (판매자 ID) -->
         </div>
         <p></p><p></p>
         
         <div>
            <input type="hidden" id="fromid" name="notefromid"/>	<!-- 보내는 사람 ID (유저 ID) -->
         </div>
         
         <div>
            <p for="message" align="left" >쪽지 내용</p>
            <textarea id="message" rows="15" name="message" style="width:300px"></textarea>
         </div>

			<div style="padding-left:16%">
	            <input type="submit" value="쪽지 보내기"  />
	            <p></p>
	            <input type="reset" value="내용지우기" />			
			</div>         
			
			<div style='margin:-75px; float: left;margin-left:7px;'>
            <input type="button" value="이전페이지로" onclick="history.back();"/>
            
            </div>
      </form>
   
   </div>

</main>
 
 <jsp:include page="/jsp/bottom.jsp" />
 