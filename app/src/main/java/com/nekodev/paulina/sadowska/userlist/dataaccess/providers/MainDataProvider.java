package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import com.nekodev.paulina.sadowska.userlist.daos.User;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.List;

/**
 * Created by Paulina Sadowska on 22.07.2016.
 */
public class MainDataProvider implements DataProvider {

    private DataProvider[] dataProviders;
    private DataReadyListener listener;

    public MainDataProvider(){
        dataProviders = new DataProvider[2];
        dataProviders[0] = new GithubDataProvider();
        dataProviders[1] = new DailyMotionDataProvider();
        for (DataProvider dataProvider : dataProviders) {
            dataProvider.setDataReadyListener(new DataReadyListener() {
                @Override
                public void DataReady(List<User> users) {
                    if(listener!=null) {
                        listener.DataReady(users);
                    }
                }
            });
        }
    }

    @Override
    public void loadData() {
        for (DataProvider dataProvider : dataProviders) {
            dataProvider.loadData();
        }
    }

    @Override
    public void setDataReadyListener(DataReadyListener listener) {
        this.listener = listener;
    }
}
