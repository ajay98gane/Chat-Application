package controller;

import java.io.IOException;
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
 * Servlet implementation class createnewgroup
 */
@WebServlet("/createnewgroup")
public class createnewgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String groupName=request.getParameter("groupname");
		String from=request.getParameter("from");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String uniqueGroupId="user_"+from+"group_"+groupName+timeStamp;
		int uniqGroupId=uniqueGroupId.hashCode();
		try {
			database.addValuesToGroupTable(uniqGroupId,groupName);
			request.setAttribute("groupname",groupName);
			request.setAttribute("groupId",uniqueGroupId);
			request.setAttribute("creator",from);

	        RequestDispatcher rd=request.getRequestDispatcher("addUsersToGroup.jsp");  
	        rd.forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
