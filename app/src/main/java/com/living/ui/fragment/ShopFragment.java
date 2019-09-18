package com.living.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.mingli.RootFragmentRecyclerAdapter;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.dagger.component.fragment.DaggerShopFragmentComponent;
import com.living.dagger.module.fragment.ShopFragmentModule;
import com.living.presenter.fragment.ShopFragmentPresenter;
import com.living.utils.SpaceItemDecoration;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by weihao on 2018/1/9.
 */

public class ShopFragment extends BaseTitleFragment {


    @Inject
    public ShopFragmentPresenter mShopFragmentPresenter;
    private RecyclerView mMinglirecycler;


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
        getBaseHeadView().showTitle("商城");  //设置标题栏
        getBaseLoadingView().showLoading();
        DaggerShopFragmentComponent.builder().shopFragmentModule(new ShopFragmentModule(this)).build().in(this);
        mShopFragmentPresenter.getShopData();

//        //延时3秒隐藏正在加载动画
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                getBaseLoadingView().hideLoading();
//            }
//        }, 2000);

    }

    public void setShopData(List<RecyclerViewItemData> recyclerItemDatas) {
        //设置适配器
        mMinglirecycler.setAdapter(new RootFragmentRecyclerAdapter(recyclerItemDatas, this.getActivity()));
    }


}
