<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webclient.*"%>
<%@page import="java.util.*"%><%@page import="org.json.*"%>


<%=(String)request.getAttribute("to") %>

<div id="testing">


	<div id="displaymsg" rows="10" cols="100" onscroll="scrollingfunction('<%=(( String)request.getAttribute("to") )%>')"
		style="width: 800px; height: 400px; font-size: 20px; background: #ccf5ff; color: #0436af; position: relative;"><div class="align" id="alignno"><div id="secondtest">
<% 	 String fromme;String fromu;
            List<String> msglist=(ArrayList<String>)request.getAttribute("msglist");
            List<String> time=(ArrayList<String>)request.getAttribute("timelist");

			for	(int i=0;i<msglist.size();i++)
			{
				//System.out.println(s.getKey() + s.getValue());	
				JSONObject message = new JSONObject(msglist.get(i));

				if(((String)message.get("user")).equals("0"))
           		{%><span class="from"><span class="fromsub"><%=((String)message.get("msg"))%></span><span class="chattime"><%=time.get(i) %></span></span><br>
		<%
          		}
				else
				{%><span class="to"><span class="tosub"><%=(String)message.get("msg")%></span><span class="chattime"><%=time.get(i) %></span></span><br>
		<%
          		}
				}%>&#10;
       </div>  		<input id="textID" type="text" onkeyup="sendclick(event)"
			style="width: 800px; height: 25px; background: #ccf5ff;left:0%; position:absolute;"> 
			<button id="sendu"
			onclick="wsSendMessage(textID.value)" value="Send"  
			style="position: absolute; left: 93.5%; height: 30px; width: 51px; background-color: #97cffb; box-shadow: 0px 0px 0 1px #147de8; ">send</button>
			</div>  </div>
			
</div>
<p>


	<br>
</p>



