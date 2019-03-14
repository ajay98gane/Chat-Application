<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page
	import="webclient.*"%><%@page import="java.util.*"%><%@page
	import="org.json.*"%><%
	
	List<Message> msglist = (ArrayList<Message>) request.getAttribute("msglist");

		for(Message msg:msglist)
		{
		if (msg.getUser().equals("0")) {
%><span class="from"><span class="fromsub"><%=(msg.getText())%></span><span
	class="fromchattime"><%=msg.getTime()%></span></span>
<br>
<%
	} else {
%><span class="to"><span class="tosub"><%=msg.getText()%></span><span
	class="tochattime"><%=msg.getTime()%></span></span>
<br>
<%
	}
	}
%>
