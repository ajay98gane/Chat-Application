
import java.io.IOException;
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
	System.out.println(name);
		clientDetails.put(name,session);
	}
	@OnMessage
	public void message(String msg) throws IOException, JSONException 
	{
		//Object obj=JSONValue.parse(msg);  
	    JSONObject message = new JSONObject(msg);
	    System.out.println((String)message.get("to"));
	    System.out.println((String)message.get("text"));

		if(clientDetails.containsKey((String)message.get("to")))
		{
			Session s=clientDetails.get((String)message.get("to"));
			s.getBasicRemote().sendText((String)message.get("text"));
			
		}
		System.out.println("msg not send");
//		String msg[]=message.split("@",2);
//		msg[0]=msg[0].trim();
//		if(clientDetails.containsKey(msg[0]))
//		{
//		Session s=clientDetails.get(msg[0]);
//		String msgvalue[]=msg[1].split(" ",2);
//		msgvalue[1]=msgvalue[1].trim();
//		s.getBasicRemote().sendText(msgvalue[1]);
//		System.out.println(msgvalue[1]);
//
//		}
//		System.out.println("msg not send");
		
		
	}
	

}
