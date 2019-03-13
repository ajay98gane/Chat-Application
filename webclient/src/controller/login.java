package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			response.setContentType("text/html");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String newpass = password.hashCode() + "";
			PrintWriter out = response.getWriter();
			boolean check = database.loginCheck(username, newpass);
			if (check == false) {
				out.print("<span style='color:red; position: absolute; left: 40%; top: 10%; ' >Sorry UserName or Password Error!</span>");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);

			} else {
				int uniqueid = Integer.parseInt(database.getUserId(username));
				session.setAttribute("username", username);
				session.setAttribute("userid", uniqueid);

				response.sendRedirect("userlist");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}

	}

}
