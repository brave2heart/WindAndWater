package com.living.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.adapter.mingli.RootFragmentRecyclerAdapter;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.dagger.component.fragment.DaggerClassRoomFragmentComponent;
import com.living.dagger.module.fragment.ClassRoomFragmentModule;
import com.living.presenter.fragment.ClassRoomFragmentPresenter;
import com.living.presenter.fragment.RootFragmentPresenter;
import com.living.utils.SpaceItemDecoration;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by weihao on 2018/1/9.
 */

public class ClassRoomFragment extends BaseTitleFragment {


    @Inject
    public ClassRoomFragmentPresenter mClassRoomFragmentPresenter;
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
        getBaseHeadView().showTitle("学堂");  //设置标题栏
//      DaggerRootFragmentComponent.builder().rootFragmentModule(new RootFragmentModule(this, RootType.CLASSROOM)).build().in(this);
        DaggerClassRoomFragmentComponent.builder().classRoomFragmentModule(new ClassRoomFragmentModule(this)).build().in(this);
        mClassRoomFragmentPresenter.getClassRoomData();
    }

    public void setClassRoomData(List<RecyclerViewItemData> recyclerItemDatas) {
        //设置适配器
        mMinglirecycler.setAdapter(new RootFragmentRecyclerAdapter(recyclerItemDatas, this.getActivity()));
    }
}
