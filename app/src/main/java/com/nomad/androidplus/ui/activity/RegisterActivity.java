package com.nomad.androidplus.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nomad.androidplus.R;
import com.nomad.androidplus.app.Constant;
import com.nomad.androidplus.contract.RegisterContract;
import com.nomad.androidplus.presenter.RegisterPresenter;
import com.nomad.androidplus.utils.RegexUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity
        implements RegisterContract.View, View.OnFocusChangeListener {

    @Inject
    RegisterPresenter mRegisterPresenter;
    @BindView(R.id.user_name)
    TextInputEditText mUserName;
    @BindView(R.id.user_name_input_layout)
    TextInputLayout mUserNameInputLayout;
    @BindView(R.id.password)
    TextInputEditText mPassword;
    @BindView(R.id.password_input_layout)
    TextInputLayout mPasswordInputLayout;
    @BindView(R.id.confirm_password)
    TextInputEditText mConfirmPassword;
    @BindView(R.id.confirm_password_input_layout)
    TextInputLayout mConfirmPasswordInputLayout;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.progress_layout)
    LinearLayout mProgressLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        super.init();

        mUserName.setOnFocusChangeListener(this);
        mPassword.setOnFocusChangeListener(this);
        mConfirmPassword.setOnFocusChangeListener(this);

        mConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                register();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRegisterPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterPresenter.dropView();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.user_name:
                mUserNameInputLayout.setErrorEnabled(false);
                break;
            case R.id.password:
                mPasswordInputLayout.setErrorEnabled(false);
                break;
            case R.id.confirm_password:
                mConfirmPasswordInputLayout.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void onRegisterSuccess() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_USER_NAME, mUserName.getText().toString().trim());
        intent.putExtra(Constant.EXTRA_PASSWORD, mPassword.getText().toString().trim());
        setResult(Constant.RESULT_CODE, intent);
        finish();
    }

    @Override
    public void onRegisterFailed() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.register_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNameTaken() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.user_name_taken), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        hideSoftKeyboard();
        register();
    }

    private void register() {
        hideSoftKeyboard();
        String username = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        if (checkUserName(username)) {
            if ((checkPassword(password))) {
                if (confirmPassword.equals(password)) {
                    mProgressLayout.setVisibility(View.VISIBLE);
                    mConfirmPasswordInputLayout.setErrorEnabled(false);
                    mRegisterPresenter.register(username, password);//开始注册
                } else {
                    mConfirmPasswordInputLayout.setErrorEnabled(true);
                    mConfirmPasswordInputLayout.setError(getString(R.string.error_confirm_password));
                }
            }
        }
    }

    private boolean checkPassword(String password) {
        if (password.length() == 0) {
            mPasswordInputLayout.setErrorEnabled(true);
            mPasswordInputLayout.setError(getString(R.string.error_password_empty));
            return false;
        } else if (!RegexUtils.isValidPassword(password)) {
            mPasswordInputLayout.setErrorEnabled(true);
            mPasswordInputLayout.setError(getString(R.string.error_password));
            return false;
        }
        return true;
    }

    private boolean checkUserName(String username) {
        if (username.length() == 0) {
            mUserNameInputLayout.setErrorEnabled(true);
            mUserNameInputLayout.setError(getString(R.string.error_user_name_empty));
            return false;
        } else if (!RegexUtils.isValidUserName(username)) {
            mUserNameInputLayout.setErrorEnabled(true);
            mUserNameInputLayout.setError(getString(R.string.error_user_name));
            return false;
        }
        return true;
    }

}
