package com.nomad.androidplus.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avos.avoscloud.AVUser;
import com.nomad.androidplus.R;
import com.nomad.androidplus.di.model.User;

public class SplashActivity extends BaseActivity {

    private static final int DELAY_TIME = 2000;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        setStatusBarTransparent();
        User currentUser = AVUser.getCurrentUser(User.class);
        if (currentUser == null) {
            navigateToLoginActivity();
        } else {
            navigateToMainActivity();
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void navigateToLoginActivity() {

    }

    private void navigateToMainActivity() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                navigateTo(MainActivity.class);
            }
        }, DELAY_TIME);
    }


}
