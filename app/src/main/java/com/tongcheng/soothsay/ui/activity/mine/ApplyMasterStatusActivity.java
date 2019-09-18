package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 宋家任 on 2016/12/13.
 * 审核状态界面
 */

public class ApplyMasterStatusActivity extends BaseTitleActivity {
    @BindView(R.id.tv_master_status)
    TextView tvMasterStatus;
    @BindView(R.id.tv_apply_master_reason)
    TextView tvApplyMasterReason;
    @BindView(R.id.btn_again_master)
    BootstrapButton btnAgainMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_apply_master_status);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplyMasterStatusActivity.this.finish();
            }
        });
        getBaseHeadView().showTitle("审核状态");
        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        if ("0".equals(status)) {//审核中
            tvMasterStatus.setText("审核中");
            Drawable topDrawable = getResources().getDrawable(R.drawable.sqds_sh_zhong);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            tvMasterStatus.setCompoundDrawables(null, topDrawable, null, null);
            getBaseHeadView().showTitle("审核中");
        } else if ("1".equals(status)) {//审核通过
            tvMasterStatus.setText("审核通过");
            Drawable topDrawable = getResources().getDrawable(R.drawable.sqds_sh_gou);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            tvMasterStatus.setCompoundDrawables(null, topDrawable, null, null);
            getBaseHeadView().showTitle("审核通过");
        } else if ("2".equals(status)) {//审核不通过
            tvMasterStatus.setText("审核不通过");
            Drawable topDrawable = getResources().getDrawable(R.drawable.sqds_sh_cha);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            tvMasterStatus.setCompoundDrawables(null, topDrawable, null, null);
            getBaseHeadView().showTitle("审核不通过");
            String reason = intent.getStringExtra("reason");
            tvApplyMasterReason.setVisibility(View.VISIBLE);
            tvApplyMasterReason.setText(reason);
            btnAgainMaster.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_again_master)
    public void onClick() {
        if (ClickUtil.isFastClick()) return;

        GotoUtil.GoToActivity(this, ApplyMasterResultActivity.class);
        finish();
    }
}
