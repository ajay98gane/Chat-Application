package controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import org.json.*; 
//import org.json.JSONValue;
//import org.json.simple.JSONValue; 

import javax.websocket.*;
import webclient.database;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/websocket/{cid}")
public class websocket {
	static Map<String,Session> clientDetails=new HashMap<>();
	String name="";
	@OnOpen
	public void start(Session session,@PathParam("cid")String name)
	{	this.name=name;
	System.out.println(name);
		clientDetails.put(name,session);
	}
	@OnMessage
	public void message(String msg) throws IOException, JSONException,Exception
	{
	    JSONObject message = new JSONObject(msg);
	    String from=(String)message.get("from");
	    String to=(String)message.get("to");
	    Connection con=database.getConnection();
	    System.out.println(((String)message.get("status")));
	    if(((String)message.get("msg")).equals("0"))
	    {
	    PreparedStatement getmap=con.prepareStatement("SELECT fromuser,touser,id FROM msgmap WHERE fromuser='"+from+"' AND touser='"+to+"'");
	    ResultSet mapresult=getmap.executeQuery();
	    String num="";
	    while(mapresult.next())
	    {
	    	
	    	num=mapresult.getString("id");

	    }
	    PreparedStatement storemessages=con.prepareStatement("INSERT INTO messages VALUES('"+num+"','"+(String)message.get("text")+"')");
	    storemessages.executeUpdate();
	    String send= "{\"from\":\""+from+"\",\"to\":\""+to+"\",\"text\":\""+(String)message.get("text")+"\",\"id\":\""+num+"\"}";
		if(clientDetails.containsKey(to))
		{	
			Session s=clientDetails.get(to);
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
	    }else if(((String)message.get("status")).equalsIgnoreCase("add friend"))
		    {		   
	    	String sen= "{\"from\":\""+from+"\",\"to\":\""+to+"\",\"text\":\""+(String)message.get("status")+"\",\"id\":\"@\"}";

		    	PreparedStatement sendrequest=con.prepareStatement("INSERT INTO friendrequest VALUES('"+from+"','"+to+"')");
		    	sendrequest.executeUpdate();
		    	if(clientDetails.containsKey(to))
				{	
					Session s=clientDetails.get(to);
					if(s.isOpen())
					{
						s.getBasicRemote().sendText(sen);
					}
		    	
				}
		    	}
	    else if(((String)message.get("status")).equalsIgnoreCase("remove friend"))
	    {
	    	PreparedStatement sendarequest=con.prepareStatement("DELETE FROM msgmap WHERE (fromuser='"+from+"' OR fromuser='"+to+"')AND(touser='"+from+"' OR touser='"+to+"')");
	    	sendarequest.executeUpdate();
	    }
	    else if(((String)message.get("status")).equalsIgnoreCase("accept friend"))
	    {
	    	try {
				database.removeFriendRequest(from,to);
				
				database.addFriend(from,to);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(((String)message.get("status")).equalsIgnoreCase("cancel request"))
	    {
	    	try {
				database.removeFriendRequest(from,to);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    }
		
		
	
	@OnClose
	public void  close(Session session) throws IOException {
	session.close();	
	}
	
	

}
