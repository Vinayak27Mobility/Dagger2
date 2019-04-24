package com.geniusplaza.android.network;


import com.geniusplaza.android.model.UserData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {
    @GET("users")
    Call<UserData> getUserList();

    @POST("users")
    @FormUrlEncoded
    Call<ResponseBody> registerUser(@Field("name") String userName,
                                    @Field("job") String userRole);
}
