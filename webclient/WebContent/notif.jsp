<%@ page import="java.sql.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="webclient.*" %>
<%
			
				
			Connection cona=database.getConnection();
			PreparedStatement notifget=cona.prepareStatement("SELECT notif FROM msgmap WHERE id='"+request.getParameter("uniqueid")+"'");
			ResultSet notifresult=notifget.executeQuery();
			String temporary="0";
			int count=0;
			while(notifresult.next())
			{	 temporary=notifresult.getString("notif");
				
			}if(temporary!=null)
			{
				count=Integer.parseInt(temporary);
			}
			
			PreparedStatement notif=cona.prepareStatement("UPDATE msgmap SET notif='"+(++count)+"' WHERE id='"+request.getParameter("uniqueid")+"'");
			notif.executeUpdate();
			%><%=count %>
