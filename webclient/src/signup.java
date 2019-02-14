

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
//        doPost(request, response);
//}
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
		database.addValueInfoTable(username,newpass,name,mobileno,emailid,address);
		//response.getWriter().println(username);
		//response.sendRedirect("displayclients.jsp?id="+username);
		request.setAttribute("username",username);
        RequestDispatcher rd=request.getRequestDispatcher("userlist");  
        rd.forward(request, response); 
		}
		catch(Exception e)
		{}
			
		



	}

}
