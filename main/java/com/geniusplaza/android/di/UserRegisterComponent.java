package com.geniusplaza.android.di;


import com.geniusplaza.android.view.impl.UserListActivity;
import com.geniusplaza.android.view.impl.UserRegisterActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = UserRegisterModule.class)
public interface UserRegisterComponent {
    void inject(UserRegisterActivity userRegisterActivity);
}
