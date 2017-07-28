package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.models.User;

public interface LoginPresenter {

    void login(Context mContext, User user);
}