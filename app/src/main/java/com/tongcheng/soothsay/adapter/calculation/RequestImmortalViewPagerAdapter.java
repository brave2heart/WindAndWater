package com.tongcheng.soothsay.adapter.calculation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 宋家任 on 2016/11/3.
 * 请仙适配器
 */

public class RequestImmortalViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> container = new ArrayList<>();
    private static String[] data;

    public RequestImmortalViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> container, String[] data) {
        super(fm);
        this.container = container;
        this.data = data;
    }


    @Override
    public Fragment getItem(int position) {
        return container.get(position);
    }

    @Override
    public int getCount() {
        return container.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}
