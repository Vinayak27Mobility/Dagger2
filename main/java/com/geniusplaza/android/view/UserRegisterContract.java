package com.geniusplaza.android.view;

public interface UserRegisterContract {
    interface View {
        void onUserRegisterSuccess();

        void onUserRegisterError();
    }

    interface presenter {
        void registerUser(String userName, String userRole);
    }
}
