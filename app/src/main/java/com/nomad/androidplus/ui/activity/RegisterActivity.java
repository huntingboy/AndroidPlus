package com.nomad.androidplus.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.nomad.androidplus.R;
import com.nomad.androidplus.contract.RegisterContract;
import com.nomad.androidplus.presenter.RegisterPresenter;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity
        implements RegisterContract.View, View.OnFocusChangeListener {

    @Inject
    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegisterFailed() {

    }

    @Override
    public void onUserNameTaken() {

    }
}
