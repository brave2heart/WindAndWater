package com.tongcheng.soothsay.adapter.calculation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.bean.calculation.NewsTypeBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputConstant;
import com.tongcheng.soothsay.ui.fragment.classroom.news.NewFragment;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.List;


/**
 * 资讯适合器
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    private List<NewsTypeBean> titles;

//    private String [] titles ;
    private List<NewFragment> fragments;

    public NewsPagerAdapter(FragmentManager fm, List<NewFragment> fragments, List<NewsTypeBean> titles ) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getTypeName();
    }


}
