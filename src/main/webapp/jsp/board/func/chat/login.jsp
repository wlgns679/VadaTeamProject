<?xml version="1.0" encoding="utf-8" ?>
<%@ page contentType = "text/xml; charset=utf-8" %>
<%

	request.setCharacterEncoding("utf-8");
	String nickName = request.getParameter("nickName");
%>  
<result>
	<code>success</code>
	<nickName><%= nickName %></nickName>
</result>