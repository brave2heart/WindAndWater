package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishTreeAPi;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

/**
 * Created by Steven on 16/12/5.
 */

public class WishTreeInputPop extends BasePopupWindow{

    private EditText etNmae;
    private EditText etContent;
    private Button btnSubmit;


    public WishTreeInputPop(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        etNmae = (EditText) layoutView.findViewById(R.id.et_name);
        etContent = (EditText) layoutView.findViewById(R.id.et_content);
        btnSubmit = (Button) layoutView.findViewById(R.id.btn_submit);

    }

    @Override
    public void initListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                String s = etNmae.getText().toString();
                String s1 = etContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(s1)){
                    ToastUtil.showShort(getActivity(),"请输入心愿");
                    return;
                }
                if (!UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToActivity(getActivity(), LoginActivity.class);
                    return;
                }

                WishTreeAPi.getInstance().saveXyTreeInfo(UserManager.getInstance().getToken(),s,null,s1).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        etNmae.setText("");
                        etContent.setText("");
                        WishTreeInputPop.this.closePop();
                        if (o != null) {
                            o.onWIshSuccessful();
                        }
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }



    @Override
    public void showPopOnRootView(Activity activity) {
        super.showPopOnRootView(activity);
    }
    OnWishTreeSuccessfulListener o;

    public void setOnWishTreeSuccessfulListener(OnWishTreeSuccessfulListener o) {
        this.o = o;
    }

    public interface OnWishTreeSuccessfulListener{
        void onWIshSuccessful();
    }


}
