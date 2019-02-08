<%@ page import="java.sql.*"  language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@page import="webclient.*" %>
<!DOCTYPE html>
<html>
<head><style>p{background:red;margin:auto;}div {
    position:relative;left:300px;background:orange;width=50px;
  }</style>
<meta charset="UTF-8">
<title>available users</title>

</head>
<body>
<script>
//console.log("script executed!!!");
var id="<%=request.getParameter("id")%>";
 var webSocket = new WebSocket("ws://localhost:8080/webclient/websocket/"+id);
var displaytext=document.getElementById("displaymsg");
//var message=document.getElementById("textID");
webSocket.onmessage = function(message){ wsGetMessage(message);};


function wsSendMessage(msg){
	var object='{"to":'+window.user+',"text":'+msg+'}';
	//var myObj = JSON.parse(object);
	webSocket.send(object);

  //  webSocket.send(window.user+"@ "+msg);

}
function wsGetMessage(message){
    displaymsg.value +=  message.data + "\n"; 
}
</script>
<%Connection con=database.getConnection();
PreparedStatement checkLogin=con.prepareStatement("SELECT username FROM storage");
ResultSet result=checkLogin.executeQuery();
 %>
<p>availables clients are</p><br>
<form name='clients'>
<%while(result.next())
{ %>
<input type='button' name='clientlist' value='<%=result.getString("username")%>' onclick='sendinfo(this.value)'><br>

 <%} %></form><br><a href='index.html'> logout</a>
<div id="users">user will be displayed here</div>
<script>function sendinfo(str) {
	 window.user=str;
  var xhttp;  
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("users").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "sendtext.jsp?user="+str, true);
  xhttp.send();
}</script><textarea id="displaymsg"rows="10" cols="100"></textarea>
</body>
</html>