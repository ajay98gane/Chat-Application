package controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;

/**
 * Servlet implementation class displaydetails
 */
@WebServlet("/displaydetails")
public class displaydetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession s=request.getSession(false);
//		if(s==null)
//		{
//			response.sendRedirect("pagenotfound.html");
//		}
		int user = Integer.parseInt(request.getParameter("user"));
		String username = request.getParameter("username");
		String fromname = request.getParameter("fromname");
		int from = Integer.parseInt(request.getParameter("from"));
		Map<String, String> userdetails = new HashMap<String, String>();
		Map<Integer, List<String>> friends = new HashMap<Integer, List<String>>();

		try {

			userdetails = (HashMap<String, String>) database.getUserDetails(user);

			friends = (HashMap<Integer, List<String>>) database.getFriends(user);
			System.out.println(from+""+user);

			String bool = database.friendsCheck(from, user);
			if (bool.equals("accept friend")) {
				request.setAttribute("bool", bool);
				request.setAttribute("toname", username);
				request.setAttribute("userdetails", userdetails);
				request.setAttribute("to", user);
				request.setAttribute("friends", friends);
				RequestDispatcher rd = request.getRequestDispatcher("displaydetails.jsp");
				rd.forward(request, response);
			} else if (bool.equals("cancel request")) {
				request.setAttribute("bool", bool);
				request.setAttribute("toname", username);
				request.setAttribute("userdetails", userdetails);
				request.setAttribute("to", user);
				request.setAttribute("friends", friends);
				RequestDispatcher rd = request.getRequestDispatcher("displaydetailscheck.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("bool", bool);
				request.setAttribute("toname", username);
				request.setAttribute("userdetails", userdetails);
				request.setAttribute("to", user);
				request.setAttribute("friends", friends);
				RequestDispatcher rd = request.getRequestDispatcher("displaydetailscheck.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
