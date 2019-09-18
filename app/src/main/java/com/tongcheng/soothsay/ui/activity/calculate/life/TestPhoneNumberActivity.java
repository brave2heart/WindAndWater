package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.CheckPhoneUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.RegexUtils;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ALing
 * 手机号码测算
 */

public class TestPhoneNumberActivity extends BaseTitleActivity {

    @BindView(R.id.tv_testnumber_introduce)
    TextView tvTestphonenumber;
    @BindView(R.id.et_testnumber)
    BootstrapEditText etNumber;
    @BindView(R.id.btn_testnumber)
    Button btnNumber;
    @BindView(R.id.tv_res_title)
    TextView textView4;
    @BindView(R.id.tv_cesuan_response)
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_testnumber);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("手机测算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestPhoneNumberActivity.this.finish();
            }
        });

    }

    @OnClick(R.id.btn_testnumber)
    public void onClick() {
        if (ClickUtil.isFastClick()) {
            return;
        }
        String phonenumber = etNumber.getText().toString();
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort(this, "手机号码不能为空");
        } else if (!RegexUtils.isMobileSimple(etNumber.getText().toString())) {
            ToastUtil.showShort(this, "请输入正确手机号");
        }
      /*  else if (CheckPhoneUtil.isPhoneNum(phonenumber)){
//            去请求数据
            getPhoneNumberMesg(phonenumber);
        }*/
        else {
            getPhoneNumberMesg(phonenumber);

//            ToastUtil.showShort(this,"手机号码格式不正确");
        }

    }

    private void getPhoneNumberMesg(String phonenumber) {
        getBaseLoadingView().showLoading(true);
        AllApi.getInstance().getNumberMsg(phonenumber).enqueue(new ApiCallBack<ApiResponseBean<NumberTestBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                NumberTestBean bean = (NumberTestBean) data;
                LogUtil.printD("bean" + bean);
                tvResponse.setText(bean.getResult());
                WindowUtil.closeInputMethod(TestPhoneNumberActivity.this);
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(TestPhoneNumberActivity.this, errorCode);
                if (b == false)
                    ToastUtil.showShort(TestPhoneNumberActivity.this, message);
            }
        }));
    }
}
