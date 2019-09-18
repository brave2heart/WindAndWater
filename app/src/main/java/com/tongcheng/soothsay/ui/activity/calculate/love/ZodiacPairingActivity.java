package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ZodiacPairing;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.MarriageLoveApi;
import com.tongcheng.soothsay.utils.AlertDialogHeightUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/11/30 0030.
 * 生肖配对
 */

public class ZodiacPairingActivity extends BaseTitleActivity {


    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.tv_boy_zodiac)
    TextView tvBoyZodiac;
    @BindView(R.id.tv_girl_zodiac)
    TextView tvGirlZodiac;
    @BindView(R.id.btn_cesuan)
    Button btnCesuan;
    private String[] ChineseZodiacArr;

    private AlertDialog mDialog;
    private Integer index = null;
    private String mBoyZodiac;
    private String mGirlZodiac;
    private int boyIndex,girlIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_zodiacpairing);
        initView();

    }

    @Override
    public void initView() {
        super.initView();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_zodiac_pairing));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        ChineseZodiacArr = getResources().getStringArray(R.array.zodiac_arr);

    }

    @OnClick({R.id.tv_boy_zodiac, R.id.tv_girl_zodiac, R.id.btn_cesuan})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_boy_zodiac:
                showChooseDialog(tvBoyZodiac);
                break;
            case R.id.tv_girl_zodiac:
                showChooseDialog(tvGirlZodiac);
                break;
            case R.id.btn_cesuan:
                ceSuan();
                break;
        }
    }

    private void ceSuan() {
        if (mBoyZodiac == null && mGirlZodiac == null) {
            ToastUtil.showShort(this, "请选择生肖");
            return;
        }else if (TextUtils.isEmpty(mBoyZodiac)){
            ToastUtil.showShort(this, "请选择男方生肖");
            return;
        }else if (TextUtils.isEmpty(mGirlZodiac)){
            ToastUtil.showShort(this, "请选择女方生肖");
            return;
        }else {
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("mIndex", String.valueOf(index));
            data.put("wIndex", String.valueOf(index));
            getBaseLoadingView().showLoading(true);
            MarriageLoveApi.getInstance().getZodiacPairing(data).
                    enqueue(new ApiCallBack<ApiResponseBean<ZodiacPairing>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            getBaseLoadingView().hideLoading();
                            ZodiacPairing bean = (ZodiacPairing) data;
                            bean.setAnimalDetail(bean.getAnimalDetail());
                            bean.setBoyIndex(String.valueOf(boyIndex));
                            bean.setGirlIndex(String.valueOf(girlIndex));

                            if (bean != null) {
                                GotoUtil.GoToActivityWithBean(ZodiacPairingActivity.this,ZodiacPairingResultActivity.class,bean);
                            }
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            getBaseLoadingView().hideLoading();
                            boolean b = ErrorCodeUtil.shownetWorkAndServerError(ZodiacPairingActivity.this, errorCode);
                            if (b == false){
                                ToastUtil.showShort(ZodiacPairingActivity.this,message);
                            }
                        }
                    }));
        }

    }

    private void showChooseDialog(final TextView view) {
        mDialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(ChineseZodiacArr, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                index = which;
                                if (view.getId() == R.id.tv_boy_zodiac) {
                                    view.setText(ChineseZodiacArr[index].toString());
                                    mBoyZodiac = ChineseZodiacArr[index].toString();
                                    boyIndex = index;
                                } else if (view.getId() == R.id.tv_girl_zodiac) {
                                    view.setText(ChineseZodiacArr[index].toString());
                                    mGirlZodiac = ChineseZodiacArr[index].toString();
                                    girlIndex = index;
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
//        设置对话框 高度
        AlertDialogHeightUtil.halfHeght(mDialog,this);
    }
}
