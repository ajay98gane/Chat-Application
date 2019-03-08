<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="availableusers">
	<form class="availableusers" action="createnewgroup" method="post"
		style="color: #0436af; font-size: 20px;">
		<input class="availableusers" type="hidden" name="from"
			value="<%=((String) request.getParameter("id"))%>"><br>
		group name<input class="availableusers" type="text" name="groupname"
			style="background-color: #97cffb; border-color: #147de8;; margin-bottom: 1px;">

		<input class="availableusers" type="submit"
			style="background-color: #97cffb; box-shadow: inset 0px 0px 0 1px #147de8; margin-bottom: 1px; width: 66px; color: #0436af; height: 25px;">
	</form>
</body>
</html>