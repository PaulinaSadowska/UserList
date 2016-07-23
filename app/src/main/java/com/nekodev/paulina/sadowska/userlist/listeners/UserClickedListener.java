package com.nekodev.paulina.sadowska.userlist.listeners;

import com.nekodev.paulina.sadowska.userlist.daos.UserData;

/**
 * Created by Paulina Sadowska on 23.07.2016.
 */
public interface UserClickedListener {
    void userClicked(UserData user);
}
