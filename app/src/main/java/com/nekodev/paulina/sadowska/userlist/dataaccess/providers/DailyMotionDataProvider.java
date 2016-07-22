package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nekodev.paulina.sadowska.userlist.daos.DailyMotionUsers;
import com.nekodev.paulina.sadowska.userlist.daos.User;
import com.nekodev.paulina.sadowska.userlist.dataaccess.API.DailyMotionAPI;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.ArrayList;
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

    @Override
    public void loadData() {

            Gson gson = new GsonBuilder()
                    .create();
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
        if(listener!=null){
            List<User> userList = new ArrayList<>();
            for(User u: response.body().getUsersList()) {
                userList.add(u);
            }
            listener.DataReady(userList);
        }
    }

    @Override
    public void onFailure(Call<DailyMotionUsers> call, Throwable t) {
        t.printStackTrace();
    }
}
