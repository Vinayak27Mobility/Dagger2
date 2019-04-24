package com.geniusplaza.android.network;

import com.geniusplaza.android.model.UserData;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ServiceHelper {

    private UserAPI userAPI;

    public ServiceHelper(UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    public Call<UserData> getUserList() {
        return userAPI.getUserList();
    }

    public Call<ResponseBody> registerUser(String userName, String userRole) {
        return userAPI.registerUser(userName, userRole);
    }
}
