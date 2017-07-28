package com.zolostaystask.forgotPwd;

import android.content.Context;

public interface ForgotPwdPresenter {

    void sendEmail(Context mContext, String email);
}