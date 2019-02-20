<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  Map<String,String> userdetails=(HashMap<String,String>)request.getAttribute("userdetails");
for(Map.Entry<String,String> entry : userdetails.entrySet())
{
	

	%><%= entry.getKey() %>:<%= entry.getValue() %><br>
	<%} %>
</body>
</html>