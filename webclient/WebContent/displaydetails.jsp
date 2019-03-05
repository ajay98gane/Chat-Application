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
	
	<button value="<%=request.getAttribute("bool")%>" onclick="wsSendFriendRequest(this.value,'<%=(String)request.getAttribute("to")%>')"><%=request.getAttribute("bool")%></button>
	<button value="cancel request" onclick="wsSendFriendRequest(this.value,'<%=(String)request.getAttribute("to")%>')">deny friend request</button>
	<details><summary>friends</summary>
	<%  List<String> friends=(ArrayList<String>)request.getAttribute("friends");
	for(String fr:friends){%>
	<p onclick="showvalue('<%=fr %>')"><%=fr %></p><%} %>
	</details>
</body>
</html>