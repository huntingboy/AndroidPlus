package com.nomad.androidplus.di;

import com.nomad.androidplus.ui.activity.LoginActivity;
import com.nomad.androidplus.ui.activity.MainActivity;
import com.nomad.androidplus.ui.activity.RegisterActivity;
import com.nomad.androidplus.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    @ActivityScoped
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract RegisterActivity registerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();


}
