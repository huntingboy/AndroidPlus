package com.nomad.androidplus.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nomad.androidplus.ui.fragment.DynamicFragment;
import com.nomad.androidplus.ui.fragment.HotQuestionFragment;
import com.nomad.androidplus.ui.fragment.QuestionFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final String[] mTitles;

    public HomePagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: return new QuestionFragment();
            case 1: return new HotQuestionFragment();
            case 2: return new DynamicFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
