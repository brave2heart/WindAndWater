package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.bazi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziMainActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziMainContract;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.RoundImageView;

import java.util.HashMap;

import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziMainFragment extends BaseTitleFragment {

    private static final String TAG = "MVP";

    RoundImageView mImgBaziSexIcon;
    TextView mTvBaziName;
    TextView mTvBaziSex;
    TextView mTvBaziBirthday;
    private BaziMainContract.BaziMainPresener mBaziMainPresenter;
    private RoundImageView mRoundImageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }




    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_bazimain, container, false);
        mBaziMainPresenter = ((BaziMainActivity) getActivity()).getBaziMainPresenter();
        mImgBaziSexIcon = (RoundImageView) view.findViewById(R.id.img_bazi_main_icon);
        mTvBaziName=(TextView)view.findViewById(R.id.tv_bazi_main_name);
        mTvBaziBirthday=(TextView)view.findViewById(R.id.tv_bazi_main_birthday);
        mTvBaziSex=(TextView)view.findViewById(R.id.tv_bazi_main_sex);


        return view;
    }

    @Override
    public void initData() {
        setUseView();
        super.initData();
    }

    public void setUseView(){

        HashMap<String, String> map = mBaziMainPresenter.getMap();
        mImgBaziSexIcon.setImageResource(map.get("sex").equals("1")?R.drawable.ctmx_ziwei_touxiang_man:R.drawable.ctmx_ziwei_touxiang_woman);
        mTvBaziName.setText("姓名： "+map.get("name"));
        mTvBaziSex.setText("性别： "+(map.get("sex").equals("1")?"男":"女"));
        mTvBaziBirthday.setText("阳历： "+map.get("year") + "年" + map.get("month") + "月" + map.get("day") + "日" + map.get("hour") + "时");
    }


    @OnClick({R.id.btn_shiye, R.id.btn_yinyuan, R.id.btn_xiantianmingpan, R.id.btn_xingge, R.id.btn_paipan,R.id.btn_switchover})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_shiye:
                mBaziMainPresenter.getShiye();
                break;
            case R.id.btn_yinyuan:
                mBaziMainPresenter.getYinyun();
                break;
            case R.id.btn_xiantianmingpan:
                mBaziMainPresenter.getXiantianmingpan();
                break;
            case R.id.btn_xingge:
                mBaziMainPresenter.getXingge();
                break;
            case R.id.btn_paipan:
                mBaziMainPresenter.getPaipan();
                break;
            case R.id.btn_switchover:
                getActivity().finish();
                break;
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
