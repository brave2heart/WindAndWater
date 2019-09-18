package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageButton;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.UserBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.event.LoginRefreshBean;
import com.tongcheng.soothsay.bean.mine.LoginBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/24.
 * 忘记密码，修改密码界面
 */

public class ForgetPsdActivity extends BaseTitleActivity {
    @BindView(R.id.et_register_phone)
    TextInputEditText etPhone;//手机号
    @BindView(R.id.et_register_password)
    TextInputEditText etPassword;//密码
    @BindView(R.id.et_register_yzm)
    TextInputEditText etYzm;//验证码
    @BindView(R.id.btn_register_yzm)
    BootstrapButton btnSendYzm;//验证码发送
    @BindView(R.id.btn_register)
    BootstrapButton btnLogin;//登录按钮
    @BindView(R.id.ibtn_can_read_psd)
    ImageButton btnReadPsd;//密码是否可见

    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_forgetpsd);
        initData();

    }

    @Override
    public void initData() {
        super.initData();
        initBaseHeadView();
        etPhone.addTextChangedListener(new ForgetPsdActivity.MobileTextWatcher());
        etYzm.addTextChangedListener(new ForgetPsdActivity.YzmTextWatcher());
        etPassword.addTextChangedListener(new ForgetPsdActivity.PasswordTextWatcher());
    }


    private void initBaseHeadView() {
        getBaseHeadView().showTitle("找回密码");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ForgetPsdActivity.this.finish();
            }
        });
    }


    @OnClick({R.id.btn_register_yzm, R.id.btn_register, R.id.ibtn_can_read_psd})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        switch (view.getId()) {
            case R.id.btn_register_yzm://发送验证码
                sendYzm();
                break;
            case R.id.btn_register://注册
                register();
                break;
            case R.id.ibtn_can_read_psd://密码是否可见
                canReadPsd();
                break;

        }
    }

    /**
     * 发送登录请求
     */
    private void register() {
        if (!TextUtils.isEmpty(mobile) && etPhone.length() == 13
                && etYzm.length() == 6 && etPassword.length() > 5 && etPassword.length() < 21) {
            final String phone = mobile.replaceAll(" ", "");//手机号
            String yzm = etYzm.getText().toString();//验证码
            final String psd = etPassword.getText().toString();//密码
            long l = System.currentTimeMillis();//当前时间毫秒数
            final String time = String.valueOf(l);
            AllApi.getInstance().forgetpsd(phone, yzm, psd, time).enqueue(
                    new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            AllApi.getInstance().login(phone, psd, time)
                                    .enqueue(new ApiCallBack<ApiResponseBean<LoginBean>>(new BaseCallBack() {
                                        @Override
                                        public void onSuccess(Object data) {
                                            LoginBean bean = (LoginBean) data;
                                            String phone1 = bean.getPhone();
                                            LogUtil.printD("电话" + phone1);
                                            String token = bean.getToken();
                                            if (token != null) {
                                                UserBean userBean = new UserBean(token);
                                                UserManager.getInstance().saveUser(userBean);
                                            }
                                            UserManager.getInstance().setUserPhotoUrl(ForgetPsdActivity.this, bean.getHeadPic());
                                            UserManager.getInstance().setUserName(ForgetPsdActivity.this, bean.getName());
                                            ToastUtil.showShort(ForgetPsdActivity.this, "修改成功");

                                            //登录成功 返回上一页面
                                            EventBus.getDefault().post(new EventLoginBean(EventLoginBean.LOGIN));
                                            Intent intent = new Intent();
                                            setResult(9999, intent);
                                            finish();
                                        }

                                        @Override
                                        public void onError(String errorCode, String message) {
                                            ToastUtil.showShort(ForgetPsdActivity.this, message);
                                        }
                                    }));
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            //网络错误
                            if (errorCode == ApiCallBack.NET_ERROR) {
                                ToastUtil.showShort(ForgetPsdActivity.this, ForgetPsdActivity.this.getResources().getString(R.string.net_error));

                                //服务器系统错误
                            } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                                ToastUtil.showShort(ForgetPsdActivity.this, "服务器系统错误");
                            }else
                                ToastUtil.showShort(ForgetPsdActivity.this, message);
                        }
                    })
            );

        } else
            ToastUtil.showShort(this, "登录失败，请检查信息填写是否符合要求");
    }

    boolean canRead = true;

    /**
     * 密码是否可见
     */
    private void canReadPsd() {
        if (canRead) {
            btnReadPsd.setSelected(true);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            canRead = false;
        } else if (canRead == false) {
            btnReadPsd.setSelected(false);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            canRead = true;
        }

    }

    /**
     * 发送验证码
     */
    private void sendYzm() {
        if (!TextUtils.isEmpty(mobile))
            AllApi.getInstance().registerYzm(mobile.replaceAll(" ", ""), "1").enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                @Override
                public void onSuccess(Object data) {
                    ToastUtil.showShort(ForgetPsdActivity.this, "发送成功");
                    ForgetPsdActivity.CountDownTimerUtils mCountDownTimerUtils = new ForgetPsdActivity.CountDownTimerUtils(btnSendYzm, 60000, 1000);
                    mCountDownTimerUtils.start();
                }

                @Override
                public void onError(String errorCode, String message) {
                    ToastUtil.showShort(ForgetPsdActivity.this, message);
                }
            }));
    }


    /**
     * 手机号输入监听
     */
    class MobileTextWatcher implements TextWatcher {
        private CharSequence temp;
        private String addString = " ";
        public boolean isTel = true;
        private boolean isRun = false;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isRun) {//这几句要加，不然每输入一个值都会执行两次onTextChanged()，导致堆栈溢出，原因不明
                isRun = false;
                return;
            }
            isRun = true;
            if (isTel) {
                String finalString = "";
                int index = 0;
                String telString = s.toString().replace(" ", "");
                if ((index + 3) < telString.length()) {
                    finalString += (telString.substring(index, index + 3) + addString);
                    index += 3;
                }
                while ((index + 4) < telString.length()) {
                    finalString += (telString.substring(index, index + 4) + addString);
                    index += 4;
                }
                finalString += telString.substring(index, telString.length());
                temp = finalString;
                etPhone.setText(finalString);
                //此语句不可少，否则输入的光标会出现在最左边，不会随输入的值往右移动
                etPhone.setSelection(finalString.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 13) {
                mobile = temp.toString();
                btnSendYzm.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
                btnSendYzm.setEnabled(true);
                etYzm.setEnabled(true);
                etYzm.setFocusable(true);
                etYzm.setFocusableInTouchMode(true);
                etYzm.requestFocus();
                try {
                    SystemTools.hideKeyBoard(ForgetPsdActivity.this);
                } catch (Exception e) {
                    return;
                }

            } else {
                btnSendYzm.setBootstrapBrand(DefaultBootstrapBrand.SECONDARY);
                btnSendYzm.setEnabled(false);

            }
        }
    }

    /**
     * 验证码输入监听
     */
    class YzmTextWatcher implements TextWatcher {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (etPhone.length() < 13) {
                etYzm.setEnabled(false);
                return;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 6)
                try {
                    SystemTools.hideKeyBoard(ForgetPsdActivity.this);
                } catch (Exception e) {
                    return;
                }

        }
    }

    /**
     * 密码输入监听
     */
    class PasswordTextWatcher implements TextWatcher {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if ((temp.length() >= 6 && temp.length() < 20) || temp.length() == 20) {
                btnLogin.setEnabled(true);
                if (temp.length() == 20)
                    try {
                        SystemTools.hideKeyBoard(ForgetPsdActivity.this);
                    } catch (Exception e) {
                        return;
                    }
            } else
                btnLogin.setEnabled(false);

        }
    }

    /**
     * 倒计时发送按钮
     */
    class CountDownTimerUtils extends CountDownTimer {

        private BootstrapButton mButton;

        public CountDownTimerUtils(BootstrapButton button, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.mButton = button;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnSendYzm.setText("重新发送(" + millisUntilFinished / 1000 + "秒)");
            btnSendYzm.setBootstrapBrand(DefaultBootstrapBrand.SECONDARY);
            btnSendYzm.setEnabled(false);
        }

        @Override
        public void onFinish() {
            btnSendYzm.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
            btnSendYzm.setEnabled(true);
            btnSendYzm.setText("发送验证码");
        }
    }
}
