package com.nekodev.paulina.sadowska.userlist.dataaccess;

import com.nekodev.paulina.sadowska.userlist.daos.GithubUserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public interface GithubAPI {
        @GET("/users")
        Call<List<GithubUserData>> loadData();
}
