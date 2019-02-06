
public class tester {
	public static void main(String args[]) throws Exception
	{
		//database.addValueInfoTable("a","a","a","a","a","a");
		String g="a".hashCode()+"";
		boolean check=database.loginCheck("a",g);
		System.out.println(check);
	}

}
