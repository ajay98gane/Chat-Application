 var xhttp = new XMLHttpRequest();
function response( sendto,params,callback) {
	
	xhttp.onreadystatechange = function() {
    if (xhttp.readyState==4 && xhttp.status==200) {
        console.log("a");
        callback();
        console.log("b");
       
    }
    };
    xhttp.open("POST", sendto, true);
    xhttp.setRequestHeader("Authorization","abc");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xhttp.send(params);
}
function wsSendMessage(msg){
			
			if((msg!==null)&&(msg!==""))
				{
					var object='{"to":"'+window.user+'","text":"'+msg+'","msg":"0","from":"'+id+'"}';
					var span=document.createElement("span");
					span.textContent=msg;
					span.setAttribute('class','fromsub');
					var spanmain=document.createElement("span");
					spanmain.appendChild(span);
					spanmain.setAttribute('class','from');

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
			
			

		}
		function wsGetMessage(message){
			
			var a=message.data;

				var msg=JSON.parse(a);

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
							function respons()
							{
								document.getElementById(msg.from).innerHTML=xhttp.responseText;
							}
							response("notif","uniqueid="+msg.id,respons);

						}
					
		}
		function wsSendFriendRequest(status,to)
		{	
			var object='{"to":"'+to+'","from":"'+id+'","msg":"1","status":"'+status+'"}';
			webSocket.send(object);
		}

	
function showvalue(str,toname)
{

//	var a="user="+str+"&from="+id; 
	 var a="user="+str+"&from="+id+"&fromname="+name+"&username="+toname; 

	function respons()
	{
		document.getElementById("usersa").innerHTML=xhttp.responseText;
	}
	response("displaydetails",a,respons);

}

function sendinfo(str,toname) {
	 window.user=str;
	 var a="user="+str+"&from="+id+"&fromname="+name+"&username="+toname; 
	 	 function scrolldown()
	 	 {	
	 		 console.log("kjf");
	 		 document.getElementById("users").innerHTML=xhttp.responseText;
	 		  var element = document.getElementById("displaymsg");
		        if (element!==null) 
		    	{	console.log("afgsdhts");
		    		 element.scrollTop = element.scrollHeight;
		    	} 
	 	 }
	 	 response("sendbox",a,scrolldown);
	 	 document.getElementById(str).innerHTML="";

	 
	
}
	
function searchInfo()
{	
		var name=document.getElementById("searchbar").value;
		var senda="value="+name+"&from="+id;
		function respons()
		{
			document.getElementById("displayall").innerHTML=xhttp.responseText;
		}
		response("showalluser",senda,respons);


}function showFriendRequest(){
			window.showfriendrequest="0";

			var a="from="+id; 
				function respons()
				{
					document.getElementById("displayfriendrequest").innerHTML=xhttp.responseText;
				}
				response("displayfriendrequest",a,respons);



		}
		function acceptrequest(num,to)
		{

				

				var a="from="+id+"&to="+to+"&num="+num;

				response("acceptfriend",a);
			
			
		}
		function  sendclick(e){
		
		

		
		  if (e.keyCode === 13) {
		  
		   event.preventDefault();
		   document.getElementById("sendu").click();

		}
		}
		var i=0;
		function scrollingfunction(userid,username)
		{
			var element=document.getElementById("displaymsg");
			if(element.scrollTop==0)
				{						i+=1;
					var a="user="+userid+"&from="+id+"&no="+(i*20)+"&username="+username+"&fromname="+fromname; 
					
					    function prepend()
					    {
					       var elementa =document.createElement("div");
					      
					  	   elementa.innerHTML=xhttp.responseText;
					      var div = document.getElementById("secondtest");

					      var divChildren = div.childNodes;
					    
	
							  var j=0;
							  while(j<divChildren.length){

						     elementa.appendChild(divChildren[j]);
						    
						  }
						  document.getElementById("secondtest").innerHTML="";
						  var k=0;
						  while(k<elementa.childNodes.length)
							  {		
						 			 document.getElementById("secondtest").appendChild((elementa.childNodes)[k]);
							  }
					      document.getElementById(window.user).innerHTML="";
					      var element1 = document.getElementById("displaymsg"); 
						      if(element1!=null&&(xhttp.responseText.startsWith("<")))
						    	  {
						    	  	element1.scrollTop=element1.scrollHeight/(i+1);
						    	  }
					    }
					    response("secondsend",a,prepend);	


					
				}
					
		}
		function createnewgroup()
		{	
			var a="from="+id;
			function respons()
			{
				document.getElementById("usersa").innerHTML=xhttp.responseText;
			}
			response("createnewgroup.jsp",a,respons);
			
		}
		