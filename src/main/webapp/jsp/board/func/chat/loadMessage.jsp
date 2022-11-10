<?xml version="1.0" encoding="utf-8" ?>
<%@page import="java.util.ArrayList"%>
<%@ page contentType = "text/xml; charset=utf-8" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "vada.util.DB" %>
<%@ page import = "vada.util.Util" %>
<% 
	request.setCharacterEncoding("utf-8");
	int lastMsgId = Integer.parseInt(request.getParameter("lastMsgId"));
	List messageList = new ArrayList();
	
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	int newLastMsgId = 0;
	
	boolean isSuccess = true;
	
	try {
		conn = DB.getConnection();
		stmt = conn.createStatement();
		
		if (lastMsgId == 0) {
			rs = stmt.executeQuery(" select max(chatmsglogid) from chatmsglog ");
			  
			if (rs.next()) {
				newLastMsgId = rs.getInt(1);
			}
			System.out.println("newLastMsgId========>" + newLastMsgId);
			
		} else {
			// 새로 쓴 메시지 가져옴
			rs = stmt.executeQuery(" select * from chatmsglog where chatmsglogid > " + lastMsgId + " order by chatmsglogdate ");
			while(rs.next()) {
				messageList.add(rs.getString("msg"));
				newLastMsgId = rs.getInt("chatmsglogid");
			}
		}
		
	} catch(SQLException ex) {
		isSuccess = false;
	} finally {
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>
<result>
	<code><%= isSuccess ? "success" : "fail" %></code>
	<%	if (isSuccess) { %>
	<lastMsgId><%= newLastMsgId %></lastMsgId>
	<messages>
	<%		for (int i = 0 ; i < messageList.size() ; i++) { %>
		<message><![CDATA[<%= Util.toJS((String)messageList.get(i)) %>]]></message>
	<%		} %>
	</messages>
	<%	} %>
</result>