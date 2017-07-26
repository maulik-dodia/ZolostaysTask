package com.zolostaystask.forgotPwd;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zolostaystask.R;
import com.zolostaystask.customWidgets.CustomEditText;
import com.zolostaystask.utils.FontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends AppCompatActivity implements ForgotPwdView {

    @BindView(R.id.rlRootLayout)
    RelativeLayout rlRootLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvToolbarText)
    TextView tvToolbarText;

    @BindView(R.id.tvForgotPwd)
    TextView tvForgotPwd;

    @BindView(R.id.tvForgotPwdText)
    TextView tvForgotPwdText;

    @BindView(R.id.editTextEmail)
    CustomEditText editTextEmail;

    @BindView(R.id.btnResetPwd)
    Button btnResetPwd;

    private ForgotPwdPresenter forgotPwdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        ButterKnife.bind(this);
        init();
    }

    private void init() {

        //Initializing Presenter
        forgotPwdPresenter = new ForgotPwdPresenterImpl(this);

        //Setting Fonts
        tvToolbarText.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        tvForgotPwd.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        tvForgotPwdText.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        btnResetPwd.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
    }

    @OnClick(R.id.btnResetPwd)
    public void sendEmailToResetPwd() {
        forgotPwdPresenter.sendEmail(ForgotPwdActivity.this, editTextEmail.getText().toString());
    }

    @OnClick(R.id.tvToolbarText)
    public void navifateToLogin(){
        onBackPressed();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar snackbar = Snackbar.make(rlRootLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}