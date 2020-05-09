<%--
  Created by IntelliJ IDEA.
  User: QUBIT
  Date: 12/28/2019
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
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
<%@ page import="javax.xml.ws.Response" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maps On Click</title>

    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

    </style>

</head>
<body>
<h1>GOOGLE MAP</h1>
<% String Pass = null;
    long l;
    long var= 0;
    String temp="Dustbin";
    List<Double> lat= new ArrayList<>();
    List<Double> log= new ArrayList<>();
    double lata[]=new double[100];
    double loga[]=new double[100];
    PrintWriter Out = response.getWriter();
    try{

        var  = Long.parseLong(request.getParameter("id"));
        //Out.println(var);
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "12345");
        PreparedStatement n = con.prepareStatement("SELECT \"Status\", \"F_Name\", \"Last_Name\",\"Latitude_Coordinates\",\"Longitude_Coordinates\",\"Driver\".\"Driver_ID\" FROM \"Driver\", \"Dustbin\" WHERE \"Driver\".\"Driver_ID\"= \"Dustbin\".\"Driver_ID\" AND \"Staff_ID\"=?");
        n.setLong(1, var);
        ResultSet rs = n.executeQuery();
        int i=0;
        while (rs.next())
        {
            /*Pass = rs.getString("Password");
            Out.println(rs.getString("F_Name"));
            Out.println(rs.getDouble("Status"));
            lat.add(rs.getDouble("Latitude_Coordinates"));
            log.add(rs.getDouble("Longitude_Coordinates"));
            lata[i]=rs.getDouble("Latitude_Coordinates");
            loga[i]=rs.getDouble("Longitude_Coordinates");
            i++;*/
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }%>

<div id="map"></div>
<script>var map;
function initMap() {
    var uluru1 = {lat: 28.619863, lng: 77.361824};
    var uluru2 = {lat: 28.619869, lng: 77.362557};

    var pic = '/resources/trash_can-512.png';
    map = new google.maps.Map(document.getElementById('map'),
        {
            center: {lat: 28.619863, lng: 77.361824},
            zoom: 18,
            mapTypeId: 'satellite'
        });
    var marker = new google.maps.Marker({position: uluru1, icon: pic, map: map, title:'Girls Hostel First Floor'});
    var marker = new google.maps.Marker({position: uluru2, icon: pic, map: map, title:'Boys Hostel First Floor'});

    map.addListener('center_changed',function () {

        window.setTimeout(function () {
            map.panTo(marker.getPosition());
        },3000);
    });
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA6NTzmJDphM56mmRW3Vr8EVJFVMFOkuZg&callback=initMap"
        async defer></script>
</body>
</html>
