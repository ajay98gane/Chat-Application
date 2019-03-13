package webclient;

import java.util.ArrayList;
import java.util.List;

public class Listcontainer {
	 List<String> list=new ArrayList<String>();
	Listcontainer(String name,String notif)
	{
		list.add(name);
		list.add(notif);
	}
	public  List<String> getList()
	{
		return list;
	}
	
}