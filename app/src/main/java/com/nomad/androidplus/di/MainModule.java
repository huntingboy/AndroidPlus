package com.nomad.androidplus.di;

import com.nomad.androidplus.ui.fragment.ArticleFragment;
import com.nomad.androidplus.ui.fragment.HomeFragment;
import com.nomad.androidplus.ui.fragment.MeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleFragment articleFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MeFragment meFragment();

}
