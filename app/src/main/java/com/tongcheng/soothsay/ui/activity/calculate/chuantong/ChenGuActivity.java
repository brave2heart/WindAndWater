package com.tongcheng.soothsay.ui.activity.calculate.chuantong;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ChengGu;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.chuantongApi;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by Administrator on 2016/11/21.
 * */


public class ChenGuActivity extends BaseTitleActivity implements TimePickerView.OnTimeSelectListener {


    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;
    @BindView(R.id.btn_cesuan)
    Button btnCesuan;
    private TimePickerView timeView;
    private String time = "1900.08.08";
    private AlertDialog mDialog;

    private int index = 0;
    private String[] timeData = new String[]{"时辰不清楚", "子时23点", "子时0点", "丑时1点", "丑时2点", "寅3点", "寅时4点", "卯时5点",
            "卯时6点", "辰时7点", "辰时8点", "巳时9点", "巳时10点", "午时11点", "午时12点", "未时13点", "未时14点", "申时15点", "申时16点",
            "酉时17点", "酉时18点", "戌时19点", "戌时20点", "亥时21点", "亥时22点"};

    private HashMap<String, String> date;
    private String mYear;
    private String mMonth;
    private String mDay;
    private String mHour;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chengu_input);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_chengu_input));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @OnClick({
            R.id.tv_choose_date,
            R.id.btn_cesuan})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_choose_date:
                showTimePicker();
                break;
            case R.id.btn_cesuan:
                ceSuan();
                break;

        }
    }

    private void ceSuan() {
        if (TextUtils.isEmpty(tvChooseDate.getText())) {
            ToastUtil.showShort(this, "请选择日期时辰");
            return;
        }
        getBaseLoadingView().showLoading(true);
        LogUtil.printD("ceSuan: " + time);
        String temp[] = time.split("\\.");
        mYear = temp[0];
        mMonth = temp[1];
        mDay = temp[2];
        mHour = temp[3];

        date = new HashMap<String, String>();
        date.put("year", mYear);
        date.put("month", mMonth);
        date.put("day", mDay);
        date.put("hour", mHour);
        LogUtil.printD("年月日时" + mYear + "/" + mMonth + "/" + mDay + "/" + mDay);
        chuantongApi.getInstance().getChengGu(date).
                enqueue(new ApiCallBack<ApiResponseBean<ChengGu>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        getBaseLoadingView().hideLoading();
                        ChengGu bean = (ChengGu) data;
                        LogUtil.printD("bean" + bean);
                        bean.setBorn(bean.getBorn());
                        bean.setLunar(bean.getLunar());
                        bean.setMing(bean.getMing());
                        bean.setNan(bean.getNan());
                        bean.setNv(bean.getNv());
                        bean.setResult(bean.getResult());
                        LogUtil.printD("onSuccess: " + bean.getBorn());
                        if (bean != null) {
                            getBaseLoadingView().hideLoading();
                            GotoUtil.GoToActivityWithBean(ChenGuActivity.this, ChengGuResultActivity.class, bean);
                        }

                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        getBaseLoadingView().hideLoading();
                        boolean b = ErrorCodeUtil.shownetWorkAndServerError(ChenGuActivity.this, errorCode);
                        if (b == false){
                            ToastUtil.showShort(ChenGuActivity.this,message);
                        }
                    }
                }));
    }

    //*
    //显示时间控件
    private void showTimePicker() {
        if (timeView == null) {
            timeView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY_HOUR);
            timeView.setCyclic(false);
            timeView.setTime(new Date());
            timeView.setOnTimeSelectListener(this);
        }
        timeView.show();
    }

    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void initListener() {
        super.initListener();
    }

    //时间选择回调
    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH");
        time = sdf.format(date);
        String temp[] = time.split("\\.");
        tvChooseDate.setText(temp[0] + "年" + temp[1] + "月" + temp[2] + "日" + temp[3] + "时");
    }
}
