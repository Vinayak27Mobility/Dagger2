package com.geniusplaza.android.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.geniusplaza.android.GPApplication;
import com.geniusplaza.android.R;
import com.geniusplaza.android.di.DaggerUserRegisterComponent;
import com.geniusplaza.android.di.UserRegisterComponent;
import com.geniusplaza.android.di.UserRegisterModule;
import com.geniusplaza.android.view.UserRegisterContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRegisterActivity extends AppCompatActivity implements UserRegisterContract.View {


    private UserRegisterComponent daggerComponent;

    @BindView(R.id.user_name_et)
    EditText userNameET;

    @BindView(R.id.user_role_et)
    EditText userRoleET;

    @BindView(R.id.progress_loader)
    ProgressBar progressBar;

    @Inject
    UserRegisterContract.presenter presenter;

    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        setUpDagger();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameET.getText().toString();
                String userRole = userRoleET.getText().toString();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userRole)) {
                    Toast.makeText(UserRegisterActivity.this, R.string.error_register_validation, Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.registerUser(userName, userRole);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        daggerComponent = null;
        super.onDestroy();
    }

    @Override
    public void onUserRegisterSuccess() {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, R.string.success_register_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserRegisterError() {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, R.string.error_register_message, Toast.LENGTH_LONG).show();
    }

    private void setUpDagger() {
        daggerComponent = DaggerUserRegisterComponent.builder()
                .userRegisterModule(new UserRegisterModule(this))
                .applicationComponent(GPApplication.getApplicationComponent())
                .build();

        daggerComponent.inject(this);
    }
}
