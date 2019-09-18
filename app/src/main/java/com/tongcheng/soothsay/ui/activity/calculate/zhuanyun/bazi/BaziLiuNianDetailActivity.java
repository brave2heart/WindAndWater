package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.BaziShenShaAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShenShaBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.BaZiApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bozhihuatong on 2016/12/14.
 */
public class BaziLiuNianDetailActivity extends BaseTitleActivity {

    @BindView(R.id.listview)
    ListView mListview;

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
        getBaseHeadView().showTitle("神煞简介");
        String shenSha = getIntent().getStringExtra("shenSha");
        getBaseLoadingView().showLoading();
        BaZiApi.getInstance().getShensha(shenSha).enqueue(new ApiCallBack<ApiResponseBean<List<BaziShenShaBean>>>(new BaseCallBack<List<BaziShenShaBean>>() {

            @Override
            public void onSuccess(List<BaziShenShaBean> data) {
                getBaseLoadingView().hideLoading();
                mListview.setAdapter(new BaziShenShaAdapter(BaziLiuNianDetailActivity.this,data,R.layout.item_bazi_shensha_intro));
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                Toast.makeText(BaziLiuNianDetailActivity.this, "网络出错", Toast.LENGTH_SHORT).show();

            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }


}
