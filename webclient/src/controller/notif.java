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

import webclient.database;


@WebServlet("/notif")
public class notif extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cona;
		try {
			cona = database.getConnection();
		
		PreparedStatement notifget=cona.prepareStatement("SELECT notif FROM msgmap WHERE id='"+request.getParameter("uniqueid")+"'");
		ResultSet notifresult=notifget.executeQuery();
		String temporary="0";
		int count=0;
		while(notifresult.next())
		{	 temporary=notifresult.getString("notif");
			
		}if(temporary!=null)
		{
			count=Integer.parseInt(temporary);
		}
		
		PreparedStatement notif=cona.prepareStatement("UPDATE msgmap SET notif='"+(++count)+"' WHERE id='"+request.getParameter("uniqueid")+"'");
		notif.executeUpdate();
		response.getWriter().print(count);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
}

}
