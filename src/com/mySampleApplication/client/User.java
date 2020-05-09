package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class User implements IsSerializable {
    private long username;
    private String password;
    private  int n;

    @SuppressWarnings("unused")
    public User() {
        //just here because GWT wants it.
    }


    public User(long username, String password, int n) {
        this.username = username;
        this.password = password;
        this.n = n;
        //System.out.println(username+" "+password);
    }
    public long getUsername()
    {
        return username;
    }
    public  String getPassword()
    {
        return password;
    }
    public  int getValue(){return n; }

}
