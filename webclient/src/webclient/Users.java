package webclient;

public class Users {
	int id;
	String name;
	Users(int id,String name)
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
