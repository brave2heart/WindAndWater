package com.tongcheng.soothsay.adapter.calculation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.PrayingStationFragment;

import java.util.List;


/**
 * @description: 适配器
 * @author: lijuan
 * @date: 2016-10-31
 * @time: 14:18
 *
 * 神仙fragment的适合器
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public List<PrayingStationFragment> list;
    private List<String> titles;

    public FragmentAdapter(FragmentManager fm, List<PrayingStationFragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }


/**
     * 返回显示的Fragment总数
     */

    @Override
    public int getCount() {
        return list.size();
    }

/**
     * 返回要显示的Fragment的某个实例
     */

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


    public void removeItem(int position) {
        list.remove(position);
        titles.remove(position);
        notifyDataSetChanged();
    }


    public void notifyDataChange(List<PrayingStationFragment> list, List<String> titles){
        this.list.clear();
        this.list.addAll(list);
        this.titles.clear();
        this.titles.addAll(titles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
