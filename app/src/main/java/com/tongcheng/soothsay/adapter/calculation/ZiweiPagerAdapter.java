package com.tongcheng.soothsay.adapter.calculation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputConstant;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.List;

/**
 * Created by Steven on 16/11/25.
 */

public class ZiweiPagerAdapter extends FragmentPagerAdapter implements MPagerSlidingTabStrip.IconTabProvider{

    private int [] titleIcons;
    private String [] titles ;
    private List<BaseLazyFragment> fragments;

    private ZiweiUserBean inputBean;

    public ZiweiPagerAdapter(FragmentManager fm, List<BaseLazyFragment> fragments, String[] titles, int[] titleIcons, ZiweiUserBean inputBean) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        this.titleIcons = titleIcons;
        this.inputBean = inputBean;
    }

    @Override
    public Fragment getItem(int position) {
        inputBean.setGongWZIndex((position - 1)+"");
        BaseLazyFragment fragment = fragments.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ZiweiInputConstant.INTENT_INPUT,inputBean);
        bundle.putString(Constant.INTENT_DATA,""+position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getPageIconResId(int position) {
        return titleIcons[position];
    }
}
