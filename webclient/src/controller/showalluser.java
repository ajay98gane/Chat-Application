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
 * Servlet implementation class showalluser
 */
@WebServlet("/showalluser")
public class showalluser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cona;
		List<String> availableusers=new ArrayList<String>(); 
		try {
			cona = database.getConnection();
		
		PreparedStatement showuser=cona.prepareStatement("SELECT username FROM storage WHERE username LIKE'"+request.getParameter("value")+"%' AND username <> '"+request.getParameter("from")+"'");
		ResultSet r=showuser.executeQuery();
		while(r.next())
		{
			System.out.println(r.getString("username"));
			availableusers.add(r.getString("username"));
		}			System.out.println("hello check 2");

		 request.setAttribute("listofusers",availableusers);
		 request.setAttribute("from",request.getParameter("from"));
	     RequestDispatcher rd=request.getRequestDispatcher("showallusers.jsp");  
	     rd.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
