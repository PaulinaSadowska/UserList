package com.nekodev.paulina.sadowska.userlist.daos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 23.07.2016.
 */
public class UserDataMapper {

    public List<UserData> mapGithubUsers(List<GithubUserData> githubUsers){
        List<UserData> mappedUsers = new ArrayList<>();
        for (GithubUserData user : githubUsers) {
            mappedUsers.add(toUserData(user));
        }
        return mappedUsers;
    }

    public List<UserData> mapDailyMotionUsers(List<DailyMotionUserData> dailyMotionUsers){
        List<UserData> mappedUsers = new ArrayList<>();
        for (DailyMotionUserData user : dailyMotionUsers) {
            mappedUsers.add(toUserData(user));
        }
        return mappedUsers;
    }

    private UserData toUserData(GithubUserData githibUser){
        return new UserData(githibUser.getUsername(), githibUser.getAvatarUrl(), UserSource.GITHUB);
    }

    private UserData toUserData(DailyMotionUserData dailyMotionUser){
        return new UserData(dailyMotionUser.getUsername(), dailyMotionUser.getAvatarUrl(), UserSource.DAILY_MOTION);
    }
}
