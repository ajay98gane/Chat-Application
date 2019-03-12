package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class createnewgroup
 */
@WebServlet("/createnewgroup")
public class createnewgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String test=request.getParameter("test");
		String groupName=request.getParameter("groupname");
		int from=Integer.parseInt(request.getParameter("from"));
		String to=(request.getParameter("groupid"));
		String fromname=request.getParameter("fromname");
		try {
			Map<Integer,List<String>> friendlist;
			int uniqGroupId;
		if(to!=null)
		{		
			uniqGroupId=Integer.parseInt(to);
			friendlist=database.friendsNotInGroup(from,Integer.parseInt(to));
		}
		else
		{
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 friendlist=database.getFriends(from);
		
		String uniqueGroupId="user_"+from+"group_"+groupName+timeStamp;
		 uniqGroupId=uniqueGroupId.hashCode();
		boolean admin_access=true;
	
		
			database.addValuesToGroupTable(uniqGroupId,groupName);
			database.addUsersToGroup(uniqGroupId,from,true,admin_access);
		}
			request.setAttribute("groupname",groupName);
			request.setAttribute("groupId",uniqGroupId);
			request.setAttribute("fromid",from);
			request.setAttribute("fromname",fromname);
			request.setAttribute("friendlist",friendlist);

	        RequestDispatcher rd=request.getRequestDispatcher("addUsersToGroup.jsp");  
	        rd.forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
