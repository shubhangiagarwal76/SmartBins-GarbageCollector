package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.RemoteService;

import java.util.ArrayList;

public interface DBConnection extends RemoteService {

    public User authenticateUser(long name, String pass, int n);
    public  ArrayList<Details> authenticateDetails(long did);
    public  Contact authenticateContact(long did);
   // public  ArrayList<Location> locationList();
}
