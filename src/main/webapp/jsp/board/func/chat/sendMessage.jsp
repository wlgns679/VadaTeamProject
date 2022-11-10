<?xml version="1.0" encoding="utf-8" ?>
<%@page import="java.net.Inet4Address"%>
<%@ page contentType="text/xml; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="vada.util.DB"%>
<%@ page import="vada.util.Util"%>
<%
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	request.setCharacterEncoding("utf-8");
	String nickName = request.getParameter("nickName");
	String msg = "[" + nickName + "] " + request.getParameter("msg") + "<br />" + timestamp;
	String message = Inet4Address.getLocalHost().getHostAddress() + timestamp + "[" + nickName + "] " + msg;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	boolean isSuccess = true;
	
	try {
		conn = DB.getConnection();
		pstmt = conn.prepareStatement(
		"insert into chatmsglog(chatmsglogroomnum, fromid, toid, chatmsglogdate, msg) values (?, ?, ?, now(), ?)" );
		
		pstmt.setInt(1, 1);
		pstmt.setString(2, nickName);
		pstmt.setString(3, "default");
		pstmt.setString(4, msg);
		
		pstmt.executeUpdate();
	
	} catch (SQLException ex) {
		isSuccess = false;
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
%>
<result> <code><%=isSuccess ? "success" : "fail"%></code> </result>
