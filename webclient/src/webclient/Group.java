package webclient;

public class Group {
	int id;
	String name;
	Group(int id,String name)
	{
		this.id=id;
		this.name=name;
		
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
}
