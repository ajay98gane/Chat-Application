<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String user=request.getParameter("user");
%>
<%=user %>
<div id="send box">
	<form action="">
                <input id="textID"   type="text"><br>
                <input onclick="wsSendMessage(textID.value)" value="Send" type="button">
                
            </form>
</div>

