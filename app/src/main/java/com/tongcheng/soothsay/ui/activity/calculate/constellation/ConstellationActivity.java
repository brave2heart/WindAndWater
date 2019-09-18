package com.tongcheng.soothsay.ui.activity.calculate.constellation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.CalulationRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.widget.DividerItemDecoration;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.tongcheng.soothsay.ui.activity.calculate.constellation.Tarot.TarotIntroActivity;
import com.living.utils.GotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ALing on 2016/11/25 0025.
 * 星座塔罗
 */


public class ConstellationActivity extends BaseTitleActivity {
    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;
    private List<CliffordBean> mDatas;
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    //item 图片
    private int[] img = {
            R.drawable.xztl_icon_xzyc, R.drawable.xztl_icon_ssj, R.drawable.god_5, R.drawable.god_3,
            R.drawable.god_13, R.drawable.god_7, R.drawable.god_7};

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
        title = getResources().getStringArray(R.array.constellation_title);
        content = getResources().getStringArray(R.array.constellation_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i], content[i]);
            mDatas.add(bean);
        }
//        mTitle.setText(getResources().getString(R.string.txt_contellation));

        getBaseHeadView().showTitle(getResources().getString(R.string.title_contellation));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(ConstellationActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
//                    星座运程
                    case 0:
                        GotoUtil.GoToActivity(ConstellationActivity.this, HoroscopeActivity.class);
                        break;
                    case 1:
                        GotoUtil.GoToActivity(ConstellationActivity.this, TarotIntroActivity.class);
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;

                }
            }
        });
    }
}

