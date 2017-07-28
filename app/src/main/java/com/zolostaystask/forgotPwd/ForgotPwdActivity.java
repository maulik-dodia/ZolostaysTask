package com.zolostaystask.forgotPwd;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zolostaystask.R;
import com.zolostaystask.customWidgets.CustomEditText;
import com.zolostaystask.utils.FontUtils;
import com.zolostaystask.utils.ValidationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends AppCompatActivity implements ForgotPwdView {

    @BindView(R.id.inputLayoutEmail)
    TextInputLayout inputLayoutEmail;

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

    private boolean validEmail;
    private ProgressDialog progress;
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

        //Initializing ProgressDialog
        progress = new ProgressDialog(this);
        progress.setMessage("Sending email..");

        //Setting Fonts
        tvToolbarText.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        tvForgotPwd.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        tvForgotPwdText.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());
        btnResetPwd.setTypeface(FontUtils.getInstance(ForgotPwdActivity.this).getRobotoRegularTypeFace());

        //Setting TextWatcher
        editTextEmail.addTextChangedListener(new MyTextWatcher(editTextEmail));
    }

    @OnClick(R.id.btnResetPwd)
    public void sendEmailToResetPwd() {
        progress.show();
        forgotPwdPresenter.sendEmail(ForgotPwdActivity.this, editTextEmail.getText().toString());
    }

    @OnClick(R.id.tvToolbarText)
    public void navifateToLogin() {
        onBackPressed();
    }

    @Override
    public void showMessage(String msg) {
        progress.dismiss();
        Snackbar snackbar = Snackbar.make(rlRootLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //TextWatcher Class
    public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {
                case R.id.editTextEmail:
                    String validationPhoneResult = ValidationUtils.validateEmail(editTextEmail.getText().toString());
                    if (!TextUtils.isEmpty(validationPhoneResult) && validationPhoneResult != null) {
                        validEmail = false;
                        inputLayoutEmail.setError(validationPhoneResult);
                    } else {
                        validEmail = true;
                        inputLayoutEmail.setError(null);
                        inputLayoutEmail.setErrorEnabled(false);
                    }
                    break;
            }
            if (validEmail) {
                btnResetPwd.setEnabled(true);
            } else {
                btnResetPwd.setEnabled(false);
            }
        }
    }
}