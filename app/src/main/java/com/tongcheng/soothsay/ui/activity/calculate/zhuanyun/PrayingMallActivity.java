package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.OfferingsPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.OfferingsMallFragment;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/11/28.
 */
public class PrayingMallActivity extends AppCompatActivity {
    @BindView(R.id.tb_praying_mall)
    TabLayout mTbPrayingMall;
    @BindView(R.id.vp_parying_mall)
    ViewPager mVpParyingMall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praying_mall);
        ButterKnife.bind(this);
        initData();

    }

    public void initData() {
        List<BaseTitleFragment> fragments = new ArrayList<>();
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_flower, R.layout.item_praying_mall, 3));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_fruit, R.layout.item_praying_mall, 3));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_incense, R.layout.item_praying_mall, 3));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_oil, R.layout.item_praying_mall, 3));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_cup, R.layout.item_praying_mall, 3));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_table, R.layout.item_praying_mall_single, 1));
        fragments.add(getOfferingsMalFragment(R.string.gp_sort_screen, R.layout.item_praying_mall_single, 1));

        String[] titiles = {"供花", "供果", "供香", "灯油", "水杯", "供桌", "屏风"};
        OfferingsPagerAdapter offeringsPagerAdapter = new OfferingsPagerAdapter(this.getSupportFragmentManager(), fragments, titiles);
        mVpParyingMall.setAdapter(offeringsPagerAdapter);
        mTbPrayingMall.setTabMode(TabLayout.MODE_FIXED);
        mTbPrayingMall.setupWithViewPager(mVpParyingMall);

    }

//    public OfferingsMallFragment getOfferingsMalFragment(int offeringsName, int itemLayout, int type) {
//        String name = getResources().getString(offeringsName);
////        return getOfferingsMalFragment(name, itemLayout, type);
//    }

    public OfferingsMallFragment getOfferingsMalFragment(int offeringsName, int itemLayout, int type) {
        Bundle bundle = new Bundle();
        String sort = getResources().getString(offeringsName);
        bundle.putString(Constant.SORT, sort);
        bundle.putInt(Constant.ITEM_LAYOUT, itemLayout);
        bundle.putInt("type", type);
        OfferingsMallFragment offeringsMallFragment = new OfferingsMallFragment();
        offeringsMallFragment.setArguments(bundle);
        return offeringsMallFragment;
    }

    @OnClick(R.id.iv_back)
    public void onClick() {

        finish();
    }


}
