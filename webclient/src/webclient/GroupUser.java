package webclient;

public class GroupUser {

	String adminStatus;
	Users users;
	GroupUser(String adminStatus,Users users)
	{
		this.adminStatus=adminStatus;
		this.users=users;
	}
	public String getAdminStatus()
	{
		return adminStatus;
	}
	public Users getUser()
	{
		return users;
	}
}
