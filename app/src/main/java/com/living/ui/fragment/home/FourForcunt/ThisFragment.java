package com.living.ui.fragment.home.FourForcunt;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.living.adapter.home.ThisAdapter;
import com.living.bean.home.ConstellationBean;
import com.living.constant.home.HomeAPI;
import com.living.dagger.component.fragment.DaggerThisFragmentComponent;
import com.living.dagger.module.activity.ThisFragmentModule;
import com.living.presenter.activity.Te;
import com.living.presenter.fragment.ThisFragmentPresenter;
import com.living.ui.activity.HomeFortuneActivity;
import com.living.utils.ConstellationUtil;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by weihao on 2018/1/2.
 */


@SuppressLint("ValidFragment")
public class ThisFragment extends BaseTitleFragment {


    private List<ConstellationBean.ConstellationIcon> mIcons = new ArrayList<>();
    private int type;
    private AlertDialog mDialog;
    private ImageView mIamge;
    private TextView mName;
    private TextView mTv_change;
    private RecyclerView mThis_recycler;
    @Inject
    public ThisFragmentPresenter mThisFragmentPresenter;
    private String mConstellationName;
    private String mConNameT;

    @SuppressLint("ValidFragment")
    public ThisFragment(int type) {
        this.type = type;
    }



    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.fragment_homeforcunt_this, container, false);
        mIamge = view.findViewById(R.id.homeforcunt_constellation_image);
        mName = view.findViewById(R.id.homeforcunt_constellation_name);
        mTv_change = view.findViewById(R.id.homeforcunt_this_change);
        mThis_recycler = view.findViewById(R.id.homeforcunt_this_recycler);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //从HomeForcuntActivity获取设置的星座名
        HomeFortuneActivity homeFortuneActivity = (HomeFortuneActivity) getActivity();
        mConstellationName = homeFortuneActivity.getConstellationName();

        DaggerThisFragmentComponent.builder().thisFragmentModule(new ThisFragmentModule(this)).build().in(this);
        mThisFragmentPresenter.getConstellationData(type, mConstellationName, HomeAPI.ConstellationType[type], HomeAPI.Constellationkey);

        initConsteData(); //初始化星座数据
        mThis_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    public void setConsData(Te tBean) {

        List<Te> mConsBeanList = new ArrayList<>();
        mConsBeanList.add(tBean);
        if (mConNameT == null) {
            mThis_recycler.setAdapter(new ThisAdapter(mConsBeanList, type, mConstellationName, mIcons, this));
        } else {
            mThis_recycler.setAdapter(new ThisAdapter(mConsBeanList, type, mConNameT, mIcons, this));
        }
    }


    private void initConsteData() {

        addToList("水瓶座");
        addToList("双鱼座");
        addToList("白羊座");
        addToList("金牛座");
        addToList("双子座");
        addToList("巨蟹座");
        addToList("狮子座");
        addToList("处女座");
        addToList("天秤座");
        addToList("天蝎座");
        addToList("射手座");
        addToList("摩羯座");
    }

    private void addToList(String name) {
        ConstellationBean.ConstellationIcon icon = new ConstellationBean.ConstellationIcon(ConstellationUtil.getConstellationIcon(name), name);
        mIcons.add(icon);
    }

    public void getConstellationNameData(String conName) {
        mConNameT = conName;
        mThisFragmentPresenter.getConstellationData(type, conName, HomeAPI.ConstellationType[type], HomeAPI.Constellationkey);
    }
}


