package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webclient.database;

@WebServlet("/notif")
public class notif extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int from = Integer.parseInt(request.getParameter("from"));
			int to = Integer.parseInt(request.getParameter("to"));
			int no = Integer.parseInt(database.getId(from, to));
			String temporary = database.getNotif(no);
			int count = 0;
			if (temporary != null) {
				count = Integer.parseInt(temporary);
			}
			database.updateNotif(count, no);
			response.getWriter().print(++count);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
