package com.nekodev.paulina.sadowska.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.userlist.activities.UserPreviewActivity;
import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.listeners.UserClickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserListFragment extends Fragment {

    @BindView(R.id.list_recycler_view)
    RecyclerView mRecyclerView;

    private boolean forceReload = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_list_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null)
        {
            forceReload = savedInstanceState.getBoolean(Constants.FORCE_RELOAD);
        }
        configureRecyclerView();
    }


    private void configureRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        UserListAdapter mListAdapter = new UserListAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setUserClickedListener(new UserClickedListener() {
            @Override
            public void userClicked(UserData user) {
                        Intent previewActivity = new Intent(getActivity(), UserPreviewActivity.class);
                        previewActivity.putExtra(Constants.IntentExtras.USERNAME_KEY, user.getUsername());
                        previewActivity.putExtra(Constants.IntentExtras.AVATAR_URL_KEY, user.getAvatarUrl());
                        previewActivity.putExtra(Constants.IntentExtras.USER_SOURCE_KEY, user.getUserSource());
                        startActivity(previewActivity);
            }
        });
        mListAdapter.loadData(forceReload);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(Constants.FORCE_RELOAD, false);
        super.onSaveInstanceState(outState);
    }
}
