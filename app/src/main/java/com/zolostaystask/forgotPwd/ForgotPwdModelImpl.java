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

public class ForgotPwdModelImpl implements ForgotPwdModel {

    private Context mContext;
    private DBHelper dbHelper;
    private OnSendingEmailCompleteListener mListner;

    @Override
    public void doSendEmail(Context context, final String email, final OnSendingEmailCompleteListener listener) {

        this.mContext = context;
        this.mListner = listener;
        dbHelper = new DBHelper(context);

        if (isConnectedToInternet(context)) {

            if (dbHelper.checkForExistingUser(email)) {

                String[] strings = {dbHelper.getUsername(email), email, newPWDString()};

                new SendingEmail().execute(strings);

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

    private class SendingEmail extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            boolean emailSentStatus = false;

            String name = params[0];
            String recipientEmail = params[1];
            String newPassword = params[2];

            final MailUtils m = new MailUtils("sociallogin007@gmail.com", "loginsocial007");
            String[] toArr = {recipientEmail};
            m.setTo(toArr);
            m.setFrom("sociallogin007@gmail.com");
            m.setSubject("New Password for Zolostays");
            m.setBody("Hello " + name + ", \n\nYour new password is " + newPassword + ".");

            try {
                if (m.send()) {
                    dbHelper = new DBHelper(mContext);
                    if (dbHelper.updateUserPwd(recipientEmail, newPassword) > 0) {
                        emailSentStatus = true;
                    }
                } else {
                    emailSentStatus = false;
                }
            } catch (Exception e) {
                Log.e("mk", "Could not send email", e);
            }
            return emailSentStatus;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                mListner.onEmailSent(true);
            } else {
                mListner.onEmailSent(false);
            }
        }
    }
}