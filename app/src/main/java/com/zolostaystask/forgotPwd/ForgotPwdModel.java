package com.zolostaystask.forgotPwd;

import android.content.Context;

public interface ForgotPwdModel {

    void doSendEmail(Context mContext, String email, OnSendingEmailCompleteListener listener);

    interface OnSendingEmailCompleteListener {

        void onUserNotFound();

        void onEmailSent(boolean b);

        void onInternetNotAvailable();
    }
}