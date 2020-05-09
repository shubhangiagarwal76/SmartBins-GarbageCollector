package com.mySampleApplication.server;
import com.mySampleApplication.client.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.*;
import org.postgresql.util.PSQLException;
import java.sql.*;
import java.util.ArrayList;
import static org.postgresql.core.SqlCommandType.SELECT;
public class Postgreconnection extends RemoteServiceServlet implements DBConnection {
    private Connection con = null;
    public Postgreconnection() {
        try {
            System.out.println("TILL HERE");
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://ec2-3-229-210-93.compute-1.amazonaws.com:5432/de7glaogm4jfro", "grofwmeazmakhx", "57a5efa8c7da81bd5191609ca480cfab6c6a511f866a2bf8f28a29636fad16cc");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public ArrayList<Details> authenticateDetails(long did)
    {
        /*Admin_home a = new Admin_home();*/
        ArrayList<Details> det = new ArrayList<Details>();
        try {
            PreparedStatement driver = con.prepareStatement("SELECT \"Dustbin_ID\",L.\"Location_name\" , \"Status\" FROM \"Driver\" d, \"Dustbin\" D2 , \"Location\" L\n" +
                    "WHERE d.\"Driver_ID\" = D2.\"Driver_ID\" and\n" +
                    "        d.\"Driver_ID\" = L.\"Driver_ID\" and\n" +
                    "        d.\"Driver_ID\"=?;\n");
            driver.setLong(1,did);
            ResultSet rs = driver.executeQuery();
            System.out.println("Drivers Table is");
            while (rs.next()) {
                det.add(new Details( rs.getLong(1) , rs.getString(2),  rs.getInt(3) ));
            }
        } catch (SQLException f) {
            f.printStackTrace();
        }
        return det;
    }
    //MY COPY PASTED WORK WITH CONTACT PRANKUR
    public Contact authenticateContact(long did)
    {
        /*Admin_home a = new Admin_home();*/
        System.out.println(did);
        Contact cont = null;
        try {
            PreparedStatement driver = con.prepareStatement("SELECT S1.\"F_Name\", \"L_Name\", S1.\"Mobile_No\", \"Email_id\", \"DOB\"\n" +
                    "FROM \"Staff\" S1, \"Driver\" D\n" +
                    "where S1.\"Staff_ID\" = D.\"Staff_ID\" AND\n" +
                    "        \"Driver_ID\"=?;");
            driver.setLong(1,did);
            ResultSet rs = driver.executeQuery();
            System.out.println("Drivers Table is with");
            while (rs.next()) {
                cont=new Contact(rs.getString(1),  rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5) );
            }
        }catch(PSQLException pe){
            System.out.println(pe);
        }
        catch (SQLException f) {
            f.printStackTrace();
        }
        return cont;
    }

    public User authenticateUser(long name, String pass, int n) {
        User user = null;
        try {
            PreparedStatement passrs = con.prepareStatement("Select \"Driver\".\"Password\" from \"Driver\" where \"Driver_ID\"=?");
            passrs.setLong(1, name);
            ResultSet rs = passrs.executeQuery();
            System.out.println("User id passed " + name + "password passed " + pass);
            //System.out.println("number of rows "+rs.getRow());
            if (rs.next()) {
                //rs.next();
                if (rs.getString(1).equals(pass)) {
                    user = new User(name, pass, 1);
                    System.out.println("password " + rs.getString(1));
                } else {
                    user = new User(name, pass, 0);
                    System.out.println("password wrong " + rs.getString(1));
                }
            } else {
                user = new User(name, pass, -1);
                System.out.println("password null ");
            }
        } catch (PSQLException pe) {
            System.out.println(pe);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }
}