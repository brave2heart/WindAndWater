package com.tongcheng.soothsay.ui.activity.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.mine.OrderStatusViewPagerFragmentAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.ui.fragment.mine.AllOrderStatusFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/25.
 * 我的订单界面
 */

public class MineOrderActivity extends BaseTitleActivity {

    @BindView(R.id.pss_tab_order)
    MPagerSlidingTabStrip pssTabOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    private ArrayList<Fragment> container;

    private Fragment allOrderStatusFragment;//全部


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mine_order);
        initBaseHeadView();
        initData();
    }

    private void initBaseHeadView() {
        getBaseHeadView().showTitle("我的订单");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MineOrderActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        String[] datas = {"未付款",  "已付款"};
        container = new ArrayList<>();
        //理论上来说后台应该只是根据一个字段判断，所以这边只要用for循环然后把这个字段传过去即可
        for (int i = 0; i < datas.length; i++) {
            allOrderStatusFragment = AllOrderStatusFragment.getInstance(i);
           /* Bundle bundle = new Bundle();
            bundle.putString("status", datas[i]);
            allOrderStatusFragment.setArguments(bundle);*/
            container.add(allOrderStatusFragment);
        }
        vpOrder.setAdapter(new OrderStatusViewPagerFragmentAdapter(getSupportFragmentManager(),
                this.container, datas));
        pssTabOrder.setViewPager(vpOrder);

        int tag = getIntent().getIntExtra(Constant.INTENT_DATA,0);
        vpOrder.setCurrentItem(tag);

    }

}
