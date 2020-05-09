package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Details implements IsSerializable {
    private long Dustbin_ID;
    private String location;
    private int Status;

    public Details()
    {

    }
    public Details(long Dustbin_ID, String location,int Status)
    {
        this.Dustbin_ID = Dustbin_ID;
        this.location = location;
        this.Status= Status;
        System.out.println(Dustbin_ID);

    }
    public long getDustbin_ID()
    {
        return Dustbin_ID;
    }
    public  String getLocation()
    {
        return location;
    }
    public int getStatus()
    {
        return Status;
    }
}
