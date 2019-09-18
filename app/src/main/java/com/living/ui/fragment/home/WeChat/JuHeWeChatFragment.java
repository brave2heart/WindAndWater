package com.living.ui.fragment.home.WeChat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.home.JuHeWeChatAdapter;
import com.living.bean.home.WeChatBean;

import com.living.constant.home.HomeAPI;
import com.living.dagger.component.fragment.DaggerJuHeWeChatFragmentComponent;
import com.living.dagger.module.fragment.JuHeWeChatFragmentModule;
import com.living.presenter.fragment.JuHeWeChatFragmentPresenter;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by weihao on 2018/1/5.
 */

public class JuHeWeChatFragment extends BaseTitleFragment {

    private RecyclerView mJuhe_recycler;

    @Inject
    public JuHeWeChatFragmentPresenter mJuHeWeChatFragmentPresenter;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//
//
//
//        return view;
//    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_wechat, container, false);
//        setWwChatAPIData(view, HomeAPI, HomeAPI.JWeChatStart, HomeAPI.JWeChatNum, HomeAPI.JWeChatkey);
        mJuhe_recycler = view.findViewById(R.id.home_recyclerview_wechat);
        mJuhe_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        DaggerJuHeWeChatFragmentComponent.builder().juHeWeChatFragmentModule(new JuHeWeChatFragmentModule(this)).build().in(this);

        mJuHeWeChatFragmentPresenter.getJuHeWeChatData(HomeAPI.WeChatPno, HomeAPI.WeChatPs, HomeAPI.WeChatkey,this);
    }

    public void setJuHeWeChatData(List<WeChatBean.ResultBean.ListBean> listBeans) {
        mJuhe_recycler.setAdapter(new JuHeWeChatAdapter(getActivity(), listBeans));

    }
}
