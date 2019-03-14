package controller;

import java.io.IOException;
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
 * Servlet implementation class showalluser
 */
@WebServlet("/showalluser")
public class showalluser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			Map<Integer,String> availableusers=new HashMap<Integer,String>(); 
			int from=Integer.parseInt(request.getParameter("from"));
			availableusers=(Map<Integer,String>)database.getAllUsers(request.getParameter("value"),from);
		 request.setAttribute("listofusers",availableusers);
		 request.setAttribute("from",from);
	     RequestDispatcher rd=request.getRequestDispatcher("showallusers.jsp");  
	     rd.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
