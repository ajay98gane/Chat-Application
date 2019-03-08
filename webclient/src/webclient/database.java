package webclient;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class database {
	public static Connection getConnection() throws Exception {
		Class driver_class=Class.forName("com.mysql.jdbc.Driver");
	    Driver driver = (Driver) driver_class.newInstance();
        DriverManager.registerDriver(driver);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","root");
		return con;
		
	}
	
	public static void createInfoTable() throws Exception
	{
		Connection con=getConnection();
		try {
		PreparedStatement createmtable=con.prepareStatement("CREATE TABLE IF NOT EXISTS userdetails(user_id INT PRIMARY KEY ,username TEXT ,mobileno TEXT ,name TEXT,emailid TEXT,address TEXT)");
		createmtable.executeUpdate();
		PreparedStatement createetable=con.prepareStatement("CREATE TABLE IF NOT EXISTS password(username TEXT,pass TEXT)");
		createetable.executeUpdate();
		PreparedStatement createmsgtable=con.prepareStatement("CREATE TABLE IF NOT EXISTS messages(pair_id INT,msg TEXT,timeat TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
		createmsgtable.executeUpdate();
		PreparedStatement createmaptable=con.prepareStatement("CREATE TABLE IF NOT EXISTS msgmap(fromuser INT,touser INT,notif INT DEFAULT '0',pair_id INT PRIMARY KEY AUTO_INCREMENT)");
		createmaptable.executeUpdate();
		PreparedStatement createfriendrequest=con.prepareStatement("CREATE TABLE IF NOT EXISTS friendrequest(fromuser INT,touser INT)");
		createfriendrequest.executeUpdate();
		PreparedStatement createtableid=con.prepareStatement("CREATE TABLE IF NOT EXISTS group_id_holder(group_id INT PRIMARY KEY,groupname TEXT,timeat TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
		createtableid.executeUpdate();
		PreparedStatement createtabledetails=con.prepareStatement("CREATE TABLE IF NOT EXISTS group_details(group_id INT ,user_id INT,admin_access BOOLEAN)");
		createtabledetails.executeUpdate();
		
		con.close();
		}catch(SQLSyntaxErrorException e)
		{
			e.printStackTrace();
		}
	}
	public static String getUserId(String username) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement getUserId=con.prepareStatement("SELECT user_id from userdetails where username='"+username+"'");
		ResultSet getId=getUserId.executeQuery();
		String userId="";
		while(getId.next())
		{
			userId= getId.getString("user_id");
		}
		con.close();
		return userId;
		
	}
	public static void addValuesToGroupTable(int uniqueId,String groupName) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement addGroupIdTable=con.prepareStatement("INSERT INTO group_id_holder(group_id,groupname) VALUES("+uniqueId+",'"+groupName+"')");
		addGroupIdTable.executeUpdate();
		con.close();

	}
	public static void addValueInfoTable(int uniqueUserId,String username,String pass,String name,String mobileno,String emailid,String address) throws Exception
	{
		Connection con=getConnection();
		createInfoTable();
		PreparedStatement putinfotable=con.prepareStatement("INSERT INTO userdetails VALUES("+uniqueUserId+",'"+username+"','"+mobileno+"','"+name+"','"+emailid+"','"+address+"')");
		PreparedStatement putpasstable=con.prepareStatement("INSERT INTO password VALUES('"+username+"','"+pass+"')");
		putpasstable.executeUpdate();
		putinfotable.executeUpdate();
		con.close();
	}
	public static boolean loginCheck(String user,String pass) throws Exception
	{
		Connection con=getConnection();
		createInfoTable();
		boolean check=false;
		PreparedStatement checkLogin=con.prepareStatement("SELECT pass FROM password WHERE username='"+user+"'");
		ResultSet result=checkLogin.executeQuery();
		while(result.next())
		{

			if(pass.equals(result.getString("pass")))
			{

				check=true;
			}
		}con.close();
		return check;
	}
	public static void removeFriendRequest(int from,int to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement deletefriendrequest=con.prepareStatement("DELETE FROM friendrequest WHERE fromuser="+to+" AND touser="+from);
		deletefriendrequest.executeUpdate();
	}
	public static void addFriend(int from,int to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement addfriend=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES("+from+","+to+")");
		PreparedStatement addfriend2=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES("+to+","+from+")");
		addfriend.executeUpdate();
		addfriend2.executeUpdate();
		con.close();

	}
	public static Map<Integer,List<String>> getFriends(int username) throws Exception
	{
		//Map<Integer,String> notiflist=new LinkedHashMap<Integer,String>();
		Map<Integer,List<String>> friendlist=new LinkedHashMap<Integer,List<String>>();
		List<String> templist=new ArrayList<String>();
		

		Connection con = database.getConnection();
		
		PreparedStatement checkLogin=con.prepareStatement("SELECT msgmap.fromuser,msgmap.notif,userdetails.username FROM msgmap INNER JOIN userdetails on msgmap.fromuser=userdetails.user_id WHERE msgmap.touser= "+username);
		ResultSet result=checkLogin.executeQuery();
		while(result.next())
		{
			//notiflist.put(Integer.parseInt(result.getString("fromuser")),result.getString("notif"));
			templist.add(result.getString("username"));
			templist.add(result.getString("notif"));

			friendlist.put(Integer.parseInt(result.getString("fromuser")),templist);
		}
		con.close();
		return friendlist;

	}
	public static Map<Integer,String> getAllUsers(String users,int from) throws Exception
	{
		Connection con=getConnection();
		Map<Integer,String> availableusers=new HashMap<Integer,String>(); 
		con = database.getConnection();
		PreparedStatement showuser=con.prepareStatement("SELECT username,user_id FROM userdetails WHERE username LIKE'"+users+"%' AND username <> "+from);
		ResultSet r=showuser.executeQuery();
		while(r.next())
		{
			availableusers.put(Integer.parseInt(r.getString("user_id")),r.getString("username"));
		}	
		con.close();
		return availableusers;

	}
	public static Map<Integer,String> getFriendRequest(int from) throws Exception
	{
		Connection con = getConnection();
		Map<Integer,String> friendrequest=new HashMap<Integer,String>();
		PreparedStatement friendreqget=con.prepareStatement("SELECT friendrequest.fromuser,userdetails.username FROM friendrequest INNER JOIN userdetails ON friendrequest.fromuser=userdetails.user_id WHERE touser="+from);
		ResultSet friendreqresult=friendreqget.executeQuery();
		
		while(friendreqresult.next())
		{	 friendrequest.put(Integer.parseInt(friendreqresult.getString("fromuser")),friendreqresult.getString("username"));
			
		}
		con.close();
	return friendrequest;
	}
	public static String getNotif(String user) throws Exception
	{	
		Connection con=getConnection();
		PreparedStatement notifget=con.prepareStatement("SELECT notif FROM msgmap WHERE pair_id='"+user+"'");
		ResultSet notifresult=notifget.executeQuery();
		String temporary="0";
		while(notifresult.next())
		{	 temporary=notifresult.getString("notif");
			
		}
		con.close();
		return temporary;
	}
	public static void updateNotif(int count,String user) throws Exception
	{	
		Connection con=getConnection();
		PreparedStatement notif=con.prepareStatement("UPDATE msgmap SET notif="+(++count)+" WHERE pair_id='"+user+"'");
		notif.executeUpdate();
		con.close();
	}
	public static String getId(int from,int to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement getmap=con.prepareStatement("SELECT fromuser,touser,pair_id FROM msgmap WHERE fromuser="+from+" AND touser="+to);
	    ResultSet mapresult=getmap.executeQuery();
	    String num="";
	    while(mapresult.next())
	    {
	    	
	    	num=mapresult.getString("pair_id");

	    }
	    con.close();
	    return num;
	}
	public static void insertMessages(String pair_id,String message) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement storemessages=con.prepareStatement("INSERT INTO messages(pair_id,msg) VALUES('"+pair_id+"','"+message+"')");
	    storemessages.executeUpdate();
	    con.close();
	}
	public static void removeFriend(int from,int to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement sendarequest=con.prepareStatement("DELETE FROM msgmap WHERE (fromuser="+from+" OR fromuser="+to+")AND(touser="+from+" OR touser="+to+")");
    	sendarequest.executeUpdate();
    	con.close();
		
	}
	public static void sendFriendRequest(int from,int to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement sendrequest=con.prepareStatement("INSERT INTO friendrequest VALUES("+from+","+to+")");
    	sendrequest.executeUpdate();
    	con.close();
	}
	public static Map<String,String> getUserDetails(int user) throws Exception
	{
		Connection con=getConnection();
		Map<String,String> userdetails=new HashMap<String,String>();

		PreparedStatement notifget=con.prepareStatement("SELECT * FROM userdetails WHERE username="+user);
		ResultSet displayresult=notifget.executeQuery();
		
		while(displayresult.next())
		{	 
			userdetails.put("username",displayresult.getString("username"));
			userdetails.put("name",displayresult.getString("name"));
			userdetails.put("mobileno",displayresult.getString("mobileno"));
			userdetails.put("emailid",displayresult.getString("emailid"));
			userdetails.put("address",displayresult.getString("address"));
			
		}
		con.close();
		return userdetails;
	}
	public static String friendsCheck(int from,int user) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement friendcheck=con.prepareStatement("SELECT fromuser,touser FROM msgmap WHERE touser="+from+" AND fromuser="+user);
		ResultSet friendchecka=friendcheck.executeQuery();
		String bool="add friend";
		while(friendchecka.next())
		{
			bool="remove friend";

		}
		PreparedStatement friendrequestcheck=con.prepareStatement("SELECT fromuser FROM friendrequest WHERE fromuser="+from+" OR fromuser="+user);
		ResultSet friendrequest=friendrequestcheck.executeQuery();
		while(friendrequest.next())
		{	

			if((friendrequest.getString("fromuser")).equals(user+""))
			{
				bool="accept friend";
			
			}
			else
			{
				bool="cancel request";
				
			}
			
		}
		con.close();
		return bool;


	}
	public static ListHolder getMessagesAndTime(int fromid,int toid) throws Exception
	{	
		List<String> messages=new ArrayList<String>();
		List<String> time=new ArrayList<String>();
		Connection con=getConnection();
		PreparedStatement checkLogin=con.prepareStatement("SELECT msg,pair_id,cast(timeat as time) FROM messages WHERE pair_id="+fromid+" OR pair_id="+toid+" ORDER BY timeat DESC LIMIT 20");
		ResultSet result=checkLogin.executeQuery();
		 while(result.next())
	     {
				if(result.getString("pair_id").equals(fromid+""))
				{
					messages.add("{\"user\":\"0\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));
				}
				else
				{
					messages.add("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));

				}
	     }
		 con.close();
		 return new ListHolder(messages,time);

	}
	public static ListHolder getMessagesAndTime(int fromid,int toid,String no) throws Exception
	{	
		List<String> messages=new ArrayList<String>();
		List<String> time=new ArrayList<String>();
		Connection con=getConnection();
		PreparedStatement checkLogin=con.prepareStatement("SELECT msg,pair_id,cast(timeat as time) FROM messages WHERE pair_id="+fromid+" OR pair_id="+toid+" ORDER BY timeat DESC LIMIT 20 OFFSET "+no);
		ResultSet result=checkLogin.executeQuery();
		 while(result.next())
	     {
				if(result.getString("pair_id").equals(fromid))
				{
					messages.add("{\"user\":\"0\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));
				}
				else
				{
					messages.add("{\"user\":\"1\",\"msg\":\""+result.getString("msg")+"\"}");
					time.add(result.getString("cast(timeat as time)"));

				}
	     }
		 con.close();
		 return new ListHolder(messages,time);

	}
	
}
