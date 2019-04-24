package com.geniusplaza.android;

import android.app.Application;
import com.geniusplaza.android.di.ApplicationComponent;
import com.geniusplaza.android.di.ApplicationModule;
import com.geniusplaza.android.di.DaggerApplicationComponent;
import com.geniusplaza.android.di.NetworkModule;

import timber.log.Timber;

public class GPApplication extends Application {

    private static GPApplication gpApplication;
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //Dagger
        setUpDagger();

        //Timber
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private static ApplicationComponent getDaggerComponent() {
        return applicationComponent;
    }


    public static ApplicationComponent getApplicationComponent() {
        return gpApplication.getDaggerComponent();
    }

    private void setUpDagger() {
        gpApplication = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .networkModule(new NetworkModule())
                .build();
        applicationComponent.inject(this);
    }
}
