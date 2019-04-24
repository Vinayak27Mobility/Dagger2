package com.geniusplaza.android.view;

import com.geniusplaza.android.model.UserData;

public interface UserListContract {
    interface View {
        void onUserListAvailable(UserData userData);

        void onUserListFetchError();

        void navigateToUserRegistration();
    }

    interface presenter {
        void getUserList();
    }
}
