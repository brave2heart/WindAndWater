package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.TimePickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.HeHun;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.MarriageLoveApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 吕才合婚
 */

public class LvCaiMarriageActivity extends BaseTitleActivity implements TimePickerView.OnTimeSelectListener {
    @BindView(R.id.img_boy)
    RoundImageView imgBoy;
    @BindView(R.id.btn_year_boy)
    Button btnYearBoy;
    @BindView(R.id.img_girl)
    RoundImageView imgGirl;
    @BindView(R.id.btn_year_girl)
    Button btnYearGirl;
    @BindView(R.id.btn_cesuan)
    Button btnCesuan;
    private TimePickerView mTimePicker;
    private String time = "1900.08.08";
    private String mYearBoy;
    private String mYearGirl;
    private final static String TYPE = "1";
    private boolean isBoy = false;
    private String[] temp;


    private String mMonthBoy;
    private String mDayBoy;


    private HashMap<String,String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marriage);
        ButterKnife.bind(this);
        initView();
        initListener();

    }

    @Override
    public void initView() {
        super.initView();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_lv_cai));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    @OnClick({R.id.btn_year_boy, R.id.btn_year_girl, R.id.btn_cesuan})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_year_boy:
                isBoy = true;
                showTimePicker();
                break;
            case R.id.btn_year_girl:
                isBoy = false;
                showTimePicker();
                break;
            case R.id.btn_cesuan:
                Log.e("tag","男生出生年份" + mYearBoy + "女生出生年份：" + mYearGirl);
                ceSuan();
                break;
        }
    }

    private void ceSuan() {
        if (TextUtils.isEmpty(mYearBoy) && TextUtils.isEmpty(mYearGirl)){
            ToastUtil.showShort(this,"请选择出生年份");
            return;
        }else if (TextUtils.isEmpty(mYearBoy)){
            ToastUtil.showShort(this,"请选择男方出生年份");
            return;
        }else if (TextUtils.isEmpty(mYearGirl)){
            ToastUtil.showShort(this,"请选择女方出生年份");
            return;
        }else {
            data = new HashMap<>();
            data.put("year_man",mYearBoy);
            data.put("year_woman",mYearGirl);
            data.put("type",TYPE);
            Log.e("tag", "cesuan年份: "+mYearBoy );
            getBaseLoadingView().showLoading(true);
            MarriageLoveApi.getInstance().getMarriageLove(data).
                    enqueue(new ApiCallBack<ApiResponseBean<HeHun>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            getBaseLoadingView().hideLoading();
                            getBaseLoadingView().hideLoading();
                            HeHun bean = (HeHun) data;
                            bean.setResult(bean.getResult());
                            bean.setJieXi(bean.getJieXi());
                            bean.setManG(bean.getManG());
                            bean.setWomanG(bean.getWomanG());
                            bean.setType(bean.getType());

                            LogUtil.printD("onSuccess: " +bean.getType());
                            if (bean != null){
                                GotoUtil.GoToActivityWithBean(LvCaiMarriageActivity.this,TestBenMingActivity.class,bean);
                            }

                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            getBaseLoadingView().hideLoading();
                            boolean b = ErrorCodeUtil.shownetWorkAndServerError(LvCaiMarriageActivity.this, errorCode);
                            if (b == false){
                                ToastUtil.showShort(LvCaiMarriageActivity.this,message);
                            }
                        }
                    }));
        }
    }

    //显示时间控件
    private void showTimePicker() {
        if (mTimePicker == null) {
            mTimePicker = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            mTimePicker.setCyclic(false);
            mTimePicker.setTime(new Date());
            mTimePicker.setOnTimeSelectListener(this);
        }
        mTimePicker.show();
    }

    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        time = sdf.format(date);
        temp = time.split("\\.");
        if (isBoy){
            btnYearBoy.setText(temp[0] + "年" + temp[1] + "月" + temp[2] + "日" );
            mYearBoy = temp[0];
            Log.e("tag", "年份: "+mYearBoy );
        }else {
            btnYearGirl.setText(temp[0] + "年" + temp[1] + "月" + temp[2] + "日" );
            mYearGirl = temp[0];
        }
    }
}
