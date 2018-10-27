package com.nomad.androidplus.contract;

public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess();

        void onLoginFailed();

        void onUserNamePasswordMismatch();

        void onUserNameDoesNotExist();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String email, String password);
    }

}
