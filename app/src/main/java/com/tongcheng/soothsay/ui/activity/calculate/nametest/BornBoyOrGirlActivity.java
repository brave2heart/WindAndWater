package com.tongcheng.soothsay.ui.activity.calculate.nametest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BornBoyOrGirl;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.NameTestApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/12/1 0001.
 */

public class BornBoyOrGirlActivity extends BaseTitleActivity {
    @BindView(R.id.tv_choose_year)
    AwesomeTextView tvChooseYear;
    @BindView(R.id.rl_choose_year)
    RelativeLayout rlChooseYear;
    @BindView(R.id.tv_choose_month)
    AwesomeTextView tvChooseMonth;
    @BindView(R.id.rl_choose_month)
    RelativeLayout rlChooseMonth;
    @BindView(R.id.btn_cesuan)
    Button btnCesuan;

    private AlertDialog mDialog;
    private String mYeartOfBirth, mMonthOfYear;

    //    1969-1996
    private String[] yearsArr = new String[]{"1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
            "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1988", "1989",
            "1990", "1991", "1992", "1993", "1994", "1995", "1996"};

    private String[] monthArr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_born_boys_or_girl);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_boys_or_girls));
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

    }

    @OnClick({R.id.tv_choose_year, R.id.tv_choose_month, R.id.btn_cesuan})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_choose_year:
                showChooseDialog(yearsArr, tvChooseYear);
                break;
            case R.id.tv_choose_month:
                showChooseDialog(monthArr, tvChooseMonth);
                break;
            case R.id.btn_cesuan:
                ceSuan();
                break;
        }
    }

    private void ceSuan() {
        if (TextUtils.isEmpty(mYeartOfBirth) && TextUtils.isEmpty(mMonthOfYear)) {
            ToastUtil.showShort(this, "请选择年份、月份");
            return;
        }else if (TextUtils.isEmpty(mYeartOfBirth)){
            ToastUtil.showShort(this, "请选择年份");
            return;
        }else if (TextUtils.isEmpty(mMonthOfYear)){
            ToastUtil.showShort(this, "请选择月份");
            return;
        }else {
            getBaseLoadingView().showLoading(true);
            HashMap<String, String> data = new HashMap<String ,String>();
            data.put("year",mYeartOfBirth);
            data.put("month",mMonthOfYear);
            NameTestApi.getInstance().getBoyOrGirl(data).
                    enqueue(new ApiCallBack<ApiResponseBean<BornBoyOrGirl>>(new BaseCallBack() {

                        @Override
                        public void onSuccess(Object data) {
                            getBaseLoadingView().hideLoading();
                            BornBoyOrGirl bean = (BornBoyOrGirl) data;
                            bean.setSex(bean.getSex());
                            bean.setHuayu(bean.getHuayu());
                            GotoUtil.GoToActivityWithBean(BornBoyOrGirlActivity.this,BornBoyOrResultGirlActivity.class,bean);
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            getBaseLoadingView().hideLoading();
                            boolean b = ErrorCodeUtil.showHaveTokenError(BornBoyOrGirlActivity.this, errorCode);
                            if (b == false){
                                ToastUtil.showShort(BornBoyOrGirlActivity.this,message);
                            }
                        }
                    }));
        }
    }

    private void showChooseDialog(final String[] arr, final TextView view) {
        mDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(arr, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (view.getId() == R.id.tv_choose_year) {
                                    view.setText(arr[which].toString());
                                    mYeartOfBirth = arr[which].toString();
                                } else if (view.getId() == R.id.tv_choose_month) {
                                    view.setText(arr[which].toString());
                                    mMonthOfYear = arr[which].toString();
                                }
                            }
                        }
                )
                .setCancelable(true)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }
}
