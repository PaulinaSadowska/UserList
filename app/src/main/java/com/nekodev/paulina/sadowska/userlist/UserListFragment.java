package com.nekodev.paulina.sadowska.userlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private UserClickedListener listener;
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
        if(getArguments()!=null)
        {
            forceReload = getArguments().getBoolean(Constants.FORCE_RELOAD);
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
                if(listener!=null){
                    listener.userClicked(user);
                }
            }
        });
        mListAdapter.loadData(forceReload);
    }

    public void setUserClickedListener(UserClickedListener listener) {
        this.listener = listener;
    }
}
