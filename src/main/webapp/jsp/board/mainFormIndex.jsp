<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 제품등록하고 새로고침시 글작성 되지 않도록 URL 변경 -->
<c:if test="${param.productnum eq null}">
	<c:redirect url="/mainform.do" />
</c:if>

<!-- 디테일폼으로 URL 변경 (게시글 세부정보에서 사진목록이 출력되지 않는 에러가 발생하여 삽입) -->
<c:if test="${param.productnum ne null}">
	<c:redirect url="/boarddetailform.do?productnum=${param.productnum }" />
</c:if>