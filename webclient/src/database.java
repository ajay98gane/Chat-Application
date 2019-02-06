

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		
		PreparedStatement createmtable=con.prepareStatement("CREATE TABLE IF NOT EXISTS storage(username TEXT ,mobileno TEXT ,name TEXT,emailid TEXT,address TEXT)");
		createmtable.executeUpdate();
		PreparedStatement createetable=con.prepareStatement("CREATE TABLE IF NOT EXISTS password(mobileno TEXT,pass TEXT)");
		createetable.executeUpdate();
		//System.out.println("table created");
	}
	public static void addValueInfoTable(String username,String pass,String name,String mobileno,String emailid,String address) throws Exception
	{
		Connection con=getConnection();
		createInfoTable();
		PreparedStatement putinfotable=con.prepareStatement("INSERT INTO storage VALUES('"+username+"','"+mobileno+"','"+name+"','"+emailid+"','"+address+"')");
		PreparedStatement putpasstable=con.prepareStatement("INSERT INTO password VALUES('"+mobileno+"','"+pass+"')");
		putpasstable.executeUpdate();
		putinfotable.executeUpdate();
		//System.out.println("value added");
	}
	public static boolean loginCheck(String user,String pass) throws Exception
	{
		Connection con=getConnection();
		//createInfoTable();
		boolean check=false;
		PreparedStatement checkLogin=con.prepareStatement("SELECT pass FROM password WHERE mobileno='"+user+"'");
		ResultSet result=checkLogin.executeQuery();
		
		while(result.next())
		{
			if(pass.equals(result.getString("pass")))
			{
				check=true;
			}
		}
		//System.out.println("value checked");
		return check;
	}
//	public static void displayMsg(String from,String to) throws Exception
//	{
//		Connection con=getConnection();
//		PreparedStatement dispMsg=con.prepareStatement("SELECT fromuser,touser,messages FROM msgs WHERE (fromuser='@"+from+"' OR fromuser= '@"+to+"') AND (touser='@"+from+"' OR touser='@"+to+"')");
//		ResultSet result=dispMsg.executeQuery();
//		while(result.next())
//		{
//			usere.get(to).out.print(result.getString("fromuser"));
//			usere.get(to).out.print(result.getString("touser"));
//			usere.get(to).out.print("-> ");
//			usere.get(to).out.println(result.getString("messages"));
//		}
//		System.out.println("result printed");
//	}

}
