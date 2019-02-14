package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class sendbox
 */
@WebServlet("/sendbox")
public class sendbox extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<String> messages=new ArrayList<String>();
	String to=request.getParameter("user");
	String from=request.getParameter("from");
	Connection con;
	try {
		con = database.getConnection();
	

	PreparedStatement testmap=con.prepareStatement("SELECT fromuser,touser FROM msgmap WHERE fromuser='"+from+"'AND touser='"+to+"'");
	ResultSet checkresult=testmap.executeQuery();
	String check="";
	while(checkresult.next())
	{
		check=checkresult.getString("fromuser");
	}check=check.trim();
	if(!check.equals(from))
	{
	PreparedStatement insertmap=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES('"+from+"','"+to+"')");
	insertmap.executeUpdate();
	}
	PreparedStatement clearnotif=con.prepareStatement("UPDATE msgmap SET notif='0' WHERE fromuser='"+to+"'AND touser='"+from+"' ");
	clearnotif.executeUpdate();
	PreparedStatement getmap=con.prepareStatement("SELECT fromuser,touser,id FROM msgmap WHERE (fromuser='"+from+"' OR fromuser= '"+to+"') AND (touser='"+from+"' OR touser='"+to+"')");
	ResultSet mapresult=getmap.executeQuery();
	String num="";
	String tempFrom="";
	String conf;
	String fromid="";String toid="";
	while(mapresult.next())
	{
		if(from.equals(mapresult.getString("fromuser")))
		{
		 
		fromid=mapresult.getString("id")+"";
		}
		else
		{
		toid=mapresult.getString("id")+"";	
		}

	}
	PreparedStatement checkLogin=con.prepareStatement("SELECT msg,id FROM messages WHERE id='"+fromid+"' OR id='"+toid+"'");
	ResultSet result=checkLogin.executeQuery();
	 while(result.next())
     {
     	
			if(result.getString("id").equals(fromid))
			{
				messages.add("{\"user\":\"0\",\"msg\":\""+result.getString("msg")+"\"}");
			}
			else
			{
				messages.add("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");

			}
			System.out.println("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");
     }
	 request.setAttribute("to",to);
	 request.setAttribute("msglist",messages);
     RequestDispatcher rd=request.getRequestDispatcher("sendtext.jsp");  
     rd.forward(request, response); 
	
	
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	}

}
