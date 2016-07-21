package com.nekodev.paulina.sadowska.userlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserListFragment extends Fragment {

    @BindView(R.id.list_recycler_view)
    RecyclerView mRecyclerView;

    private UserListAdapter mListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_list_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mListAdapter = new UserListAdapter();
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.loadData();
    }
}
