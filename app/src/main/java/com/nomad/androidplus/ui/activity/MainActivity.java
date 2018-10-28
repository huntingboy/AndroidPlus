package com.nomad.androidplus.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.nomad.androidplus.R;
import com.nomad.androidplus.event.ScrollEvent;
import com.nomad.androidplus.ui.fragment.ArticleFragment;
import com.nomad.androidplus.ui.fragment.HomeFragment;
import com.nomad.androidplus.ui.fragment.MeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;

public class MainActivity extends BaseActivity {

    @Inject
    Lazy<HomeFragment> homeFragmentProvider;

    @Inject
    Lazy<ArticleFragment> articleFragmentProvider;

    @Inject
    Lazy<MeFragment> meFragmentProvider;

    @BindView(R.id.fragment_frame)
    FrameLayout mFragmentFrame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.main_home);

        EventBus.getDefault().register(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switchPage(item.getItemId());
            return true;
        }

        private void switchPage(int itemId) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (itemId) {
                case R.id.main_home:
                    fragmentTransaction.replace(R.id.fragment_frame, homeFragmentProvider.get());
                    break;
                case R.id.main_article:
                    fragmentTransaction.replace(R.id.fragment_frame, articleFragmentProvider.get());
                    break;
                case R.id.main_me:
                    fragmentTransaction.replace(R.id.fragment_frame, meFragmentProvider.get());
                    break;
            }
            fragmentTransaction.commit();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onScrollChange(ScrollEvent scrollEvent) {
        if (scrollEvent.getDirection() == ScrollEvent.Direction.UP) {
            hideNavigationView();
        } else {
            showNavigationView();
        }
    }

    private void showNavigationView() {
        animationNavigationView(mBottomNavigation.getHeight(), 0);
    }

    private void hideNavigationView() {
        animationNavigationView(0, mBottomNavigation.getHeight());
    }

    private void animationNavigationView(float from ,float to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBottomNavigation, "translationY",
                from, to);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}
