<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form class="availableusers" action="groupcreation" method="post">
		<input type="hidden" name="groupid" class="availableusers"
			value="<%=(Integer) request.getAttribute("groupId")%>">

			<span class="availableusers"><%=(String)request.getAttribute("groupname") %></span><br>
		created by
		<%=request.getParameter("fromname")%><br>
		<%
			Map<Integer, List<String>> friendlist = (HashMap<Integer, List<String>>) request.getAttribute("friendlist");
			for (Map.Entry<Integer, List<String>> s : friendlist.entrySet()) {
		%>
		<input type="checkbox" name="groupusers"  class="availableusers" value="<%=s.getKey()%>"><%=s.getValue().get(0)%><input class="availableusers"
			type="radio" name="<%=s.getKey() %>" value="admin">admin <input class="availableusers"
			type="radio" name="<%=s.getKey() %>" value="user" checked>user<br>
		<%
			}
		%>
		<input class="availableusers" type="submit" value="submit">
	</form>
</body>
</html>