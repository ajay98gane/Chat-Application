

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayclients
 */
@WebServlet("/displayclients")
public class displayclients extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		Connection con=database.getConnection();
		PreparedStatement checkLogin=con.prepareStatement("SELECT username FROM storage");
		ResultSet result=checkLogin.executeQuery();
		PrintWriter out=response.getWriter();
		out.println("<html><head><style>p{background:red;margin:auto;}div {\n" + 
				"    position:relative;left:300px;background:orange;width=50px;\n" + 
				"  }</style></head><body>");
		out.println("<p>availble clients  are</p><br>");
		out.println("<form name='clients'>");
		while(result.next())
		{	
			//out.print("<a href='displayinfo?user=");
			out.print("<input type='button' name='clientlist' value='");
			out.print(result.getString("username"));
			out.print("' onclick='sendinfo(this.value)'><br>\n");
			//out.print(result.getString("username"));
			//out.print("<br>");

		}out.print("</form><br><a href='index.html'> logout</a></form>"
				+ "\n<div id=\"users\">user will be displayed here</div>\n"
				+ "<script>function sendinfo(str) {\n" + 
				"  var xhttp;  \n" + 
				"  xhttp = new XMLHttpRequest();\n" + 
				"  xhttp.onreadystatechange = function() {\n" + 
				"    if (this.readyState == 4 && this.status == 200) {\n" + 
				"      document.getElementById(\"users\").innerHTML = this.responseText;\n" + 
				"    }\n" + 
				"  };\n" + 
				"  xhttp.open(\"GET\", \"sendtext.jsp?user=\"+str, true);\n" + 
				"  xhttp.send();\n" + 
				"}</script></body></html>");
		}catch(Exception e) {}
	}
}


