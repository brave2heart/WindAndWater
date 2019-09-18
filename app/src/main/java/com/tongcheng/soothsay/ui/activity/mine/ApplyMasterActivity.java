package com.tongcheng.soothsay.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/12/5.
 * 申请成为大师
 */

public class ApplyMasterActivity extends BaseTitleActivity {
    @BindView(R.id.btn_apply_master)
    BootstrapButton btnApplyMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_apply_master);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("申请认证须知");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplyMasterActivity.this.finish();
            }
        });
    }

    @OnClick(R.id.btn_apply_master)
    public void onClick() {
        if (ClickUtil.isFastClick()) return;

        GotoUtil.GoToActivity(this, ApplyMasterResultActivity.class);
        finish();
    }
}
