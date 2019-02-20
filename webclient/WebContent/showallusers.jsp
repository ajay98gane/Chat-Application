<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function showvalue(str)
{var xhttp; 

var a="user="+str+"&from=<%=request.getAttribute("from")%>"; 

xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    document.getElementById("usersa").innerHTML = this.responseText;
  }
};
xhttp.open("POST", "displaydetails", true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(a);
	}
</script>
<%
List<String> msglist=(ArrayList<String>)request.getAttribute("listofusers");
for(String s: msglist)
{

%>
<button value="<%=s %>" onclick="showvalue(this.value)"><%=s %></button><br>
<%} %><div id="usersa"></div>
</body>
</html>