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
 * Servlet implementation class displayfriendrequest
 */
@WebServlet("/displayfriendrequest")
public class displayfriendrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection cona;
		try {
			cona = database.getConnection();
			List<String> friendrequest=new ArrayList<String>();
		
		PreparedStatement friendreqget=cona.prepareStatement("SELECT fromuser FROM friendrequest WHERE touser='"+request.getParameter("from")+"'");
		ResultSet friendreqresult=friendreqget.executeQuery();
		int count=0;
		while(friendreqresult.next())
		{	 friendrequest.add(friendreqresult.getString("fromuser"));
			
		}
		if(friendrequest!=null)
		{
		 request.setAttribute("friendrequest",friendrequest);}
		 request.setAttribute("from",request.getParameter("from"));
	     RequestDispatcher rd=request.getRequestDispatcher("displayfriendrequest.jsp");  
	     rd.forward(request, response); 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
