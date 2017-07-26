package com.zolostaystask.forgotPwd;

import android.content.Context;

/**
 * Created by techniche-android on 26/7/17.
 */

public interface ForgotPwdModel {

    void doSendEmail(Context mContext, String email, OnSendingEmailCompleteListener listener);

    interface OnSendingEmailCompleteListener {

        void onUserNotFound();
        void onEmailSent(boolean b);
        void onInternetNoAvailable();
    }
}