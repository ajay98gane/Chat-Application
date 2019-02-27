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
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		//response.sendRedirect("displayclients.jsp?id="+username);
//		request.setAttribute("username",username);
//        RequestDispatcher rd=request.getRequestDispatcher("userlist");  
//        rd.forward(request, response);
		System.out.println("login");
		session.setAttribute("username",username);
		response.sendRedirect("userlist");

		//response.getWriter().println("loginsuccessful");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
		}

	}

}
