package com.nomad.androidplus.ui.fragment;

import android.support.v4.view.PagerAdapter;

import com.nomad.androidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class ArticleFragment extends BaseMainFragment {
    @Inject
    public ArticleFragment() {
    }

    @Override
    protected PagerAdapter getPagerAdapter() {
        return null;
    }
}
