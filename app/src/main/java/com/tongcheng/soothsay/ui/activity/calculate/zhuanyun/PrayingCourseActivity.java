package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.RequestImmortalViewPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.RealizeWishFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 祈福历程
 */

public class PrayingCourseActivity extends BaseTitleActivity {


    @BindView(R.id.vp_immortal)
    ViewPager vp;
    @BindView(R.id.pss_tab_immortal)
    MPagerSlidingTabStrip pssTab;

    private ArrayList<Fragment> container;

    private Fragment mRealizeWishFragment;//现求愿望
//    private Fragment bodhisattvaFragment;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_request_immortal);
        initBaseHeadView();
        initData();
    }

    private void initBaseHeadView() {
        getBaseHeadView();
        getBaseHeadView().showTitle("祈福历程");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PrayingCourseActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        String[] datas = {"现求愿望"/*, "实现愿望"*/};
        container = new ArrayList<>();
        mRealizeWishFragment = new RealizeWishFragment();
//        bodhisattvaFragment = new BodhisattvaFragment();
        container.add(mRealizeWishFragment);
//        container.add(bodhisattvaFragment);
        vp.setAdapter(new RequestImmortalViewPagerAdapter(getSupportFragmentManager(), this.container, datas));
        pssTab.setViewPager(vp);
    }
}
