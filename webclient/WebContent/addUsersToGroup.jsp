<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="java.util.*"%><%@page import="webclient.*"%>
	
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
		List<Pair<Users,String>> friendlist = (List<Pair<Users,String>>) request.getAttribute("friendlist");
				for(Pair<Users,String> s:friendlist){
		%>
		<input type="checkbox" name="groupusers"  class="availableusers" value="<%=s.getLeft().getId()%>"><%=s.getLeft().getName()%><input class="availableusers"
			type="radio" name="<%=s.getLeft().getId() %>" value="admin">admin <input class="availableusers"
			type="radio" name="<%=s.getLeft().getId() %>" value="user" checked>user<br>
		<%
			}
		%>
		<input class="availableusers" type="submit" value="submit">
	</form>
</body>
</html>