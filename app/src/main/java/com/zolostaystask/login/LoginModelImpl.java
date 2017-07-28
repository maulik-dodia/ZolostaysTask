package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.database.DBHelper;
import com.zolostaystask.models.User;

public class LoginModelImpl implements LoginModel {

    @Override
    public void doLogin(Context mContext, User user, OnLoginCompleteListener listener) {

        DBHelper dbHelper = new DBHelper(mContext);
        boolean loginResult = dbHelper.loginUser(user.getPhone(), user.getPwd());

        listener.onLoginComplete(loginResult);
    }
}