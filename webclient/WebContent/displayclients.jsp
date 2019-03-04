<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webclient.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
p {
	background: #97cffb;
	margin: 0%;
}

#users{

left:25%;background: #97cffb;
}

button:hover {
	filter: brightness(85%);
}

.button:active {
	transform: translateY(4px);
}

 #searchbar{
      position: absolute;
    left: 25%;
    top: 10%;
    height: 50%;
    width: 49.5%;}
    #showfriend
    {
        position: absolute;
    top: 0%;
    left: 90%;}
    #displayall
    {
     
     position:absolute;
     left:30%;
     width:50%;
     top:5%;
     z-index:2;}
body {
	color: #0436af;
	font-size: 20px;
}
span.from{
display:inline-block;float:top;
  
 width:45%;overflow-wrap: break-word; white-space:normal;
  height:auto;left:50%;position:relative;
  margin:auto;text-align:right;margin-bottom:2%;
}
span.fromsub{
background-color: #95cdfd;    padding-right: 20px;
    padding-bottom: 10px;
    }
    span.tosub{
    padding-right: 20px;
    padding-bottom: 10px;    box-shadow: 0 0 0 2px #95cdfd;
    }
  span.to{
  display:inline-block;position:relative;
  left:5%;
   max-width:50%;overflow-wrap: break-word;
      
  width:auto;
  height:auto;margin:auto;
  margin-bottom:2%;
}
#displayall
{
    left: 25%;
    top: 110%;
    width: 50%;
    
    background-color: #95cdfd;}
  
  #displaymsg{
  overflow-y: scroll;
  left:2%;
  }
  div.align{
  position:relative;
  left:0%;}
  span.chattime{
      font-size: 9px;
    min-width: auto;
    width: auto;
    text-align: center;
    margin-top: 6px;
    padding-right: 4px;    position: absolute;
    right: 0%;
    top: 70%;}
    #top{
    position:relative;
    background-color: #95cdfd;
        margin-bottom: 1%;}
</style>
<meta charset="UTF-8">

</head>
<body bgcolor="#ccf5ff">
	<script>
//console.log("script executed!!!");
var id="<%=(String)request.getAttribute("username")%>";<% String id=(String)request.getAttribute("username");%>
<%Map<String,String> userlist=(HashMap<String,String>)request.getAttribute("list");%>
 var webSocket = new WebSocket("ws://"+window.location.hostname+":8080/webclient/websocket/"+id);
webSocket.onmessage = function(message){ wsGetMessage(message);};
var tempuser="5jhgfd";
window.user=tempuser;



function wsSendMessage(msg){
	<%System.out.println("checkdff");%>
	
	var object='{"to":"'+window.user+'","text":"'+msg+'","msg":"0","from":"'+id+'"}';
	//console.log(object);
	var span=document.createElement("span");
	span.textContent=msg;
	span.setAttribute('class','fromsub');
	var spanmain=document.createElement("span");
	spanmain.appendChild(span);
	spanmain.setAttribute('class','from');
	//var br=document.createElement("br");
	//spanmain.innerHTML+="<br>";
	var today=new Date();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	var spantime=document.createElement("span");
	spantime.textContent=time;
	spantime.setAttribute('class','chattime');
	spanmain.appendChild(spantime);
	document.getElementById("displaymsg").appendChild(spanmain);
	document.getElementById("displaymsg").innerHTML+="<br>";
	webSocket.send(object);
	 var element = document.getElementById("displaymsg");
     element.scrollTop = element.scrollHeight;
	textID.value="";
	
	

}
function wsGetMessage(message){
	
	var a=message.data;

		var msg=JSON.parse(a);
		/* if(msg.id.localeCompare("@"))
			{
				if(window.showfriendrequest.localeCompare("0")==0)
				{
					displaymsg.value +=  msg.text + "\n"; 
				}
			}
			else
			{ */
				if((window.user.localeCompare(msg.from))==0)
				{	
					var span=document.createElement("span");
					span.textContent=msg.text;
					span.setAttribute('class','tosub');
					var spanmain=document.createElement("span");
					spanmain.appendChild(span);
					spanmain.setAttribute('class','to');
					var today=new Date();
					var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
					var spantime=document.createElement("span");
					spantime.setAttribute('class','chattime');

					spantime.textContent=time;
					spanmain.appendChild(spantime);
					
				
					document.getElementById("displaymsg").appendChild(spanmain) ;
					document.getElementById("displaymsg").innerHTML+="<br>";
					 var element = document.getElementById("displaymsg");
				      element.scrollTop = element.scrollHeight;
				}
				else
				{
					var http;
		
					  http = new XMLHttpRequest();
					  http.onreadystatechange = function() {
					    if (this.readyState == 4 && this.status == 200) 
					    {
					      document.getElementById(msg.from).innerHTML=this.responseText;
					    }
					  };
					  http.open("POST","notif", true);
					  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					  http.send("uniqueid="+msg.id);
				}
			
}
function wsSendFriendRequest(status,to)
{	
	var object='{"to":"'+to+'","from":"'+id+'","msg":"1","status":"'+status+'"}';
	webSocket.send(object);
}

