<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class='availableusers'>
<p><%=(String)request.getAttribute("toname") %></p>
<%  Map<String,String> userdetails=(HashMap<String,String>)request.getAttribute("userdetails");
for(Map.Entry<String,String> entry : userdetails.entrySet())
{
	

	%><%= entry.getKey() %>:<%= entry.getValue() %><br>
	<%} %>
	
	<button class='availableusers' value="<%=request.getAttribute("bool")%>" onclick="wsSendFriendRequest(this.value,'<%=(Integer)request.getAttribute("to")%>')"><%=request.getAttribute("bool")%></button>
	<button class='availableusers' value="cancel request" onclick="wsSendFriendRequest(this.value,'<%=(Integer)request.getAttribute("to")%>')">deny friend request</button>
	<details class='availableusers'><summary class='availableusers'>friends</summary>
	<%  Map<Integer,List<String>> friends=(HashMap<Integer,List<String>>)request.getAttribute("friends");
	for(Map.Entry<Integer,List<String>> entry:friends.entrySet()){%>
	<p  class='availableusers' onclick="showvalue('<%=entry.getKey() %>'.'<%= entry.getValue().get(0) %>')"><%=entry.getKey() %></p><%} %>
	</details>
</body>
</html>