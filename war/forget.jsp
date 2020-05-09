<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@page import="java.io.BufferedReader"%>
<%@page import ="java.io.IOException" %>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLConnection"%>
<%@page import=" java.net.URLEncoder"%>
<%@ page import="sun.security.util.Password" %>
<%@ page import="org.omg.CORBA.Request" %>
<%@ page import="javax.xml.ws.Response" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 17-Nov-19
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>


</head>
<body>


<% String Pass = null;
long var= 0;
    PrintWriter Out = response.getWriter();
    try{

    var  = Long.parseLong(request.getParameter("number"));


    Out.println(var);

    Connection con = null;
        Class.forName("org.postgresql.Driver");
       con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "12345");
        PreparedStatement n = con.prepareStatement("SELECT \"Staff\".\"Password\" FROM \"Staff\" WHERE \"Mobile_No\"=?");
        n.setLong(1, var);
        ResultSet rs = n.executeQuery();
        while (rs.next())
        {
             Pass = rs.getString("Password");


        }
    } catch (SQLException ex) {
        System.out.println("Enter correct Number");
    }
   /* OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://gurubrahma-smsly-sms-to-india-v1.p.rapidapi.com/sms/transactional/9717061463/hello")
            .get()
            .addHeader("x-rapidapi-host", "gurubrahma-smsly-sms-to-india-v1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "1f5491a913msh456e6e3668c4fb8p1e9912jsn9302562091f8")
            .addHeader("accept", "text/plain")
            .build();

    Response response = client.newCall(request).execute();*/
    String authkey = "18sUVSjxhSo-ok70P2kfLE9FIkGSax0ISKq1KBR2zr";
    String senderId = "TXTLCL";
    String route="4";
    URLConnection myURLConnection=null;
    URL myURL=null;
    BufferedReader reader=null;
    //String encoded_message=URLEncoder.encode(Pass);
    String mainUrl="https://api.textlocal.in/send/?";
    StringBuilder sbPostData= new StringBuilder(mainUrl);
    sbPostData.append("apikey="+authkey);
    sbPostData.append("&numbers="+var);
    sbPostData.append("&message="+Pass);
    //sbPostData.append("&route="+route);
    sbPostData.append("&sender="+senderId);

    mainUrl = sbPostData.toString();
    try
    {
//prepare connection
        myURL = new URL(mainUrl);
        myURLConnection = myURL.openConnection();
        myURLConnection.connect();
        reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        String success="Your password sent successfully on "+var;
        Out.println(success);
        Out.println("NOTE: PLEASE MAKE SURE YOUR NUMBER IS NOT ON DND, OTHERWISE MESSAGE WILL NOT BE RECEIVED");

//finally close connection
        reader.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }

%>

</body>

</html>