<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Map<Integer,String> msglist=(HashMap<Integer,String>)request.getAttribute("listofusers");
for(Map.Entry<Integer,String> s: msglist.entrySet())
{

%>
<div class='availableusers' onclick="showvalue('<%=s.getKey() %>','<%=s.getValue() %>')"><%=s.getValue() %></div><br>
<%} %>
</body>
</html>