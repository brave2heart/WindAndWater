package com.living.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.living.ui.fragment.home.WeChat.JuHeWeChatFragment;
import com.living.ui.fragment.home.WeChat.WeChatFragment;

/**
 * Created by weihao on 2017/12/12.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    //    , "感情", "八卦", "美食", "养生", "星座", "搞笑", "历史", "段子", "热点", "体育", "旅游"
//    头条，社会，国内，娱乐，体育，军事，科技，财经，时尚
//    private String[] mTitles = new String[]{"精选", "头条", "社会", "国内", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    public static String[] mTitles = new String[]{"精选", "热门", "推荐", "段子", "养生", "私房话", "八卦", "爱生活", "财经", "汽车", "科技",
            "潮人", "辣妈", "旅行", "点赞党", "美食", "职场", "学霸族", "星座", "体育", "古今通"};


    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new JuHeWeChatFragment();
        } else {
            return new WeChatFragment(position);
        }

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
