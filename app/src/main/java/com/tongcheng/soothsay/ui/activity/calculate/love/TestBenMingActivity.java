package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.HeHun;
import com.tongcheng.soothsay.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 本命卦象合婚、吕才合婚结果页
 */

public class TestBenMingActivity extends BaseTitleActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_analysis)
    TextView tvAnalysis;
    @BindView(R.id.tv_man_g)
    TextView tvManG;
    @BindView(R.id.tv_woman_g)
    TextView tvWomanG;
    @BindView(R.id.analysis)
    TextView analysis;
    @BindView(R.id.tv_boy_g)
    TextView tvBoyG;
    @BindView(R.id.tv_girl_g)
    TextView tvGirlG;
    private HeHun mHeHun;
    private String mResult;
    private String mJiexi;
    private String manG;
    private String womanG;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testbenming);
        ButterKnife.bind(this);
        initData();
        initView();
        setDate();


    }

    @Override
    public void initView() {
        super.initView();
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        if (type.equals("1")){
            getBaseHeadView().showTitle(getResources().getString(R.string.title_lv_cai_gua));
            tvManG.setVisibility(View.GONE);
            tvManG.setVisibility(View.GONE);
            tvBoyG.setVisibility(View.GONE);
            tvGirlG.setVisibility(View.GONE);
        }else {
            getBaseHeadView().showTitle(getResources().getString(R.string.title_benming_gua));
        }
    }

    @Override
    public void initData() {
        super.initData();
        mHeHun = (HeHun) getIntent().getSerializableExtra("bean");
        mResult = mHeHun.getResult();
        mJiexi = mHeHun.getJieXi();
        manG = mHeHun.getManG();
        womanG = mHeHun.getWomanG();
        type = mHeHun.getType();
        LogUtil.printD(mResult);
    }

    private void setDate() {
        if (type.equals("0")){  //本命卦象合婚
            tvResult.setText(mResult);
            tvAnalysis.setText(mJiexi);
            tvManG.setText(manG);
            tvWomanG.setText(womanG);
        }else {     //吕才合婚
            tvResult.setText(mResult);
            tvAnalysis.setText(mJiexi);

        }

    }
}
