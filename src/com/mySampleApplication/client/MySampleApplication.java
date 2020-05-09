package com.mySampleApplication.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class  MySampleApplication implements EntryPoint, ClickHandler{
    VerticalPanel vpanel_login;
    HorizontalPanel hpanel;
    Button login;
    static TextBox uname;
    private PasswordTextBox pwd;
    Image img;
    Anchor forget;
    private DBConnectionAsync rpc;
    FlowPanel flow;

    public MySampleApplication() {
        this.uname = new TextBox();
        this.forget = new Anchor();
        this.pwd = new PasswordTextBox();
        this.vpanel_login = new VerticalPanel();
        this.hpanel = new HorizontalPanel();
        this.login = new Button("LOGIN");
        login.addClickHandler(this::onClick);
        this.img = new Image();
        this.flow=new FlowPanel();

    }

    //Event handled at login
    public void onClick(ClickEvent event) {

        //client side verification
        if(event.getSource()!=null)
        {
            String u=uname.getText().toUpperCase().trim();
            if (!u.matches("^[0-9\\.]{1,10}$")) {
                Window.alert("'" + u + "' is not a valid symbol.");
                uname.selectAll();
                uname.setText("");
                pwd.selectAll();
                pwd.setText("");
            }
            //server side verification
            else
            {
                connection();
            }}
    }

    //getter for textbox

    public void connection() {
        //int n;
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        long uname=Long.parseLong(this.uname.getText());
        String pass=this.pwd.getText();
        AsyncCallback<User> callback3 = new AuthenticationHandlers<User>();
        rpc.authenticateUser(uname,pass,-1,callback3);

    }
    public static long getuname(){
        return Long.parseLong(uname.getText());}

    private class AuthenticationHandlers<T> implements AsyncCallback<User> {

        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");
            RootPanel.get().add(h);
        }

        public void onSuccess(User result)
        {
            if(result.getValue()==1) {
                RootPanel.get().clear();
                vpanel_login.clear();
                userdash ad = new userdash();
                ad.onModuleLoad();
            }
            else if(result.getValue()==0)
            {
                Window.alert("Invalid Password");
                System.out.println("VALUE OF N"+result.getValue());
            }
            else if (result.getValue()==-1)
            {
                Window.alert("Invalid Username");
                System.out.println("VALUE OF N"+result.getValue());
            }

           /* while (count!=1) {
                connection();
                count++;*/


        }
    }
    public void addingpanel(){
        vpanel_login.setWidth("50");
        vpanel_login.setHeight("50");
        hpanel.setWidth("50");
        //login.getElement().getStyle().setBackgroundImage("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");
        vpanel_login.add(uname);
        uname.setName("UserName");
        vpanel_login.add(pwd);
        pwd.setName("Password");
        hpanel.add(login);
        hpanel.add(forget);
        // vpanel_login.add(hpanel);
        hpanel.setSpacing(10);
        vpanel_login.setSpacing(10);
        uname.setStyleName("textbox");
        pwd.setStyleName("textbox");
        forget.setHref("LOGIN.html");
        forget.setText("Forget Password?");
        forget.setSize("5","2");
        forget.setStyleName("hyper");
        uname.getElement().setPropertyString("placeholder", "USERID");
        pwd.getElement().setPropertyString("placeholder", "PASSWORD");
        flow.add(vpanel_login);
        flow.add(hpanel);
        flow.setStyleName("Vertical");
        login.setStyleName("gwt-searchbutton");
        RootPanel.get().setStyleName("root");
        flow.setWidth("350");

    }

    public void onModuleLoad() {
        addingpanel();
        RootPanel.get().add(flow);

    }
}