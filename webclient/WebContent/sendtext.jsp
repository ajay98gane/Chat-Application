<%@ page import="java.sql.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@page import="webclient.*" %>

<%
String to=request.getParameter("user");
String from=request.getParameter("from");
Connection con=database.getConnection();

PreparedStatement testmap=con.prepareStatement("SELECT fromuser,touser FROM msgmap WHERE fromuser='"+from+"'AND touser='"+to+"'");
ResultSet checkresult=testmap.executeQuery();
String check="";
while(checkresult.next())
{
	check=checkresult.getString("fromuser");
}check=check.trim();
System.out.println("a"+check+from);
if(!check.equals(from))
{
PreparedStatement insertmap=con.prepareStatement("INSERT INTO msgmap(fromuser,touser) VALUES('"+from+"','"+to+"')");
insertmap.executeUpdate();
}//System.out.println("check");
PreparedStatement clearnotif=con.prepareStatement("UPDATE msgmap SET notif='0' WHERE fromuser='"+to+"'AND touser='"+from+"' ");
clearnotif.executeUpdate();
PreparedStatement getmap=con.prepareStatement("SELECT fromuser,touser,id FROM msgmap WHERE (fromuser='"+from+"' OR fromuser= '"+to+"') AND (touser='"+from+"' OR touser='"+to+"')");
ResultSet mapresult=getmap.executeQuery();
String num="";
String tempFrom="";
String conf;
while(mapresult.next())
{
	//System.out.println("check2");
	 tempFrom=mapresult.getString("fromuser");
	num+=mapresult.getString("id")+" ";

}
//System.out.println(num);

String[] id=num.split(" ",2);
id[0]=id[0].trim();
id[1]=id[1].trim();
//System.out.println("check4");

if(tempFrom.equals(from))
{
	conf=id[1];
}else{
	conf=id[0];
}
//System.out.println("check5");

PreparedStatement checkLogin=con.prepareStatement("SELECT msg,id FROM messages WHERE id='"+id[0]+"' OR id='"+id[1]+"'");
ResultSet result=checkLogin.executeQuery();
 
%>
<%=to%>

<div>

	
            <textarea id="displaymsg"rows="10" cols="100" style="
    width: 800px;
    height: 400px;font-size:20px;background:#ccf5ff;color: #0436af;position: relative;
    
    

"><% 
            while(result.next())
            {
            	//System.out.println("check6");
				if(result.getString("id").equals(conf))
           		{%>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<%=
           		 result.getString("msg")
            %><%
          		}
				else
				{%><%=
           		 	result.getString("msg")%><%
          		}%>&#10;<% 
            }%></textarea>
            <form action="">
                <input id="textID"   type="text" style="
    width: 800px;
    height: 25px;background:#ccf5ff;
">
                <input onclick="wsSendMessage(textID.value)" value="Send" type="button" style="
    position: absolute;
    left: 755px;
    height: 30px;
    width: 51px;
    background-color: #97cffb;    box-shadow: 0px 0px 0 1px #147de8;
    
    right: 620px;
">
                
            </form>
            </div>



