package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/12/13 0013.
 * 妈祖求签介绍
 */

public class MazuSignActivity extends BaseTitleActivity {
    @BindView(R.id.tv_mazu_desc)
    TextView tvMazuDesc;
    @BindView(R.id.btn_divination)
    Button btnDivination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mazu_sign);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_mazusign));
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
        GotoUtil.GoToActivity(this,MazuSignStartActivity.class);
    }
}
