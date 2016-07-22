package com.nekodev.paulina.sadowska.userlist.listeners;

import com.nekodev.paulina.sadowska.userlist.daos.User;

import java.util.List;

/**
 * Created by Paulina Sadowska on 22.07.2016.
 */
public interface DataReadyListener {
    void DataReady(List<User> users);
}
