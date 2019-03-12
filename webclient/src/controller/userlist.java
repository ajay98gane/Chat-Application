package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;

@WebServlet("/userlist")
public class userlist extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, List<String>> friendlist = new HashMap<Integer, List<String>>();
		Map<Integer, List<String>> grouplist = new HashMap<Integer, List<String>>();
		HttpSession session = request.getSession(false);
//		if(session==null)
//		{
//			response.sendRedirect("pagenotfound.html");
//		}
		String username = (String) session.getAttribute("username");
		int userId = ((Integer) session.getAttribute("userid"));
		try {

			friendlist = (HashMap<Integer, List<String>>) database.getFriends(userId);
			grouplist = (HashMap<Integer, List<String>>) database.getGroup(userId);
			request.setAttribute("username", username);
			request.setAttribute("userid", userId);
			request.setAttribute("list", friendlist);
			request.setAttribute("grouplist", grouplist);
			RequestDispatcher rd = request.getRequestDispatcher("displayclients.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
