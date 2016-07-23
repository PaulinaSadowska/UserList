package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nekodev.paulina.sadowska.userlist.Constants;
import com.nekodev.paulina.sadowska.userlist.daos.DailyMotionUsers;
import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserDataMapper;
import com.nekodev.paulina.sadowska.userlist.dataaccess.API.DailyMotionAPI;
import com.nekodev.paulina.sadowska.userlist.dataaccess.FileManager;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Paulina Sadowska on 22.07.2016.
 */
public class DailyMotionDataProvider implements Callback<DailyMotionUsers>, DataProvider {

    private static final String ADDRESS = "https://api.dailymotion.com/";
    private DataReadyListener listener;
    private Gson gson;
    private FileManager fileManager;

    public DailyMotionDataProvider(String appFilesPath){
        gson = new GsonBuilder().create();
        fileManager = new FileManager(appFilesPath, Constants.FileNames.DAILY_MOTION);
    }

    @Override
    public void loadData(boolean forceReload) {
        if(forceReload)
            fileManager.saveToFile("");
        else if(!fileManager.isFileEmpty()){
            if(listener!=null){
                listener.DataReady((List<UserData>) gson.fromJson(fileManager.readFromFile(), new TypeToken<List<UserData>>(){}.getType()));
            }
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Call<DailyMotionUsers> call = retrofit.create(DailyMotionAPI.class).loadData();
        call.enqueue(this);
    }

    @Override
    public void setDataReadyListener(DataReadyListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<DailyMotionUsers> call, Response<DailyMotionUsers> response) {
        List<UserData> users = new UserDataMapper().mapDailyMotionUsers(response.body().getUsersList());
        if(listener!=null){
            listener.DataReady(users);
        }
        fileManager.saveToFile(gson.toJson(users));
    }

    @Override
    public void onFailure(Call<DailyMotionUsers> call, Throwable t) {
        t.printStackTrace();
    }
}
