package com.tongcheng.soothsay.adapter.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by 宋家任 on 2016/11/25.
 * 订单状态滑动适配器
 */

public class OrderStatusViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> container = new ArrayList<>();
    private static String[] data;

    public OrderStatusViewPagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> container, String[] data) {
        super(fm);
        this.container = container;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return container == null ? null : container.get(position);
    }

    @Override
    public int getCount() {
        return container == null ? 0 : container.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}
