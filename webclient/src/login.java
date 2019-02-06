

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
//        doPost(request, response);
//}
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String mobileno=request.getParameter("mobileno");
		String password=request.getParameter("password");
		String newpass=password.hashCode()+"";
		
		boolean check=database.loginCheck(mobileno,newpass);
		if(check==false)
		{
			response.sendRedirect("login.html");
		}
		response.sendRedirect("displayclients");

		response.getWriter().println("loginsuccessful");
		}catch(Exception e) {
			System.out.println(e);
			
		}

	}

}
