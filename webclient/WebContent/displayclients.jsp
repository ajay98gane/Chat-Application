<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webclient.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css//style.css">
<script src="js//functions.js"></script>
<script>
		var id=<%=(Integer) request.getAttribute("userid")%>;
		var name="<%=(String) request.getAttribute("username")%>";
		var webSocket = new WebSocket("ws://" + window.location.hostname
			+ ":8080/webclient/websocket/" + id);
	webSocket.onmessage = function(message) {
		wsGetMessage(message);
	};
	var tempuser = "5jhgfd";
	window.user = tempuser;
	window.onload = function() {
		var divToHide = document.getElementById('displayfriendrequest');
		document.onclick = function(e) {

			if ((e.target.id !== 'displayfriendrequest')
					&& (e.target.id !== 'showfriend')) {
				divToHide.style.display = 'none';
			}
			else{
				divToHide.style.display = 'block';

			}
			if ((e.target.id !== 'searchbar') && (e.target.id !== 'displayall')) {
				document.getElementById("displayall").style.display = 'none';
			}
			else{
				document.getElementById("displayall").style.display = 'block';
			}
			if ((e.target.id !== 'usersa') && (e.target.id !== 'displayall')
					&& (e.target.className !== 'availableusers')&&(e.target.className !== 'admin')) {
				document.getElementById("usersa").style.display = 'none';
			}
			else{
				document.getElementById("usersa").style.display = 'block';
			}

		};
	};
</script>
</head>
<body bgcolor="#ccf5ff">


	<%
		int id = ((Integer) request.getAttribute("userid"));
		String username = (String) request.getAttribute("username");

		Map<Integer, List<String>> userlist = (HashMap<Integer, List<String>>) request.getAttribute("list");
		Map<Integer, List<String>> grouplist = (HashMap<Integer, List<String>>) request.getAttribute("grouplist");
	%>
	<div id="top">
		<p style="width: 200px;color: white;
    background-color: #003366;">
			welcome
			<%=username%>
		</p>
		<input type="text" id="searchbar" name="name" onkeyup="searchInfo()"
			autocomplete="off">
		<div id="displayall"></div>
		<button id="showfriend" onclick="showFriendRequest()">show
			friend request</button>
		<button id="logout" onclick="logout()">logout</button>
	</div>



	<div id="bottom">
		<div class="left">
			<div class="friends">
				friends
				<form name='clients'
					style="width: 200px; position: relative; right: 300px;">
					<%
						for (Map.Entry<Integer, List<String>> entry : userlist.entrySet()) {
					%>
					<div style="position: relative;">
						<button type='button' name='clientlist'
							value='<%=entry.getKey()%>'
							onclick="sendinfo(this.value,'<%=(entry.getValue()).get(0)%>')"
							style="width: 216px; height: 50px; margin-left: 300px; background-color: #feffff; box-shadow: 0px 0px 0 1px #147de8; margin-bottom: 10px; position: relative; left: 1%; color: #0436af; font-size: 20px;"><%=(entry.getValue()).get(0)%></button>
						<div id='<%=entry.getKey()%>'
							style="width: 25px; height: 25px; left: 490px; right: 0px; position: absolute; margin-bottom: 30px; bottom: -10px; background-color: #feffff;"><%=(entry.getValue().get(1).equals("0")) ? "" : entry.getValue().get(1)%></div>
					</div>
					<%
						}
					%>

				</form>
			</div>
			<div class="group">
				group
				<form name='group'
					style="width: 200px; position: relative; right: 300px;">

					<%
						for (Map.Entry<Integer, List<String>> entry : grouplist.entrySet()) {
							System.out.println(entry.getKey());
					%>
					<div style="position: relative;">
						<button type='button' name='clientlist'
							value='<%=entry.getKey()%>'
							onclick="sendinfo(this.value,'<%=(entry.getValue()).get(0)%>')"
							style="width: 216px; height: 50px; margin-left: 300px; background-color: #feffff; box-shadow: 0px 0px 0 1px #147de8; margin-bottom: 10px; position: relative; left: 1%; color: #0436af; font-size: 20px;"><%=(entry.getValue()).get(0)%></button>
						<div id='<%=entry.getKey()%>'
							style="width: 25px; height: 25px; left: 490px; right: 0px; position: absolute; margin-bottom: 30px; bottom: -10px; background-color: #feffff;"><%=(entry.getValue().get(1).equals("0")) ? "" : entry.getValue().get(1)%></div>
					</div>
					<%
						}
					%>

				</form>
			</div>
			<div class="availableusers" onclick="createnewgroup()">+create
				new group</div>
		</div>


		<div id="users"
			style="position: absolute; z-index: 1; top: 10%; width: 70%;">
		</div>
		<div id="displayfriendrequest"></div>
		<div id="usersa"></div>
	</div>



</body>
</html>