</script>


	<div id="top"><p style="width: 200px; ">
		welcome
		<%=id %>
	</p>
		<input type="text"  id="searchbar" name="name" onkeyup="searchInfo()"
			autocomplete="off">
			<div id="displayall"
		></div>
		
			
	
	
	<button id="showfriend" onclick="showFriendRequest()">show
		friendrequest</button>
	
	</div>
	<div id="bottom">
	<form name='clients'
		style="width: 200px; position: relative; right: 300px;">
		<%for (Map.Entry<String,String> entry : userlist.entrySet()) { %>
	<div style="position:relative;">	<button type='button' name='clientlist' value='<%=entry.getKey()%>'
			onclick='sendinfo(this.value)'
			style="width: 216px; height: 50px; margin-left: 300px; background-color: #97cffb; box-shadow: 0px 0px 0 1px #147de8; margin-bottom: 10px; position: relative; color: #0436af; font-size: 20px;"><%=entry.getKey()%></button>
		<div id='<%= entry.getKey()%>'
			style="width: 25px; height: 25px; left: 490px; right: 0px; position: absolute; margin-bottom: 30px; bottom: -10px; background-color: #97cffb;"><%=(entry.getValue().equals("0"))?"": entry.getValue()%></div></div>
		<%} %>

	</form>
	
	
	<div id="users" style="position: absolute;z-index:1; top: 10%; width: 70%;">user
		will be displayed here</div>
				<div id="displayfriendrequest"></div>
	<a href='index.html'> logout</a></div>
	<script>
function showvalue(str)
{var xhttp; 

var a="user="+str+"&from=<%=id%>"; 

xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    document.getElementById("usersa").innerHTML = this.responseText;
  }
};
xhttp.open("POST", "displaydetails", true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(a);
	}function sendinfo(str) {
	 window.user=str;
  var xhttp; 
  
  var a="user="+str+"&from=<%=id%>"; 

  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    	
    
      document.getElementById("users").innerHTML = this.responseText;
      document.getElementById(window.user).innerHTML="";
      var element = document.getElementById("displaymsg");
      
    	if (element!==null) 
    		{
      element.scrollTop = element.scrollHeight;
    		}
    	  

    }
  };
  xhttp.open("POST", "sendbox", true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(a);}</script>
	<script>
	function searchInfo()
	{	var name=document.getElementById("searchbar").value;

			var http;
			
			if(name == "")
				{
				document.getElementById("displayall").style.display="none";
				}
			else
				{
				document.getElementById("displayall").style.display="block";
				}
			var senda="value="+name+"&from=<%=id%>";
			http=new XMLHttpRequest();
			http.onreadystatechange=function(){
				if(this.readyState==4&&this.status==200)
					{
					document.getElementById("displayall").innerHTML=this.responseText;
					
					}
			};
			
			http.open("POST","showalluser",true);
			http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			http.send(senda);
		
			
		
		}function showFriendRequest(){
			window.showfriendrequest="0";
			var xhttp; 

			var a="from=<%=id%>"; 

			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    document.getElementById("displayfriendrequest").innerHTML = this.responseText;
			  }
			};
			xhttp.open("POST", "displayfriendrequest", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send(a);

		}
		function acceptrequest(num,to)
		{

				var xhttp; 

				var a="from=<%=id%>&to="+to+"&num="+num;

				xhttp = new XMLHttpRequest();
			/* 	xhttp.onreadystatechange = function() {
				  if (this.readyState == 4 && this.status == 200) {
				    document.getElementById("displayfriendrequest").innerHTML = this.responseText;
				  }
				}; */
				xhttp.open("POST", "acceptfriend", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhttp.send(a);
				
			
		}
		function  sendclick(e){
		
		
		/* var input = document.getElementById("textID");
		input.addEventListener("keyup", function(event) {
			 */
		
		  if (e.keyCode === 13) {
			 // console.log("jfk");
		  
		   event.preventDefault();
		   document.getElementById("sendu").click();

		 /*  } */
		}
		}
		var i=0;
		function scrollingfunction(tao)
		{
			var element=document.getElementById("displaymsg");
			//console.log(element.scrollTop);
			if(element.scrollTop==0)
				{	console.log("check2");
					i+=1;
					var a="user="+tao+"&from=<%=id%>&no="+(i*20); 
					xhttp = new XMLHttpRequest();
					  xhttp.onreadystatechange = function() {
					    if (this.readyState == 4 && this.status == 200) {
					    	
					    
					       var elementa =document.createElement("div");
					      console.log(this.responseText);
					      //var textnode=document.createTextNode(this.responseText);
					  	   elementa.innerHTML=this.responseText;
					      var div = document.getElementById("secondtest");

					      var divChildren = div.childNodes;
					      //console.log(divChildren.length);
					     // console.log(divChildren);
	
							  var j=0;
							  while(j<divChildren.length){
							 //console.log(j);
							//console.log(div.childNodes.length);
						     elementa.appendChild(divChildren[j]);
						     //console.log(divChildren[j]);
						    
						  }
						  document.getElementById("secondtest").innerHTML="";
						  //(var k=0;k<elementa.childNodes.length;)
						  var k=0;
						  while(k<elementa.childNodes.length)
							  {		console.log(k);
						 			 document.getElementById("secondtest").appendChild((elementa.childNodes)[k]);
							  }
					      document.getElementById(window.user).innerHTML="";
					      var element1 = document.getElementById("displaymsg"); 
						      if(element1!=null&&(this.responseText.startsWith("<")))
						    	  {
						    	  	element1.scrollTop=element1.scrollHeight/(i+1);
						    	  }

					  }
					  };
					  xhttp.open("POST", "secondsend", true);
					  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhttp.send(a);
						}
					
				}
		
				
		
	</script>

</body>
</html>