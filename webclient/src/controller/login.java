package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
//        doPost(request, response);
//}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			//ServletContext context=getServletContext();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String newpass=password.hashCode()+"";
		boolean check=database.loginCheck(username,newpass);
		if(check==false)
		{
			response.sendRedirect("login.html");
		}
		int uniqueid=Integer.parseInt(database.getUserId(username));
		session.setAttribute("username",username);
		session.setAttribute("userid",uniqueid);

		response.sendRedirect("userlist");

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
		}

	}

}
