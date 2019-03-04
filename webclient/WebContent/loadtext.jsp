<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page import="webclient.*"%><%@page import="java.util.*"%><%@page import="org.json.*"%><% 	 String fromme;String fromu;
            List<String> msglist=(ArrayList<String>)request.getAttribute("msglist");
            List<String> time=(ArrayList<String>)request.getAttribute("timelist");

			for	(int i=0;i<msglist.size();i++)
			{
				//System.out.println(s.getKey() + s.getValue());	
				JSONObject message = new JSONObject(msglist.get(i));

				if(((String)message.get("user")).equals("0"))
           		{%><span class="from"><span class="fromsub"><%=((String)message.get("msg"))%></span><span class="chattime"><%=time.get(i) %></span></span><br><%
          		}
				else
				{%><span class="to"><span class="tosub"><%=(String)message.get("msg")%></span><span class="chattime"><%=time.get(i) %></span></span><br><%
          		}
				}%>
          