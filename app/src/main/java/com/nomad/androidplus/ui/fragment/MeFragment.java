package com.nomad.androidplus.ui.fragment;

import com.nomad.androidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class MeFragment extends BaseFragment {

    @Inject
    public MeFragment() {
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
