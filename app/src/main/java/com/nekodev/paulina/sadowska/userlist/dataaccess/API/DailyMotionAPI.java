package com.nekodev.paulina.sadowska.userlist.dataaccess.API;

import com.nekodev.paulina.sadowska.userlist.daos.DailyMotionUsers;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public interface DailyMotionAPI {
    @GET("/users?fields=avatar_360_url,username")
    Call<DailyMotionUsers> loadData();
}
