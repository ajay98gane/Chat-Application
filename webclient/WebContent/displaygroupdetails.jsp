<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="availableusers">
	<p><%=request.getAttribute("toname")%>
		<br>users
	</p>

	<%
		int fromid = (Integer) request.getAttribute("from");
		int toid = (Integer) request.getAttribute("to");
		String fromname=(String) request.getAttribute("fromname");
		Map<Integer, List<String>> groupusers = (Map<Integer, List<String>>) request.getAttribute("userdetails");
		for (Map.Entry<Integer, List<String>> entry : groupusers.entrySet()) {
	%>
	<div class='availableusers'>
		<span onclick="showvalue('<%=entry.getKey()%>','<%=entry.getValue().get(0)%>')"><%=entry.getValue().get(0)%>   -  </span><button> <%=entry.getValue().get(1).equals("1") ? "admin" : "user" %></button></div>
	<%
		}
	%>
	<div class='availableusers'
		onclick="showvalue('<%=fromid%>','<%=fromname%>')"><%=fromname%>   -   <button>user </button>  <button onclick="removefromgroup('<%=fromid%>','<%=toid%>')">exit group</button></div> 
</body>
</html>