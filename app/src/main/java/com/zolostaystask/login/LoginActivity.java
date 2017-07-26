package com.zolostaystask.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zolostaystask.R;
import com.zolostaystask.customWidgets.CustomEditText;
import com.zolostaystask.forgotPwd.ForgotPwdActivity;
import com.zolostaystask.models.User;
import com.zolostaystask.profile.ProfileActivity;
import com.zolostaystask.register.RegisterActivity;
import com.zolostaystask.utils.FontUtils;
import com.zolostaystask.utils.PrefUtils;
import com.zolostaystask.utils.ValidationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.llRootLayout)
    LinearLayout llRootLayout;

    @BindView(R.id.inputLayoutPhoneNum)
    TextInputLayout inputLayoutPhoneNum;

    @BindView(R.id.editTextPhoneNum)
    CustomEditText editTextPhoneNum;

    @BindView(R.id.editTextPwd)
    CustomEditText editTextPwd;

    @BindView(R.id.inputLayoutPwd)
    TextInputLayout inputLayoutPwd;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.tvForgotPwd)
    TextView tvForgotPwd;

    @BindView(R.id.btnCreateAcc)
    Button btnCreateAcc;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //Checking User Already Login or Not
        if (PrefUtils.getUserPhone(this) != null) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }

        //Initializing Presenter
        loginPresenter = new LoginPresenterImpl(this);

        //Setting Fonts
        btnLogin.setTypeface(FontUtils.getInstance(LoginActivity.this).getRobotoRegularTypeFace());
        tvForgotPwd.setTypeface(FontUtils.getInstance(LoginActivity.this).getRobotoRegularTypeFace());
        btnCreateAcc.setTypeface(FontUtils.getInstance(LoginActivity.this).getRobotoRegularTypeFace());

        //Setting TextWatcher
        //editTextPhoneNum.addTextChangedListener(new MyTextWatcher(editTextPhoneNum));
        //editTextPwd.addTextChangedListener(new MyTextWatcher(editTextPwd));
    }

    @Override
    public void gotLoginStatus(boolean b) {
        if (b) {
            PrefUtils.setUserPhone(LoginActivity.this, editTextPhoneNum.getText().toString());
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        } else {
            Snackbar snackbar = Snackbar.make(llRootLayout, R.string.str_login_failure, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @OnClick(R.id.btnLogin)
    public void loginUser() {
        User user = new User(editTextPhoneNum.getText().toString(), editTextPwd.getText().toString());
        loginPresenter.login(LoginActivity.this, user);
    }

    @OnClick(R.id.tvForgotPwd)
    public void navigateToForgotPwd() {
        startActivity(new Intent(this, ForgotPwdActivity.class));
    }

    @OnClick(R.id.btnCreateAcc)
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
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
                case R.id.editTextPhoneNum:
                    String validationPhoneResult = ValidationUtils.validatePhone(editTextPhoneNum.getText().toString());
                    if (!TextUtils.isEmpty(validationPhoneResult) && validationPhoneResult != null) {
                        inputLayoutPhoneNum.setError(validationPhoneResult);
                    } else {
                        inputLayoutPhoneNum.setError(null);
                        inputLayoutPhoneNum.setErrorEnabled(false);
                    }
                    break;

                case R.id.editTextPwd:
                    String validationPwdResult = ValidationUtils.validatePwd(editTextPwd.getText().toString());
                    if (!TextUtils.isEmpty(validationPwdResult) && validationPwdResult != null) {
                        inputLayoutPwd.setError(validationPwdResult);
                    } else {
                        inputLayoutPwd.setError(null);
                        inputLayoutPwd.setErrorEnabled(false);
                    }
                    break;
            }
        }
    }
}