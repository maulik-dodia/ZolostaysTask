package com.zolostaystask.forgotPwd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.zolostaystask.database.DBHelper;
import com.zolostaystask.utils.MailUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by techniche-android on 26/7/17.
 */

public class ForgotPwdModelImpl implements ForgotPwdModel {

    //private Context mContext;

    @Override
    public void doSendEmail(Context mContext, final String email, final OnSendingEmailCompleteListener listener) {

        //this.mContext = context;

        if (isConnectedToInternet(mContext)) {
            final DBHelper dbHelper = new DBHelper(mContext);
            if (dbHelper.checkForExistingUser(email)) {
                String username = dbHelper.getUsername(email);
                String newPwd = newPWDString();
                if (sendEmail(username, email, newPwd) == 1) {
                    if (dbHelper.updateUserPwd(email, newPwd) > 0) {
                        listener.onEmailSent(true);
                    }
                } else {
                    listener.onEmailSent(false);
                }
            } else {
                listener.onUserNotFound();
            }
        } else {
            listener.onInternetNotAvailable();
        }
    }

    public boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        if (!networkInfo.isConnected()) {
            return false;
        }
        if (!networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    private boolean sendEmail(String name, String recipientEmail, String newPassword) {

        final boolean[] emailSentStatus = {false};

        final MailUtils m = new MailUtils("sociallogin007@gmail.com", "loginsocial007");
        String[] toArr = {recipientEmail};
        m.setTo(toArr);
        m.setFrom("sociallogin007@gmail.com");
        m.setSubject("New Password for Zolostays");
        m.setBody("Hello " + name + ", \n\nYour new password is " + newPassword + ".");

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (m.send()) {
                        msg.arg1=1;
                        handler.sendMessage(msg);
                    } else {
                    }
                } catch (Exception e) {
                    Log.e("mk", "Could not send email", e);
                }
            }
        }).start();*/

        class CustomTask extends AsyncTask<Void, Void, Void> {

            protected Void doInBackground(Void... param) {
                try {
                    if (m.send()) {
                        emailSentStatus[0] = true;
                    } else {
                        emailSentStatus[0] = false;
                    }
                } catch (Exception e) {
                    Log.e("mk", "Could not send email", e);
                }
                return null;
            }
        }
        return emailSentStatus[0];
    }

    public static String newPWDString() {
        char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[5];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }
}