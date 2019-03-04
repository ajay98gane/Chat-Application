package webclient;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

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
		PreparedStatement createmtable=con.prepareStatement("CREATE TABLE IF NOT EXISTS storage(username TEXT ,mobileno TEXT ,name TEXT,emailid TEXT,address TEXT)");
		createmtable.executeUpdate();
		PreparedStatement createetable=con.prepareStatement("CREATE TABLE IF NOT EXISTS password(username TEXT,pass TEXT)");
		createetable.executeUpdate();
		PreparedStatement createmsgtable=con.prepareStatement("CREATE TABLE IF NOT EXISTS messages(id INT,msg TEXT,timeat TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
		createmsgtable.executeUpdate();
		PreparedStatement createmaptable=con.prepareStatement("CREATE TABLE IF NOT EXISTS msgmap(fromuser TEXT,touser TEXT,notif INT DEFAULT '0',id INT PRIMARY KEY AUTO_INCREMENT)");
		createmaptable.executeUpdate();
		PreparedStatement createfriendrequest=con.prepareStatement("CREATE TABLE IF NOT EXISTS friendrequest(fromuser TEXT,touser TEXT)");
		createfriendrequest.executeUpdate();
		}catch(SQLSyntaxErrorException e)
		{
			e.printStackTrace();
		}
	}
	public static void addValueInfoTable(String username,String pass,String name,String mobileno,String emailid,String address) throws Exception
	{
		Connection con=getConnection();
		createInfoTable();
		PreparedStatement putinfotable=con.prepareStatement("INSERT INTO storage VALUES('"+username+"','"+mobileno+"','"+name+"','"+emailid+"','"+address+"')");
		PreparedStatement putpasstable=con.prepareStatement("INSERT INTO password VALUES('"+username+"','"+pass+"')");
		putpasstable.executeUpdate();
		putinfotable.executeUpdate();
	}
	public static boolean loginCheck(String user,String pass) throws Exception
	{
		Connection con=getConnection();
		createInfoTable();
		boolean check=false;
		PreparedStatement checkLogin=con.prepareStatement("SELECT pass FROM password WHERE username='"+user+"'");
		ResultSet result=checkLogin.executeQuery();
		//System.out.println (pass);
		//System.out.println(user);
		while(result.next())
		{//System.out.println (pass);

			if(pass.equals(result.getString("pass")))
			{//System.out.println (pass);

				check=true;
			}
		}
		return check;
	}
	public static void removeFriendRequest(String from,String to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement deletefriendrequest=con.prepareStatement("DELETE FROM friendrequest WHERE fromuser='"+to+"' AND touser='"+from+"'");
		deletefriendrequest.executeUpdate();
	}
	public static void addFriend(String from,String to) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement addfriend=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES('"+from+"','"+to+"')");
		PreparedStatement addfriend2=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES('"+to+"','"+from+"')");
		addfriend.executeUpdate();
		addfriend2.executeUpdate();

	}


}
