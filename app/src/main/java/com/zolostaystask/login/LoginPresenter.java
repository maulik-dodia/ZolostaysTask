package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.models.User;

/**
 * Created by techniche-android on 26/7/17.
 */

public interface LoginPresenter {

    void login(Context mContext, User user);
}