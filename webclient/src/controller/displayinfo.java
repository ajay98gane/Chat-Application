package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webclient.database;


@WebServlet("/displayinfo")
public class displayinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String user=request.getParameter("user");
			
		
			Connection con=database.getConnection();
			PreparedStatement checkLogin=con.prepareStatement("SELECT * FROM storage WHERE username='"+user+"'");
			ResultSet resulta=checkLogin.executeQuery();
			PrintWriter out=response.getWriter();
			//out.println("kjhgf");
			out.print("<html><body>details about "+user+" are as follows");
			while(resulta.next())
			{	
				out.println("<br>username -"+resulta.getString("username"));
				out.println("<br>mobileno -"+resulta.getString("mobileno"));
				out.println("<br>name -"+resulta.getString("name"));
				out.println("<br>emailid -"+resulta.getString("emailid"));
				out.println("<br>address -"+resulta.getString("address"));
				out.println("<br></body></html>");
			}
			
		}catch(Exception e)
		{}
		}

	}


