package com.nekodev.paulina.sadowska.userlist;

import com.nekodev.paulina.sadowska.userlist.daos.DailyMotionUserData;
import com.nekodev.paulina.sadowska.userlist.daos.GithubUserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserData;
import com.nekodev.paulina.sadowska.userlist.daos.UserDataMapper;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Paulina Sadowska on 23.07.2016.
 */
public class UserDataMapperTest {

    private static final String[] usernames = {"Anna", "Jan"};
    private static final String[] avatarUrls = {"http://someurl", "some address"};

    @Test
    public void GithubUserData_UserData_converts() throws NoSuchFieldException, IllegalAccessException {
        List<GithubUserData> gitUsers = new ArrayList<>();
        gitUsers.add(getGithubUser(usernames[0], avatarUrls[0]));
        gitUsers.add(getGithubUser(usernames[1], avatarUrls[1]));
        List<UserData> convertedUserList = new UserDataMapper().mapGithubUsers(gitUsers);
        assertNotNull(convertedUserList);
        for (int i = 0; i < gitUsers.size(); i++) {
            assertEquals(usernames[i], gitUsers.get(i).getUsername());
            assertEquals(avatarUrls[i], gitUsers.get(i).getAvatarUrl());
            assertEquals(usernames[i], convertedUserList.get(i).getUsername());
            assertEquals(avatarUrls[i], convertedUserList.get(i).getAvatarUrl());
        }
    }

    @Test
    public void DailyMotionUserData_UserData_converts() throws NoSuchFieldException, IllegalAccessException {
        List<DailyMotionUserData> dailyUsers = new ArrayList<>();
        dailyUsers.add(getDailyMotionUser(usernames[0], avatarUrls[0]));
        dailyUsers.add(getDailyMotionUser(usernames[1], avatarUrls[1]));
        List<UserData> convertedUserList = new UserDataMapper().mapDailyMotionUsers(dailyUsers);
        assertNotNull(convertedUserList);
        for (int i = 0; i < dailyUsers.size(); i++) {
            assertEquals(usernames[i], dailyUsers.get(i).getUsername());
            assertEquals(avatarUrls[i], dailyUsers.get(i).getAvatarUrl());
            assertEquals(usernames[i], convertedUserList.get(i).getUsername());
            assertEquals(avatarUrls[i], convertedUserList.get(i).getAvatarUrl());
        }
    }

    private DailyMotionUserData getDailyMotionUser(String username, String avatarUrl) throws NoSuchFieldException, IllegalAccessException {
        DailyMotionUserData userData = new DailyMotionUserData();
        Field f = userData.getClass().getDeclaredField("username"); //NoSuchFieldException
        f.setAccessible(true);
        f.set(userData, username);
        f = userData.getClass().getDeclaredField("avatar_360_url");
        f.setAccessible(true);
        f.set(userData, avatarUrl);
        return userData;
    }

    private GithubUserData getGithubUser(String username, String avatarUrl) throws NoSuchFieldException, IllegalAccessException {
        GithubUserData userData = new GithubUserData();
        Field f = userData.getClass().getDeclaredField("login"); //NoSuchFieldException
        f.setAccessible(true);
        f.set(userData, username);
        f = userData.getClass().getDeclaredField("avatar_url");
        f.setAccessible(true);
        f.set(userData, avatarUrl);
        return userData;
    }
}
