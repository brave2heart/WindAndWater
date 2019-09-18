package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.AlertDialogHeightUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.RegexUtils;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/11/24 0024.
 */

public class TestLicensePlateFragment extends BaseTitleFragment {

    @BindView(R.id.et_testlicense)
    BootstrapEditText mEtLicense;
    @BindView(R.id.btn_cesuan_license)
    Button mBtnCeSuan;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.et_province)
    AwesomeTextView mEtProvince;
    private View view;
    private AlertDialog mDialog;
    private int index;
    private String mProvince = "京";
    private String[] provinceData = new String[]{"京", "津", "冀", "晋", "蒙", "辽", "吉", "黑", "沪", "苏", "浙", "皖", "闽", "赣",
            "鲁", "豫", "鄂", "湘", "粤", "桂", "琼", "渝", "川", "贵", "云", "藏", "陕", "甘", "青", "宁", "新"};

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_test_license, container, false);
        }
        return view;
    }

    @OnClick({R.id.btn_cesuan_license, R.id.et_province})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_cesuan_license:
                cesuanLicense();
                break;
            case R.id.et_province:
                createProvinceAlert();
                break;
        }
        WindowUtil.closeInputMethod(getActivity());
    }


    private void createProvinceAlert() {
        mDialog = new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(provinceData, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                if (ClickUtil.isFastClick()) {
                                    return;
                                }
                                dialog.dismiss();
                                index = which;
                                Log.e("tag", "onClick: " + index);
                                mProvince = provinceData[index].toString();
                                mEtProvince.setText(provinceData[index].toString());
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
        AlertDialogHeightUtil.halfHeght(mDialog, getActivity());
    }

    private void cesuanLicense() {
        String et_license = mProvince + mEtLicense.getText().toString();
        boolean licenseNumber = RegexUtils.isLicensePlate(et_license);
        Log.e("tag", "是否匹配车牌号：" + et_license + "...." + licenseNumber);
        if (TextUtils.isEmpty(mEtLicense.getText())) {
            ToastUtil.showShort(getContext(), "车牌号码不能为空");
        } else if (licenseNumber) {
            getLisenseMsg(et_license);

        } else {
            ToastUtil.showShort(getContext(), "格式不正确，格式：省份简称+字母+5位数字");
        }
    }

    private void getLisenseMsg(String idcard) {
        getBaseLoadingView().showLoading(true);
        AllApi.getInstance().getNumberMsg(idcard).enqueue(new ApiCallBack<ApiResponseBean<NumberTestBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                NumberTestBean bean = (NumberTestBean) data;
                LogUtil.printD("bean" + bean);
                mTvResult.setText(bean.getResult());
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                if (b == false)
                    ToastUtil.showShort(getActivity(), message);
            }
        }));
    }

}

