<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id=friendrequestdisplay>
<%
Map<Integer,String> friendrequestlist=(HashMap<Integer,String>)request.getAttribute("friendrequest");
if(friendrequestlist!=null)
{
	if(friendrequestlist.size()==0)
	{%>
		<div style="font-size:15px;">no friendrequest to display</div>
	<%}
for(Map.Entry<Integer,String> s:friendrequestlist.entrySet())
{
	%><div id="<%=s.getKey() %>"><%=s.getValue() %><button value="<%=s.getKey() %>" onclick="acceptrequest('1',this.value)">accept request</button><button onclick="acceptrequest('0','<%=s.getKey()%>')">reject request</button></div>
<% }}

%></div>
</body>
</html>