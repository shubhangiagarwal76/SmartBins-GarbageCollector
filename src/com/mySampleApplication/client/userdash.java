package com.mySampleApplication.client;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.mySampleApplication.server.Postgreconnection;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.maps.client.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class userdash implements EntryPoint {
    private DBConnectionAsync rpc;
    Button search;
    private TabPanel tp;
    CellTable<user> table;
    VerticalPanel verticalPanel, verticalPanel1,verticalPanelcontact;
    DecoratorPanel decoratorPanel, decoratorPanel1;
    Timer refresh;
    HorizontalPanel hhPanel;
    static int count=0;
    Button logout;
    Anchor maps;
    private static class user {
        private String Dustbin_ID;
        private String location;
        private String Status;

        public user(String Dustbin_ID, String location, String Status) {
            this.Dustbin_ID = Dustbin_ID;
            this.location = location;
            this.Status = Status;
            System.out.println(Dustbin_ID);
            System.out.println(location);
            System.out.println(Status);
        }

        public String getDustbin_ID() {
            return Dustbin_ID;
        }

        public String getLocation() {
            return location;
        }

        public String getStatus() {
            return Status;
        }
    }

    private static class userContact {
        private String Driver_ID;
        private String location;
        private String f_name;
        private String l_name;
        private String ContactNo;
        public userContact(String Driver_ID, String location, String f_name, String l_name, String ContactNo) {
            this.Driver_ID = Driver_ID;
            this.location = location;
            this.f_name = f_name;
            this.l_name = l_name;
            this.ContactNo = ContactNo;
            System.out.println(Driver_ID);
        }

        public String getDriver_ID() {
            return Driver_ID;
        }
        public String getLocation() {
            return location;
        }
        public String getF_name() {
            return f_name;
        }
        public String getL_name() {
            return l_name;
        }
        public String getContactNo() {
            return ContactNo;
        }
    }

    public userdash() {
        hhPanel=new HorizontalPanel();
        verticalPanel = new VerticalPanel();
        verticalPanel1 = new VerticalPanel();
        verticalPanelcontact=new VerticalPanel();
        search = new Button("Search");
        logout = new Button("Logout");
        maps = new Anchor("CLICK TO SEE MAPS","MAPS.html","_blank");
        search.setStyleName("gwt-searchbutton");
        logout.setStyleName("gwt-searchbutton");
        maps.setStyleName("hyper1");
        Label Home = new Label("Home");
        Home.setStyleName("labelhome_Stats_contact");
        //Label Map = new Label("Map");
        //maps.setHref("MAPS.html");
        //Map.addStyleName("labelhome_Stats_contact");
        Label Contact = new Label("Contact");
        Contact.setStyleName("labelhome_Stats_contact");
        decoratorPanel = new DecoratorPanel();
        decoratorPanel1 = new DecoratorPanel();
        decoratorPanel.setWidth("1200");
        decoratorPanel.setHeight("200");
        decoratorPanel.setStyleName("admin_table");
        verticalPanelcontact.setWidth("1200");
        verticalPanelcontact.setHeight("200");
        verticalPanelcontact.setStyleName("admin_table");
        tp = new TabPanel();
        tp.add(decoratorPanel, Home);
        //tp.add(new Label("Map"), Map);
        tp.add(verticalPanelcontact, Contact);
        // Show the 'Home' tab initially.
        tp.selectTab(0);
        tp.setWidth("1000");
        tp.setHeight("100");
        tp.setStyleName("tabStyle");
        RootPanel.get().setStyleName("root");

        /*Add it to the root panel.*/
         //List<user> CONTACTS = Arrays.asList(new user("101",
           //     "gh","50","pari","tiwari","ni krunga"),
             //   new user("102", " Lancer Lane","55","shubhi","agg",
               //         "nhi na"));
    }

    public void refreshrate()
    {
        AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
        rpc.authenticateDetails(MySampleApplication.getuname(), callback1);
    }

    public void onModuleLoad() {
        System.out.println("flow2");
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        System.out.println("flow4");
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        System.out.println("flow3");

        table = new CellTable<user>();
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        TextColumn<user> nameColumn = new TextColumn<user>() {
            @Override
            public String getValue(user user) {
                return user.Dustbin_ID;
            }
        };
        TextColumn<user> loc = new TextColumn<user>() {
            @Override
            public String getValue(user user) {
                return user.location;
            }
        };

        TextColumn<user> status = new TextColumn<user>() {
            @Override
            public String getValue(user user) {
                return user.Status;
            }
        };
        table.addColumn(nameColumn, "Dustbin ID");
        table.addColumn(loc, "Location");
        table.addColumn(status, "Status");

        AsyncCallback<Contact> callback = new AuthenticationHandlers<Contact>();
        rpc.authenticateContact(MySampleApplication.getuname(), callback);

        RootPanel.get().add(tp);
        verticalPanel.add(table);
        verticalPanel.add(search);
        hhPanel.add(maps);
        hhPanel.add(logout);
        verticalPanel.add(hhPanel);
        decoratorPanel.add(verticalPanel);
        decoratorPanel1.add(verticalPanel1);

        logout.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event)
            {
                Window.Location.reload();
            }
        });

        search.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Widget sender = (Widget) event.getSource();

                if (sender.equals(search)) {
                    count++;

                    AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
                    rpc.authenticateDetails(MySampleApplication.getuname(),callback1);
                    refresh = new Timer(){
                        public void run()
                        {
                            if(count>=2)
                            {
                                refresh.cancel();
                                count=0;
                                refreshrate();
                            }
                            else
                                refreshrate();
                        }
                    } ;
                    refresh.scheduleRepeating(5000);
                    System.out.println("Drivers Table is");
                }
            }
        });

//        AsyncCallback<ArrayList<Location>> callback2 = new locationList<ArrayList<Location>>();
//        rpc.locationList(callback2);
//        location = new ListBox();
//        verticalPanel.add(location);
    }
      private   class AuthenticationHandler<T> implements AsyncCallback<ArrayList<Details>> {
            public void onFailure(Throwable ex) {
                HTML h = new HTML("RPC unsuccessful");
                RootPanel.get().add(h);
            }
            public void onSuccess(ArrayList<Details> result) {
                List<user> ADMIN = new ArrayList<user>();
                for (Details details : result) {
                    String d = Long.toString(details.getDustbin_ID());
                    String l = details.getLocation();
                    String s = Integer.toString(details.getStatus()*Random.nextInt(5));
                    ADMIN.add(new user(d, l, s));
                }
                table.setRowData(ADMIN);
            }
        }
    private class AuthenticationHandlers<T> implements AsyncCallback<Contact> {
        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");
            RootPanel.get().add(h);
        }
        public void onSuccess(Contact contact) {
                HTML h1 = new HTML("Mobile No: "+Long.toString(contact.getMobileno()));
                String fc = contact.getF_name();
                String lnc = contact.getL_name();
                HTML h2=new HTML("Name:"+fc+" "+lnc);
                HTML dc1 = new HTML("D.O.B.:"+contact.getDOB());
                HTML h3 = new HTML("Email:"+contact.getEmailid());
                verticalPanelcontact.add(h1);
                verticalPanelcontact.add(h2);
                verticalPanelcontact.add(dc1);
                verticalPanelcontact.add(h3);
        }
    } }
