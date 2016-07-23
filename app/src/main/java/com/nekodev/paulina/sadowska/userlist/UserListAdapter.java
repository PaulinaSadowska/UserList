package com.nekodev.paulina.sadowska.userlist;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserSource;
import com.nekodev.paulina.sadowska.userlist.dataaccess.providers.MainDataProvider;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder>  {

    private int githubBackgroundColor;
    private int dailyMotionBackgroundColor;

    private List<UserData> userList = new ArrayList<>();
    private Context context;

    public UserListAdapter(Context context){
        this.context = context;
        githubBackgroundColor = ContextCompat.getColor(context, R.color.githubBackground);
        dailyMotionBackgroundColor =  ContextCompat.getColor(context, R.color.dailyBackground);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserData user = userList.get(position);
        Picasso.with(context).load(user.getAvaratUrl()).into(holder.userAvatar);
        holder.userName.setText(user.getUsername());
        holder.userLayout.setBackgroundColor(getBackgroundColor(user.getUserSource()));
    }

    private int getBackgroundColor(UserSource userSource) {
        if(userSource == UserSource.DAILY_MOTION)
            return dailyMotionBackgroundColor;
        return githubBackgroundColor;
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void loadData() {
        MainDataProvider mainDataProvider = new MainDataProvider();
        mainDataProvider.setDataReadyListener(new DataReadyListener() {
            @Override
            public void DataReady(List<UserData> users) {
                if(users!=null) {
                    userList.addAll(users);
                    notifyDataSetChanged();
                }
            }
        });
        mainDataProvider.loadData();
    }
}
