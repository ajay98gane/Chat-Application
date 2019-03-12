package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webclient.database;

/**
 * Servlet implementation class acceptfriend
 */
@WebServlet("/acceptfriend")
public class acceptfriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int from=Integer.parseInt(request.getParameter("from"));
		int to=Integer.parseInt(request.getParameter("to"));
		String num=request.getParameter("num");
		
		try {
			database.removeFriendRequest(from,to);
			if(num.equals("1"))
			{
				database.addFriend(from,to);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
