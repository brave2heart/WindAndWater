package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.RegexUtils;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 手机号码测算
 * Created by bozhihuatong on 2016/11/23.
 */

public class TestQQActivityV2 extends BaseTitleActivity {

    @BindView(R.id.tv_testnumber_introduce)
    TextView tvTestnumber;
    @BindView(R.id.et_testnumber)
    BootstrapEditText etNumber;
    @BindView(R.id.btn_testnumber)
    Button btnNumber;
    @BindView(R.id.tv_res_title)
    TextView textView4;
    @BindView(R.id.tv_cesuan_response)
    TextView tvResponse;
    @BindView(R.id.tv_hintinput)
    TextView tvHintinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_testnumber);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("QQ测算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestQQActivityV2.this.finish();
            }
        });
        tvTestnumber.setText(R.string.test_id_introduce);
        tvHintinput.setText("请输入QQ号码");

    }

    @OnClick(R.id.btn_testnumber)
    public void onClick() {
        if (ClickUtil.isFastClick()) return;

        String idNumber = etNumber.getText().toString();
        if (TextUtils.isEmpty(idNumber)) {
            ToastUtil.showShort(this, "QQ号码不能为空");
        } else if (RegexUtils.isQQ(idNumber)) {
//            去请求数据
            getNumberMesg(idNumber);
        } else {
            ToastUtil.showShort(this, "QQ号码格式不正确");
        }

    }

    private void getNumberMesg(String number) {
        AllApi.getInstance().getNumberMsg(number).enqueue(new ApiCallBack<ApiResponseBean<NumberTestBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                NumberTestBean bean = (NumberTestBean) data;
                LogUtil.printD("bean" + bean);
                tvResponse.setText(bean.getResult());
            }

            @Override
            public void onError(String errorCode, String message) {
                Toast.makeText(TestQQActivityV2.this, message, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
