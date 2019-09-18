package com.living.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.living.ui.fragment.home.FourForcunt.ThisFragment;

/**
 * Created by weihao on 2017/12/14.
 */

public class GeRenFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"明天", "本周", "本月", "本年"};

    public GeRenFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        return new ThisFragment(position + 1);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
