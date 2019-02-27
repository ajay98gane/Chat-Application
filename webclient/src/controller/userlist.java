package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webclient.database;

@WebServlet("/userlist")
public class userlist extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> friendlist=new HashMap<String,String>();
		HttpSession session = request.getSession(false);
		//ServletContext context=getServletContext();
		String username=(String)session.getAttribute("username");
		Connection con;
		try {
			con = database.getConnection();
		
		PreparedStatement checkLogin=con.prepareStatement("SELECT fromuser,notif FROM msgmap WHERE touser= '"+username+"'");
		ResultSet result=checkLogin.executeQuery();
		while(result.next())
		{
			friendlist.put(result.getString("fromuser"),result.getString("notif"));
			//System.out.println(result.getString("fromuser")+result.getString("notif"));
		}		System.out.println("logidfsn");

		request.setAttribute("username",username);
		request.setAttribute("list",friendlist);
        RequestDispatcher rd=request.getRequestDispatcher("displayclients.jsp");  
        rd.forward(request, response); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
