package com.living.ui.fragment.home.WeChat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.home.WeChatAdapter;
import com.living.bean.home.JWeChatBean;

import com.living.constant.home.HomeAPI;


import com.living.dagger.component.fragment.DaggerWeChatFragmentComponent;
import com.living.dagger.module.fragment.WeChatFragmentModule;
import com.living.presenter.fragment.WeChatFragmentPresenter;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by weihao on 2018/1/3.
 */

@SuppressLint("ValidFragment")
public class WeChatFragment extends BaseTitleFragment {

    private static List<JWeChatBean.ResultBean.ListBean> mBeanList;
    private RecyclerView mRecycler_wechat;
    @Inject
    public WeChatFragmentPresenter mWeChatFragmentPresenter;
    private int channelid;

    @SuppressLint("ValidFragment")
    public WeChatFragment(int channelid) {
        this.channelid = channelid;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_wechat, container, false);
        mRecycler_wechat = view.findViewById(R.id.home_recyclerview_wechat);
        mRecycler_wechat.setLayoutManager(new LinearLayoutManager(getActivity())); //不能少
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        DaggerWeChatFragmentComponent.builder().weChatFragmentModule(new WeChatFragmentModule(this)).build().in(this);
        mWeChatFragmentPresenter.getJWeChatData(channelid, HomeAPI.JWeChatStart, HomeAPI.JWeChatNum, HomeAPI.JWeChatkey,this);
    }



    public void setData(List<JWeChatBean.ResultBean.ListBean> listBeans) {
        mBeanList = listBeans;
        mRecycler_wechat.setItemAnimator(null);
        mRecycler_wechat.setAdapter(new WeChatAdapter(channelid,listBeans, getActivity()));
    }

    public static List<JWeChatBean.ResultBean.ListBean> getListBeans() {
        return mBeanList;
    }
}
