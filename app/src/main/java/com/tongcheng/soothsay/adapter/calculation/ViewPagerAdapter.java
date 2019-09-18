package com.tongcheng.soothsay.adapter.calculation;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @description: 命理推算中GridView模块的适配器
 * @author: lijuan
 * @date: 2016-10-30
 * @time: 16:55
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}