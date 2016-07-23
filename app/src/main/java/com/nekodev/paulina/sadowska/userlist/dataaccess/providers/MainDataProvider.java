package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.List;

/**
 * Created by Paulina Sadowska on 22.07.2016.
 */
public class MainDataProvider implements DataProvider {

    private DataProvider[] dataProviders;
    private DataReadyListener listener;

    public MainDataProvider(String appFilesPath){
        dataProviders = new DataProvider[2];
        dataProviders[0] = new DailyMotionDataProvider(appFilesPath);
        dataProviders[1] = new GithubDataProvider(appFilesPath);
        for (DataProvider dataProvider : dataProviders) {
            dataProvider.setDataReadyListener(new DataReadyListener() {
                @Override
                public void DataReady(List<UserData> users) {
                    if(listener!=null) {
                        listener.DataReady(users);
                    }
                }
            });
        }
    }

    @Override
    public void loadData(boolean forceReload) {
        for (DataProvider dataProvider : dataProviders) {
            dataProvider.loadData(forceReload);
        }
    }

    @Override
    public void setDataReadyListener(DataReadyListener listener) {
        this.listener = listener;
    }
}
