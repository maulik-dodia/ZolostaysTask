package com.zolostaystask.register;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zolostaystask.R;
import com.zolostaystask.customWidgets.CustomEditText;
import com.zolostaystask.models.User;
import com.zolostaystask.utils.FontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.rlRootLayout)
    RelativeLayout rlRootLayout;

    @BindView(R.id.tvToolbarText)
    TextView tvToolbarText;

    @BindView(R.id.tvRegister)
    TextView tvRegister;

    @BindView(R.id.tvRegisterText)
    TextView tvRegisterText;

    @BindView(R.id.editTextPhoneNum)
    CustomEditText editTextPhoneNum;

    @BindView(R.id.editTextEmail)
    CustomEditText editTextEmail;

    @BindView(R.id.editTextName)
    CustomEditText editTextName;

    @BindView(R.id.editTextPwd)
    CustomEditText editTextPwd;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    private boolean flag = true;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //Initializing Presenter
        registerPresenter = new RegisterPresenterImpl(this);

        //Setting Fonts
        tvToolbarText.setTypeface(FontUtils.getInstance(RegisterActivity.this).getRobotoRegularTypeFace());
        tvRegister.setTypeface(FontUtils.getInstance(RegisterActivity.this).getRobotoRegularTypeFace());
        btnRegister.setTypeface(FontUtils.getInstance(RegisterActivity.this).getRobotoRegularTypeFace());
        tvRegisterText.setTypeface(FontUtils.getInstance(RegisterActivity.this).getRobotoRegularTypeFace());
    }

    @OnTouch(R.id.editTextPwd)
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (motionEvent.getRawX() >= (editTextPwd.getRight() - editTextPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                if (flag) {
                    editTextPwd.setInputType(InputType.TYPE_CLASS_TEXT);
                    editTextPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_show_pwd, 0);
                } else {
                    editTextPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    editTextPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_hide_pwd, 0);
                }
                flag = !flag;
                return true;
            }
        }
        return false;
    }

    @OnClick(R.id.btnRegister)
    public void registerUser() {
        User user = new User(editTextName.getText().toString(), editTextEmail.getText().toString(),
                editTextPwd.getText().toString(), editTextPhoneNum.getText().toString());
        registerPresenter.register(RegisterActivity.this, user);
    }

    @OnClick(R.id.tvToolbarText)
    public void navifateToLogin() {
        onBackPressed();
    }

    @Override
    public void gotRegisterStatus(long l) {
        Snackbar snackbar;
        if (l != -11) {
            if (l != -1) {
                snackbar = Snackbar.make(rlRootLayout, R.string.str_register_success, Snackbar.LENGTH_LONG);
            } else {
                snackbar = Snackbar.make(rlRootLayout, R.string.str_register_failure, Snackbar.LENGTH_LONG);
            }
        } else {
            snackbar = Snackbar.make(rlRootLayout, R.string.str_register_exists, Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }
}