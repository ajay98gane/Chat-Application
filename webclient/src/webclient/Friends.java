package webclient;

public class Friends {
	String username;
	String notif;
	Friends(String username,String notif)
	{
		this.username=username;
		this.notif=notif;
	}
	public String getUsername()
	{
		return username;
	}
	public String getNotif()
	{
		return notif;
	}

}
