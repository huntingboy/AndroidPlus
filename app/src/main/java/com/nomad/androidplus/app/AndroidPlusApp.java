package com.nomad.androidplus.app;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.nomad.androidplus.BuildConfig;
import com.nomad.androidplus.di.DaggerAppComponent;
import com.nomad.androidplus.di.model.User;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AndroidPlusApp extends DaggerApplication {
//    private static final String APP_ID = "GpMDDutUfnxMUOfs0A2unyH6-gzGzoHsz";
//    private static final String APP_KEY = "1tDAhYiKKG8DRao6aOK2D8UD";
    private static final String APP_ID = "e23RXU0ywb3H2hfHgPbmAL3s-gzGzoHsz";
    private static final String APP_KEY = "Hy5zNJf0I4oOAwUA7k7sf1CN";

    @Override
    public void onCreate() {
        super.onCreate();
        initSubClasses();
        AVOSCloud.initialize(this, APP_ID, APP_KEY);
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
    }

    private void initSubClasses() {
        AVObject.registerSubclass(User.class);
        AVUser.alwaysUseSubUserClass(User.class);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }
}
