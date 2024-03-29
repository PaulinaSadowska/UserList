package com.nekodev.paulina.sadowska.userlist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.userlist.R;
import com.nekodev.paulina.sadowska.userlist.fragments.UserListFragment;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        if ((findViewById(R.id.activity_user_list_fragment_container) != null && savedInstanceState == null)
                || findViewById(R.id.activity_user_list_fragment_container) == null) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_user_list_fragment_container, new UserListFragment())
                        .commit();
        }
    }
}
