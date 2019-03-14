package webclient;

public class Message {
	String from;
	String text;
	String to;
	String time;
	String user;
	Message(String from,String text,String time,String user)
	{
		this.from=from;
		this.text=text;
		this.time=time;
		this.user=user;
	}
	Message(String user,String text,String time)
	{
		this.time=time;
		this.user=user;
		this.text=text;
	}
	public String getFrom()
	{
		return from;
	}
	public String getTo()
	{
		return to;
	}
	public String getText()
	{
		return text;
	}
	public String getTime()
	{
		return time;
	}
	public String getUser()
	{
		return user;
	}
}
