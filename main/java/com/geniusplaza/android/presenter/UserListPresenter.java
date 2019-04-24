package com.geniusplaza.android.presenter;


import com.geniusplaza.android.model.UserData;
import com.geniusplaza.android.network.ServiceHelper;
import com.geniusplaza.android.view.UserListContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UserListPresenter implements UserListContract.presenter {

    private UserListContract.View view;
    private ServiceHelper serviceHelper;

    public UserListPresenter(UserListContract.View view, ServiceHelper serviceHelper) {
        this.view = view;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public void getUserList() {
        Call<UserData> userCall = serviceHelper.getUserList();

        userCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Timber.d("response for user list is ", response);
                view.onUserListAvailable(response.body());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Timber.e("doInBackground: Something went wrong fetching data", t.getMessage());
                view.onUserListFetchError();
            }
        });
    }
}
