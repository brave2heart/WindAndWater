package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import butterknife.OnClick;


/**
 * 黄大仙求签介绍
 */

public class HuangDaXianSignActivity extends BaseTitleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_huangdaxian_sign);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.hdxlq_app_name));
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
        GotoUtil.GoToActivity(this,HuangDaXianSignStartActivity.class);
    }
}
