package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.database.DBHelper;
import com.zolostaystask.models.User;

/**
 * Created by techniche-android on 26/7/17.
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public void doLogin(Context mContext, User user, OnLoginCompleteListener listener) {

        DBHelper dbHelper = new DBHelper(mContext);
        boolean loginResult = dbHelper.loginUser(user.getPhone(), user.getPwd());

        listener.onLoginComplete(loginResult);
    }
}