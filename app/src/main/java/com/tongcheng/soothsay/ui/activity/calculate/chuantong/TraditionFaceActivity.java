package com.tongcheng.soothsay.ui.activity.calculate.chuantong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.CalulationRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.widget.DividerItemDecoration;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan.BaziPaipanInputActivity;
import com.living.utils.GotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 传统命相
 */
public class TraditionFaceActivity extends BaseTitleActivity {

    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;
    private List<CliffordBean> mDatas;
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    //item 图片
    private int[] img = {
            R.drawable.ctsm_icon_zwds, R.drawable.ctsm_icon_cglm, R.drawable.ctsm_icon_bzpp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clifford);
        ButterKnife.bind(this);
        initData();
        initRecycleView();
    }

    @Override
    public void initData() {
        super.initView();
        mDatas = new ArrayList<>();
        title = getResources().getStringArray(R.array.tradition_face_title);
        content = getResources().getStringArray(R.array.tradition_face_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i], content[i]);
            mDatas.add(bean);
        }
//        mTitle.setText(getResources().getString(R.string.txt_contellation));

        getBaseHeadView().showTitle("八字测算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(TraditionFaceActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
//                    星座运程
                    case 0:
                        GotoUtil.GoToActivity(TraditionFaceActivity.this, ZiweiInputActivity.class);
                        break;
                    case 1:
                        GotoUtil.GoToActivity(TraditionFaceActivity.this, ChenGuActivity.class);
                        break;
                    case 2:
                        GotoUtil.GoToActivity(TraditionFaceActivity.this, BaziPaipanInputActivity.class);
                        break;
                    default:
                        Log.d("TraditionFaceActivity", "此功能未完善");

                }
            }
        });
    }
}
