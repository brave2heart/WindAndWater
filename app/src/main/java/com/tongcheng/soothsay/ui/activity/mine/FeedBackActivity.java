package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;

import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;

import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;

import com.tongcheng.soothsay.ui.fragment.mine.MineFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/12/16 0016.
 * 意见反馈
 */

public class FeedBackActivity extends BaseTitleActivity {
    @BindView(R.id.et_feed_back)
    EditText etFeedBack;
    @BindView(R.id.btn_feed_back)
    Button btnFeedBack;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_feedback));
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
        if (UserManager.getInstance().isLogin()){
            token = UserManager.getInstance().getToken();
        }else {
            startActivity(new Intent(FeedBackActivity.this,LoginActivity.class));
        }

    }

    @OnClick(R.id.btn_feed_back)
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;
        if (TextUtils.isEmpty(etFeedBack.getText())){
            ToastUtil.showShort(FeedBackActivity.this,"请提出您的宝贵意见");
        }else {
            submitFeedBack();
        }
        WindowUtil.closeInputMethod(this);
    }

    private void submitFeedBack() {
        getBaseLoadingView().showLoading();
        AllApi.getInstance().getFeedBack(token,etFeedBack.getText().toString()).enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                ToastUtil.showShort(FeedBackActivity.this,"感谢您的宝贵意见");
                finish();
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.showHaveTokenError(FeedBackActivity.this, errorCode);
                if (b == false){
                    ToastUtil.showShort(FeedBackActivity.this,message);
                }
            }
        }));
    }
}
