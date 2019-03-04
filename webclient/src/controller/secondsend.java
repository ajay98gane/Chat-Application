package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class secondsend
 */
@WebServlet("/secondsend")
public class secondsend extends HttpServlet {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  List<String> messages=new ArrayList<String>();
		List<String> time=new ArrayList<String>();
	 String to=request.getParameter("user");
		String from=request.getParameter("from");
		String no=request.getParameter("no");
		String offsetValue=request.getParameter("no");
		try {

		Connection con=database.getConnection();


	

 
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
	PreparedStatement checkLogin=con.prepareStatement("SELECT msg,id,cast(timeat as time) FROM messages WHERE id='"+fromid+"' OR id='"+toid+"' ORDER BY timeat DESC LIMIT 20 OFFSET "+no);
	ResultSet result=checkLogin.executeQuery();		 
	while(result.next())
	     {
	     		//System.out.println(result.getString("cast(timeat as time)")+result.getString("msg"));
				if(result.getString("id").equals(fromid))
				{
					messages.add("{\"user\":\"0\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));
				}
				else
				{
					messages.add("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));

				}
				//System.out.println("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");
	     }
		Collections.reverse(messages);
		Collections.reverse(time);
		 request.setAttribute("to",to);
		 
		 request.setAttribute("msglist",messages);
		 request.setAttribute("timelist",time);
	     RequestDispatcher rd=request.getRequestDispatcher("loadtext.jsp");  
	     rd.forward(request, response); 
		} catch (Exception e) {
		
		e.printStackTrace();
	}
	}

}