package com.zolostaystask.forgotPwd;

import android.content.Context;

/**
 * Created by techniche-android on 26/7/17.
 */

public class ForgotPwdPresenterImpl implements ForgotPwdPresenter, ForgotPwdModel.OnSendingEmailCompleteListener {

    private ForgotPwdView forgotPwdView;
    private ForgotPwdModel forgotPwdModel;

    public ForgotPwdPresenterImpl(ForgotPwdView forgotPwdView) {
        this.forgotPwdView = forgotPwdView;
        forgotPwdModel = new ForgotPwdModelImpl();
    }

    //Presenter Methods
    @Override
    public void sendEmail(Context mContext, String email) {
        forgotPwdModel.doSendEmail(mContext, email, this);
    }

    //Model Methods
    @Override
    public void onUserNotFound() {
        if (forgotPwdView != null) {
            forgotPwdView.showMessage("User not found!");
        }
    }

    @Override
    public void onEmailSent(boolean b) {
        if (forgotPwdView != null) {
            if (b) {
                forgotPwdView.showMessage("New password is sent to your email!");
            } else {
                forgotPwdView.showMessage("Some error occurred while sending email. Please try after some time.");
            }
        }
    }
    
    @Override
    public void onInternetNotAvailable() {
        forgotPwdView.showMessage("Check your internet connection!");
    }
}