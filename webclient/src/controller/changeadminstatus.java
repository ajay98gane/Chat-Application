
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class changeadminstatus
 */
@WebServlet("/changeadminstatus")
public class changeadminstatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fromid=Integer.parseInt(request.getParameter("fromid"));
		int groupid=Integer.parseInt(request.getParameter("groupid"));
		String status=request.getParameter("adminaccess");
		boolean bool;
		if(status.equals("admin"))
		{
			bool=false;
			
		}
		else
		{
			bool=true;
		}
		try {
			database.changeAdminAccess(fromid,groupid,bool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
