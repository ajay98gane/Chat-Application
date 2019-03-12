package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;


@WebServlet("/notif")
public class notif extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int from=Integer.parseInt(request.getParameter("from"));
			int to=Integer.parseInt(request.getParameter("to"));
			int no=Integer.parseInt(database.getId(from,to));
			String temporary=database.getNotif(no);
			int count=0;
			if(temporary!=null)
			{
				count=Integer.parseInt(temporary);
			}
		database.updateNotif(count,no);
		response.getWriter().print(++count);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
}

}
