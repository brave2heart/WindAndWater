package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.RequestImmortalViewPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.BodhisattvaFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.TaoismImmortalFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/3.
 * 请仙
 */

public class RequestImmortalActivity extends BaseTitleActivity {


    @BindView(R.id.vp_immortal)
    ViewPager vp;
    @BindView(R.id.pss_tab_immortal)
    MPagerSlidingTabStrip pssTab;

    private ArrayList<Fragment> container;

    private Fragment taoismImmortalFragment;//道教
    private Fragment bodhisattvaFragment;//佛教

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_request_immortal);
        initBaseHeadView();
        initData();
    }

    private void initBaseHeadView() {
        getBaseHeadView();
        getBaseHeadView().showTitle("请仙");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestImmortalActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        String[] datas = {"道教神仙", "佛教菩萨"};
        container = new ArrayList<>();
        taoismImmortalFragment = new TaoismImmortalFragment();
        bodhisattvaFragment = new BodhisattvaFragment();
        container.add(taoismImmortalFragment);
        container.add(bodhisattvaFragment);
        vp.setAdapter(new RequestImmortalViewPagerAdapter(getSupportFragmentManager(), this.container, datas));
        pssTab.setViewPager(vp);
    }
}
