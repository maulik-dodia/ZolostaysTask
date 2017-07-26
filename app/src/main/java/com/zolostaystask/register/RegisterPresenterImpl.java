package com.zolostaystask.register;

import android.content.Context;

import com.zolostaystask.models.User;

/**
 * Created by techniche-android on 26/7/17.
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterModel.OnRegisterCompleteListener {

    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    //Presenter Methods
    @Override
    public void register(Context mContext, User user) {
        registerModel.doRegister(mContext, user, this);
    }

    //Model Methods
    @Override
    public void onRegisterComplete(long l) {
        if (registerView != null) {
            registerView.gotRegisterStatus(l);
        }
    }
}