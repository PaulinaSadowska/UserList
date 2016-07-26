package com.nekodev.paulina.sadowska.userlist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.userlist.Constants;
import com.nekodev.paulina.sadowska.userlist.R;
import com.nekodev.paulina.sadowska.userlist.UserListFragment;
import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.listeners.UserClickedListener;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        if ((findViewById(R.id.activity_user_list_fragment_container) != null && savedInstanceState == null)
                || findViewById(R.id.activity_user_list_fragment_container) == null) {

                UserListFragment usersFragment = new UserListFragment();
                usersFragment.setUserClickedListener(new UserClickedListener() {
                    @Override
                    public void userClicked(UserData user) {
                        Intent previewActivity = new Intent(getApplicationContext(), UserPreviewActivity.class);
                        previewActivity.putExtra(Constants.IntentExtras.USERNAME_KEY, user.getUsername());
                        previewActivity.putExtra(Constants.IntentExtras.AVATAR_URL_KEY, user.getAvatarUrl());
                        previewActivity.putExtra(Constants.IntentExtras.USER_SOURCE_KEY, user.getUserSource());
                        startActivity(previewActivity);
                    }
                });
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_user_list_fragment_container, usersFragment)
                        .commit();
        }
    }
}
