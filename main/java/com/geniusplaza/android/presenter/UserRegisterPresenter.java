package com.geniusplaza.android.presenter;


import com.geniusplaza.android.network.ServiceHelper;
import com.geniusplaza.android.view.UserRegisterContract;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UserRegisterPresenter implements UserRegisterContract.presenter {

    private UserRegisterContract.View view;
    private ServiceHelper serviceHelper;

    public UserRegisterPresenter(UserRegisterContract.View view, ServiceHelper serviceHelper) {
        this.view = view;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public void registerUser(String userName, String userRole) {
        Call<ResponseBody> userCall = serviceHelper.registerUser(userName, userRole);

        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Timber.d("response for user register ", response);
                if (response.code() == 200) {
                    view.onUserRegisterSuccess();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Timber.e("doInBackground: Something went wrong fetching data", t.getMessage());
                view.onUserRegisterError();
            }
        });
    }
}
