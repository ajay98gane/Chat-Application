package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class displaydetails
 */
@WebServlet("/displaydetails")
public class displaydetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String user=request.getParameter("user");
				String from=request.getParameter("from");
				Connection cona;
				Map<String,String> userdetails=new HashMap<String,String>();
				try {
					cona = database.getConnection();
				
				PreparedStatement notifget=cona.prepareStatement("SELECT * FROM storage WHERE username='"+user+"'");
				ResultSet displayresult=notifget.executeQuery();
				String temporary="0";
				int count=0;
				while(displayresult.next())
				{	 
					userdetails.put("username",displayresult.getString("username"));
					userdetails.put("name",displayresult.getString("name"));
					userdetails.put("mobileno",displayresult.getString("mobileno"));
					userdetails.put("emailid",displayresult.getString("emailid"));
					userdetails.put("address",displayresult.getString("address"));
					
				}
				PreparedStatement friendcheck=cona.prepareStatement("SELECT fromuser,touser FROM msgmap WHERE touser='"+from+"' AND fromuser='"+user+"'");
				ResultSet friendchecka=friendcheck.executeQuery();
				String temp="a";
				while(friendchecka.next())
				{
					temp="b";
				}
				String bool="0";
				if(temp.equals("b"))
				{
					bool="1";
				}
				 request.setAttribute("bool",bool);
				 request.setAttribute("userdetails",userdetails);
			     RequestDispatcher rd=request.getRequestDispatcher("displaydetails.jsp");  
			     rd.forward(request, response); 
				
				} catch (Exception e) {
					
					e.printStackTrace();
				}

	}

}
