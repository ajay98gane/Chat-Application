package controller;

import java.io.IOException;
import java.util.*;
import org.json.*;
import javax.websocket.*;

import webclient.*;
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
		if (((String) message.get("msg")).equals("0")) {

			String num = database.getId(from, to);
			if (num.equals("")) {
				num = database.getId(to, from);
			}

			database.insertMessages(Integer.parseInt(num), (String) message.get("text"));

			String send = "{\"from\":\"" + from + "\",\"to\":\"" + to + "\",\"text\":\"" + (String) message.get("text")
					+ "\",\"id\":\"" + num + "\",\"fromname\":\"" + (String) message.get("fromname") + "\"";
			boolean groupCheck = database.checkGroup(to);
			if (groupCheck) {
				List<GroupUser> groupusers = database.getGroupUserDetails(to, from);
				send = send + ",\"group\":\"true\"}";
				for (GroupUser a : groupusers) {
					if ((clientDetails.containsKey(a.getUser().getId() + ""))&&(a.getUser().getId()!=from)) {
						Session s = clientDetails.get(a.getUser().getId() + "");
						if (s.isOpen()) {
							s.getBasicRemote().sendText(send);
						} else {
							int no = Integer.parseInt(database.getId(to, (a.getUser().getId())));
							String temporary = database.getNotif(no);
							int count = 0;
							if (temporary != null) {
								count = Integer.parseInt(temporary);
							}
							database.updateNotif(count, no);
						}
					} else {
						int no = Integer.parseInt(database.getId(to, (a.getUser().getId())));
						String temporary = database.getNotif(no);
						int count = 0;
						if (temporary != null) {
							count = Integer.parseInt(temporary);
						}
						database.updateNotif(count, no);
					}

				}
			}
			if (clientDetails.containsKey(to + "")) {
				Session s = clientDetails.get(to + "");
				if (s.isOpen()) {
					send = send + ",\"group\":\"false\"}";

					s.getBasicRemote().sendText(send);
				} else {
					String temporary = database.getNotif(Integer.parseInt(num));
					int count = 0;
					if (temporary != null) {
						count = Integer.parseInt(temporary);
					}
					database.updateNotif(count, Integer.parseInt(num));
				}

			}
		}
	}

	@OnClose
	public void close(Session session) throws IOException {
		session.close();
	}

}
