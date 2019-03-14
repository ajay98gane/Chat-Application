package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webclient.Message;
import webclient.database;

/**
 * Servlet implementation class sendbox
 */
@WebServlet("/sendbox")
public class sendbox extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Message> messages = new ArrayList<>();
		int to = Integer.parseInt(request.getParameter("user"));
		String toname = request.getParameter("username");
		int from = Integer.parseInt(request.getParameter("from"));
		String no = request.getParameter("no");

		try {

			String fromid = database.getId(from, to);
			int toid = Integer.parseInt(database.getId(to, from));
			database.updateNotif(-1, toid);

			if ((!fromid.equals(""))) {

				messages = database.getMessagesAndTime(Integer.parseInt(fromid), toid, no);
				
				Collections.reverse(messages);
				request.setAttribute("to", to);
				request.setAttribute("toname", toname);
				request.setAttribute("msglist",messages);
				RequestDispatcher rd;
				if (no == null) {
					rd = request.getRequestDispatcher("sendtext.jsp");
				} else {
					rd = request.getRequestDispatcher("loadtext.jsp");

				}
				rd.forward(request, response);
			} else {
				List<Message> groupmessage;
				groupmessage= database.getGroupMessagesAndTime(to, from, no);
				
				Collections.reverse(groupmessage);
				request.setAttribute("to", to);
				request.setAttribute("toname", toname);
				request.setAttribute("msglist", groupmessage);
				RequestDispatcher rd;
				if (no == null) {
					rd = request.getRequestDispatcher("groupsentext.jsp");
				} else {
					rd = request.getRequestDispatcher("grouploadtext.jsp");
				}
				rd.forward(request, response);
			}

		} catch (

		Exception e) {

			e.printStackTrace();
		}

	}

}
