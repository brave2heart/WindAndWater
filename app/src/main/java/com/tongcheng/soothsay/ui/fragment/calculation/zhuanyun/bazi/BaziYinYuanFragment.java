package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziYinyuanBean;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziMainContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziYinYuanFragment extends BaseTitleFragment {


    @BindView(R.id.bazi_yinyuan_day_content)
    TextView mBaziYinyuanDayContent;
    @BindView(R.id.bazi_yinyuan_shishen_content)
    TextView mBaziYinyuanShishenContent;
    @BindView(R.id.bazi_yinyuan_details_content)
    TextView mBaziYinyuanDetailsContent;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_bazi_yinyuan, container, false);
        EventBus.getDefault().register(this);
        return view;
    }


    @Subscribe
    public void showBaziYinYuan(BaziYinyuanBean bean) {
        mBaziYinyuanShishenContent.setText(bean.getShiShen());
        mBaziYinyuanDayContent.setText(bean.getGanZhiDay());
        mBaziYinyuanDetailsContent.setText("      "+bean.getResult());
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    public void back(View view) {
        onBtnClick.onClick(view);
    }

    BaziMainContract.OnBtnClickListener onBtnClick;

    public void setOnBtnClick(BaziMainContract.OnBtnClickListener onBtnClick) {
        this.onBtnClick = onBtnClick;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }
}
