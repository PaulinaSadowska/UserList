package com.nekodev.paulina.sadowska.userlist.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nekodev.paulina.sadowska.userlist.Constants;
import com.nekodev.paulina.sadowska.userlist.R;
import com.nekodev.paulina.sadowska.userlist.daos.UserSource;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paulina Sadowska on 23.07.2016.
 */
public class UserPreviewActivity extends AppCompatActivity {

    @BindView(R.id.user_preview_avatar)
    ImageView mAvatar;
    @BindView(R.id.user_preview_name)
    TextView mUserName;

    private int githubTextColor;
    private int dailyMotionTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preview);
        ButterKnife.bind(this);
        githubTextColor = ContextCompat.getColor(this, R.color.githubTextColor);
        dailyMotionTextColor = ContextCompat.getColor(this, R.color.dailyTextColor);
        fillWithData();
    }

    private void fillWithData() {
        if(getIntent().hasExtra(Constants.IntentExtras.USERNAME_KEY)){
            mUserName.setText(getIntent().getStringExtra(Constants.IntentExtras.USERNAME_KEY));
        }
        if(getIntent().hasExtra(Constants.IntentExtras.AVATAR_URL_KEY)){
            String avatarUrl = getIntent().getStringExtra(Constants.IntentExtras.AVATAR_URL_KEY);
            Picasso.with(this).load(avatarUrl).into(mAvatar);
        }
        if(getIntent().hasExtra(Constants.IntentExtras.USER_SOURCE_KEY)){
            UserSource source = (UserSource) getIntent().getSerializableExtra(Constants.IntentExtras.USER_SOURCE_KEY);
            mUserName.setTextColor(getTextColor(source));
        }
    }

    private int getTextColor(UserSource source) {
        if(source==UserSource.GITHUB)
            return githubTextColor;
        return dailyMotionTextColor;
    }
}
