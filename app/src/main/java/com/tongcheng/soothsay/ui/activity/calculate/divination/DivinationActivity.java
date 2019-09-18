package com.tongcheng.soothsay.ui.activity.calculate.divination;

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
import com.living.utils.GotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by ALing on 2016/12/1 0001.
 * 占卜
 */
public class DivinationActivity extends BaseTitleActivity {
    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;

    private List<CliffordBean> mDatas;
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    //    item 图片
    private int[] img = {
            R.drawable.hdxlq_icon, R.drawable.gylq_icon, R.drawable.maq_icon, R.drawable.zgjm_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_clifford);
        initListener();
        initData();
        initRecycleView();
    }

    @Override
    public void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        title = getResources().getStringArray(R.array.divination_title);
        content = getResources().getStringArray(R.array.divination_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i], content[i]);
            mDatas.add(bean);
        }
//        mTv.setText(getResources().getString(R.string.txt_divination));
    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(DivinationActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    case 0://黄大仙灵签
                        GotoUtil.GoToActivity(DivinationActivity.this, HuangDaXianSignActivity.class);

                        break;
                    case 1://观音灵签
                        GotoUtil.GoToActivity(DivinationActivity.this, GuanYinSignActivity.class);

                        break;
                    case 2://妈祖签
                        GotoUtil.GoToActivity(DivinationActivity.this, MazuSignActivity.class);
                        break;
                    case 3://周公解梦
                        GotoUtil.GoToActivity(DivinationActivity.this, OneiromancyActivity.class);
                        break;

                }
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_divination));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
