package com.nekodev.paulina.sadowska.userlist.dataaccess.providers;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nekodev.paulina.sadowska.userlist.Constants;
import com.nekodev.paulina.sadowska.userlist.daos.GithubUserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserDataMapper;
import com.nekodev.paulina.sadowska.userlist.dataaccess.API.GithubAPI;
import com.nekodev.paulina.sadowska.userlist.dataaccess.FileManager;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Paulina Sadowska on 20.07.2016.
 */
public class GithubDataProvider implements Callback<List<GithubUserData>>, DataProvider {

    private static final String ADDRESS = "https://api.github.com/";
    private DataReadyListener listener;
    private Gson gson;
    private FileManager fileManager;

    public GithubDataProvider(String appFilesPath){
        gson = new GsonBuilder().create();
        fileManager = new FileManager(appFilesPath, Constants.FileNames.GITHUB);
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
        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Call<List<GithubUserData>> call = retrofit.create(GithubAPI.class).loadData();
        call.enqueue(this);
    }

    @Override
    public void setDataReadyListener(DataReadyListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<List<GithubUserData>> call, Response<List<GithubUserData>> response) {
        List<UserData> users = new UserDataMapper().mapGithubUsers(response.body());
        if(listener!=null){
                listener.DataReady(users);
        }
        fileManager.saveToFile(gson.toJson(users));
    }

    @Override
    public void onFailure(Call<List<GithubUserData>> call, Throwable t) {
        t.printStackTrace();
    }
}

