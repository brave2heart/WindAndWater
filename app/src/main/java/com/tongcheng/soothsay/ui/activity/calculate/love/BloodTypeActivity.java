package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BloodType;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.MarriageLoveApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/11/30 0030.
 * 血型配对
 */

public class BloodTypeActivity extends BaseTitleActivity {

    @BindView(R.id.tv_boy_bloodtype)
    TextView tvBoyBloodtype;
    @BindView(R.id.tv_girl_bloodtype)
    TextView tvGirlBloodtype;
    @BindView(R.id.btn_cesuan)
    Button btnCesuan;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    private String mBoyBloodType;
    private String mGirlBloodType;
    private int clicktime = 0;
    private String[] bloodTypeArr;
    private AlertDialog mDialog;
    private int index;
    private int mBoyIndex, mGirlIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_blood_type);
        initView();
        initListener();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_blood_group_matching));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        bloodTypeArr = getResources().getStringArray(R.array.bloodType_arr);
    }

    @OnClick({R.id.tv_boy_bloodtype, R.id.tv_girl_bloodtype, R.id.btn_cesuan})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_boy_bloodtype:
                showChooseDialog(tvBoyBloodtype);
                break;
            case R.id.tv_girl_bloodtype:
                showChooseDialog(tvGirlBloodtype);
                break;
            case R.id.btn_cesuan:
                ceSuan();
                break;
        }
    }

    private void showChooseDialog(final TextView view) {
        mDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(bloodTypeArr, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                index = which;
                                if (view.getId() == R.id.tv_boy_bloodtype) {
                                    view.setText(bloodTypeArr[index].toString());
                                    mBoyBloodType = bloodTypeArr[index].toString();
                                    mBoyIndex = index;
                                } else if (view.getId() == R.id.tv_girl_bloodtype) {
                                    view.setText(bloodTypeArr[index].toString());
                                    mGirlBloodType = bloodTypeArr[index].toString();
                                    mGirlIndex = index;
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

    private void ceSuan() {
        Log.e("tag", "按钮点击次数:" + clicktime);
        if (TextUtils.isEmpty(mBoyBloodType) && TextUtils.isEmpty(mGirlBloodType)) {
            ToastUtil.showShort(this, "请选择血型");
            return;
        }
        if (TextUtils.isEmpty(mBoyBloodType)) {
            ToastUtil.showShort(this, "请选择男生血型");
            return;
        }
        if (TextUtils.isEmpty(mGirlBloodType)) {
            ToastUtil.showShort(this, "请选择女生血型");
            return;
        }
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("manBlood", mBoyBloodType);
        data.put("womanBlood", mGirlBloodType);
        getBaseLoadingView().showLoading(true);
        MarriageLoveApi.getInstance().getBloodType(data).
                enqueue(new ApiCallBack<ApiResponseBean<BloodType>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        getBaseLoadingView().hideLoading();
                        BloodType bean = (BloodType) data;
                        bean.setBoyIndex(String.valueOf(mBoyIndex));
                        bean.setGirlIndex(String.valueOf(mGirlIndex));
                        bean.setIndex(bean.getIndex());
                        bean.setTitle(bean.getTitle());
                        bean.setDetailTile(bean.getDetailTile());
                        bean.setDetailContent(bean.getDetailContent());
                        bean.setTitleContent(bean.getTitleContent());
                        bean.setInstructionTitle(bean.getInstructionTitle());
                        bean.setInstructionContent(bean.getInstructionContent());
                        bean.setRemindTitle(bean.getRemindTitle());
                        bean.setRemindeContent(bean.getRemindeContent());

                        if (bean != null) {
                            GotoUtil.GoToActivityWithBean(BloodTypeActivity.this, TestBloodTypeActivity.class, bean);
                        }
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        getBaseLoadingView().hideLoading();
                        boolean b = ErrorCodeUtil.shownetWorkAndServerError(BloodTypeActivity.this, errorCode);
                        if (b == false){
                            ToastUtil.showShort(BloodTypeActivity.this,message);
                        }
                    }
                }));
    }
}
