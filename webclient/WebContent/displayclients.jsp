<%@ page import="java.sql.*"  language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@page import="webclient.*" %>
<!DOCTYPE html>
<html>
<head><style>p{background:#97cffb;margin:auto;}div {
    position:relative;left:300px;background:#97cffb;width=50px;}button:hover {
  filter: brightness(85%);

  }body{color: #0436af;
	     font-size:20px;}</style>
<meta charset="UTF-8">
<title>available users</title>
</head>
<body bgcolor="#ccf5ff">
<script>
//console.log("script executed!!!");
var id="<%=request.getParameter("id")%>";
 var webSocket = new WebSocket("ws://"+window.location.hostname+":8080/webclient/websocket/"+id);
//var displaytext=document.getElementById("chatbox").contentWindow.document.getElementId("displaymsg");
//var message=document.getElementById("textID");
webSocket.onmessage = function(message){ wsGetMessage(message);};
var tempuser="5jhgfd";
window.user=tempuser;


function wsSendMessage(msg){
	var object='{"to":"'+window.user+'","text":"'+msg+'","from":"'+id+'"}';
	displaymsg.value+="\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+msg+"\n";
	webSocket.send(object);
	textID.value="";
	
	

}
function wsGetMessage(message){
	
	var a=message.data;

		var msg=JSON.parse(a);
		if((window.user.localeCompare(msg.from))==0)
		{
			displaymsg.value +=  msg.text + "\n"; 
		}
		else
		{
			var http;

			  http = new XMLHttpRequest();
			  http.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    /* 	var para = document.createElement("P");
			    	  var t = document.createTextNode(this.responseText);
			    	  para.appendChild(t);
			    	  document.getElementById(msg.from).appendChild(para); */
			      document.getElementById(msg.from).innerHTML=this.responseText;
			    }
			  };
			  http.open("GET", "notif.jsp?uniqueid="+msg.id, true);
			  http.send();
		}
}

</script>
<% String id=request.getParameter("id");
Connection con=database.getConnection();
PreparedStatement checkLogin=con.prepareStatement("SELECT username FROM storage WHERE mobileno <> '"+id+"'");
ResultSet result=checkLogin.executeQuery();
 %>
 
<p	style ="
    width: 200px;
    margin-left: 0px;">welcome <%=id %><br>available users are</p><br>
<form name='clients' style="
    width: 200px;
    position: relative;
    right: 300px;">
<%while(result.next())
{ %>
<input type='button' name='clientlist' value='<%=result.getString("username")%>' onclick='sendinfo(this.value)' style="
    width: 216px;
    margin-left: 300px;background-color: #97cffb;
    box-shadow: 0px 0px 0 1px #147de8;
		    margin-top: 1px;
		    color: #0436af;
  font-size:20px;
    
    
">
<div id='<%=result.getString("username")%>' style="
    width: 15px;
    height:25px;
    left: 500px;
    right: 0px;
    bottom: 27px;background-color: #97cffb;
"><%PreparedStatement getnotification=con.prepareStatement("SELECT notif FROM msgmap WHERE fromuser='"+result.getString("username")+"' AND touser='"+id+"'");
ResultSet notifi=getnotification.executeQuery();
while(notifi.next())
{if((!notifi.getString("notif").equals("0"))&&(notifi.getString("notif")!=null)){
%><%=notifi.getString("notif") %><%}} %></div>

 <%} %></form><br><a href='index.html'> logout</a>
<div id="users" style="
    position:absolute;top:0px;width:1500px;">user will be displayed here</div>
<script>function sendinfo(str) {
	 window.user=str;
  var xhttp; 
  
  var a="user="+str+"&from=<%=request.getParameter("id")%>"; 

  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("users").innerHTML = this.responseText;
      document.getElementById(window.user).innerHTML="";
    }
  };
  xhttp.open("POST", "sendtext.jsp", true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  // xhttp.setRequestHeader("Content-length", b.length);
  //xhttp.setRequestHeader("Connection", "close"); 

	

<%--   xhttp.send("user="+str+"&from=<%=request.getParameter("id")%>");
 --%>xhttp.send(a);}</script>
</body>
</html>