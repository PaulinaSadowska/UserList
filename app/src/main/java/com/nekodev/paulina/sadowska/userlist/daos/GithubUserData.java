package com.nekodev.paulina.sadowska.userlist.daos;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class GithubUserData implements User {

    private String login;
    private String avatar_url;

    @Override
    public String getName() {
        return login;
    }

    @Override
    public String getAvatarUrl() {
        return avatar_url;
    }
}
