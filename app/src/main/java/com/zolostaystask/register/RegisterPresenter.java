package com.zolostaystask.register;

import android.content.Context;

import com.zolostaystask.models.User;

public interface RegisterPresenter {

    void register(Context mContext, User user);
}