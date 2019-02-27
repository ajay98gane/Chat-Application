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
List<String> msglist=(ArrayList<String>)request.getAttribute("listofusers");
for(String s: msglist)
{

%>
<button value="<%=s %>" onclick="showvalue(this.value)"><%=s %></button><br>
<%} %><div id="usersa"></div>
</body>
</html>