package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class removefromgroup
 */
@WebServlet("/removefromgroup")
public class removefromgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int userid=Integer.parseInt((String)request.getParameter("from"));
	int groupid=Integer.parseInt((String)request.getParameter("groupid"));
	try {
		database.removefromgroup(userid,groupid);
		response.sendRedirect("userlist");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
