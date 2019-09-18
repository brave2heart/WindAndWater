package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan.BaZiImp;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan.BaziPaipanInputConstant;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipanActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi.BaziMainFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi.BaziShiYeFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi.BaziXianTianPanFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi.BaziXinggeFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi.BaziYinYuanFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.HashMap;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziMainActivity extends BaseTitleActivity implements BaziMainContract.BaZiMainView, BaziMainContract.OnBtnClickListener {


    private BaziMainFragment mBaziMainFragment;
    private BaziShiYeFragment mBaziShiYeFragment;
    private BaziXianTianPanFragment mBaziXianTianPanFragment;
    private BaziXinggeFragment mBaziXinggeFragment;
    private BaziYinYuanFragment mBaziYinYuanFragment;
    private BaziMainContract.BaziMainPresener mBaziMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazimain);
        initView();
        initListener();
        initData();
//        map.put("year", temp[0]);
//        map.put("month", temp[1]);
//        map.put("day", temp[2]);
//        map.put("hour", temp[3]);
//        map.put("name", name);
//        map.put("sex", sex);


    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle("八字");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                if (getCurrentFragment() != mBaziMainFragment) {
                    getBaseHeadView().showTitle("八字");
                    switchFragment(mBaziMainFragment, R.id.fragment);
                } else {
                    finish();
                }
            }
        });
        mBaziMainFragment = new BaziMainFragment();
        mBaziShiYeFragment = new BaziShiYeFragment();
        mBaziXianTianPanFragment = new BaziXianTianPanFragment();
        mBaziXinggeFragment = new BaziXinggeFragment();
        mBaziYinYuanFragment = new BaziYinYuanFragment();


        mBaziXianTianPanFragment.setOnBtnClick(this);
        mBaziXinggeFragment.setOnBtnClick(this);
        mBaziYinYuanFragment.setOnBtnClick(this);
        mBaziShiYeFragment.setOnBtnClick(this);
        switchFragment(mBaziShiYeFragment, R.id.fragment);
        switchFragment(mBaziXianTianPanFragment, R.id.fragment);
        switchFragment(mBaziXinggeFragment, R.id.fragment);
        switchFragment(mBaziYinYuanFragment, R.id.fragment);
        switchFragment(mBaziMainFragment, R.id.fragment);

        mBaziMainPresenter = new BaziMainPresenter(new BaZiImp(), this);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        String year = getIntent().getStringExtra("year");
        String month = getIntent().getStringExtra("month");
        String day = getIntent().getStringExtra("day");
        String hour = getIntent().getStringExtra("hour");
        String name = getIntent().getStringExtra("name");
        String sex = getIntent().getStringExtra("sex");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("year",year);
        stringStringHashMap.put("month",month);
        stringStringHashMap.put("day",day);
        stringStringHashMap.put("hour",hour);
        stringStringHashMap.put("name",name);
        stringStringHashMap.put("sex",sex);
        mBaziMainPresenter.setMap(stringStringHashMap);
    }

    @Override
    public void showYinyun() {
        getBaseHeadView().showTitle("八字婚姻");
        switchFragment(mBaziYinYuanFragment);
    }

    @Override
    public void showXingge() {
        getBaseHeadView().showTitle("八字性格");
        switchFragment(mBaziXinggeFragment);
    }

    @Override
    public void showShiye() {
        getBaseHeadView().showTitle("八字事业");
        switchFragment(mBaziShiYeFragment);
    }


    @Override
    public void showXiantiammingpan() {
        getBaseHeadView().showTitle("八字命运");
        switchFragment(mBaziXianTianPanFragment);
    }

    @Override
    public void showPaipan(BaziPaipanBean bean) {

    }


    @Override
    public void showPaipan(BaziPaipanBean bean, BaziUserBean inputBean) {
        Intent intent = new Intent(this,BaziPaipanActivity.class);
        intent.putExtra(Constant.INTENT_DATA,bean);
        intent.putExtra(BaziPaipanInputConstant.INTENT_INPUT,inputBean);
        startActivity(intent);
    }

    @Override
    public void showPaipan(BaziPaipanBean data, HashMap<String, String> map) {


    }


    public void switchFragment(Fragment fragment) {
        switchFragment(fragment, R.id.fragment);
    }


    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.getGoodsList();
            }
        });
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, info);
    }

    @Override
    public void showEmpty() {
        getBaseEmptyView().showEmptyContent();
    }

    @Override
    public void showTitle(String title) {
getBaseHeadView().showTitle(title);
    }

    @Override
    public void showLoad() {
getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {

    }




    public BaziMainContract.BaziMainPresener getBaziMainPresenter() {
        return mBaziMainPresenter;
    }

    //    这是给fragment返回用的。
    @Override
    public void onClick(View view) {

        switchFragment(mBaziMainFragment);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getCurrentFragment() != mBaziMainFragment) {
                getBaseHeadView().showTitle("八字");
                switchFragment(mBaziMainFragment, R.id.fragment);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void setPresenter(BaziMainContract.BaziMainPresener presenter) {
        mBaziMainPresenter=presenter;
    }
}
