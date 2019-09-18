package com.living.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.living.adapter.mingli.RootFragmentItemRecyclerViewAdapter;
import com.living.bean.mingli.T;
import com.living.bean.mingli.layout.RecyclerViewItemData;
import com.living.bean.xuetang.Mv;
import com.living.constant.root.RootType;
import com.living.constant.xuetang.MVType;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

public class MVListActivity extends BaseTitleActivity {


    private int mTitle;
    private int mTitleType;
    private RecyclerView mRecycler;
    private List<RecyclerViewItemData> mRecyclerViewItemData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvlist);

        initView();
        initData();
    }

    @Override
    public void initView() {

        //根据传过来的type，设置headvie的标题
        Intent intent = getIntent();
        mTitleType = intent.getIntExtra("titleType", 0);

        if (mTitleType == MVType.JINGXUAN) {
            mTitle = R.string.title_xuetang_jingxuan;
        } else if (mTitleType == MVType.REMEN) {
            mTitle = R.string.title_xuetang_remen;
        } else if (mTitleType == MVType.TEJIA) {
            mTitle = R.string.title_xuetang_tejia;
        } else if (mTitleType == MVType.HAOPING) {
            mTitle = R.string.title_xuetang_haoping;
        } else if (mTitleType == MVType.ZHUANLAN) {
            mTitle = R.string.title_xuetang_zhuanlan;
        } else if (mTitleType == MVType.KECHENG) {
            mTitle = R.string.title_xuetang_kecheng;
        }
        getBaseHeadView().showTitle(getString(mTitle));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化控件
        mRecycler = (RecyclerView) findViewById(R.id.mv_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {

        List<T> mvs = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Mv mv1 = new Mv(
                    R.drawable.xuetang_item_wudao,
                    "2018全年运势——生肖龙",
                    "桃桃喜",
                    "时长 09:12",
                    "试看",
                    "1.8万学习",
                    "￥199",
                    "￥79"
            );
            mvs.add(mv1);
            Mv mv2 = new Mv(
                    R.drawable.xuetang_kecheng_kanxiang,
                    "神奇的面相识人术（已完结）",
                    "1351.TV",
                    "已更新13期",
                    "专栏",
                    "331.8万学习",
                    "￥199",
                    "￥9.9"
            );
            mvs.add(mv2);
            Mv mv3 = new Mv(
                    R.drawable.xuetang_kecheng_shengxiao,
                    "2018生肖属相全年运势大揭秘",
                    "桃桃喜",
                    "已更新12期",
                    "试看",
                    "111.8万学习",
                    "￥299",
                    "￥69"
            );
            mvs.add(mv3);
        }


        mRecycler.setAdapter(new RootFragmentItemRecyclerViewAdapter(mvs, RootType.MVITEM, this));
    }
}