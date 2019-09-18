package com.tongcheng.soothsay.adapter.calculation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ALing on 2016/11/25 0025.
 */

public class HoroscopAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments = null;
    private String[] tabTitle = null;


    public HoroscopAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
