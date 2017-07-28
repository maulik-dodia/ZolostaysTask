package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.models.User;

public interface LoginModel {

    void doLogin(Context mContext, User user, OnLoginCompleteListener listener);

    interface OnLoginCompleteListener {
        void onLoginComplete(boolean b);
    }
}