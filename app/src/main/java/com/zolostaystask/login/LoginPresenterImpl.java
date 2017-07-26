package com.zolostaystask.login;

import android.content.Context;

import com.zolostaystask.models.User;

/**
 * Created by techniche-android on 26/7/17.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginCompleteListener {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    //Presenter Methods
    @Override
    public void login(Context mContext, User user) {
        loginModel.doLogin(mContext, user, this);
    }

    //Model Methods
    @Override
    public void onLoginComplete(boolean b) {
        if (loginView != null) {
            loginView.gotLoginStatus(b);
        }
    }
}