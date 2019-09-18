package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.HoroscopAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.fragment.calculation.life.WishingSquareFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ALing on 2016/12/5 0005.
 * 许愿灯广场
 */

public class WishingSquareActivity extends BaseTitleActivity {

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    /*@BindView(R.id.tabLayout_square)
    TabLayout tabLayoutSquare;*/
    @BindView(R.id.vp_wishing_square)
    ViewPager vpWishingSquare;
    @BindView(R.id.tabLayout_square)
    MPagerSlidingTabStrip tabLayoutSquare;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_wishing_square);
        initListener();
        initData();
//        initViewPager();
    }

    private void initViewPager() {
        String[] arrTabTitles = getResources().getStringArray(R.array.wishing_title);
        for (int i = 0; i < arrTabTitles.length; i++) {
            WishingSquareFragment fragment = WishingSquareFragment.getInstance(i);
            fragments.add(fragment);
        }

        vpWishingSquare.setAdapter(new HoroscopAdapter(getSupportFragmentManager(), fragments, arrTabTitles));
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_wishing_square));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        String[] arrTabTitles = getResources().getStringArray(R.array.wishing_title);
        for (int i = 0; i < arrTabTitles.length; i++) {
            WishingSquareFragment fragment = WishingSquareFragment.getInstance(i);
            fragments.add(fragment);
        }
        vpWishingSquare.setAdapter(new HoroscopAdapter(getSupportFragmentManager(),
                this.fragments, arrTabTitles));
        tabLayoutSquare.setViewPager(vpWishingSquare);

        int tag = getIntent().getIntExtra(Constant.INTENT_DATA,0);
        vpWishingSquare.setCurrentItem(tag);
    }

}
