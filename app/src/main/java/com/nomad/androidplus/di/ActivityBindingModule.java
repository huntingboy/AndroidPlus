package com.nomad.androidplus.di;

import com.nomad.androidplus.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    @ActivityScoped
    abstract SplashActivity splashActivity();
}
