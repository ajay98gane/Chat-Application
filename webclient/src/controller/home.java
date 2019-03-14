package controller;

import java.io.IOException;
import java.util.ArrayList;
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
import webclient.*;
@WebServlet("/home")
public class home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Pair<Users,String>> friendlist;
		ArrayList<Pair<Group,String>> grouplist;
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		int userId = ((Integer) session.getAttribute("userid"));
		try {

			friendlist = (ArrayList<Pair<Users,String>>) database.getFriends(userId);
			grouplist = (ArrayList<Pair<Group,String>>) database.getGroup(userId);
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
