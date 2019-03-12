package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class groupcreation
 */
@WebServlet("/groupcreation")
public class groupcreation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int groupid = Integer.parseInt(request.getParameter("groupid"));
		String[] groupusers = request.getParameterValues("groupusers");
		String access ;
		if (groupusers != null) {

			for (int i = 0; i < groupusers.length; i++) {
				boolean admin_access = true;
				access = request.getParameter(groupusers[i]);
				if (access.equals("user")) {
					admin_access = false;
				} else {
					admin_access = true;
				}
				try {
					database.addUsersToGroup(groupid, Integer.parseInt(groupusers[i]), true, admin_access);
					response.sendRedirect("userlist");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
