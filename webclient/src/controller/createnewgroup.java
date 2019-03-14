package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webclient.Friends;
import webclient.*;

/**
 * Servlet implementation class createnewgroup
 */
@WebServlet("/createnewgroup")
public class createnewgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String test=request.getParameter("test");
		String groupName = request.getParameter("groupname");

		int from = Integer.parseInt(request.getParameter("from"));
		String to = request.getParameter("groupid");
		String fromname = request.getParameter("fromname");
		try {
			List<Pair<Users,String>> friendlist;
			int uniqGroupId;
			if (groupName != "") {
				if (!to.equals("undefined")) {
					uniqGroupId = Integer.parseInt(to);
					friendlist = database.friendsNotInGroup(from, Integer.parseInt(to));
				} else {
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					friendlist = database.getFriends(from);

					String uniqueGroupId = "user_" + from + "group_" + groupName + timeStamp;
					uniqGroupId = uniqueGroupId.hashCode();
					boolean admin_access = true;

					database.addValuesToGroupTable(uniqGroupId, groupName);
					database.addUsersToGroup(uniqGroupId, from, true, admin_access);
				}
				request.setAttribute("groupname", groupName);
				request.setAttribute("groupId", uniqGroupId);
				request.setAttribute("fromid", from);
				request.setAttribute("fromname", fromname);
				request.setAttribute("friendlist", friendlist);

				RequestDispatcher rd = request.getRequestDispatcher("addUsersToGroup.jsp");
				rd.forward(request, response);
			}else
			{
				PrintWriter out = response.getWriter();
				out.print("<span style='color:red; position: absolute; left: 40%; top: 10%; ' >groupname cannot be empty! </span>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
