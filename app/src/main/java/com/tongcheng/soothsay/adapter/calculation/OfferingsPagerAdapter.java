package com.tongcheng.soothsay.adapter.calculation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/29.
 * 供品 viewpager适配器
 */

public class OfferingsPagerAdapter extends FragmentStatePagerAdapter {
    List<BaseTitleFragment> mFragments;
    String[] mStrings;

    public OfferingsPagerAdapter(FragmentManager fm, List<BaseTitleFragment> fragments, String[] titiles) {
        super(fm);
        mFragments = fragments;
        mStrings = titiles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
