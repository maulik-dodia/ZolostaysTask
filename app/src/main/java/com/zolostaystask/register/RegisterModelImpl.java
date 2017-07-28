package com.zolostaystask.register;

import android.content.Context;

import com.zolostaystask.database.DBHelper;
import com.zolostaystask.models.User;

public class RegisterModelImpl implements RegisterModel {

    @Override
    public void doRegister(Context mContext, User user, OnRegisterCompleteListener listener) {

        DBHelper dbHelper = new DBHelper(mContext);
        long registerResult = dbHelper.registerUser(user);

        listener.onRegisterComplete(registerResult);
    }
}