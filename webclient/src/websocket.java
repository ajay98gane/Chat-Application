
import java.io.IOException;
import java.sql.*;
import java.util.*;
import org.json.*; 
//import org.json.JSONValue;
//import org.json.simple.JSONValue; 

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/websocket/{cid}")
public class websocket {
	static Map<String,Session> clientDetails=new HashMap<>();
	String name="";
	@OnOpen
	public void start(Session session,@PathParam("cid")String name)
	{	this.name=name;
	//System.out.println(name);
		clientDetails.put(name,session);
	}
	@OnMessage
	public void message(String msg) throws IOException, JSONException,Exception
	{
	    JSONObject message = new JSONObject(msg);
	    Connection con=database.getConnection();
	    PreparedStatement getmap=con.prepareStatement("SELECT fromuser,touser,id FROM msgmap WHERE fromuser='"+(String)message.get("from")+"' AND touser='"+(String)message.get("to")+"'");
	    ResultSet mapresult=getmap.executeQuery();
	    String num="";
	    while(mapresult.next())
	    {
	    	
	    	num=mapresult.getString("id");

	    }
	    PreparedStatement storemessages=con.prepareStatement("INSERT INTO messages VALUES('"+num+"','"+(String)message.get("text")+"')");
	    storemessages.executeUpdate();
	    String send= "{\"from\":\""+(String)message.get("from")+"\",\"to\":\""+(String)message.get("to")+"\",\"text\":\""+(String)message.get("text")+"\",\"id\":\""+num+"\"}";
		if(clientDetails.containsKey((String)message.get("to")))
		{	
			Session s=clientDetails.get((String)message.get("to"));
			if(s.isOpen())
			{
				s.getBasicRemote().sendText(send);
			}else
			{
				PreparedStatement notifget=con.prepareStatement("SELECT notif FROM msgmap WHERE id='"+num+"'");
				ResultSet notifresult=notifget.executeQuery();
				String temporary="0";
				int count=0;
				while(notifresult.next())
				{	 temporary=notifresult.getString("notif");
					
				}if(temporary!=null)
				{
					count=Integer.parseInt(temporary);
				}
				
				PreparedStatement notif=con.prepareStatement("UPDATE msgmap SET notif='"+(++count)+"' WHERE id='"+num+"'");
				notif.executeUpdate();
			}
			
		}
		
		
	}
	@OnClose
	public void  close(Session session) throws IOException {
	session.close();	
	}
	
	

}
