package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.BaziLiuNianAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.living.utils.GotoUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Gubr on 2016/12/13.
 */

public class BaziLiuNianActivity extends BaseTitleActivity {

    private ListView mListview;
    private BaziPaipanBean mBean;
    private BaziLiuNianAdapter mBaziLiuNianAdapter;
    private List<BaziPaipanBean.LiuNianBean> mLiuNian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);
        initView();
        initData();
        initListener();


    }



    @Override
    public void initView() {
        mListview = (ListView) findViewById(R.id.listview);
        getBaseHeadView().showTitle("大运流年");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void initData() {
        mBean = (BaziPaipanBean) getIntent().getSerializableExtra("bean");
        mLiuNian = mBean.getLiuNian();
        mBaziLiuNianAdapter = new BaziLiuNianAdapter(this, mLiuNian, R.layout.item_bazi_dayunliunian_head, R.layout.item_bazi_dayunliunian);
        mListview.setAdapter(mBaziLiuNianAdapter);
    }

    @Override
    public void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaziPaipanBean.LiuNianBean bean = (BaziPaipanBean.LiuNianBean)((BaziLiuNianAdapter.ViewHolder) view.getTag()).getTag();
                String shenSha = bean.getShenSha();
                if (TextUtils.isEmpty(shenSha)){
                    return;
                }
                String replace = shenSha.replace(" ", ",");
                HashMap<String, String> map = new HashMap<>();
                map.put("shenSha",replace);
                GotoUtil.GoToActivityWithData(BaziLiuNianActivity.this,BaziLiuNianDetailActivity.class,map);
            }
        });
    }
}
