package com.nomad.androidplus.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.nomad.androidplus.contract.RegisterContract;
import com.nomad.androidplus.data.model.User;

import javax.inject.Inject;

public class RegisterPresenter implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void takeView(RegisterContract.View view) {

    }

    @Override
    public void dropView() {

    }

    @Override
    public void register(String email, String password) {
        final User user = new User();
        user.setUsername(email);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {

            }
        });
    }
}
