package com.nekodev.paulina.sadowska.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.userlist.daos.User;
import com.nekodev.paulina.sadowska.userlist.dataaccess.providers.MainDataProvider;
import com.nekodev.paulina.sadowska.userlist.listeners.DataReadyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder>  {

    List<User> userList = new ArrayList<>();

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.fillWIthData(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void loadData() {
        MainDataProvider mainDataProvider = new MainDataProvider();
        mainDataProvider.setDataReadyListener(new DataReadyListener() {
            @Override
            public void DataReady(List<User> users) {
                if(users!=null) {
                    userList.addAll(users);
                    notifyDataSetChanged();
                }
            }
        });
        mainDataProvider.loadData();
    }
}
