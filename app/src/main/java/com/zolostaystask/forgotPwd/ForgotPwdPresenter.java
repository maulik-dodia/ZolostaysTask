package com.zolostaystask.forgotPwd;

import android.content.Context;

/**
 * Created by techniche-android on 26/7/17.
 */

public interface ForgotPwdPresenter {

    void sendEmail(Context mContext, String email);
}