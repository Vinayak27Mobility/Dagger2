package com.geniusplaza.android.di;

import com.geniusplaza.android.GPApplication;
import com.geniusplaza.android.network.ServiceHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(GPApplication gpApplication);

    ServiceHelper getServiceHelper();
}
