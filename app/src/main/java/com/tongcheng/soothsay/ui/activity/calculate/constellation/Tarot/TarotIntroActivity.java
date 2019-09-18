package com.tongcheng.soothsay.ui.activity.calculate.constellation.Tarot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.popwindow.TarotTypePop;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;

/**
 * 塔罗牌简介
 */
public class TarotIntroActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.btn_tarot_type)           Button btnType;
    @BindView(R.id.btn_tarot_start)          Button btnStart;

    private TarotTypePop mPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot_intro);

        initView();
        initListener();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_tarot));
        getBaseHeadView().showBackButton(this);

        mPop = new TarotTypePop(this,R.layout.pop_tarot_type);

    }

    @Override
    public void initListener() {
        btnStart.setOnClickListener(this);
        btnType.setOnClickListener(this);

        mPop.setOnTypeSelectListener(new TarotTypePop.OnTypeSelectListener() {
            @Override
            public void onTypeSelect(String type) {
                btnType.setText(type);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (v.getId()){
            case R.id.headBackButton:
                finish();
                break;

            case R.id.btn_tarot_type:
                mPop.showPopOnRootView(this);
                break;

            case R.id.btn_tarot_start:
                gotoTarot();
                break;
        }
    }

    private void gotoTarot(){
        if(mPop.getType() == -1){
            ToastUtil.showShort(this,"请选择占卜类型");
            return ;
        }
        Intent intent = new Intent(this,TarotActivity.class);
        intent.putExtra(Constant.INTENT_DATA,mPop.getType());
        startActivity(intent);
    }
}
