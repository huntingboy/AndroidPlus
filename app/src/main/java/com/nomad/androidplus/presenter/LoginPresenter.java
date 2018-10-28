package com.nomad.androidplus.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.nomad.androidplus.contract.LoginContract;
import com.nomad.androidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(String email, String password) {
        AVUser.logInInBackground(email, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    mView.onLoginSuccess();
                } else {
                    handleError(e.getCode());
                }
            }
        });
    }

    private void handleError(int code) {
        switch (code) {
            case AVException.USERNAME_PASSWORD_MISMATCH:
                mView.onUserNamePasswordMismatch();
                break;
            case AVException.USER_DOESNOT_EXIST:
                mView.onUserNameDoesNotExist();
                break;
            default:
                mView.onLoginFailed();
        }
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
