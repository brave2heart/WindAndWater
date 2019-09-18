package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.base.BaziMingYunAdapter;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziMainContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziXianTianPanFragment extends BaseTitleFragment {


    @BindView(R.id.listview)
    ListView mListview;
    private BaziMingYunAdapter mAdapter;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_bazi_mingyun, container, false);
        EventBus.getDefault().register(this);

        return view;
    }


    @Subscribe
    public void showBaziXianTianPan(List<BaziSzshishenBean> bean) {

//        这个需要写一个listView?

//            mTextView16.setText(bean.getJieLun());
//            mTextView15.setText(bean.getShiShen());
//            mTextView14.setText(bean.getSiZhu());

        if (mAdapter==null){
            mAdapter = new BaziMingYunAdapter(getActivity(), bean, R.layout.fragment_bazi_mingyun_item);
            mListview.setAdapter(mAdapter);
        }else{
            mAdapter.notifyChangeData(bean);
        }



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
