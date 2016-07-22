package com.nekodev.paulina.sadowska.userlist.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.userlist.R;
import com.nekodev.paulina.sadowska.userlist.UserListFragment;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_user_list_fragment_container, new UserListFragment());
        transaction.commit();
    }
}
