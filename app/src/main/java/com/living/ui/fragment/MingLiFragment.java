package com.living.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.mingli.RootFragmentRecyclerAdapter;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.dagger.component.fragment.DaggerMingLiFragmentComponent;
import com.living.dagger.module.fragment.MingLiFragmentModule;
import com.living.presenter.fragment.MingLiFragmentPresenter;
import com.living.utils.SpaceItemDecoration;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by weihao on 2018/1/9.
 */


public class MingLiFragment extends BaseTitleFragment {


    private RecyclerView mMinglirecycler;

    @Inject
    public MingLiFragmentPresenter mLiFragmentPresenter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mingli, container, false);
        mMinglirecycler = view.findViewById(R.id.mingli_recycler);
        mMinglirecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mMinglirecycler.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        return view;
    }

    @Override
    public void initData() {

        //设置标题栏
        getBaseHeadView().showTitle("命理");
        //大师推荐数据
        //getListInfo();
//       DaggerRootFragmentComponent.builder().rootFragmentModule(new RootFragmentModule(this, RootType.MINGLI)).build().in(this);
        DaggerMingLiFragmentComponent.builder().mingLiFragmentModule(new MingLiFragmentModule(this)).build().in(this);

        mLiFragmentPresenter.getMingLiData();

    }

    public void setMingLiData(List<RecyclerViewItemData> recyclerItemDatas) {

        //设置适配器
        mMinglirecycler.setAdapter(new RootFragmentRecyclerAdapter(recyclerItemDatas, this.getActivity()));
    }

}

