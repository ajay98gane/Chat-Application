package webclient;

import java.util.ArrayList;
import java.util.List;

public class ListHolder {
	List<String> messages=new ArrayList<String>();
	List<String> time=new ArrayList<String>();
	ListHolder(List<String> messages,List<String> time)
	{
		this.messages=messages;
		this.time=time;
	}
	public List<String> getMessages()
	{
		return messages;
	}
	public List<String> getTime()
	{
		return time;
	}

}
