package com.tongcheng.soothsay.ui.activity.calculate.constellation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.HoroscopAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.constellation.HoroscopeConstant;
import com.tongcheng.soothsay.ui.fragment.calculation.constellation.HoroscopeFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.constellation.HoroscopeMonthFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.constellation.HoroscopeTomorrowFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.constellation.HoroscopeWeekFragment;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ALing on 2016/11/25 0025.
 * 星座运程
 */

public class HoroscopeActivity extends BaseTitleActivity {

    @BindView(R.id.tabLayout_main)
    TabLayout mTabLayoutMain;
    @BindView(R.id.viewPager_main)
    ViewPager mViewPagerMain;

    private HoroscopeConstant.Presenter mPresenter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_horoscope);

        initData();
        initViewPager();

    }

    private void initViewPager() {
        String[] arrTabTitles = getResources().getStringArray(R.array.horoscope_title);

            HoroscopeFragment todayFragment = HoroscopeFragment.getInstance();
            fragments.add(todayFragment);
            HoroscopeTomorrowFragment tomorrowFragment = HoroscopeTomorrowFragment.getInstance();
            fragments.add(tomorrowFragment);
            HoroscopeWeekFragment todayFragment1 = HoroscopeWeekFragment.getInstance();
            fragments.add(todayFragment1);
            HoroscopeMonthFragment tomorrowFragment1 = HoroscopeMonthFragment.getInstance();
            fragments.add(tomorrowFragment1);

            mViewPagerMain.setAdapter(new HoroscopAdapter(getSupportFragmentManager(), fragments, arrTabTitles));
    }

    @Override
    public void initData() {
        super.initData();
        mTabLayoutMain.setupWithViewPager(mViewPagerMain);
        getBaseHeadView().showTitle(getResources().getString(R.string.title_horoscope));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

}

