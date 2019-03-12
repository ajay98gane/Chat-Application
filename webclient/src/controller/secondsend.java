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
import javax.servlet.http.HttpSession;

import webclient.ListHolder;
import webclient.database;

/**
 * Servlet implementation class secondsend
 */
@WebServlet("/secondsend")
public class secondsend extends HttpServlet {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	  List<String> messages=new ArrayList<String>();
		List<String> time=new ArrayList<String>();
		int to=Integer.parseInt(request.getParameter("user"));
		int from=Integer.parseInt(request.getParameter("from"));
		String no=request.getParameter("no");
		try {



	

		String fromid=database.getId(from,to);
		String toid=database.getId(to,from);
		ListHolder lists=database.getMessagesAndTime(Integer.parseInt(fromid),Integer.parseInt(toid),no);
		messages=lists.getMessages();
		time=lists.getTime();

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
