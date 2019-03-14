<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*"%><%@page import="webclient.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="availableusers"><%
		int fromid = (Integer) request.getAttribute("from");
		int toid = (Integer) request.getAttribute("to");
		String fromname=(String) request.getAttribute("fromname");
		String a;%>
	<p style="background-color: #93ccff;"><%=request.getAttribute("toname")%></p>
	<div class="availableusers" onclick="newGroupUser('<%=(String)request.getAttribute("toname")%>','<%=toid%>')">+add users</div>
		<p style="background-color: #93ccff;">users</p>
	
		<% 
		List<GroupUser> groupusers = (ArrayList<GroupUser>) request.getAttribute("userdetails");
		for(GroupUser entry:groupusers){

			a=entry.getAdminStatus().equals("1") ? "admin" : "user";
	%>
	<div class='availableusers' id="groupadminaccess">
		<span onclick="showvalue('<%=entry.getUser().getId()%>','<%=entry.getUser().getName()%>')"><%=entry.getUser().getName()%>   -   </span><button   class="admin" name="<%=entry.getUser().getId() %>" value="<%=a%>" onclick="changeadminstatus(this.value,'<%=fromid%>','<%=toid%>','<%=entry.getUser().getId()%>')"><%=a %></button><button  onclick="removefromgroup('<%=entry.getUser().getId()%>','<%=toid%>')">remove from group</button></div>
	<%
		}
	%>
	<div class='availableusers'
		onclick="showvalue('<%=fromid%>','<%=fromname%>')"><%=fromname%>   -  <button  class="admin" >admin</button>    <button class="availableusers" onclick="removefromgroup('<%=fromid%>','<%=toid%>')">exit group</button></div> 
		<button  onclick="deletegroup('<%=toid %>')" >deletegroup</button>
</body>
</html>