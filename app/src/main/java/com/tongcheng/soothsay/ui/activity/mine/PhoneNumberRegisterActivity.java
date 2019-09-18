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
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.UserBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.mine.RegisterBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/18.
 * 手机号注册
 */

public class PhoneNumberRegisterActivity extends BaseTitleActivity {

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
    @BindView(R.id.cb_register_read)
    TextView cbRead;//是否同意
    @BindView(R.id.tv_register_xy)
    TextView tvXy;//协议
    @BindView(R.id.ibtn_can_read_psd)
    ImageButton btnReadPsd;//密码是否可见

    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_phone_register);
        initData();

    }

    @Override
    public void initData() {
        super.initData();
        initBaseHeadView();
        etPhone.addTextChangedListener(new MobileTextWatcher());
        etYzm.addTextChangedListener(new YzmTextWatcher());
        etPassword.addTextChangedListener(new PasswordTextWatcher());
//        initReadAgree();
    }


    private void initBaseHeadView() {
        getBaseHeadView().showTitle("手机号注册");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneNumberRegisterActivity.this.finish();
            }
        });
        getBaseHeadView().showHeadRightButton("老用户登录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneNumberRegisterActivity.this.finish();
            }
        });
    }

    /**
     * 协议按钮是否选中
     */
//    private void initReadAgree() {
//        cbRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked && etYzm.getText().length() == 6
//                        && etPhone.length() > 10 && etPassword.getText().length() > 5 && etPassword.getText().length() < 21) {
//                    btnLogin.setEnabled(true);
//                } else {
//                    btnLogin.setEnabled(false);
//                }
//            }
//        });
//    }
    @OnClick({R.id.btn_register_yzm, R.id.btn_register,
            R.id.ibtn_can_read_psd, R.id.tv_register_xy})
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
            case R.id.tv_register_xy://用户协议
                GotoUtil.GoToWebViewActivity(this, "风生水协议", Constant.Url.AGREEMENT);
                break;

        }
    }

    /**
     * 发送注册请求
     */
    private void register() {
        if (!TextUtils.isEmpty(mobile) && etPhone.length() == 13
                && etYzm.length() == 6 && etPassword.length() > 5 && etPassword.length() < 21) {
            String uniquePsuedoID = SystemTools.getUniquePsuedoID();//唯一标示
            String phone = mobile.replaceAll(" ", "");//手机号
            String yzm = etYzm.getText().toString();//验证码
            String psd = etPassword.getText().toString();//密码
            long l = System.currentTimeMillis();//当前时间毫秒数
            String time = String.valueOf(l);
            Intent intent = getIntent();
            String isThird = intent.getStringExtra("isThird");
            String ThirdType = intent.getStringExtra("thirdtype");
            if ("no".equals(isThird)) {
                AllApi.getInstance().register(uniquePsuedoID, phone, yzm, psd, time, "", "", "", "", "", "").enqueue(
                        new ApiCallBack<ApiResponseBean<RegisterBean>>(new BaseCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.showShort(PhoneNumberRegisterActivity.this, "注册成功");
                                //存储token
                                RegisterBean registerBean = (RegisterBean) data;
                                String token = registerBean.getToken();
                                UserBean userBean = new UserBean(token);
                                UserManager.getInstance().saveUser(userBean);

                                //注册成功 返回上一页面
                                Intent intent = new Intent();
                                intent.putExtra("status", true);
                                setResult(9999, intent);
                                finish();
                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                //网络错误
                                if (errorCode == ApiCallBack.NET_ERROR) {
                                    ToastUtil.showShort(PhoneNumberRegisterActivity.this, PhoneNumberRegisterActivity.this.getResources().getString(R.string.net_error));

                                    //服务器系统错误
                                } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                                    ToastUtil.showShort(PhoneNumberRegisterActivity.this, "服务器系统错误");
                                } else
                                    ToastUtil.showShort(PhoneNumberRegisterActivity.this, message);

                            }
                        })
                );
            } else if ("yes".equals(isThird) && "1".equals(ThirdType)) {//qq登录
                String uid = intent.getStringExtra("uid");
                final String screenName = intent.getStringExtra("screenName");
                final String profileImageUrl = intent.getStringExtra("profileImageUrl");
                String gender = intent.getStringExtra("gender");
                String thirdtype = intent.getStringExtra("thirdtype");
                String sex = new String();
                if ("男".equals(gender))
                    sex = "1";
                else if ("女".equals(gender))
                    sex = "2";
                AllApi.getInstance().register(uniquePsuedoID, phone, yzm, psd, time,
                        "1", thirdtype, uid, screenName, profileImageUrl, sex).enqueue(
                        new ApiCallBack<ApiResponseBean<RegisterBean>>(new BaseCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.showShort(PhoneNumberRegisterActivity.this, "注册成功");
                                //存储token
                                RegisterBean registerBean = (RegisterBean) data;
                                String token = registerBean.getToken();
                                UserBean userBean = new UserBean(token);
                                UserManager.getInstance().saveUser(userBean);

                                //注册成功 返回上一页面
                                Intent intent = new Intent();
                                //登录成功 返回上一页面
                                EventBusUtil.post(new EventLoginBean(EventLoginBean.LOGIN));
                                UserManager.getInstance().setUserPhotoUrl(PhoneNumberRegisterActivity.this,profileImageUrl);
                                UserManager.getInstance().setUserName(PhoneNumberRegisterActivity.this,screenName);
                                setResult(9999, intent);
                                finish();
                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                ToastUtil.showLong(PhoneNumberRegisterActivity.this, message);
                            }
                        })
                );
            } else if ("yes".equals(isThird) && "2".equals(ThirdType)) {//微信登录
                String openid = intent.getStringExtra("openid");
                final String screen_name = intent.getStringExtra("screenName");
                final String profile_image_url = intent.getStringExtra("profileImageUrl");
                AllApi.getInstance().register(uniquePsuedoID, phone, yzm, psd, time,
                        "1", ThirdType, openid, screen_name, profile_image_url, "").enqueue(
                        new ApiCallBack<ApiResponseBean<RegisterBean>>(new BaseCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                ToastUtil.showShort(PhoneNumberRegisterActivity.this, "注册成功");
                                //存储token
                                RegisterBean registerBean = (RegisterBean) data;
                                String token = registerBean.getToken();
                                UserBean userBean = new UserBean(token);
                                UserManager.getInstance().saveUser(userBean);
                                //注册成功 返回上一页面
                                EventBusUtil.post(new EventLoginBean(EventLoginBean.LOGIN));
                                UserManager.getInstance().setUserPhotoUrl(PhoneNumberRegisterActivity.this,profile_image_url);
                                UserManager.getInstance().setUserName(PhoneNumberRegisterActivity.this,screen_name);
                                Intent intent = new Intent();
                                setResult(9999, intent);
                                finish();
                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                ToastUtil.showLong(PhoneNumberRegisterActivity.this, message);
                            }
                        }));
            }
        } else
            ToastUtil.showShort(this, "注册失败，请检查信息填写是否符合要求");
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
     * 发送注册验证码
     */
    private void sendYzm() {
        if (!TextUtils.isEmpty(mobile))
            AllApi.getInstance().registerYzm(mobile.replaceAll(" ", ""), "0").enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                @Override
                public void onSuccess(Object data) {
                    ToastUtil.showShort(PhoneNumberRegisterActivity.this, "发送成功");
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btnSendYzm, 60000, 1000);
                    mCountDownTimerUtils.start();
                }

                @Override
                public void onError(String errorCode, String message) {
                    //网络错误
                    if (errorCode == ApiCallBack.NET_ERROR) {
                        ToastUtil.showShort(PhoneNumberRegisterActivity.this, PhoneNumberRegisterActivity.this.getResources().getString(R.string.net_error));

                        //服务器系统错误
                    } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                        ToastUtil.showShort(PhoneNumberRegisterActivity.this, "服务器系统错误");

                    }else
                        ToastUtil.showShort(PhoneNumberRegisterActivity.this, message);

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
                    SystemTools.hideKeyBoard(PhoneNumberRegisterActivity.this);
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
                    SystemTools.hideKeyBoard(PhoneNumberRegisterActivity.this);
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
                        SystemTools.hideKeyBoard(PhoneNumberRegisterActivity.this);
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
