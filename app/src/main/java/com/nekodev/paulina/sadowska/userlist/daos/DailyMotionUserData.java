package com.nekodev.paulina.sadowska.userlist.daos;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class DailyMotionUserData implements User {

    private String username;
    private String avatar_360_url;

    @Override
    public String getName() {
        return username;
    }

    @Override
    public int getAvatar() {
        return 0; //TODO - implement me
    }
}
