package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class friendrequesthandler
 */
@WebServlet("/friendrequesthandler")
public class friendrequesthandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		try {

			if(status.equalsIgnoreCase("add friend")) {
				database.sendFriendRequest(from, to);
			} else if (status.equalsIgnoreCase("remove friend")) {
				database.removeFriend(from, to);
			} else if (status.equalsIgnoreCase("accept friend")) {
				database.removeFriendRequest(from, to);

				database.addFriend(from, to);

			} else if (status.equalsIgnoreCase("cancel request")) {
				database.removeFriendRequest(from, to);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
