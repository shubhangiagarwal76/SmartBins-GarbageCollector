package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import java.util.ArrayList;

public interface DBConnectionAsync {
    // public User authenticateUser(String name, String pass);
    void authenticateDetails(long did, AsyncCallback<ArrayList<Details>> callback1);

    void authenticateContact(long did,AsyncCallback<Contact> callback);

    //void locationList(AsyncCallback<ArrayList<Location>> callback2);



    void authenticateUser(long name, String pass, int n, AsyncCallback<User> callback3);
}
