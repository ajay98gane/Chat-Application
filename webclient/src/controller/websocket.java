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
	static Map<String, Session> clientDetails = new HashMap<>();
	String name = "";

	@OnOpen
	public void start(Session session, @PathParam("cid") String name) {
		this.name = name;
		clientDetails.put(name, session);
	}

	@OnMessage
	public void message(String msg) throws IOException, JSONException, Exception {
		JSONObject message = new JSONObject(msg);
		int from = Integer.parseInt((String) message.get("from"));
		int to = Integer.parseInt((String) message.get("to"));
		Connection con = database.getConnection();
		if (((String) message.get("msg")).equals("0")) {

			String num = database.getId(from, to);
			database.insertMessages(num, (String) message.get("text"));

			String send = "{\"from\":\"" + from + "\",\"to\":\"" + to + "\",\"text\":\"" + (String) message.get("text")
					+ "\",\"id\":\"" + num + "\"}";
			System.out.println("to"+to);
			if (clientDetails.containsKey(to+"")) {
				Session s = clientDetails.get(to+"");
				if (s.isOpen()) {
					s.getBasicRemote().sendText(send);
				} else {
					String temporary = database.getNotif(num);
					int count = 0;
					if (temporary != null) {
						count = Integer.parseInt(temporary);
					}
					database.updateNotif(count, num);
				}

			}
		} else if (((String) message.get("status")).equalsIgnoreCase("add friend")) {
			String sen = "{\"from\":\"" + from + "\",\"to\":\"" + to + "\",\"text\":\"" + (String) message.get("status")
					+ "\",\"id\":\"@\"}";

			database.sendFriendRequest(from, to);
			if (clientDetails.containsKey(to)) {
				Session s = clientDetails.get(to);
				if (s.isOpen()) {
					s.getBasicRemote().sendText(sen);
				}

			}
		} else if (((String) message.get("status")).equalsIgnoreCase("remove friend")) {
			database.removeFriend(from, to);
		} else if (((String) message.get("status")).equalsIgnoreCase("accept friend")) {
			try {
				database.removeFriendRequest(from, to);

				database.addFriend(from, to);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (((String) message.get("status")).equalsIgnoreCase("cancel request")) {
			try {
				database.removeFriendRequest(from, to);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void close(Session session) throws IOException {
		session.close();
	}

}
