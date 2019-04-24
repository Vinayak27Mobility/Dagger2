package com.geniusplaza.android.di;

import com.geniusplaza.android.network.ServiceHelper;
import com.geniusplaza.android.presenter.UserRegisterPresenter;
import com.geniusplaza.android.view.UserRegisterContract;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRegisterModule {
    private UserRegisterContract.View view;

    public UserRegisterModule(UserRegisterContract.View mainView) {
        this.view = mainView;
    }

    @Provides
    @ActivityScope
    UserRegisterContract.presenter providePresenter(ServiceHelper serviceHelper) {
        return new UserRegisterPresenter(view, serviceHelper);
    }
}
