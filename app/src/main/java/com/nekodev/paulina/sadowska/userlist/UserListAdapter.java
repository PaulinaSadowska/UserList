package com.nekodev.paulina.sadowska.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.userlist.daos.User;

import java.util.List;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder>  {

    List<User> users;

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.fillWIthData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public void loadData() {
        new GithubDataProvider().getItems();
    }
}
