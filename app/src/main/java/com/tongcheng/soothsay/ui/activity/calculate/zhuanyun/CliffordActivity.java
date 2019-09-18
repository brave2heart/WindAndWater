package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.CalulationRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;
import com.tongcheng.soothsay.widget.DividerItemDecoration;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.living.utils.GotoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @description: 命理推算-祈福转运
 * @author: lijuan
 * @date: 2016-10-25
 * @time: 15:20
 */
public class CliffordActivity extends BaseTitleActivity {
    private static final String TAG = "CliffordActivity";
    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;
    private ListView listview;
    private List<CliffordBean> mDatas = new ArrayList<>();
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    private int[] img = {R.drawable.funyun_icon, R.drawable.qifu_ic_launcher, R.drawable.qfzy_icon_xys,
            R.drawable.kyzy_icon, R.drawable.bmf_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_clifford);
        getBaseHeadView().showTitle("祈福转运");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CliffordActivity.this.finish();
            }
        });
        initData();
        initRecycleView();
    }

    @Override
    public void initData() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = getResources().getStringArray(R.array.qfzy_title);
        content = getResources().getStringArray(R.array.qfzy_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i], content[i]);
            mDatas.add(bean);
        }
//        mTv.setText(getResources().getString(R.string.txt_qfzy));
    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(CliffordActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //大德符运
                if (position == 0)
                    GotoUtil.GoToActivity(CliffordActivity.this, VirtueTransferActivity.class);

                //祈福台
                if (position == 1)
                    GotoUtil.GoToActivity(CliffordActivity.this, PrayingStationActivity.class);

                //许愿树
                if (position == 2)
                    GotoUtil.GoToActivity(CliffordActivity.this, WishTreeActivity.class);

                //开运转运
                if (position == 3)
                    gotoWebView(6,title[3]);

                //本命佛
                if (position == 4)
                    gotoWebView(2,title[4]);
            }
        });
    }

    private void gotoWebView(int pos, String title){
        String temp = Constant.Url.BASE_URL + "html/sale.html?sortId=%d";
        String url = String.format(Locale.CHINA,temp,pos);

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("web_url",url);
        intent.putExtra("web_title",title);
        startActivity(intent);
    }


    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
