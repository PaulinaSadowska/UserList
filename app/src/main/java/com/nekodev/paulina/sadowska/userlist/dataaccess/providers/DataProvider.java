package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

/**
 * Created by Paulina Sadowska on 22.07.2016.
 */
public interface DataProvider  {
    void loadData();
    void setDataReadyListener(DataReadyListener listener);
}
