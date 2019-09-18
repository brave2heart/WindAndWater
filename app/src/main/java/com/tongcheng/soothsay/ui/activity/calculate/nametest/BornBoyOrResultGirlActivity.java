package com.tongcheng.soothsay.ui.activity.calculate.nametest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BornBoyOrGirl;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;

/**
 * Created by ALing on 2016/12/9 0009.
 */

public class BornBoyOrResultGirlActivity extends BaseTitleActivity {
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_des_huayu)
    TextView tvDesHuayu;
    @BindView(R.id.tv_huayu)
    TextView tvHuayu;
    @BindView(R.id.ll_huayu)
    LinearLayout llHuayu;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private BornBoyOrGirl bean;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_born_boys_girls_result);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_detial));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        bean = (BornBoyOrGirl) getIntent().getSerializableExtra("bean");
        sex = bean.getSex();
        if (bean != null) {
            if (sex.equals("1")) {
                ivSex.setImageResource(R.drawable.qmcz_snsn_nan);
                tvDesHuayu.setText("他的花语：");
                tvResult.setText("恭喜你，生的是一位王子");
            } else {
                ivSex.setImageResource(R.drawable.qmcz_snsn_nv);
                tvDesHuayu.setText("她的花语：");
                tvResult.setText("恭喜你，生的是一位公主");
            }

            tvHuayu.setText(bean.getHuayu());
        }
    }
}
