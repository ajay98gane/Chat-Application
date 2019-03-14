<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %><%@page import="webclient.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class='availableusers'>
<p style="background-color: #93ccff;"><%=(String)request.getAttribute("toname") %></p>
<%  Map<String,String> userdetails=(HashMap<String,String>)request.getAttribute("userdetails");
for(Map.Entry<String,String> entry : userdetails.entrySet())
{
	

	%><%= entry.getKey() %>:<%= entry.getValue() %><br>
	<%} %>
	
	<button class='availableusers'value="<%=request.getAttribute("bool")%>" onclick="wsSendFriendRequest(this.value,'<%=(Integer)request.getAttribute("to")%>')"><%=request.getAttribute("bool")%></button>
	<details class='availableusers'><summary class='availableusers'>friends</summary>
	<% 	 List<Pair<Users,String>> friends=(ArrayList<Pair<Users,String>>)request.getAttribute("friends");
	for(Pair<Users,String> entry:friends){%>
	<p  class='availableusers' onclick="showvalue('<%=entry.getLeft().getId() %>'.'<%= entry.getLeft().getName() %>')"><%=entry.getLeft().getName() %></p><%}
	%>
	</details>
	<details class='availableusers'><summary class='availableusers'>group</summary>
		<% 	 List<Pair<Group,String>> group=(ArrayList<Pair<Group,String>>)request.getAttribute("group");
	for(Pair<Users,String> entry:friends){%>
	
	<p  class='availableusers' onclick="showvalue('<%=entry.getLeft().getId() %>'.'<%= entry.getLeft().getName() %>')"><%=entry.getLeft().getName() %></p><%}
	%>
	</details></body>
</html>