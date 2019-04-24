package com.geniusplaza.android.di;

import com.geniusplaza.android.network.ServiceHelper;
import com.geniusplaza.android.network.UserAPI;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AndroidSupportInjectionModule.class)
public class
NetworkModule {

    @Provides
    @Singleton
    ServiceHelper provideServiceHelper(UserAPI userAPI) {
        return new ServiceHelper(userAPI);
    }

    @Provides
    @Singleton
    UserAPI provideUserApi() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15, TimeUnit.SECONDS);
        client.writeTimeout(15, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        return retrofit.create(UserAPI.class);
    }
}
