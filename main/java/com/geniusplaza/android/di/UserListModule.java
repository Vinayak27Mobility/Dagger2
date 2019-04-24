package com.geniusplaza.android.di;

import com.geniusplaza.android.network.ServiceHelper;
import com.geniusplaza.android.presenter.UserListPresenter;
import com.geniusplaza.android.view.UserListContract;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListModule {
    private UserListContract.View view;

    public UserListModule(UserListContract.View mainView) {
        this.view = mainView;
    }

    @Provides
    @ActivityScope
    UserListContract.presenter providePresenter(ServiceHelper serviceHelper) {
        return new UserListPresenter(view, serviceHelper);
    }
}
