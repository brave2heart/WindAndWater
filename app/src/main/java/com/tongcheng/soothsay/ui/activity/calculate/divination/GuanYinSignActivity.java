package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import butterknife.OnClick;


/**
 * 观音求签介绍
 */

public class GuanYinSignActivity extends BaseTitleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_guanyin_sign);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.gylq_app_name));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    @OnClick(R.id.btn_divination)
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        GotoUtil.GoToActivity(this,GuanYinSignStartActivity.class);
    }
}
