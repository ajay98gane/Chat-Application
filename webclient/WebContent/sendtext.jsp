<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webclient.*"%>
<%@page import="java.util.*"%><%@page import="org.json.*"%>


<%=(String)request.getAttribute("to") %>

<div id="testing">


	<div id="displaymsg" rows="10" cols="100" onscroll="scrollingfunction('<%=(( String)request.getAttribute("to") )%>')"
		><div class="align" id="alignno"><div id="secondtest">
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
				}%>
				</div>  </div>  </div>
		<input id="textID" type="text" onkeyup="sendclick(event)"
			style="width: 95%; height: 25px; background: #ccf5ff;left:2%; position:relative;"> 
			<button id="sendu"
			onclick="wsSendMessage(textID.value)" value="Send"  
			style="position: absolute; right: 2.5%; height: 30px; width: 51px; background-color: #97cffb; box-shadow: 0px 0px 0 1px #147de8; ">send</button>
			       
			
</div>
<p>


	<br>
</p>



