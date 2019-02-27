<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webclient.*"%>
<%@page import="java.util.*"%><%@page import="org.json.*"%>


<%=(String)request.getAttribute("to") %>

<div>


	<textarea id="displaymsg" rows="10" cols="100"
		style="width: 800px; height: 400px; font-size: 20px; background: #ccf5ff; color: #0436af; position: relative;">
<% 	 String fromme;String fromu;
            List<String> msglist=(ArrayList<String>)request.getAttribute("msglist");
			for	(String s:msglist)
			{
					
				JSONObject message = new JSONObject(s);

				if(((String)message.get("user")).equals("0"))
           		{%><%=
           		  fromme="\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+(String)message.get("msg")+"\n" %>
		<%
          		}
				else
				{%><%=fromu=(String)message.get("msg")+"\n"%>
		<%
          		}}%>&#10;
           </textarea>
	<form action="">
		<input id="textID" type="text"
			style="width: 800px; height: 25px; background: #ccf5ff;"> <input
			onclick="wsSendMessage(textID.value)" value="Send" type="button"
			style="position: absolute; left: 755px; height: 30px; width: 51px; background-color: #97cffb; box-shadow: 0px 0px 0 1px #147de8; right: 620px;">

	</form>
</div>
<p>


	<br>
</p>



