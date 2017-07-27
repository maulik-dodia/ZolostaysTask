package com.zolostaystask.forgotPwd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zolostaystask.database.DBHelper;
import com.zolostaystask.utils.GMailSender;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by techniche-android on 26/7/17.
 */

public class ForgotPwdModelImpl implements ForgotPwdModel {

    private Context mContext;

    @Override
    public void doSendEmail(Context context, final String email, final OnSendingEmailCompleteListener listener) {

        this.mContext = context;

        if (isConnectedToInternet(context)) {
            final DBHelper dbHelper = new DBHelper(context);
            if (dbHelper.checkForExistingUser(email)) {

                String newPwd = newPWDString();
                if (sendEmail(email)) {
                    if (dbHelper.updateUserPwd(email, newPwd) > 0) {
                        listener.onNewPwdGenerated(newPwd);
                    }
                } else {
                    listener.onEmailSent(false);
                }

                /*Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();*/

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

    private boolean sendEmail(String recipientEmail) {
        try {
            GMailSender sender = new GMailSender("sociallogin007@gmail.com", "loginsocial007");
            sender.sendMail("Hello from JavaMail", "Body from JavaMail",
                    "sociallogin007@gmail.com", recipientEmail);
            return true;
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
            return false;
        }
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