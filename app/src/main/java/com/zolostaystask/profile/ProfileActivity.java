package com.zolostaystask.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zolostaystask.R;
import com.zolostaystask.login.LoginActivity;
import com.zolostaystask.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.btnLogout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogout)
    public void navigateToLogin() {
        PrefUtils.clearSP(ProfileActivity.this);
        finish();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
    }
}