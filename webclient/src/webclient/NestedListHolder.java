package webclient;

import java.util.ArrayList;
import java.util.List;

public class NestedListHolder {
	List<List<String>> messages=new ArrayList<>();
	List<String> time=new ArrayList<>();
	NestedListHolder(List<List<String>> messages,List<String> time)
	{
		this.messages=messages;
		this.time=time;
	}
	public List<List<String>> getMessages()
	{
		return messages;
		
	}
	public List<String> getTime()
	{
		return time;
	}

}
