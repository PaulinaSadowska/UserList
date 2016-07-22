package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nekodev.paulina.sadowska.userlist.daos.DailyMotionUsers;
import com.nekodev.paulina.sadowska.userlist.dataaccess.API.DailyMotionAPI;

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
    public void onResponse(Call<DailyMotionUsers> call, Response<DailyMotionUsers> response) {
        Log.d(DailyMotionDataProvider.class.getName(), response.body().toString());
    }

    @Override
    public void onFailure(Call<DailyMotionUsers> call, Throwable t) {
        t.printStackTrace();
    }
}
