<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page
	import="webclient.*"%><%@page import="java.util.*"%><%@page
	import="org.json.*"%><%
					String fromme;
					String fromu;
					System.out.println("check");
					List<List<String>> msglist = (ArrayList<List<String>>) request.getAttribute("msglist");
					List<String> time = (ArrayList<String>) request.getAttribute("timelist");

					for (int i = 0; i < msglist.size(); i++) {
						//System.out.println(s.getKey() + s.getValue());	
						JSONObject message = new JSONObject(msglist.get(i).get(1));

						if (((String) message.get("user")).equals("0")) {
				%><span class="from"><span class="fromsub"><%=((String) message.get("msg"))%></span><span
					class="fromchattime"><%=time.get(i)%></span></span><br>
				<%
					} else {
				%><span class="to"><span class="touser"><%=((String)msglist.get(i).get(0)) %></span><span class="tosub"><%=(String) message.get("msg")%></span><span
					class="tochattime"><%=time.get(i)%></span></span><br>
				<%
					}
					}
				%>