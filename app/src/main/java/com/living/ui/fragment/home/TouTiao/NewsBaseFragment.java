package com.living.ui.fragment.home.TouTiao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.home.TopFragmentAdapter;
import com.living.bean.home.TopBean;


import com.living.dagger.component.fragment.DaggerNewsBaseFragmentComponent;
import com.living.dagger.module.fragment.NewsBaseFragmentModule;
import com.living.presenter.NewsBaseFragmentPresenter;
import com.tongcheng.soothsay.R;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by weihao on 2017/12/18.
 */

public class NewsBaseFragment extends Fragment {

    private RecyclerView mRecyclerview;
//    public List<TopBean.ResultBean.DataBean> mData;

    @Inject
    public NewsBaseFragmentPresenter mNewsBaseFragmentPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_toutiao, container, false);

        return view;
    }

    public View initView(View view, int recyclerview, String type, String key) {
//
        DaggerNewsBaseFragmentComponent.builder().newsBaseFragmentModule(new NewsBaseFragmentModule(this)).build().in(this);


        mRecyclerview = view.findViewById(recyclerview);
        mNewsBaseFragmentPresenter.getData(type, key);


        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }


    public void setData(List<TopBean.ResultBean.DataBean> data) {
        mRecyclerview.setAdapter(new TopFragmentAdapter(getActivity(), data));
    }
}

