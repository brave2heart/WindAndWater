package com.tongcheng.soothsay.ui.activity.calculate.chuantong;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ChengGu;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class ChengGuResultActivity extends BaseTitleActivity {

    @BindView(R.id.img_cheng)
    ImageView imgCheng;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_cg_song)
    TextView tvCgSong;
    @BindView(R.id.tv_man)
    TextView tvMan;
    @BindView(R.id.tv_femal)
    TextView tvFemal;
    @BindView(R.id.minglun)
    TextView minglun;
    @BindView(R.id.tv_minglun)
    TextView tvMinglun;
    private ChengGu bean;
    private String mMan;
    private String mFemal;
    private String mMing;
    private String mLiang;
    private String mQian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheng_gu_result);
        ButterKnife.bind(this);
        initView();
        initData();
        setDate();

    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getResources().getString(R.string.title_chengu_inputv2));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void setDate() {
        tvResult.setText("您的骨骼重" + mLiang + "两" + mQian + "钱");
        tvMan.setText(mMan);
        tvFemal.setText(mFemal);
        tvMinglun.setText(mMing);
    }

    @Override
    public void initData() {
        super.initData();
        bean = (ChengGu) getIntent().getSerializableExtra("bean");
        List<String> result = bean.getResult();
        mQian = result.get(1);
        mLiang = result.get(0);
        mMan = bean.getNan();
        mFemal = bean.getNv();
        mMing = bean.getMing();
        LogUtil.printD(mMing);
    }


}
