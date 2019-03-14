package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirmpassword = request.getParameter("confirmpassword");
			String name = request.getParameter("name");
			String mobileno = request.getParameter("mobileno");
			String emailid = request.getParameter("emailid");
			String address = request.getParameter("address");
			PrintWriter out = response.getWriter();
			boolean regexchecker = noChecker(mobileno);
			boolean usernameChecker = database.usernameChecker(username);
			response.setContentType("text/html");
			if (!password.equals(confirmpassword)) {
				out.print(
						"<span style='color:red; position: absolute; left: 40%; top: 10%;' >password and confirm password does not match!</span>");
				RequestDispatcher rd = request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			} else if (regexchecker == false) {
				out.print(
						"<span style='color:red;  position: absolute; left: 40%; top: 10%;' >enter a valid mobile no!</span>");
				RequestDispatcher rd = request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			} else if (usernameChecker == true) {
				out.print(
						"<span style='color:red; position: absolute; left: 40%; top: 10%;'>username already exists enter a new username!</span>");
				RequestDispatcher rd = request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			} else {
				String newpass = password.hashCode() + "";
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

				String uniqueUserId = "user_" + username + timeStamp;
				int uniqueid = uniqueUserId.hashCode();
				database.addValueInfoTable(uniqueid, username, newpass, name, mobileno, emailid, address);

				session.setAttribute("username", username);
				session.setAttribute("userid", uniqueid);

				response.sendRedirect("home");
			}
		} catch (Exception e) {
		}

	}

	private static boolean noChecker(String s) {
		return s.matches("[789](\\d){9}");
	}

}
