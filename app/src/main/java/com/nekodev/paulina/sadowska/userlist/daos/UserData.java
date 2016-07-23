package com.nekodev.paulina.sadowska.userlist.daos;

/**
 * Created by Paulina Sadowska on 23.07.2016.
 */
public class UserData {

    private String username;
    private String avaratUrl;
    private UserSource userSource;

    public UserData(String username, String avaratUrl, UserSource source){
        this.username = username;
        this.avaratUrl = avaratUrl;
        this.userSource = source;
    }

    public UserSource getUserSource() {
        return userSource;
    }

    public String getAvaratUrl() {
        return avaratUrl;
    }

    public String getUsername() {
        return username;
    }
}
