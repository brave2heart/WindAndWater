package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.NumberTestBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
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

public class TestIDCardFragment extends BaseTitleFragment {
    @BindView(R.id.et_testidcard)
    BootstrapEditText mEtIDCard;
    @BindView(R.id.btn_cesuan_idcard)
    Button mBtnCesuanIdcard;
    @BindView(R.id.tv_cesuan_response)
    TextView mResult;
    private View view;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_test_idcard_first, container, false);
        }
        return view;
    }

    @OnClick(R.id.btn_cesuan_idcard)
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        String idcard = mEtIDCard.getText().toString();
        boolean idCard18 = RegexUtils.isIDCard18(idcard);
        if (TextUtils.isEmpty(idcard)){
            ToastUtil.showShort(getContext(),"身份证号码不能为空");
        }
        else if (idCard18) {
//            ((TestIDCardActivity) getActivity()).setIdCard(idcard);
            getIDCardMsg(idcard);

        } else {
            ToastUtil.showShort(getContext(), "格式不正确，请输入18位身份证号码");
        }
        WindowUtil.closeInputMethod(getActivity());
    }

    private void getIDCardMsg(String idcard) {
        getBaseLoadingView().showLoading(true);
        AllApi.getInstance().getNumberMsg(idcard).enqueue(new ApiCallBack<ApiResponseBean<NumberTestBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                NumberTestBean bean = (NumberTestBean) data;
                LogUtil.printD("bean" + bean);
                mResult.setText(bean.getResult());
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(getActivity(), errorCode);
                if (b ==false)
                    ToastUtil.showShort(getActivity(),message);
            }
        }));
    }


}
