package com.zolostaystask.forgotPwd;

import android.content.Context;

import com.zolostaystask.database.DBHelper;

/**
 * Created by techniche-android on 26/7/17.
 */

public class ForgotPwdModelImpl implements ForgotPwdModel {

    @Override
    public void doSendEmail(Context mContext, String email, OnSendingEmailCompleteListener listener) {

        DBHelper dbHelper = new DBHelper(mContext);

        if (dbHelper.checkForExistingUser(email)) {
            //Send Email Login Here
            boolean sendEmailStatus = true;
            if (sendEmailStatus) {
                listener.onEmailSent(true);
            } else {
                listener.onEmailSent(false);
            }
        } else {
            listener.onUserNotFound();
        }
    }
}