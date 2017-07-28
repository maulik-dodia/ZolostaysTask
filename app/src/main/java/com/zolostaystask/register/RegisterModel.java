package com.zolostaystask.register;

import android.content.Context;

import com.zolostaystask.models.User;

public interface RegisterModel {

    void doRegister(Context mContext, User user, OnRegisterCompleteListener listener);

    interface OnRegisterCompleteListener {
        void onRegisterComplete(long l);
    }
}