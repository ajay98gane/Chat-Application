package controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
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
		String confirmpassword=request.getParameter("confirmpassword");
		String name=request.getParameter("name");
		String mobileno=request.getParameter("mobileno");
		String emailid=request.getParameter("emailid");
		String address=request.getParameter("address");
		
		if(!password.equals(confirmpassword))
		{	
		
			response.sendRedirect("signup.html");
		}
		String newpass=password.hashCode()+"";
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String uniqueUserId="user_"+username+timeStamp;
		int uniqueid=uniqueUserId.hashCode();
		database.addValueInfoTable(uniqueid,username,newpass,name,mobileno,emailid,address);
 
		session.setAttribute("username",username);
		session.setAttribute("userid",uniqueid);

		response.sendRedirect("userlist");
		System.out.println("logidfsn");
		}
		catch(Exception e)
		{}
			
		



	}

}
