package com.nomad.androidplus.ui.fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nomad.androidplus.R;
import com.nomad.androidplus.adapter.HomePagerAdapter;
import com.nomad.androidplus.di.ActivityScoped;
import com.nomad.androidplus.ui.activity.AddQuestionActivity;

import javax.inject.Inject;

@ActivityScoped
public class HomeFragment extends BaseMainFragment {

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void init() {
        super.init();
        mToolBar.inflateMenu(R.menu.menu_home);
        mToolBar.setOnMenuItemClickListener(mOnMenuItemClickListener);
    }
    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            startActivity(new Intent(getContext(), AddQuestionActivity.class));
            return true;
        }
    };

    @Override
    protected PagerAdapter getPagerAdapter() {
        return new HomePagerAdapter(getChildFragmentManager(),
                getResources().getStringArray(R.array.home_category));
    }
}
