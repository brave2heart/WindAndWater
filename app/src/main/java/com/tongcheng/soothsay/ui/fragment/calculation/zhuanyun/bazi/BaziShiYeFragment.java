package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShiyeBean;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziMainContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziShiYeFragment extends BaseTitleFragment {

    @BindView(R.id.bazi_shiye_zhiye_title)
    TextView mBaziShiyeZhiyeTitle;
    @BindView(R.id.bazi_shiye_zhiye_content)
    TextView mBaziShiyeZhiyeContent;
    @BindView(R.id.bazi_shiye_fangwei_title)
    TextView mBaziShiyeFangweiTitle;
    @BindView(R.id.bazi_shiye_fangwei_content)
    TextView mBaziShiyeFangweiContent;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_bazi_shiye, container, false);
        EventBus.getDefault().register(this);


        return view;
    }


    @Subscribe
    public void showBaziShiYe(BaziShiyeBean bean) {
        mBaziShiyeFangweiContent.setText("      "+bean.getFangwei());
        mBaziShiyeZhiyeContent.setText("      "+bean.getZhiYe());
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
}
