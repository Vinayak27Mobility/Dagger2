package com.geniusplaza.android.di;


import com.geniusplaza.android.view.impl.UserListActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = UserListModule.class)
public interface UserListComponent {
    void inject(UserListActivity userListActivity);
}
