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
//		HttpSession s=request.getSession(false);
//		if(s==null)
//		{
//			response.sendRedirect("pagenotfound.html");
//		}
		try {
			String temporary=database.getNotif(request.getParameter("uniqueid"));
			int count=0;
			if(temporary!=null)
			{
				count=Integer.parseInt(temporary);
			}
			System.out.println("from notif"+count);
		database.updateNotif(count,request.getParameter("uniqueid"));
		response.getWriter().print(++count);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
}

}
