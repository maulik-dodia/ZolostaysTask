package com.zolostaystask.register;

import android.content.Context;

import com.zolostaystask.models.User;

/**
 * Created by techniche-android on 26/7/17.
 */

public interface RegisterModel {

    void doRegister(Context mContext, User user, OnRegisterCompleteListener listener);

    interface OnRegisterCompleteListener {
        void onRegisterComplete(long l);
    }
}