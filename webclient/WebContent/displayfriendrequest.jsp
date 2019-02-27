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
List<String> friendrequestlist=(ArrayList<String>)request.getAttribute("friendrequest");
if(friendrequestlist!=null)
{
	
for(String s:friendrequestlist)
{
	%><div id="<%=s %>"><%=s %><button value="<%=s %>" onclick="acceptrequest('1','<%=s%>')">accept request</button><button onclick="acceptrequest('0','<%=s%>')">reject request</button></div>
<% }}

%></div>
</body>
</html>