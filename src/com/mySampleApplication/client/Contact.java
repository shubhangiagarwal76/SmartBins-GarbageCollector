package com.mySampleApplication.client;


import com.google.gwt.user.client.rpc.IsSerializable;

public class Contact implements IsSerializable {
    private String f_name;
    private String l_name;

    private long mobileno;

    private String emailid;

    private String DOB;

//

    public Contact()
    {

    }
    public Contact(String f_name,
                   String l_name,
                   long mobileno,
                   String emailid,
                   String DOB
    )
    {


        this.f_name = f_name;
        this.l_name=l_name;
        this.mobileno=mobileno;
        this.emailid=emailid;
        this.DOB=DOB;
        System.out.println(DOB);
    }

    public long getMobileno(){ return mobileno;}
    public String  getEmailid(){ return emailid;}
    public String getF_name() {
        return f_name;
    }
    public String getL_name()
    {
        return l_name;
    }
    public String getDOB()
    {
        return DOB;
    }

}
