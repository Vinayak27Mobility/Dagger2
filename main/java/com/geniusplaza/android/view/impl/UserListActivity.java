package com.geniusplaza.android.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geniusplaza.android.GPApplication;
import com.geniusplaza.android.R;
import com.geniusplaza.android.adapter.UserListAdapter;
import com.geniusplaza.android.di.DaggerUserListComponent;
import com.geniusplaza.android.di.UserListComponent;
import com.geniusplaza.android.di.UserListModule;
import com.geniusplaza.android.model.UserData;
import com.geniusplaza.android.view.UserListContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.LinearLayout.HORIZONTAL;

public class UserListActivity extends AppCompatActivity implements UserListContract.View {


    private UserListComponent daggerComponent;
    private UserListAdapter userListAdapter;

    @BindView(R.id.user_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_loader)
    ProgressBar progressBar;

    @BindView(R.id.error_text)
    TextView errorMessage;

    @Inject
    UserListContract.presenter presenter;

    @BindView(R.id.addUser)
    FloatingActionButton addUserBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        setUpDagger();
        setUpRecyclerView();
        presenter.getUserList();
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToUserRegistration();
            }
        });
    }

    private void setUpRecyclerView() {
        userListAdapter = new UserListAdapter(this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);
        recyclerView.setAdapter(userListAdapter);
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
    public void onUserListAvailable(UserData userData) {
        errorMessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        userListAdapter.setData(userData.getData());
    }

    @Override
    public void onUserListFetchError() {
        errorMessage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void navigateToUserRegistration() {
        Intent intent = new Intent(UserListActivity.this, UserRegisterActivity.class);
        UserListActivity.this.startActivity(intent);
    }

    private void setUpDagger() {
        daggerComponent = DaggerUserListComponent.builder()
                .userListModule(new UserListModule(this))
                .applicationComponent(GPApplication.getApplicationComponent())
                .build();

        daggerComponent.inject(this);
    }
}
